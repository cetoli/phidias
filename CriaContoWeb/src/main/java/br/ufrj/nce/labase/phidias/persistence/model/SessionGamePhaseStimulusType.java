package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @generated
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "fase_jogo_sessao_tipo_estimulo")
public class SessionGamePhaseStimulusType implements java.io.Serializable {

	/**
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "JJTE_ID_FASE_JOGO_TIPO_ESTIMULO", length = 10, nullable = false)
	private Integer id;

	/**
	 * @generated
	 */
	@Column(name = "FAJ_ID_FASEJOGO")
	private Integer phaseId;
	/**
	 * @generated
	 */
	@Column(name = "SES_ID_SESSAO")
	private Integer sessionId;

	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "TIE_ID_TIPO_ESTIMULO", length = 10, nullable = false)
	private int stimulusTypeId;

	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "JJTE_TX_COMANDO_ESTIMULO", length = 200, nullable = true)
	private String stimulusText;
	
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "JJTE_FG_ESTIMULO_ENVIADO", length = 200, nullable = true)
	private Boolean stimulusSent;
	
	
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 1270276497L;

	/**
	 * @generated
	 */
	public SessionGamePhaseStimulusType(Integer id) {
		this.id = id;
	}

	public SessionGamePhaseStimulusType() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @generated
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @generated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "FaseJogoSessao" + " textoComandoEstimulo=" + stimulusText;
	}

	public String getStimulusText() {
		return stimulusText;
	}

	public void setStimulusText(String stimulusText) {
		this.stimulusText = stimulusText;
	}

	public Integer getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(Integer phaseId) {
		this.phaseId = phaseId;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public int getStimulusTypeId() {
		return stimulusTypeId;
	}

	public void setStimulusTypeId(int stimulusTypeId) {
		this.stimulusTypeId = stimulusTypeId;
	}

	public Boolean isStimulusSend() {
		return stimulusSent;
	}

	public void setStimulusSent(Boolean stimulusSent) {
		this.stimulusSent = stimulusSent;
	}
}