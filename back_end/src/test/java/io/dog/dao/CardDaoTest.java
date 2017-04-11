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
	CardDB one = new CardDB(2,false);
	CardDB two = new CardDB(0,true);
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
	public void createAndDelete(){
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
	public void update(){
		em.getTransaction().begin();
		dao.create(one);
		dao.create(two);
		if(!em.contains(one)){
			System.out.println("Need to merge u");
			em.merge(one);
		}
		
		//Add card to player one 
		CardDB newOne = one;
		newOne.setPickable(false);
		newOne.setPlayer(1);
		dao.updateCard(newOne);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		boolean found = dao.findById(one.getId()).isPickable();
		int player = dao.findById(one.getId()).getPlayer();
		assertFalse(found);
		em.getTransaction().commit();
		
		//Add card to disguard
		em.getTransaction().begin();
		newOne = one;
		newOne.setPlayer(0);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		player = dao.findById(one.getId()).getPlayer();
		assertTrue(player == 0);
		em.getTransaction().commit();

		
	}


}
