package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;

/**
 * @generated
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "acao_jogo")
public class Action implements java.io.Serializable {
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@javax.persistence.Column(name = "acj_id_acao", length = 10, nullable = false)
	private int id;
	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne(optional = false)
	@JoinColumns( { @JoinColumn(name = "FAJ_ID_FASEJOGO"), @JoinColumn(name = "SES_ID_SESSAO") })
	private SessionGamePhase sessionGamePhase;

	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne(optional = false)
	@javax.persistence.JoinColumn(name = "tia_id_tipoacao", nullable = false)
	private ActionType actionType;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "acj_tp_jogada", length = 10, nullable = false)
	private long moveTime;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "acj_fg_jogada_valida", length = 0, nullable = false)
	private Boolean validMove;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "acj_nm_objeto_manipulavel_1", length = 50)
	private String object1;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "acj_nm_objeto_manipulavel_2", length = 50)
	private String object2;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "ACJ_FG_SENT_ATTENDANT", length = 0)
	private Boolean sentToAttendant;
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 1283548244L;

	/**
	 * @generated
	 */
	public Action() {
	}

	/**
	 * @generated
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @generated
	 */
	public void setId(int id) {
		this.id = id;
	}

	public void setSessionGamePhase(SessionGamePhase sessionGamePhase) {
		this.sessionGamePhase = sessionGamePhase;
	}

	public SessionGamePhase getSessionGamePhase() {
		return sessionGamePhase;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setMoveTime(long moveTime) {
		this.moveTime = moveTime;
	}

	public long getMoveTime() {
		return moveTime;
	}

	public void setValidMove(Boolean validMove) {
		this.validMove = validMove;
	}

	public Boolean getValidMove() {
		return validMove;
	}

	public void setObject1(String object1) {
		this.object1 = object1;
	}

	public String getObject1() {
		return object1;
	}

	public void setObject2(String object2) {
		this.object2 = object2;
	}

	public String getObject2() {
		return object2;
	}

	/**
	 * @return the sentToAttendant
	 */
	public Boolean getSentToAttendant() {
		return sentToAttendant;
	}

	/**
	 * @param sentToAttendant the sentToAttendant to set
	 */
	public void setSentToAttendant(Boolean sentToAttendant) {
		this.sentToAttendant = sentToAttendant;
	}
}