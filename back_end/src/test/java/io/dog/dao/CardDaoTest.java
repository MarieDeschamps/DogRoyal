package io.dog.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import io.dog.EmFactory;
import io.dog.entities.CardDB;

public class CardDaoTest {

	EntityManager em;
	CardDao dao;
	CardDB one = new CardDB(2, 0);
	CardDB test = new CardDB(2, 0);
	CardDB test2 = new CardDB(2, 0);

	@Before
	public void setUp() {
		em = EmFactory.createEntityManager();
		dao = new CardDao(em);
		em.getTransaction().begin();
		// Create Data
		for (int i = 0; i < 7; i++) {

			dao.create(new CardDB(2, 0));
			dao.create(new CardDB(3, 0));
			dao.create(new CardDB(5, 0));
			dao.create(new CardDB(6, 0));
			dao.create(new CardDB(8, 0));
			dao.create(new CardDB(9, 0));
			dao.create(new CardDB(10, 0));
			dao.create(new CardDB(12, 0));

		}

		for (int i = 0; i < 20; i++) {
			dao.create(new CardDB(0, 0));
		}

		// Update 2 players with 5 cards
		for (int i = 0; i < 5; i++) {
			dao.updatePickedCards(i + 15, 2);
		}

			dao.updatePickedCards(6, 1);
			dao.updatePickedCards(65, 1);
			dao.updatePickedCards(3, 1);
			dao.updatePickedCards(66, 1);
			dao.updatePickedCards(12, 1);

		// Update 10 cards to disguard
		for (int i = 0; i < 10; i++) {
			dao.updatePickedCards(i + 30, 2);
			dao.updateDisguardCard(i + 30);
		}
		em.getTransaction().commit();
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
	public void findAllTest() {
		em.getTransaction().begin();
		List<CardDB> all = dao.findAll();
		assertTrue(all.size() > 75);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		dao.deleteAll();
		all = dao.findAll();
		assertTrue(all.isEmpty());
		em.getTransaction().commit();


	}

	@Test
	public void createAndDeleteTest() {
		em.getTransaction().begin();
		dao.create(test);
		assertTrue(test.getId() > 0);
		em.getTransaction().commit();

		em.getTransaction().begin();
		dao.delete(test);
		assertEquals(null, dao.findById(test.getId()));
		em.getTransaction().commit();

	}

	@Test
	public void updatePickedAndDisguardCardTest() {

		// Check if card 6 of for player one
		em.getTransaction().begin();
		assertTrue(dao.findById(6).getPlayer() == 1);
		em.getTransaction().commit();

		// Check if card 6 of for disguard
		em.getTransaction().begin();
		assertTrue(dao.findById(33).getPlayer() == 0);
		em.getTransaction().commit();
	}

	@Test
	public void getPlayersPickablesDisguardsCardsAndNewDeckTest() {

		em.getTransaction().begin();
		List<CardDB> pickable = dao.getPickablesCards();
		List<CardDB> disguard = dao.getDiguardsCards();
		List<CardDB> player1Cards = dao.getPlayersCards(1);
		List<CardDB> player2Cards = dao.getPlayersCards(2);

		for (CardDB cardDB : player1Cards) {
			System.out.println(cardDB.getId() + "-- value -- "+cardDB.getValue());
			
		}
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

}
