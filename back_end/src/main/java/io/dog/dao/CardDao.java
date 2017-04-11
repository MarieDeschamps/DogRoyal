package io.dog.dao;

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
		CardDB cards = findById(id);
		cards.setPlayer(0);
	}

	public void updatePickedCards(int id, int numberplayer) {
		CardDB cards = findById(id);
		cards.setPlayer(numberplayer);
		cards.setPickable(false);
	}

}
