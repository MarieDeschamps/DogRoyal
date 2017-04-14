package io.dog.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
	PieceDB two = new PieceDB(3, 32);

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
	public void createAndDeleteTest() {
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
	public void updatePieceTest() {
		em.getTransaction().begin();

		// Create Data
		dao.create(two);
		em.getTransaction().commit();

		// Add Piece One to Status True
		em.getTransaction().begin();
		dao.updateStatus(two.getId());
		assertTrue(dao.findById(two.getId()).isReady());
		em.getTransaction().commit();

		// Move this piece to position 10
		em.getTransaction().begin();
		dao.updatePosition(two.getId(), 10);
		assertTrue(dao.findById(two.getId()).getPosition() == 10);
		em.getTransaction().commit();
	}

	@Test
	public void getNbPlayersAndPlayersTest() {
		em.getTransaction().begin();

		// Create Data
		for (int i = 0; i < 2; i++) {
			em.persist(new PieceDB(1, 0));
			em.persist(new PieceDB(2, 16));
		}

		em.getTransaction().commit();

		em.getTransaction().begin();
		int i = dao.getNbPlayers();
		System.out.println(i);
		assertTrue(i == 2);
		em.getTransaction().commit();

		em.getTransaction().begin();

		List<PieceDB> onePiece = dao.getPlayersPieces(1);
		List<PieceDB> twoPiece = dao.getPlayersPieces(2);
		assertTrue(onePiece.size() == 2);
		assertTrue(twoPiece.size() == 2);

		em.getTransaction().commit();

	}

	@Test
	public void findAllTest() {
		em.getTransaction().begin();

		// Create Data
		for (int i = 0; i < 2; i++) {
			em.persist(new PieceDB(1, 0));
			em.persist(new PieceDB(2, 16));
		}

		em.getTransaction().commit();

		em.getTransaction().begin();
		List<PieceDB> all = dao.findAll();
		assertTrue(all.size() > 3);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		dao.deleteAll();
		all = dao.findAll();
		assertTrue(all.isEmpty());
		em.getTransaction().commit();

	}
}
