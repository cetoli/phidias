package br.ufrj.nce.labase.phidias.enxoval;

import br.ufrj.nce.labase.phidias.persistence.EntityManagerHelper;
import br.ufrj.nce.labase.phidias.persistence.dao.PieceCollectionDAO;
import br.ufrj.nce.labase.phidias.persistence.model.PieceCollection;

public class EnxovalBean {
	
	private PieceCollection pieceCollection;
	
	public EnxovalBean() {		
		this.pieceCollection = new PieceCollection();
	}
	
	public PieceCollection getPieceCollection(){
		return this.pieceCollection;
	}
	
	public String goToEnxoval(){		
		return "enxoval";
	}
	
	public String cadastrar(){
		
		EntityManagerHelper.getInstance().startTransaction();

		PieceCollectionDAO dao = new PieceCollectionDAO();
		dao.create(pieceCollection);		
		
		System.out.println("Nome do Enxoval : " + pieceCollection.getPieceCollectionName());
		
		EntityManagerHelper.getInstance().commitTransaction();


		
		return "sucesso";
	}
	
}
