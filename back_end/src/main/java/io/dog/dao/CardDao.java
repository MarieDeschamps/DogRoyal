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

	public CardDB updateCard(CardDB card) {
		CardDB cards = findById(card.getId());
		card.setPickable(card.isPickable());
		card.setPlayer(card.getPlayer());
		return cards;
	}

}
