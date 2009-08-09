package br.ufrj.nce.labase.phidias.persistence.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @generated
 */
@Entity
@Table(name = "acao_jogo")
public class Action implements java.io.Serializable {
	/**
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "acj_id_acao", length = 10, nullable = false)
	private int id;
	/**
	 * @generated
	 */
	@ManyToOne(optional = false)
	@JoinColumns( { @JoinColumn(name = "FAJ_ID_FASEJOGO"), @JoinColumn(name = "SES_ID_SESSAO") })
	private SessionGamePhase sessionGamePhase;

	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne(optional = false)
	@JoinColumn(name = "tia_id_tipoacao", nullable = false)
	private ActionType actionType;
	/**
	 * @generated
	 */
	@Column(name = "acj_tp_jogada", length = 10, nullable = false)
	private long moveTime;
	/**
	 * @generated
	 */
	@Column(name = "acj_fg_jogada_valida", length = 0, nullable = false)
	private Boolean validMove;
	/**
	 * @generated
	 */
	@Column(name = "acj_nm_objeto_manipulavel_1", length = 50)
	private String object1;
	/**
	 * @generated
	 */
	@Column(name = "acj_nm_objeto_manipulavel_2", length = 50)
	private String object2;
	/**
	 * @generated
	 */
	@Column(name = "acj_fg_enviado_aplicador", length = 0)
	private Boolean sentToAttendant;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ACJ_ID_ACAO")
	private List<ActionMovement> actionMovements;
	
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

	/**
	 * @return the actionMovements
	 */
	public List<ActionMovement> getActionMovements() {
		return actionMovements;
	}

	/**
	 * @param actionMovements the actionMovements to set
	 */
	public void setActionMovements(List<ActionMovement> actionMovements) {
		this.actionMovements = actionMovements;
	}
}