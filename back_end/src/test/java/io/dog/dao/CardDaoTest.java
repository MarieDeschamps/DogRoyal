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
	public void updatePickedAndDisguardCardTest() {
		em.getTransaction().begin();

		// Create Data
		for (int i = 0; i < 7; i++) {

			dao.create(new CardDB(2, false));
			dao.create(new CardDB(3, false));
			dao.create(new CardDB(5, false));
			dao.create(new CardDB(6, false));
			dao.create(new CardDB(8, false));
			dao.create(new CardDB(9, false));
			dao.create(new CardDB(10, false));
			dao.create(new CardDB(12, false));

		}

		for (int i = 0; i < 20; i++) {
			dao.create(new CardDB(0, true));
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

	@Test
	public void getPlayersPickablesDisguardsCardsAndNewDeckTest() {
		em.getTransaction().begin();

		// Create Data
		for (int i = 0; i < 7; i++) {

			dao.create(new CardDB(2, false));
			dao.create(new CardDB(3, false));
			dao.create(new CardDB(5, false));
			dao.create(new CardDB(6, false));
			dao.create(new CardDB(8, false));
			dao.create(new CardDB(9, false));
			dao.create(new CardDB(10, false));
			dao.create(new CardDB(12, false));

		}

		for (int i = 0; i < 20; i++) {
			dao.create(new CardDB(0, true));
		}

		// Update 2 players with 5 cards
		for (int i = 0; i < 5; i++) {
			dao.updatePickedCards(i + 5, 1);
			dao.updatePickedCards(i + 15, 2);
		}

		// Update 10 cards to disguard
		for (int i = 0; i < 10; i++) {
			dao.updatePickedCards(i + 30, 2);
			dao.updateDisguardCard(i + 30);
		}

		em.getTransaction().commit();

		em.getTransaction().begin();
		List<CardDB> pickable = dao.getPickablesCards();
		List<CardDB> disguard = dao.getDiguardsCards();
		List<CardDB> player1Cards = dao.getPlayersCards(1);
		List<CardDB> player2Cards = dao.getPlayersCards(2);

		assertTrue(player1Cards.size() == 5);
		assertTrue(player2Cards.size() == 5);
		assertTrue(disguard.size() == 10);
		assertTrue(pickable.size() == 56);

		em.getTransaction().commit();

		em.getTransaction().begin();
		dao.updateNewDeck();
		pickable = dao.getPickablesCards();
		disguard = dao.getDiguardsCards();
		player1Cards = dao.getPlayersCards(1);
		player2Cards = dao.getPlayersCards(2);
		assertTrue(disguard.size() == 0);
		assertTrue(pickable.size() == 66);
		assertTrue(player1Cards.size() == 5);
		assertTrue(player2Cards.size() == 5);
		em.getTransaction().commit();

	}

	@Test
	public void findAllTest() {
		em.getTransaction().begin();

		// Create Data
		for (int i = 0; i < 7; i++) {

			dao.create(new CardDB(2, false));
			dao.create(new CardDB(3, false));
			dao.create(new CardDB(5, false));
			dao.create(new CardDB(6, false));
			dao.create(new CardDB(8, false));
			dao.create(new CardDB(9, false));
			dao.create(new CardDB(10, false));
			dao.create(new CardDB(12, false));

		}

		for (int i = 0; i < 20; i++) {
			dao.create(new CardDB(0, true));
		}

		// Update 2 players with 5 cards
		for (int i = 0; i < 5; i++) {
			dao.updatePickedCards(i + 5, 1);
			dao.updatePickedCards(i + 15, 2);
		}

		// Update 10 cards to disguard
		for (int i = 0; i < 10; i++) {
			dao.updatePickedCards(i + 30, 2);
			dao.updateDisguardCard(i + 30);
		}

		em.getTransaction().commit();
		
		em.getTransaction().begin();
		List<CardDB> all = dao.findAll();
		assertTrue(all.size() == 76);
		em.getTransaction().commit();

	}

}
