package br.ufrj.nce.labase.phidias.persistence.dao;

import java.util.List;

import javax.persistence.Query;

import br.ufrj.nce.labase.phidias.persistence.model.Piece;

public class PieceDAO extends GenericDAO<Piece> {

	@SuppressWarnings("unchecked")
	public Integer getPieceId(String pieceName) {
		Query query = this
				.getSession()
				.createQuery(
						"select f.pk.gameId from Piece f where f.pieceName = :pieceName");
		query.setParameter("pieceName", pieceName);

		List<Integer> resultList = (List<Integer>) query.getResultList();
		
		if (!resultList.isEmpty()) {
			return resultList.get(0);
		}
		
		return 0;
	}
}
