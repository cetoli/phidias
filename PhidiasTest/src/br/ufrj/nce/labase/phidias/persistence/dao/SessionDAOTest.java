package br.ufrj.nce.labase.phidias.persistence.dao;

import junit.framework.TestCase;

public class SessionDAOTest extends TestCase {

	public void testFindByStatus() {
		SessionDAO dao = new SessionDAO();
		dao.findByStatus(1);
	}

	public void testFindDeadSession() {
		fail("Not yet implemented");
	}

	public void testRemoveDeadSessions() {
		fail("Not yet implemented");
	}

	public void testFindCurrentPatientSessionId() {
		fail("Not yet implemented");
	}

	public static void main(String[] args){
		SessionDAO dao = new SessionDAO();
		dao.findByStatus(1);
	}
}
