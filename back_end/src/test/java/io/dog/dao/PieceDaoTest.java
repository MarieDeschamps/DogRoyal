package io.dog.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import io.dog.EmFactory;
import io.dog.entities.PieceDB;

public class PieceDaoTest {
	EntityManager em;
	PieceDao dao;
	PieceDB one = new PieceDB(2, 16);

	@Before
	public void setUp() {
		em = EmFactory.createEntityManager();
		dao = new PieceDao(em);
	}

	@After
	public void tearDown() {
		if (em.isOpen()) {
			em.close();
		}
	}

	@AfterClass
	public static void close() {
		EmFactory.getInstance().close();
	}

	@Test
	public void createAndDelete() {
		em.getTransaction().begin();
		dao.create(one);
		assertTrue(one.getId() > 0);
		em.getTransaction().commit();

		em.getTransaction().begin();
		dao.delete(one);
		assertEquals(null, dao.findById(one.getId()));
		em.getTransaction().commit();

	}

	@Test
	public void updatePiece() {
		em.getTransaction().begin();

		// Create Data
		for (int i = 0; i < 2; i++) {
			em.persist(new PieceDB(1, 0));
			em.persist(new PieceDB(2, 16));
		}

		em.getTransaction().commit();

		// Add Piece One to Status True
		em.getTransaction().begin();
		dao.updateStatus(1);
		assertTrue(dao.findById(1).isStatus());
		em.getTransaction().commit();

		// Move this piece to position 10
		em.getTransaction().begin();
		dao.updatePosition(1, 10);
		assertTrue(dao.findById(1).getPosition() == 10);
		em.getTransaction().commit();
	}
}
