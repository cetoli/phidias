package br.ufrj.nce.labase.attention.conjuntos;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import br.ufrj.nce.labase.phidias.toolkit.GameBoard;
import br.ufrj.nce.labase.phidias.toolkit.mapping.Mapping;

public class ConjuntosGameBoard extends GameBoard {

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.conjuntos.images"; 
	
	public void initGame(){
		this.setBackgroundImage("fundo_final.gif");
		
		// mapeamento impossível para identificar peças errôneas
		Rectangle2D rosa = new Rectangle(23, 111, 293, 218);
		Rectangle2D verde = new Rectangle(447, 106, 303, 222);
		Rectangle2D verde_rosa = new Rectangle(312, 113, 151, 207);

		this.createSprite(new Point(37,29), "peca-11.gif", Mapping.getMapping(verde_rosa));
		this.createSprite(new Point(159, 16), "peca-12.gif", Mapping.getMapping(verde));
		this.createSprite(new Point(283,22), "peca-13.gif", Mapping.getMapping(rosa));
		this.createSprite(new Point(408,23), "peca-14.gif", Mapping.getMapping(rosa));
		this.createSprite(new Point(530,21), "peca-15.gif", Mapping.getMapping(verde_rosa));
		this.createSprite(new Point(635,21), "peca-16.gif", Mapping.getMapping(verde));
		this.createSprite(new Point(488,350), "peca-17.gif", Mapping.getMapping(verde_rosa));
		this.createSprite(new Point(32,357), "peca-18.gif", Mapping.getMapping(rosa));
		this.createSprite(new Point(200,340), "peca-20.gif", Mapping.getMapping(verde_rosa));
		this.createSprite(new Point(343,345), "peca-22.gif", Mapping.getMapping(verde));
		this.spriteManager.setSnapToDestination(false);
		
	}
	
	@Override
	public String getImagesPackageName() {
		return IMAGES_PACKAGE;
	}

	@Override
	public int getScreenHeight() {
		return 600;
	}

	@Override
	public int getScreenWidth() {
		return 800;
	}

	@Override
	public void handlePhaseFive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePhaseFour() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePhaseOne() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePhaseSeven() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePhaseSix() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePhaseThree() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlePhaseTwo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintGameBoard(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
