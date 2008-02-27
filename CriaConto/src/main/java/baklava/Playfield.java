package baklava;

import java.applet.Applet;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.net.URL;
import java.util.Hashtable;
import java.util.Vector;

public class Playfield extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	Image workImage = null;
	int cost[];
	int sint[];
	private Sprite selectedSprite;
	Vector <Sprite> sprites;
	Vector <Sprite> spritesRemoved;
	Vector <Sprite> spritesAdded;
	Vector <Object> eventQueue;
	Vector <Timer> timers;
	public Applet applet;
	GlobalTimerObserver observer;
	int w;
	int h;
	boolean showProgress = true;
	boolean suspended = false;
	boolean mapped = false;
	boolean d7 = false;
	boolean d5 = false;
	Thread mover = null;
	Thread progressThread = null;
	// Collisions grid: a two-dimensional array of vectors, yow
	Vector collisionGrid[][];
	Vector <Dimension> repaintList;
	Hashtable <Image, Object> imageMasks;
	MediaTracker imagesExtant;
	int imagesExtantTotal = 0;
	int progressMeterPosition = 0;
	int progressMeterStep = 0;
	int progressCount = 0;
	String progressMessage;
	Integer progressLock = new Integer(0);
	public Playfield(Applet appletArg, int wArg, int hArg) {
		super();
		applet = appletArg;
		sprites = new Vector <Sprite>();
		spritesRemoved = new Vector <Sprite>();
		spritesAdded = new Vector <Sprite>();
		repaintList = new Vector <Dimension>();
		eventQueue = new Vector <Object>();
		imageMasks = new Hashtable <Image, Object>();
		timers = new Vector <Timer>();
		w = wArg;
		h = hArg;
		setSize(w, h);
		progressMessage = "Calculating Tables";
		progressStart();
		// Collision grid construction
		int rows = (h / 32) + 2;
		int cols = (w / 32) + 2;
		collisionGrid = new Vector[rows][cols];	
		int i;
		for (i = 0; (i < rows); i++) {
			int j;
			for (j = 0; (j < cols); j++) {
				collisionGrid[i][j] = new Vector();
			}
		}
		cost = new int[360];
		sint = new int[360];
		// Compute math tables
		for (i = 0; (i < 360); i++) {
			sint[i] = (int)
				(Math.sin((double) i * .0174532925) * 255.0);
			cost[i] = (int)
				(Math.cos((double) i * .0174532925) * 255.0);
		}	
		progressEnd();
		progressMessage = "Loading Images";
	}
	public void setGlobalTimerObserver(GlobalTimerObserver observerArg)
	{
		observer = observerArg;
	}
	public void setGlobalTimer(int delay, int timerId)
	{
		setTimer(null, delay, timerId);
	}
	public void suspend()
	{
		suspended = true;
	}
	public void resume()
	{
		suspended = false;
	}
	public void start()
	{
		mover = new Thread(this);
		try {
			mover.setPriority(Thread.NORM_PRIORITY - 1);
		} catch (Exception e) { };
		if (mapped) {
			try {
				workImage = applet.createImage(w, h);
			} catch (Exception e) {
				System.out.println(
					"Couldn't create workImage");
				workImage = null;
			}	
			mover.start();
		}
	}
	public Image getImage(URL url)
	{
		if (imagesExtant == null) {
			imagesExtant = new MediaTracker(this);
		}
		Image image = applet.getImage(url);
		if (image == null) {
			return null;
		}
		imagesExtant.addImage(image, 0);
		imagesExtantTotal++;
		// HACK: the Sun mediatracker freaks out if
		// there are too many images downloaded at once,
		// so download some of them now if there are
		// three ready to go.
		if (imagesExtantTotal >= 3) {
			imagesNeededNow();
		}	
		return image;
		
	}
	public Image unpackImage(String imgdata)
	{
		Image image;
		byte bytes[] = imgdata.getBytes();
		int pixtable[] = new int[256];
		int i;
		int pos = 0;
		int w;
		int h;
		w = (((int) bytes[pos] & 0xFF) << 8) +
			((int) bytes[pos + 1] & 0xFF);
		pos += 2;
		h = (((int) bytes[pos] & 0xFF) << 8) +
			((int) bytes[pos + 1] & 0xFF);
		pos += 2;
		int colorsTotal = ((int) bytes[pos++] & 0xFF);
		if (colorsTotal == 0) {
			colorsTotal = 256;
		}
		int transparent = (((int) bytes[pos] & 0xFF) << 8) +
			((int) bytes[pos + 1] & 0xFF);
		pos += 2;
		if (transparent == 257) {
			transparent = -1;
		}
		for (i = 0; (i < 256); i++) {
			pixtable[i] += ((int) bytes[pos++] & 0xFF) << 16;
			pixtable[i] += ((int) bytes[pos++] & 0xFF) << 8;
			pixtable[i] += ((int) bytes[pos++] & 0xFF);
			pixtable[i] += 255 << 24;
		}
		if ((transparent > 0) && (transparent < 256)) {
			pixtable[transparent] -= 255 << 24;
		}
		int pix[] = new int[w * h];
		int index = 0;
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				pix[index++] = pixtable[
					(int) bytes[pos++] & 0xFF];
			}
		}
		image = createImage(
			new MemoryImageSource(w, h, pix, 0, w));
		determineImageMask(image, pix);
		return image;
	}
	public void progressStart()
	{
		progressCount++;
		if (progressCount == 1) {
			progressThread = new Thread(this);
			progressThread.setPriority(Thread.NORM_PRIORITY - 2);
			progressThread.start();
		}
	}
	public void progressEnd()
	{
		progressCount--;
		if (progressCount == 0) {
			synchronized (progressLock) {
				progressThread.stop();
				progressThread = null;
			}
		}
		if (progressCount < 0) {
			progressCount = 0;
		}
		// Schedule a full repaint.
		repaint();
	}
	public void imagesNeededNow()
	{
		if (imagesExtant != null) {
			if (showProgress || (mover == null)) {
				progressStart();
				try {
					imagesExtant.waitForAll();
				} catch (Exception e) { };
				progressEnd();
			} else {
				try {
					imagesExtant.waitForAll();
				} catch (Exception e) { };
			}
		}
		imagesExtant = null;
		imagesExtantTotal = 0;
	}
	public Image getImage(String urls)
	{
                URL url = null;
                try {
                        url = new URL(urls);
;
                } catch (Exception e) { };
		if (url != null) {
                	return getImage(url);
		} else {
			return null;
		}
	}
	// Public for convenient precaching to avoid delays at runtime
	public int[][] getImageMask(Image image)
	{
		if (imageMasks.containsKey(image)) {
			// We already have a mask for this image.
			return (int[][]) imageMasks.get(image);	
		} 
		// We have to compute a mask.
		int w = image.getWidth(this);
		int h = image.getHeight(this);
		int[] pixels = new int[(int) w * (int) h];
		PixelGrabber pg = new PixelGrabber(
			image, 0, 0, w, h, pixels, 0, w);
		try {
			if (!pg.grabPixels()) {
				image = null;
				return null;
			}
		} catch (Exception e) {
			image = null;
			return null;	
		}
		return determineImageMask(image, pixels);
	}
	int[][] determineImageMask(Image image, int[] pixels)
	{
		int w = image.getWidth(this);
		int h = image.getHeight(this);
		int maskWidth = (w / 32) + 1;
		int[][] masks = new int[32][(maskWidth + 2) * (int) h];
		int i;
		int row;
		int opaque = (255 << 24);
		for (i = 0; (i < 32); i++) {
			int maskRowOffset = 0;
			for (row = 0; (row < h); row++) {
				int bit = 1 << 31;
				int j;
				int word = 0;
				int rowOffset = row * (int) w;
				int offset = maskRowOffset;
				for (j = i & 31; (j < w); j++) {
					if ((pixels[j + rowOffset] 
						& opaque) != 0) 
					{
						word |= bit;
					}
					if (bit == 1) {
						bit = 1 << 31;
						masks[i][offset] = word;
						offset++;
						word = 0;
					} else {	
						bit >>>= 1;
					}
				}
				if (bit != (1 << 31)) {
					masks[i][offset] = word;
				}
				maskRowOffset += maskWidth;
			}
		}	
		imageMasks.put(image, (Object) masks);
		return masks;
	}
	public int getWidth() {
		return w;
	}
	public int getHeight() {
		return h;
	}
	public void addSpriteToList(Sprite s)
	{
		// Remove any earlier reference so there is just one...
		spritesAdded.removeElement(s);
		spritesAdded.addElement(s);
	}
	public void removeSpriteFromList(Sprite s)
	{	
		// Remove any earlier reference so there is just one...
		spritesRemoved.removeElement(s);
		spritesRemoved.addElement(s);
	}		
	public void removeSpritesFromListNow()
	{	
		synchronized (sprites) {
			while (spritesRemoved.size() > 0) {
				Sprite s = (Sprite) spritesRemoved.elementAt(0);
				if (s.getCleanup()) {
					spriteCleanup(s);
				}
				sprites.removeElement(s);
				spritesRemoved.removeElementAt(0);
			}
		}
	}		
	void addSpritesToListNow()
	{
		synchronized (sprites) {
			int i, size;
			while (spritesAdded.size() > 0) {
				Sprite s = (Sprite) spritesAdded.elementAt(0);
				spritesAdded.removeElementAt(0);
				boolean inserted = false;
				size = sprites.size();
				for (i = 0; (i < size); i++) {
					Sprite o = (Sprite) sprites.
						elementAt(i);
					if (o.getLevel() > s.getLevel()) {
						sprites.insertElementAt(s, i);
						inserted = true;
						break;
					}
				}
				if (!inserted) {
					sprites.addElement(s); 		
				}
			}
		}
	}
	public void update(Graphics g) {
		paint(g);
	}
	boolean fastPaint()
	{
		// NOTE: THE DIMENSION CLASS IS USED HERE AS A PAIR
		// OF Y AXIS COORDINATES. Yes, 'width' and 'height' 
		// are confusing in this context, but I didn't want
		// Yet Another Class File Syndrome (YACFS).

		// Find horizontal stripes that need to be painted,
		// merging them when the opportunity arises.
		int size = sprites.size();
		int lsize;
		int i;
		int j;
		Dimension general = null;
		for (i = 0; (i < size); i++) {	
			Sprite s = (Sprite) sprites.elementAt(i);
			if (s.getMoved()) {
				Dimension d = s.getYRange();
				lsize = repaintList.size();
				boolean merged = false;
				for (j = 0; (j < lsize); j++) {	
					Dimension o = (Dimension)
						repaintList.elementAt(j);	
					if (d.width >= o.width) {
						if (o.height >= d.width) {
							if (d.height > o.height) {
								o.height = d.height;
							}
							merged = true;
							break;
						}
					} else {
						if (d.height >= o.width) {
							if (d.height > o.height) {
								o.height = d.height;
							}
							if (o.width > d.width) {
									o.width = d.width;
							}
							merged = true;
							break;
						}
					}
				}
				if (!merged) {
					repaintList.addElement(d);	
				}
				if (general == null) {
					general = new Dimension(d);
				} else {
					if (d.width < general.width) {
						general.width = d.width;
					} 
					if (d.height > general.height) {
						general.height = d.height;
					}
				}
			}
		}
		lsize = repaintList.size();
		for (i = 0; (i < lsize); i++) {	
			Dimension d = (Dimension)
				repaintList.elementAt(i);	
			// Now paint the stripes.
			Graphics c;
			if (workImage != null) {
				c = workImage.getGraphics();
			} else {
				c = getGraphics();
			}
			c.clipRect(0, d.width, w, d.height - d.width);
			c.clearRect(0, d.width, w, d.height - d.width);
			for (j = 0; (j < size); j++) {	
				Sprite s = (Sprite) sprites.elementAt(j);
				int y1 = (int) s.getY();	
				int y2 = (int) s.getHeight() + y1;	
				if (d.width >= y1) {
					if (y2 >= d.width) {
						s.paint(c);
					}
				} else {
					if (d.height >= y1) {
						s.paint(c);
					}
				}
			}
		}	
		if (lsize > 0) {
			if (workImage != null) {
				Graphics g = getGraphics();
				g.clipRect(0, general.width, 
					w, general.height - general.width);
				g.drawImage(workImage, 0, 0, this);
			}
		}
		repaintList.setSize(0);
		if (lsize > 0) {
			return true;
		} else {
			return false;
		}
	}
	public boolean handleEvent(Event evt)
	{
		eventQueue.addElement((Object) evt);
		return true;
	}
	public void paint(Graphics g)
	{
		if (!mapped) {
			// Okay, it is now safe to assume
			// this is a real component and
			// AWT stuff is available.
			mapped = true;
			// But do we have a mover yet?
			if (mover == null) {
				return;
			}
			try {
				workImage = applet.createImage(w, h);
			} catch (Exception e) {
				System.out.println("Couldn't create workImage");
				workImage = null;
			}	
			mover.start();		
		}
		if (d5) {
			d5p(g);
			return;
		}
		if (progressThread != null) {
			progressPaintBackground(g);
			return;
		}
		Graphics c;
		if (workImage != null) {
			c = workImage.getGraphics();
		} else {
			c = g;
		}
		if (mover == null) {
			if (c != g) {
				g.drawImage(workImage, 0, 0, this);
			}
			return;
		}
		c.clipRect(0, 0, w, h);
		int i;
		synchronized(sprites) {
			for (i = 0; (i < sprites.size()); i++) { 
				Sprite s = (Sprite) sprites.elementAt(i);
				s.paint(c);
			}
		}
		if (c != g) {
			g.drawImage(workImage, 0, 0, this);
		}
	}
	public void d5p(Graphics g)
	{
		g.clearRect(0, 0, w, h);
		String d5 = "Baklava Expired";
		Font f = g.getFont();
		FontMetrics fm = getFontMetrics(f);
		int textHeight = fm.getHeight();
		g.drawString(d5, 0, textHeight);
	}
	public boolean keyDown(Event evt, int key) {
		int size = sprites.size();
		int i;
		for (i = (size - 1); (i >= 0); i--) { 
			Sprite si = (Sprite) sprites.elementAt(i);
			si.keyDown(evt, key);
		}		
		return true;
	}
	public boolean keyUp(Event evt, int key) {
		int size = sprites.size();
		int i;
		for (i = (size - 1); (i >= 0); i--) { 
			Sprite si = (Sprite) sprites.elementAt(i);
			si.keyUp(evt, key);
		}			
		return true;
	}
	public boolean mouseDown(Event evt, int x, int y) {
		int size = sprites.size();
		int i;
		for (i = (size - 1); (i >= 0); i--) { 
			Sprite si = (Sprite) sprites.elementAt(i);
			if (si.test(x, y)) {
				si.mouseDown(evt, 
					x - (int) si.getX(), 
					y - (int) si.getY());
				
				setSelectedSprite(si);
				si.setLevel(sprites.size() + 1);
				
				break;
			}
		}	
		return true;
	}
	public boolean mouseUp(Event evt, int x, int y) {
		int size = sprites.size();
		int i;
		for (i = (size - 1); (i >= 0); i--) { 
			Sprite si = (Sprite) sprites.elementAt(i);
			if (si.test(x, y) && si == getSelectedSprite()) {
				si.mouseUp(evt, 
					x - (int) si.getX(), 
					y - (int) si.getY());
				return true;
			}
		}	
		
		//if no sprite was selected, then the user just clicked the playfield
		if (getSelectedSprite() != null) {
			getSelectedSprite().putOnBoard();
		}
		return true;
	}	
	public boolean mouseMove(Event evt, int x, int y) {
		int size = sprites.size();
		int i;
		for (i = (size - 1); (i >= 0); i--) { 
			Sprite si = (Sprite) sprites.elementAt(i);
			si.checkMouseIn(evt, x, y);
			if (si.test(x, y)) {
				si.mouseMove(evt, 
					x - (int) si.getX(), 
					y - (int) si.getY());
				break;
			}
		}	
		return true;
	}		
	public boolean mouseDrag(Event evt, int x, int y) {
		int size = sprites.size();
		int i;
		for (i = (size - 1); (i >= 0); i--) { 
			Sprite si = (Sprite) sprites.elementAt(i);
			if (si.test(x, y) && si == getSelectedSprite()) {
				si.mouseDrag(evt, 
					x - (int) si.getX(), 
					y - (int) si.getY());
				break;
			}
		}	
		return true;
	}		
	void moverRun() {
		if (d5) {
			return;
		}
		// Try to move 30 times a second (ideally).
		int i;
		long last = System.currentTimeMillis();
		while (true) {
			if (suspended) {
				try {
					Thread.currentThread().sleep(200);
				} catch (Exception e) { };
				continue;
			}	
			long now = System.currentTimeMillis();
			int elapsed = (int) (now - last);
			last = now;
			// Even if we lose realtime, it's critical to 
			// avoid "overshooting" a timer delay.
			synchronized(timers) {
				if (timers.size() > 0) {
					Timer t = (Timer) timers.elementAt(0);
					if (t.delay < elapsed) {
						elapsed = t.delay;
					}
				}
			}
			checkTimers(elapsed);
			step(elapsed);
			collisions();
			// Now paint the outcome
			boolean paintingDone = fastPaint();
			// Now removals
			removeSpritesFromListNow();
			// Now clear the collision and movement flags
			int s = sprites.size();	
			for (i = 0; (i < s); i++) { 
				Sprite si = (Sprite) 
					sprites.elementAt(i);
				si.getHits().clear();
				si.clearMoved();
			}
			// Now additions
			addSpritesToListNow();
			// Now deliver events
			while (eventQueue.size() > 0) {
				Event evt = (Event) 
					eventQueue.firstElement();
				super.handleEvent(evt);	
				eventQueue.removeElementAt(0);
			}
			// Force garbage collection for the benefit
			// of iffy jdks
			// System.gc();
			// Yield for the benefit of green threads
			if (paintingDone == true) {
				try {
					Thread.currentThread().sleep(5);
				} catch (Exception e) { };
			} else {
				// Yield longer. Sigh.
				try {
					Thread.currentThread().sleep(50);
				} catch (Exception e) { };
			}
		}
	}
	void step(int elapsed)
	{
		int i;
		int s = sprites.size();
		for (i = 0; (i < s); i++) { 
			((Sprite) sprites.elementAt(i)).
				step(elapsed);
		}
	}
	void addToGrid(Sprite s)
	{
		if (s.getBackground()) {
			return;
		}
		int row1 = s.getRow1();
		int row2 = s.getRow2();
		int col1 = s.getCol1();
		int col2 = s.getCol2();
		int row, col;
		for (row = row1; (row <= row2); row++) {
			for (col = col1; (col <= col2); col++) {
				collisionGrid[row][col].addElement(s);
			}
		}		
	}
	void removeFromGrid(Sprite s)
	{
		if (s.getBackground()) {
			return;
		}
		int row1 = s.getOldRow1();
		int row2 = s.getOldRow2();
		int col1 = s.getOldCol1();
		int col2 = s.getOldCol2();
		if (row1 < 0) {
			return;
		}
		int row, col;
		for (row = row1; (row <= row2); row++) {
			for (col = col1; (col <= col2); col++) {
				collisionGrid[row][col].removeElement(s);
			}
		}		
	}
	void collisions() {	
		int row, col;
		int i;
		int s = sprites.size();
		// Test the edges.
		for (i = 0; (i < s); i++) { 
			Sprite si = (Sprite) 
				sprites.elementAt(i);
			if (si.getMoved()) {
				si.handleEdges(w, h);
			}
		}
		// Update the grid.
		for (i = 0; (i < s); i++) { 
			Sprite si = (Sprite) 
				sprites.elementAt(i);
			if (si.getMoved()) {
				int x = (int) si.getX(); 
				int y = (int) si.getY(); 
				int w = (int) si.getWidth(); 
				int h = (int) si.getHeight(); 
				if (si.changeGridBounds(y / 32, (y + h - 1) / 32,
					x / 32, (x + w - 1) / 32))
				{
					removeFromGrid(si);
					addToGrid(si);
				}
			}
		}
		// Test for collisions.
		Integer tokenValue = new Integer(1);
		for (i = 0; (i < s); i++) { 
			Sprite si = (Sprite) 
				sprites.elementAt(i);
			if (si.getBackground()) {
				continue;
			}
			if (!si.getMoved()) {
				continue;
			}
			int j;
			int rowstart = si.getRow1();
			int rowend = si.getRow2();
			int colstart = si.getCol1();
			int colend = si.getCol2();
			for (row = rowstart; (row <= rowend); row++) {
				for (col = colstart; (col <= colend); col++) {
					int vs = collisionGrid[row][col].size();
					for (j = 0; (j < vs); j++) { 
						Sprite sj = (Sprite) 
							collisionGrid[row][col]
								.elementAt(j);
						if (sj == si) {
							continue;
						}
						if (si.test(sj)) {
							Hashtable <Sprite, Integer> hits = si.getHits();
							if (!hits.containsKey(sj)) {
								si.collisionWith(sj);
								hits.put(sj, tokenValue);
							}	
							hits = sj.getHits();
							if (!hits.containsKey(si)) {
								sj.collisionWith(si);
								hits.put(si, tokenValue);
							}
						}
					}
				}
			}
		}
	}
	public void stop() {
		mover.stop();
		mover = null;
		goodbyeAll();
	}
	public void run() {
		if (Thread.currentThread() == mover) {
			moverRun();
			return;
		}			
		if (Thread.currentThread() == progressThread) {
			progressRun();
			return;
		}
	}
	public void goodbyeAll(Class whichClass)
	{
		goodbyeAllBody(whichClass);
	}
	public void goodbyeAll()
	{
		goodbyeAllBody(null);
	}
	void goodbyeAllBody(Class whichClass)
	{
		synchronized (sprites) {
			int i;
			for (i = 0; (i < sprites.size()); i++) { 
				Sprite s = (Sprite) sprites.elementAt(i);
				if (whichClass != null) {
					if (s.getClass() != whichClass) {
						continue;
					}
				}
				s.goodbye();
			}
		}
	}
	public void clearGlobalTimers()
	{
		// Remove any global timers
		synchronized (timers) {
			int j;
			for (j = 0; (j < timers.size()); j++) 
			{ 
				Timer t = (Timer) 
					timers.elementAt(j);
				if (t.s == null) {
					timers.removeElementAt
						(j);
					// Make sure we 
					// don't skip
					j--;
				}
			}	
		}	
	}
	void spriteCleanup(Sprite s)
	{
		// Remove any relevant timers
		synchronized (timers) {
			int j;
			for (j = 0; (j < timers.size()); j++) 
			{ 
				Timer t = (Timer) 
					timers.elementAt(j);
				if (t.s == s) {
					timers.removeElementAt
						(j);
					// Make sure we 
					// don't skip
					j--;
				}
			}	
		}
		// Remove from the collision grid.
		// We have to remove it from its final position,
		// not from the immediately preceding position,
		// so call changeGridBounds one last time.
		// This is a bit kludgy.
		s.changeGridBounds(s.getRow1(), s.getRow2(),
			s.getCol1(), s.getCol2());
		removeFromGrid(s);
	}
	void checkTimers(int elapsed)
	{
		int i, s;
		Vector <Timer> winners = new Vector <Timer>();
		synchronized (timers) {
			Timer t;
			i = 0;
   		  	while (i < timers.size()) {
				t = (Timer) timers.elementAt(i);	
				t.delay -= elapsed;
				if (t.delay <= 0) {
					winners.addElement(t);
					timers.removeElementAt(i);
				} else {
					i++;
				}
			}
		}
		s = winners.size();
		for (i = 0; (i < s); i++) {
			Timer t = (Timer) winners.elementAt(i);
			if (t.s == null) {
				observer.globalTimer(t.id);
			} else {
				t.s.timer(t.id);
			}
		}	
	}
	public void setTimerAll(int delay, int timerId)
	{
		setTimerAll(null, delay, timerId);
	}
	public void setTimerAll(Class c, int delay, int timerId)
	{
		int i;
		for (i = 0; (i < sprites.size()); i++) { 
			Sprite s = (Sprite) sprites.elementAt(i);
			if ((s.getClass() == c) || (c == null)) {
				setTimer(s, delay, timerId);
			}
		}	
	}
	public void setTimer(Sprite s, int delay, int timerId)
	{
		Timer t = new Timer();
		t.delay = delay;
		t.id = timerId;
		t.s = s;
		boolean inserted = false;
		synchronized (timers) {
			int i;
			int size = timers.size();
			for (i = 0; (i < size); i++) {
				Timer it = (Timer) timers.elementAt(i);
				if ( it.delay > delay) 
				{
					timers.insertElementAt(t, i);
					inserted = true;
					break;
				}
			}
			if (!inserted) {
				timers.addElement(t);
			}
		}	
	}
	Hashtable <Image, Object> getImageMasks()
	{
		return imageMasks;
	}
	public void setShowProgress(boolean showProgressArg) {
		showProgress = showProgressArg;
	}
	public void setProgressMessage(String progressMessageArg) {
		progressMessage = progressMessageArg;
	}
	void progressPaintBackground(Graphics g)
	{
		int y;
		int steps = h / 15;
		int step = steps;
		for (y = 0; (y < h); y += 15) {
			if (y != 1) {
				Color c = new Color(128, 128, 
					128 + step * 127 / steps);
				g.setColor(c);
				g.fillRect(0, y, w, 15);
				step--;	
			}
		}
	}
	void progressRun() {
		boolean sizeKnown = false;
		int textWidth = 0;
		int textHeight = 0;
		int x = progressMeterPosition;
		int step = progressMeterStep;
		Graphics g;
		while (true) {
			g = getGraphics();
			if (g == null) {
				try {
					Thread.sleep(100);
				} catch (Exception e) { };
				continue;	
			}
			progressPaintBackground(g);
			g.dispose();
			break;
		}
		while (true) {
			try {
				Thread.sleep(100);
			} catch (Exception e) { };
			synchronized (progressLock) {
				g = getGraphics();
				if (g == null) {
					continue;
				}
				if (!sizeKnown) {
					Font f = g.getFont();
					FontMetrics fm = getFontMetrics(f);
					textWidth = fm.stringWidth(progressMessage);
					textHeight = fm.getHeight();
					sizeKnown = true;
				}
				g.drawString(progressMessage, 
					w / 2 - textWidth / 2, 
					h / 2 - textHeight / 2);
				int y = 15;
				g.setColor(Color.black);
				g.fillRect(0, y, x, 15);
				g.setColor(Color.red);
				g.fillRect(x, y, 32, 7);
				g.setColor(Color.black);
				g.fillRect(x, y + 7, 32, 1);
				g.setColor(Color.red);
				g.fillRect(x, y + 8, 32, 7);
				g.setColor(Color.black);
				g.fillRect(x + 32, y, w - x - 32, 15);
				g.dispose();
				x += step;
				if (x <= 0) {
					step = 16;
				} else if (x >= (w - 32)) {
					step = -16;
				}
				progressMeterPosition = x;
				progressMeterStep = step;
			}
		}
	}
	public int angleOfVector(int x1, int y1, int x2, int y2)
	{
		double xdiff = (double) (x2 - x1);
		double ydiff = (double) (y2 - y1);
		double radians = Math.atan2(ydiff, xdiff);
		int degrees = (int) (radians * 360.0 / (Math.PI * 2));
		while (degrees < 0) {
			degrees += 360;
		}
		degrees %= 360;
		return degrees;
	}
	
	public void setSelectedSprite(Sprite selectedSprite) {
		this.selectedSprite = selectedSprite;
	}
	
	public Sprite getSelectedSprite() {
		return selectedSprite;
	}
};

class Timer
{
	public Timer() { };
	public int delay;
	public int id;
	public Sprite s;
};

