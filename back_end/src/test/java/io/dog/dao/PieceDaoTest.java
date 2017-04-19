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

import io.dog.entities.PieceDB;

public class PieceDaoTest {
	private static EntityManagerFactory emf;

	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("dogroyal");

		executeInTransaction(new Consumer<PieceDao>() {
			@Override
			public void accept(PieceDao dao) {
				int game_id = 300;
				// Create Data
				
				dao.create(new PieceDB(1, 0, game_id));
				dao.create(new PieceDB(1, 0, game_id));
				dao.create(new PieceDB(2, 16, game_id));
				dao.create(new PieceDB(2, 16, game_id));
			}
		});
	}

	@AfterClass
	public static void close() {
		emf.close();
	}
@Test
public void updatePieceTest(){
	executeInTransaction((dao) -> {
		dao.updatePiece(2, 0, true);
		assertTrue(dao.findById(2).isReady());
		dao.updatePiece(2, 4, true);
		assertEquals(dao.findById(2).getPosition(),4);
	});
}
	@Test
	public void getPlayersTest(){
		executeInTransaction((dao) -> {
			int nbPlayers = dao.getNbPlayers(300);
			assertEquals(2, nbPlayers);
			List<PieceDB> listPieces = dao.getPlayersPieces(2, 300);
			assertTrue(listPieces.contains(dao.findById(3)));
			assertEquals(2,listPieces.size());			
		});
	}

	@Test
	public void deleteTest() {
		executeInTransaction((dao) -> {
			dao.deleteAll(300);
			assertEquals(0, dao.getNbPlayers(300));
			assertTrue(dao.getPlayersPieces(1, 300).isEmpty());
		});
	}

	private void executeInTransaction(Consumer<PieceDao> consumer) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();

			PieceDao dao = new PieceDao(em);

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
