package io.dog.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.FREE_MEM;

import io.dog.entities.GameDB;

public class GameDaoTest {
	private static EntityManagerFactory emf;

	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("dogroyal");

		executeInTransaction(new Consumer<GameDao>() {
			@Override
			public void accept(GameDao dao) {
				int game_id = 300;

				dao.create(new GameDB(game_id, 1));
				dao.create(new GameDB(game_id, 2));
				dao.create(new GameDB(game_id, 3));
				dao.create(new GameDB(game_id, 4));
				dao.create(new GameDB(game_id + 1, 1));
				dao.create(new GameDB(game_id + 1, 2));

			}
		});
	}

	@AfterClass
	public static void close() {
		emf.close();
	}

	@Test
	public void updateFreeTest() {
		executeInTransaction((dao) -> {
			GameDB game = dao.findById(1);
			assertTrue(game.isFree());
			dao.updateFree(300, 1, false);
			List<GameDB> free300= dao.getFreeGame(300);
			assertTrue(free300.size() == 3);
		});
	}

	@Test
	public void getGameStatusTest() {
		executeInTransaction((dao) -> {
			List<GameDB> gameList = dao.getGameStatus(300);
			assertTrue(gameList.contains(dao.findById(3)));
			assertEquals(4, gameList.size());
		});
	}

	@Test
	public void getGamesTest() {
		executeInTransaction((dao) -> {
			List<GameDB> gameList = dao.getGames();
			assertTrue(gameList.contains(dao.findById(5)));
			assertEquals(6, gameList.size());
			assertEquals(2, dao.getGameIds().size());
		});
	}

	@Test
	public void getFreeTest() {
		executeInTransaction((dao) -> {
			dao.updateFree(300, 1, false);
			dao.updateFree(300, 3, false);
			dao.updateFree(301, 2, false);
			List<GameDB> freeGames = dao.getFreeGames();
			List<GameDB> free301 = dao.getFreeGame(301);
			assertTrue(freeGames.contains(dao.findById(2)));
			assertTrue(freeGames.contains(dao.findById(5)));
			assertEquals(3, freeGames.size());

			assertTrue(free301.contains(dao.findById(5)));
			assertEquals(1, free301.size());

		});
	}

	@Test
	public void deleteTest() {
		executeInTransaction((dao) -> {
			dao.deleteAll(300);
			assertTrue(dao.getGameStatus(300).isEmpty());
			assertEquals(2, dao.getGames().size());
		});
	}

	private void executeInTransaction(Consumer<GameDao> consumer) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();

			GameDao dao = new GameDao(em);

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
