package br.ufrj.nce.labase.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * @generated
 */
public class EntityManagerHelper {
	/**
	 * @generated
	 */
	private static EntityManagerHelper singleton = new EntityManagerHelper();
	/**
	 * @generated
	 */
	private javax.persistence.EntityManagerFactory factory;
	/**
	 * @generated
	 */
	private ThreadLocal<EntityManager> currentEntityManager = new ThreadLocal<EntityManager>();

	/**
	 * @generated
	 */
	private EntityManagerHelper() {
	}

	/**
	 * @generated
	 */
	public static EntityManagerHelper getInstance() {
		return singleton;
	}

	/**
	 * @generated
	 */
	private synchronized javax.persistence.EntityManagerFactory getFactory() {
		if (factory == null) {
			factory = javax.persistence.Persistence.createEntityManagerFactory("CriaContoPersist");
		}
		return factory;
	}

	/**
	 * @generated
	 */
	public synchronized void closeEntityManagerFactory() {
		closeEntityManager();
		if (factory != null) {
			factory.close();
			factory = null;
		}
	}

	/**
	 * @generated
	 */
	public javax.persistence.EntityManager getEntityManager() {
		try {
			javax.persistence.EntityManager entityManager = (javax.persistence.EntityManager) currentEntityManager.get();
			if (entityManager == null || !entityManager.isOpen()) {
				entityManager = getFactory().createEntityManager();
				currentEntityManager.set(entityManager);
			}
			return entityManager;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * @generated
	 */
	public void startTransaction() {
		try {
			EntityTransaction et = this.getEntityManager().getTransaction();
			et.begin();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * @generated
	 */
	public void commitTransaction() {
		EntityTransaction et = this.getEntityManager().getTransaction();
		et.commit();
		this.closeEntityManager();
	}

	/**
	 * @generated
	 */
	public void rollbackTransaction() {
		EntityTransaction et = this.getEntityManager().getTransaction();
		et.rollback();
		this.closeEntityManager();
	}

	/**
	 * @generated
	 */
	public void closeEntityManager() {
		javax.persistence.EntityManager entityManager = (javax.persistence.EntityManager) currentEntityManager.get();
		if (entityManager != null && entityManager.isOpen()) {
			entityManager.close();
		}
		currentEntityManager.set(null);
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "EntityManagerHelper";
	}
}