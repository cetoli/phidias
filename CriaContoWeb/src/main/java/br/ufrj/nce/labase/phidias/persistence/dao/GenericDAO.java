package br.ufrj.nce.labase.phidias.persistence.dao;

import java.io.Serializable;

import br.ufrj.nce.labase.persistence.EntityManagerHelper;

/**
 * @generated
 */
public class GenericDAO<T extends Serializable> {
	/**
	 * @generated
	 */
	public GenericDAO() {
	}

	/**
	 * @generated
	 */
	protected javax.persistence.EntityManager getSession() {
		return EntityManagerHelper.getInstance().getEntityManager();
	}

	/**
	 * @generated
	 */
	public T create(T object) {
		if (object == null)
			throw new IllegalArgumentException("object");
		getSession().persist(object);
		return object;
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public T findById(Class clazz, Object id) {
		return (T) getSession().find(clazz, id);
	}

	/**
	 * @generated
	 */
	public void update(T object) {
		if (object == null)
			throw new IllegalArgumentException("object");
		getSession().merge(object);
	}

	/**
	 * @generated
	 */
	public void delete(T object) {
		if (object == null)
			throw new IllegalArgumentException("object");
		getSession().remove(object);
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "GenericDAO";
	}
}