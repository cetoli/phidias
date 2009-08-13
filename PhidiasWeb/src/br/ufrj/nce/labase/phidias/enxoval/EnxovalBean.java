package br.ufrj.nce.labase.phidias.enxoval;

import java.util.ArrayList;

import org.richfaces.component.html.HtmlDataTable;

import br.ufrj.nce.labase.phidias.common.Util;
import br.ufrj.nce.labase.phidias.persistence.EntityManagerHelper;
import br.ufrj.nce.labase.phidias.persistence.dao.PieceCollectionDAO;
import br.ufrj.nce.labase.phidias.persistence.model.PieceCollection;

public class EnxovalBean {
	
	private PieceCollection pieceCollection = null;	
	
	private HtmlDataTable tabela = null;
	
	public EnxovalBean() {	
		pieceCollection = new PieceCollection();		
	}
	
	public PieceCollection getPieceCollection() {
		return pieceCollection;
	}
	public void setPieceCollection(PieceCollection pieceCollection) {
		this.pieceCollection = pieceCollection;
	}	

	public String goToEnxoval(){		
		return "enxoval";
	}
	
	public HtmlDataTable getTabela() {
		return tabela;
	}

	public void setTabela(HtmlDataTable tabela) {
		this.tabela = tabela;
	}

	public String cadastrar(){
		
		EntityManagerHelper.getInstance().startTransaction();

		PieceCollectionDAO dao = new PieceCollectionDAO();
		dao.create(pieceCollection);		
				
		EntityManagerHelper.getInstance().commitTransaction();
		
		return "sucesso";
	}
	
	public String consultaedicao(){		
		this.pieceCollection = (PieceCollection)tabela.getRowData();		
		return "editar";
	}
	
	public String alterar(){
		EntityManagerHelper.getInstance().startTransaction();

		PieceCollectionDAO dao = new PieceCollectionDAO();
		dao.update(this.pieceCollection);		
				
		EntityManagerHelper.getInstance().commitTransaction();	
		
		return "sucesso";
	}
	
	public String excluir(){		
		pieceCollection = (PieceCollection)tabela.getRowData();
		
		EntityManagerHelper.getInstance().startTransaction();

		PieceCollectionDAO dao = new PieceCollectionDAO();
		dao.delete(pieceCollection);		
				
		EntityManagerHelper.getInstance().commitTransaction();
		
		return "sucesso";
	}
	
	public String consultar(){
		
		PieceCollectionDAO dao = new PieceCollectionDAO();
		pieceCollection = dao.findById(PieceCollection.class, pieceCollection.getPieceCollectionId());		
		
		return "sucesso";
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<PieceCollection> getConsultarTodos(){
				
		String sql = "select * from enxoval";
		ArrayList<PieceCollection> resultList = Util.convertListForArrayList( EntityManagerHelper.getInstance().getEntityManager().createNativeQuery(sql,PieceCollection.class).getResultList() );
	/*	for (PieceCollection enxoval : resultList) {
			System.out.println("-----------> nome do enxoval : " + enxoval.getPieceCollectionName());
		} */
		return resultList;
	}
	
}
