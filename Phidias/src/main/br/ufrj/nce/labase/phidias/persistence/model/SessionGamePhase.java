package br.ufrj.nce.labase.phidias.persistence.model;

/**
 * @generated
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "fase_jogo_sessao")
public class SessionGamePhase implements java.io.Serializable {

	/**
	 * @generated
	 */
	@javax.persistence.EmbeddedId
	private SessionGamePhaseId id;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "fjs_tx_comentarios", length = 500, nullable = true)
	private String comments;
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 1270276497L;

	/**
	 * @generated
	 */
	public SessionGamePhase(Integer phaseId, Integer sessionId) {
		this.id = new SessionGamePhaseId(phaseId, sessionId);
	}

	public SessionGamePhase() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @generated
	 */
	public SessionGamePhaseId getId() {
		return this.id;
	}

	/**
	 * @generated
	 */
	public void setId(SessionGamePhaseId id) {
		this.id = id;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "FaseJogoSessao" + " fjsTxComentarios=" + comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getComments() {
		return comments;
	}
}