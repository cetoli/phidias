package baklava;

import java.awt.*;
import java.util.Hashtable;

public class Sprite extends Object {
	static public final int edgeLeft = 0;
	static public final int edgeRight = 1;
	static public final int edgeTop = 2;
	static public final int edgeBottom = 3;
	static public final int edgeBounce = 16;
	static public final int edgeWrap = 17;
	static public final int edgeSolid = 18;
	private Playfield playfield;
	boolean tilePending = false;
	int x = 0;
	int y = 0;
	int xaccum = 0;
	int yaccum = 0;
	int w = 0;
	int h = 0;
	int oldX = 0;
	int oldY = 0;
	int oldW = 0;
	int oldH = 0;
	int level = 0;
	Image image = null;
	Image tile = null;
	int speed = 0;
	int direction = 0;
	int edgeHandling = edgeSolid;
	boolean mouseIn = false;
	int row1 = -1;
	int row2 = -1;
	int col1 = -1;
	int col2 = -1;
	int oldRow1 = -1;
	int oldRow2 = -1;
	int oldCol1 = -1;
	int oldCol2 = -1;
	boolean cleanup = false;
	int masks[][];
	int maskWidth;
	Hashtable <Sprite, Integer> hits;
	boolean moved;
	boolean background;
	boolean rectangular;
	int xtarget;
	int ytarget;
	boolean seeking;
	public Sprite() {		
	}
	
