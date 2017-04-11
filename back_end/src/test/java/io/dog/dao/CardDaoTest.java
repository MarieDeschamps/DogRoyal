package io.dog.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import io.dog.EmFactory;
import io.dog.entities.CardDB;

public class CardDaoTest {

	EntityManager em;
	CardDao dao;
	CardDB one = new CardDB(2, false);

	@Before
	public void setUp() {
		em = EmFactory.createEntityManager();
		dao = new CardDao(em);
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
	public void updatePickedAndDisguardCard() {
		em.getTransaction().begin();

		// Create Data
		for (int i = 0; i < 7; i++) {
			em.persist(new CardDB(2, false));
			em.persist(new CardDB(3, false));
			em.persist(new CardDB(5, false));
			em.persist(new CardDB(6, false));
			em.persist(new CardDB(8, false));
			em.persist(new CardDB(9, false));
			em.persist(new CardDB(10, false));
			em.persist(new CardDB(12, false));

		}

		for (int i = 0; i < 20; i++) {
			em.persist(new CardDB(0, true));
		}
		em.getTransaction().commit();

		// Player one pick one card
		em.getTransaction().begin();
		dao.updatePickedCards(5, 1);
		assertTrue(dao.findById(5).getPlayer() == 1);
		em.getTransaction().commit();

		// Add this card to disguard
		em.getTransaction().begin();
		dao.updateDisguardCard(5);
		assertTrue(dao.findById(5).getPlayer() == 0);
		em.getTransaction().commit();
	}

}
