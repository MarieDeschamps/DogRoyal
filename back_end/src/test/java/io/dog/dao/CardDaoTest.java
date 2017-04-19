package io.dog.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import io.dog.entities.CardDB;

public class CardDaoTest {
	private static EntityManagerFactory emf;

	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("dogroyal");

		executeInTransaction(new Consumer<CardDao>() {
			@Override
			public void accept(CardDao dao) {
				int game_id = 300;
				// Create Data
				for (int i = 0; i < 7; i++) {

					dao.create(new CardDB(2, game_id));
					dao.create(new CardDB(3, game_id));
					dao.create(new CardDB(5, game_id));
					dao.create(new CardDB(6, game_id));
					dao.create(new CardDB(8, game_id));
					dao.create(new CardDB(9, game_id));
					dao.create(new CardDB(10, game_id));
					dao.create(new CardDB(12, game_id));

				}

				for (int i = 0; i < 20; i++) {
					dao.create(new CardDB(0, game_id));
				}
			}
		});
	}

	@AfterClass
	public static void close() {
		emf.close();
	}

	@Test
	public void getGameIdtest() {
		executeInTransaction((dao) -> {
			int game_id = dao.getGameId(5);
			assertEquals(game_id, 300);
		});
	}

	@Test
	public void updateCardTest() {
		executeInTransaction((dao) -> {
			for (int i = 1; i < 6; i++) {
				dao.updatePickedCards(i, 1);
				dao.updatePickedCards(i + 10, 2);
			}

			for (int i = 0; i < 10; i++) {
				dao.updatePickedCards(i + 40, 0);
				dao.updateDisguardCard(i + 40);
			}

			List<CardDB> player1 = dao.getPlayersCards(1, 300);
			List<CardDB> player2 = dao.getPlayersCards(2, 300);
			List<CardDB> disguard = dao.getDiguardsCards(300);
			List<CardDB> pickable = dao.getPickablesCards(300);

			assertEquals(player1.size(), 5);
			assertEquals(player2.size(), 5);
			assertEquals(disguard.size(), 10);
			assertEquals(pickable.size(), 56);

			dao.updateNewDeck(300);
			disguard = dao.getDiguardsCards(300);
			pickable = dao.getPickablesCards(300);
			assertEquals(disguard.size(), 0);
			assertEquals(pickable.size(), 66);

		});
	}

	@Test
	public void deleteTest() {
		executeInTransaction((dao) -> {
			assertEquals(dao.getPickablesCards(300).size(), 76);
			dao.deleteAll(300);
			List<CardDB> disguard = dao.getDiguardsCards(300);
			List<CardDB> pickable = dao.getPickablesCards(300);
			assertTrue(disguard.isEmpty());
			assertTrue(pickable.isEmpty());
		});
	}

	private void executeInTransaction(Consumer<CardDao> consumer) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();

			CardDao dao = new CardDao(em);

			consumer.accept(dao);

			em.getTransaction().commit();
		} catch (Throwable t) {
			em.getTransaction().rollback();
			throw t;
		} finally {
			em.close();
		}
	}
}