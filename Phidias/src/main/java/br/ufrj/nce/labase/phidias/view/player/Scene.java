package br.ufrj.nce.labase.phidias.view.player;

public class Scene {
	private String name;
	private int x;
	private int y;
	
	public Scene(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}
}
