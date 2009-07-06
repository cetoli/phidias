package br.ufrj.nce.labase.phidias.toolkit.sprite.event;

import br.ufrj.nce.labase.phidias.toolkit.sprite.Sprite;

/**
 * Event fired by an Sprite. May be fired by a mouse event, or any other event that may occur in an Sprite.
 *  
 * @author Sabrina Bettini
 *
 */
public class SpriteActionEvent {
	/** The type of event that occurred in the Sprite */
	private int type;
	
	/** The Sprite that originated the event. */
	private Sprite source;
	
	/* Mouse events */
	public static final int MOUSE_CLICKED = 0;
	public static final int MOUSE_PRESSED = 1;
	public static final int MOUSE_RELEASED = 2;
	public static final int MOUSE_MOVED = 3;
	public static final int MOUSE_ENTERED = 4;
	public static final int MOUSE_EXITED = 5;
	public static final int MOUSE_DRAGGED = 4;
	
	public SpriteActionEvent(Sprite source, int type) {
		this.source = source;
		this.type = type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}

	public void setSource(Sprite source) {
		this.source = source;
	}

	public Sprite getSource() {
		return source;
	}
	
}
