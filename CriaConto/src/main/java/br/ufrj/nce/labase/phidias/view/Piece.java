package br.ufrj.nce.labase.phidias.view;

import java.awt.Event;
import java.awt.Image;

import baklava.Sprite;

public class Piece extends Sprite {
	private String name;
	protected int originalX;
	protected int originalY;
	protected int downX;
	protected int downY;
	protected long moveStartTime;
	protected long moveEndTime;
	
	public Piece() {
		super();
	}
	
	public Piece(Board p, Image image, String name, int x, int y) {
		super(p);
		setImage(image);
		this.setName(name);
		
		originalX = x;
		originalY = y;
		
		setX(x);
		setY(y);

		setDirection(0);
		setSpeed(0);
	}
	
	public void mouseDrag(Event evt, int x, int y) {
		setX(evt.x - downX);
		setY(evt.y - downY);
	}
	
	public void mouseDown(Event evt, int x, int y) {
		downX = x;
		downY = y;
		
		moveStartTime = System.currentTimeMillis();	}
	
	public void mouseUp(Event evt, int x, int y) {
		moveEndTime = System.currentTimeMillis();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public Board getPlayfield() {
		return (Board) super.getPlayfield();
	}
	
	public long getMoveTime() {
		System.out.println("moveEndTime - moveStartTime = " + (moveEndTime - moveStartTime));
		return moveEndTime - moveStartTime;
	}
	
	public void collisionWith(Piece p) {
		
	}
	
	public void collisionWith(Sprite s) {
		if (s instanceof Piece) {
			collisionWith ((Piece) s);
		}
	}
}
