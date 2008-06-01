package br.ufrj.nce.labase.phidias.toolkit.sprite.event;

public interface SpriteActionListener {
	
	/** 
	 * Called whenever an event occurs in a Sprite. May be a mouse event or any other event.
	 *
	 * @param event The SpriteActionEvent that occurred in the Sprite.
	 */
	public void spriteActionPerformed(SpriteActionEvent event);
}
