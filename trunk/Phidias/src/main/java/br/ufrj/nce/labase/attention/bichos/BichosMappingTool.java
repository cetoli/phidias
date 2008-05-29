package br.ufrj.nce.labase.attention.bichos;

import br.ufrj.nce.labase.tools.MappingTool;

public class BichosMappingTool extends MappingTool {

	private static final String IMAGES_PACKAGE = "br.ufrj.nce.labase.attention.bichos.images"; 

	public void initGame() {
			this.setBackgroundImage("jogo_completo.gif");
	}

	@Override
    public String getImagesPackageName() {
    	return IMAGES_PACKAGE ;
    }
	
	public String getImagefile(){
		return "background.gif";
	}

}
