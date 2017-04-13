package io.dog.dao;

import io.dog.entities.CardDB;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * La même transformation est à faire dans PieceDaoTest au niveau de la gestion des transactions.
 *
 * Dans le persistence.xml des tests, peut-être se brancher sur une BDD dédiée au tests
 * Dans laquelle vous pouvez tout effacer.
 *
 * Il existe meme des BDD en mémoire très pratiques pour cela...
 *
 * NE PAS OUBLIER DE FAIRE UN mvn test POUR VERIFIER QUE LES TESTS PASSENT EN LIGNE DE COMMANDE
 */
public class CardDaoTest {
	private static EntityManagerFactory emf;

	CardDB one = new CardDB(2, false);

	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("dogroyal");

		executeInTransaction( new Consumer<CardDao>()
		{
			@Override public void accept( CardDao dao )
			{
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
					dao.updatePickedCards(i + 15, 2);
				}

				dao.updatePickedCards(2, 1);
				dao.updatePickedCards(65, 1);
				dao.updatePickedCards(3, 1);
				dao.updatePickedCards(66, 1);
				dao.updatePickedCards(12, 1);

				// Update 10 cards to disguard
				for (int i = 0; i < 10; i++) {
					dao.updatePickedCards(i + 30, 2);
					dao.updateDisguardCard(i + 30);
				}
			}
		} );
	}

	@AfterClass
	public static void close() {
		emf.close();
	}

	@Test
	public void findAllTest() {
		executeInTransaction( (dao)->{
			List<CardDB> all = dao.findAll();
			assertTrue(all.size() > 75);
		} );
	}

	@Test
	public void createAndDeleteTest() {
		executeInTransaction( (dao)-> {
			dao.create( one );
			assertTrue( one.getId() > 0 );
		});

		executeInTransaction( new Consumer<CardDao>()
		{
			@Override public void accept( CardDao dao )
			{
				dao.delete( one );
				assertEquals( null, dao.findById( one.getId() ) );
			}
		} );
	}

	@Test
	public void updatePickedAndDisguardCardTest() {
		// Check if card 6 of for player one
		executeInTransaction( (dao)-> {
			// COMMENT EXPLIQUER VOUS QUE L'ON DOIT AVOIR PLAYER==1 ? JE N'AI PAS BIEN COMPRIS ET LE TEST ECHOUE
			assertTrue( dao.findById( 6 ).getPlayer() == 1 );
		});

		// Check if card 6 of for disguard
		executeInTransaction( (dao)-> {
			assertTrue( dao.findById( 33 ).getPlayer() == 0 );
		});
	}

	@Test
	public void getPlayersPickablesDisguardsCardsAndNewDeckTest() {
		executeInTransaction( (dao)-> {
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
		});

		executeInTransaction( (dao)-> {
			dao.updateNewDeck();
			List<CardDB> pickable = dao.getPickablesCards();
			List<CardDB> disguard = dao.getDiguardsCards();
			List<CardDB> player1Cards = dao.getPlayersCards(1);
			List<CardDB> player2Cards = dao.getPlayersCards(2);
			assertTrue(disguard.size() == 0);
			assertTrue(pickable.size() == 66);
			assertTrue(player1Cards.size() == 5);
			assertTrue(player2Cards.size() == 5);
		});
	}

	private void executeInTransaction(Consumer<CardDao> consumer) {
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();

			CardDao dao = new CardDao( em );

			consumer.accept( dao );

			em.getTransaction().commit();
		}
		catch(Throwable t){
			em.getTransaction().rollback();
			throw t;
		}
		finally {
			em.close();
		}
	}
}
