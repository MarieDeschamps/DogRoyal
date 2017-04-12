package io.dog.dao;

import java.util.List;

import javax.persistence.EntityManager;

import io.dog.entities.CardDB;

public class CardDao {

	EntityManager em;

	public CardDao(EntityManager em) {
		this.em = em;
	}

	public void create(CardDB card) {
		em.persist(card);
	}

	public void delete(CardDB card) {
		em.remove(card);

	}

	public void update(CardDB card) {

	}

	public CardDB findById(int id) {
		return em.find(CardDB.class, id);
	}

	public void updateDisguardCard(int id) {
		CardDB cards = this.findById(id);
		cards.setPlayer(0);
	}

	public void updatePickedCards(int id, int numberplayer) {
		CardDB cards = this.findById(id);
		cards.setPlayer(numberplayer);
		cards.setPickable(false);
	}

	public List<CardDB> getPlayersCards(int numberplayer) {
		String jpql = "SELECT c FROM CardDB c WHERE c.player = :player";
		return em.createQuery(jpql, CardDB.class).setParameter("player", numberplayer).getResultList();
	}

	public List<CardDB> getPickablesCards() {
		String jpql = "SELECT c FROM CardDB c WHERE c.pickable = :pickable";
		return em.createQuery(jpql, CardDB.class).setParameter("pickable", true).getResultList();
	}

	public List<CardDB> getDiguardsCards() {
		String jpql = "SELECT c FROM CardDB c WHERE c.pickable = false AND c.player = 0";
		return em.createQuery(jpql, CardDB.class).getResultList();
	}

	public List<CardDB> findAll() {
		String jpql = "SELECT * FROM CardDB c";
		return em.createQuery(jpql, CardDB.class).getResultList();

	}

	public void updateNewDeck() {
		String jpql = "UPDATE CardDB c SET c.pickable=1 WHERE c.player=0";
		em.createQuery(jpql, CardDB.class);
	}
}
