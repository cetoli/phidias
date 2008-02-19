package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * @generated
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "sessao")
public class Session implements java.io.Serializable {

	/**
	 * @generated
	 */
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@javax.persistence.Column(name = "ses_id_sessao", length = 10, nullable = false)
	private Integer id;

	/**
	 * @generated
	 */
	@javax.persistence.OneToMany
	@Column(name = "ses_id_sessao")
	private java.util.Set<SessionGamePhase> gamePhase = new java.util.HashSet<SessionGamePhase>();
	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne(optional = false)
	@javax.persistence.JoinColumn(name = "pac_id_login", nullable = false)
	private Patient patient;
	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne(optional = false)
	@javax.persistence.JoinColumn(name = "jog_id_jogo", nullable = false)
	private Game game;
	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne(optional = false)
	@javax.persistence.JoinColumn(name = "apl_id_aplicador", nullable = false)
	private Attendant attendant;
	/**
	 * @generated
	 */
	@javax.persistence.Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
	@javax.persistence.Column(name = "ses_dt_inicio_sessao", length = 0, nullable = false)
	private java.util.Date sessionStartDate;

	@javax.persistence.Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
	@javax.persistence.Column(name = "ses_dt_fim_sessao", length = 0, nullable = true)
	private java.util.Date sessionEndDate;

	/**
	 * @generated
	 */
	@javax.persistence.OneToMany(fetch = FetchType.LAZY)
	@Column(name = "ses_id_sessao")
	private java.util.Set<SessionQuestion> questionary = new java.util.HashSet<SessionQuestion>();
	/**
	 * @generated
	 */
	private static final long serialVersionUID = -1402737385L;

	/**
	 * @generated
	 */
	public Session() {
	}

	/**
	 * @generated
	 */
	public void addQuestionary(SessionQuestion questionary) {
		getQuestionary().add(questionary);
	}

	/**
	 * @generated
	 */
	public void removeQuestionary(SessionQuestion questionary) {
		getQuestionary().remove(questionary);
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "Session" + " id=" + id + " sessionStartDate=" + sessionStartDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public java.util.Set<SessionGamePhase> getGamePhase() {
		return gamePhase;
	}

	public void setGamePhase(java.util.Set<SessionGamePhase> gamePhase) {
		this.gamePhase = gamePhase;
	}

	public Patient getPaciente() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Game getJogo() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Attendant getAttendat() {
		return attendant;
	}

	public void setAttendant(Attendant attendant) {
		this.attendant = attendant;
	}

	public java.util.Date getSessionStartDate() {
		return sessionStartDate;
	}

	public void setSessionStartDate(java.util.Date sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
	}

	public java.util.Set<SessionQuestion> getQuestionary() {
		return questionary;
	}

	public void setQuestionary(java.util.Set<SessionQuestion> questionary) {
		this.questionary = questionary;
	}

	public java.util.Date getSessionEndDate() {
		return sessionEndDate;
	}

	public void setSessionEndDate(java.util.Date sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}
}