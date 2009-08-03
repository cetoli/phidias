package br.ufrj.nce.labase.phidias.persistence.model;

import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


/**
 * @generated
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "fase_jogo")
public class GamePhase implements java.io.Serializable {
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@javax.persistence.Column(name = "faj_id_fasejogo", length = 10, nullable = false)
	private int id;
	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne(optional = false)
	@javax.persistence.JoinColumn(name = "jog_id_jogo", nullable = false)
	private Game game;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "faj_ds_fasejogo", length = 300, nullable = false)
	private String description;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "fase_jogo_perguntas",
        joinColumns = @JoinColumn(name = "faj_id_fasejogo"),  
        inverseJoinColumns = {@JoinColumn(name = "QUE_ID_QUESTIONARIO"), @JoinColumn(name = "PEF_ID_PERGUNTA")})
	private Set<Question> questionList;
	
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 138747473L;

	/**
	 * @generated
	 */
	public GamePhase() {
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

	/**
	 * @generated
	 */
	public Game getGame() {
		return this.game;
	}

	/**
	 * @generated
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @generated
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @generated
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "FaseJogo" + " id=" + id + " fajDsFasejogo=" + description;
	}
	public Set<Question> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(Set<Question> questionList) {
		this.questionList = questionList;
	}
}