	public Sprite(Playfield playfieldArg) {
		playfield = playfieldArg;
		playfield.addSpriteToList(this);		
		hits = new Hashtable <Sprite, Integer>();
		moved = true;
		seeking = false;
		background = false;
		rectangular = false;
	}
	final public void goodbye() {
		if (cleanup) {
			// It's been done already.
			return;
		}
		// Make sure it gets erased.
		moved = true;
		cleanup = true;
		getPlayfield().removeSpriteFromList(this);
		onGoodbye();
	}
	public void onGoodbye()
	{

	}
	final public void setSpeed(int s) {
		speed = s;
	}	
	final public int getSpeed() {
		return speed;
	}
	void checkMouseIn(Event evt, int x, int y) {
		if (test(x, y)) {
			if (!mouseIn) {
				mouseEnter(evt, x, y);
				mouseIn = true;
			}
		} else {
			if (mouseIn) {
				mouseExit(evt, x, y);
				mouseIn = false;
			}
		}
	}
	final public void setLevel(int l) {
		getPlayfield().removeSpriteFromList(this);
		level = l;
		getPlayfield().addSpriteToList(this);
		moved = true;
	}	
	final public int getLevel() {
		return level;
	}	
	final public void setX(int xArg) {
		x = xArg;
		moved = true;
	}	
	final public int getX() {
		return x;
	}
	final public void setY(int yArg) {
		y = yArg;
		moved = true;
	}	
	final public int getY() {
		return y;
	}
	public void setTile(Image imageArg, int wArg, int hArg) {
		image = null;
		tile = imageArg;
		tilePending = true;
		moved = true;
		w = wArg;
		h = hArg;
	}
	public void setImage(Image imageArg) {
		getPlayfield().imagesNeededNow();
		image = imageArg;
		w = image.getWidth(playfield);
		h = image.getHeight(playfield);
		moved = true;
		if (background) {
			return;
		}
		if (rectangular) {
			return;	
		}
		maskWidth = w / 32;
		maskWidth++;
		masks = getPlayfield().getImageMask(image);
	}
	public Image getImage() {
		return image;
	}
	final public void setWidth(int wArg) {
		w = wArg;
		moved = true;
	}
	final public void setHeight(int hArg) {
		h = hArg;
		moved = true;
	}
	final public int getWidth() {
		return w;
	}
	final public int getHeight() {
		return h;
	}
	public void setDirection(int directionArg) {
		direction = directionArg;
		while (direction < 0) {
			direction += 360;
		}
		direction %= 360;
	}	
	public void setDirectionToward(int xArg, int yArg) {
		setDirection(getPlayfield().angleOfVector(x, y, xArg, yArg));
	}
	public void setDirectionToward(Sprite s) {
		setDirection(getPlayfield().angleOfVector(x, y, s.x, s.y));
	}
	public int getDirection() {
		return direction;
	}
	public void setEdgeHandling(int edgeHandlingArg) {
		edgeHandling = edgeHandlingArg;
	}
	public void mouseDown(Event evt, int x, int y)
	{
	}
	public void mouseUp(Event evt, int x, int y)
	{
	}
	public void mouseMove(Event evt, int x, int y)
	{
	}
	public void mouseDrag(Event evt, int x, int y)
	{
	}
	public void mouseEnter(Event evt, int x, int y)
	{
	}
	public void mouseExit(Event evt, int x, int y)
	{
	}
	public void keyDown(Event evt, int key)
	{
	}
	public void keyUp(Event evt, int key)
	{
	}
	public boolean test(Sprite s)
	{
		int tw = w;
		int th = h;
		int sw = s.w;
		int sh = s.h;
		if (background || (s.background)) {
			// Never
			return false;
		}
		// Left occlusion, right occlusion,
		// encapsulation by, and encapsulation of
		if (!(((s.x >= x) && (s.x <= (x + tw))) ||
			(((s.x + sw) > x) && ((s.x + sw) <= (x + tw))) ||
			((s.x <= x) && ((s.x + sw) >= (x + tw))) ||
			((x <= s.x) && ((x + tw) >= (s.x + sw))))) 
		{ 
			return false; 
		}		
		if (!(((s.y >= y) && (s.y <= (y + th))) ||
			(((s.y + sh) >= y) && ((s.y + sh) <= (y + th))) ||
			((s.y <= y) && ((s.y + sh) >= (y + th))) ||
			((y <= s.y) && ((y + th) >= (s.y + sh))))) 
		{ 
			return false;
		}		
		if ((getRectangular()) || (s.getRectangular())) {
			return true;
		}
		// Now it's time to play with pixels, boys and girls.
		// First, find the intersection rectangle.
		int ix = (int) x;
		if (s.x > ix) {
			ix = (int) s.x;
		}
		int iy = (int) y;
		if (s.y > iy) {
			iy = (int) s.y;
		}
		int iw;
		if ((x + w) > (s.x + s.w)) {
			iw = (int) ((s.x + s.w) - ix);
		} else {
			iw = (int) ((x + w) - ix);
		}
		int ih;
		if ((y + h) > (s.y + s.h)) {
			ih = (int) ((s.y + s.h) - iy);
		} else {
			ih = (int) ((y + h) - iy);
		}		
		// Now check the pixels in the square
		// marked out by ix, iy, iw, and ih.
		int modulusOne = ((int) (ix - x)) & 31;
		int modulusTwo = ((int) (ix - s.x)) & 31;
		int[] maskOne = masks[modulusOne];	
		int[] maskTwo = s.masks[modulusTwo];	
		int rowOffsetOne = ((int) (iy - y)) * maskWidth;
		int rowOffsetTwo = ((int) (iy - s.y)) * s.maskWidth;
		int colOffsetOne = ((int) (ix - x)) / 32;
		int colOffsetTwo = ((int) (ix - s.x)) / 32;
		int row, col;
		int colMaskWidth = iw / 32;
		if ((iw & 31) != 0) {
			colMaskWidth++;
		}	
		for (row = 0; (row < ih); row++) {
			int r1 = rowOffsetOne + colOffsetOne;
			int r2 = rowOffsetTwo + colOffsetTwo;
			for (col = 0; (col < colMaskWidth); col++) {
				if ((maskOne[r1] & maskTwo[r2]) != 0) {
					// JACKPOT!
					return true;
				}
				r1++;
				r2++;
			}
			rowOffsetOne += maskWidth;
			rowOffsetTwo += s.maskWidth;
		}
		return false;
	}
	public boolean test(int xArg, int yArg)
	{
		if (background) {
			// There are never collisions with background objects
			return false;
		}
		int tw = w;
		int th = h;
		boolean inRectangle = false;
		if ((xArg >= x) && (xArg < (x + tw))) {
			if ((yArg >= y) && (yArg < (y + th))) {
				inRectangle = true;
			}		
		}
		if (!inRectangle) {
			return false;
		}
		if (getRectangular()) {
			return true;
		}
		int ix = xArg;
		int iy = yArg;
		int modulusOne = ((int) (ix - x)) & 31;
		int[] maskOne = masks[modulusOne];	
		int rowOffsetOne = ((int) (iy - y)) * maskWidth;
		int colOffsetOne = ((int) (ix - x)) / 32;
		int r1 = rowOffsetOne + colOffsetOne;
		if ((maskOne[r1] & (1 << 31)) != 0) {
			// JACKPOT!
			return true;
		}
		return false;
	}
	void handleEdges(int pw, int ph)
	{
		int tw = w;
		int th = h;
		if (x < 0) {
			collisionEdge(edgeLeft);
			// Then the failsafe
			if (x < 0) {
				x = 0;
			}
		} 
		if (x + tw > pw) {
			collisionEdge(edgeRight);
			if (x + tw > pw) {
				x = pw - tw;
			}
		}
		if (y < 0) {
			collisionEdge(edgeTop);
			if (y < 0) {
				y = 0;
			}
		}
		if (y + th > ph) {
			collisionEdge(edgeBottom);
			if (y + th > ph) {
				y = ph - th;
			}
		}	
	}
	public void collisionEdge(int edge)
	{
		switch (edgeHandling) {
			case edgeSolid:
			// Do nothing and let the failsafe code handle this.
			break;
			case edgeBounce:
			switch(edge) {
				case edgeBottom:
				if (direction < 180) {
					direction = 360 - direction;
				}
				break;
				case edgeLeft:
				if ((direction > 90) && (direction < 270)) {
					direction = 180 - direction;
				}
				break;
				case edgeTop:
				if (direction > 180) {
					direction = 360 - direction;
				}
				break;
				case edgeRight:
				if ((direction < 90) || (direction > 270)) {
					direction = 180 - direction;
				}
				break;
				default:
				break;
			}
			while (direction < 0) {
				direction += 360;
			}
			direction %= 360;	
			break;
			case edgeWrap:
			int tw = w;
			int th = h;
			int cw = getPlayfield().getWidth();
			while ((x - tw) < 0) {
				x += cw;
			}
			while (x >= cw) {
				x -= (cw + tw);
			}
			int ch = getPlayfield().getHeight();
			while ((y - th) < 0) {
				y += ch;
			}
			while (y >= ch) {
				y -= (ch + th);
			}
			break;
			default:	
			break;
		}
	}
	public void collisionWith(Sprite s)
	{
	}
	void step(int elapsed) 
	{
		if (seeking) {
			// Are we close enough to simply decide
			// we have arrived? This is fudged to try
			// to guarantee eventual arrival even under
			// choppy conditions.
			int dist = ((speed * elapsed >> 10) + 1) << 1;
			int dt = 0;
			if (x > xtarget) {
				dt += (x - xtarget);
			} else {
				dt += (xtarget - x);
			}
			if (y > ytarget) {
				dt += (y - ytarget);
			} else {
				dt += (ytarget - y);
			}
			if (dt <= dist) {
				x = xtarget;
				y = ytarget;
				moved = true;
				speed = 0;
				seeking = false;
				onArrival();
				onStep(elapsed);			
				return;
			}
		}
		xaccum += (speed * getPlayfield().cost[direction] * elapsed) >> 8;
		yaccum += (speed * getPlayfield().sint[direction] * elapsed) >> 8;
		x += xaccum >> 10;
		y += yaccum >> 10;
		xaccum &= 0x03FF;
		yaccum &= 0x03FF;
		if (speed != 0) {
			moved = true;
		}
		onStep(elapsed);
	}
	public void onArrival()
	{
	}
	public void onStep(int elapsed)
	{
	}
	public void paint(Graphics g)
	{
		if (cleanup) {
			return;
		}
		if (tilePending) {
			int x, y;
			int tw = tile.getWidth(getPlayfield());
			int th = tile.getHeight(getPlayfield());
			image = getPlayfield().applet.createImage((int) w, (int) h);
			Graphics ig = image.getGraphics();
			for (y = 0; (y < h); y += th) {
				for (x = 0; (x < w); x += tw) {
					ig.drawImage(tile, x, y, getPlayfield());
				}
			}	
			tilePending = false;
		}
		if (image != null) {
			g.drawImage(image, (int) x, (int) y, getPlayfield());
		}
	}	
	public void setTimer(int delay, int id)
	{
		getPlayfield().setTimer(this, delay, id);
	}
	public void timer(int timerId)
	{
	}
	final boolean changeGridBounds(
		int row1Arg, int row2Arg,
		int col1Arg, int col2Arg)
	{
		boolean result = false;
		oldRow1 = row1;
		oldRow2 = row2;
		oldCol1 = col1;
		oldCol2 = col2;
		row1 = row1Arg;
		row2 = row2Arg;
		col1 = col1Arg;
		col2 = col2Arg;
		if ((row1 != oldRow1) || (row2 != oldRow2) 
			|| (col1 != oldCol1) || (col2 != oldCol2)) 
		{ 
			result = true;
		}	
		return result;
	}
	final int getRow1() {
		return row1;
	}
	final int getRow2() {
		return row2;
	}
	final int getCol1() {
		return col1;
	}
	final int getCol2() {
		return col2;
	}
	final int getOldRow1() {
		return oldRow1;
	}
	final int getOldRow2() {
		return oldRow2;
	}
	final int getOldCol1() {
		return oldCol1;
	}
	final int getOldCol2() {
		return oldCol2;
	}
	final boolean getCleanup() {
		return cleanup;
	}
	final Hashtable <Sprite, Integer> getHits() {
		return hits;
	}
	final public boolean getBackground() {
		return background;
	}
	final public boolean getRectangular() {
		return (rectangular || (image == null));
	}
	final public void setBackground(boolean backgroundArg) {
		background = backgroundArg;
	}
	final public void setRectangular(boolean rectangularArg) {
		rectangular = rectangularArg;
	}
	final boolean getMoved() {
		return moved;
	}
	final void clearMoved() {
		moved = false;
		oldX = x;
		oldY = y;
		oldW = w;
		oldH = h;
	}
	final public void setTarget(int x, int y) {
		xtarget = x;
		ytarget = y;
		seeking = true;
		setDirectionToward(xtarget, ytarget);
	}
	final Dimension getYRange() {
		Dimension d = new Dimension();
		if (oldY < y) {
			d.width = (int) oldY;
		} else {	
			d.width = (int) y;
		}
		if ((oldY + oldH) > (y + h)) {
			d.height = (int) (oldY + oldH);
		} else {	
			d.height = (int) (y + h);
		}
		return d;
	}
	
	public Playfield getPlayfield() {
		return playfield;
	}
	
	public void setPlayfield(Playfield playfield) {
		this.playfield = playfield;
	}
	
	public void putOnBoard() {
		
	}
};


