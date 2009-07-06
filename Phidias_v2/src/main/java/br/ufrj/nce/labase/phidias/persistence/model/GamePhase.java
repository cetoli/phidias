package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


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
}