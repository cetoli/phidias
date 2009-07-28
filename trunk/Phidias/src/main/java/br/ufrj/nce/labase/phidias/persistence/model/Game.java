package br.ufrj.nce.labase.phidias.persistence.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * @generated
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "jogo")
public class Game implements java.io.Serializable {
	/**
	 * @generated
	 */
	@javax.persistence.OneToMany(mappedBy = "game")
	private java.util.Set<GamePhase> gamePhase = new java.util.HashSet<GamePhase>();
	/**
	 * @generated
	 */
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@javax.persistence.Column(name = "jog_id_jogo", length = 10, nullable = false)
	private int id;
	/**
	 * @generated
	 */
	@javax.persistence.Column(name = "jog_nm_jogo", length = 50, nullable = false)
	private String name;
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 713364452L;

	/**
	 * @generated
	 */
	public Game() {
	}

	/**
	 * @generated
	 */
	public java.util.Set<GamePhase> getGamePhase() {
		return this.gamePhase;
	}

	/**
	 * @generated
	 */
	public void setGamePhase(java.util.Set<GamePhase> gamePhase) {
		this.gamePhase = gamePhase;
	}

	/**
	 * @generated
	 */
	public void addGamePhase(GamePhase gamePhase) {
		getGamePhase().add(gamePhase);
	}

	/**
	 * @generated
	 */
	public void removeGamePhase(GamePhase gamePhase) {
		getGamePhase().remove(gamePhase);
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
	public String getName() {
		return this.name;
	}

	/**
	 * @generated
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "Jogo" + " id=" + id + " jogNmJogo=" + name;
	}
}