package br.ufrj.nce.labase.phidias.jogo;

import java.util.ArrayList;

import org.richfaces.component.html.HtmlDataTable;

import br.ufrj.nce.labase.phidias.common.Util;
import br.ufrj.nce.labase.phidias.persistence.EntityManagerHelper;
import br.ufrj.nce.labase.phidias.persistence.dao.GameDAO;
import br.ufrj.nce.labase.phidias.persistence.model.Game;

public class JogoBean {
	
	private Game game = null;	
	
	private HtmlDataTable tabela = null;
	
	public JogoBean() {	
		game = new Game();		
	}
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}	

	public String goToJogo(){		
		return "jogo";
	}
	
	public HtmlDataTable getTabela() {
		return tabela;
	}

	public void setTabela(HtmlDataTable tabela) {
		this.tabela = tabela;
	}

	public String cadastrar(){
		
		EntityManagerHelper.getInstance().startTransaction();

		GameDAO dao = new GameDAO();
		dao.create(game);		
				
		EntityManagerHelper.getInstance().commitTransaction();
		
		return "sucesso";
	}
	
	public String consultaedicao(){		
		this.game = (Game)tabela.getRowData();		
		return "editar";
	}
	
	public String alterar(){
		EntityManagerHelper.getInstance().startTransaction();

		GameDAO dao = new GameDAO();
		dao.update(this.game);		
				
		EntityManagerHelper.getInstance().commitTransaction();	
		
		return "sucesso";
	}
	
	public String excluir(){		
		game = (Game)tabela.getRowData();
		
		EntityManagerHelper.getInstance().startTransaction();

		GameDAO dao = new GameDAO();
		dao.delete(game);		
				
		EntityManagerHelper.getInstance().commitTransaction();
		
		return "sucesso";
	}
	
	public String consultar(){
		
		GameDAO dao = new GameDAO();
		game = dao.findById(Game.class, game.getId());		
		
		return "sucesso";
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Game> getConsultarTodos(){
				
		String sql = "select * from jogo";
		ArrayList<Game> resultList = Util.convertListForArrayList( EntityManagerHelper.getInstance().getEntityManager().createNativeQuery(sql,Game.class).getResultList() );
		/*for (Game jogo : resultList) {
			System.out.println("-----------> nome do jogo : " + jogo.getName());
		}*/
		return resultList;
	}
	
}
