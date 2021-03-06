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

	public int getGameId(int card_id) {
		return this.findById(card_id).getGame_id();
	}

	public List<CardDB> getPlayersCards(int numberplayer, int game_id) {
		String jpql = "SELECT c FROM CardDB c WHERE c.player = :player AND c.game_id=:game_id ORDER BY c.value";
		return em.createQuery(jpql, CardDB.class).setParameter("player", numberplayer).setParameter("game_id", game_id)
				.getResultList();
	}

	public List<CardDB> getPickablesCards(int game_id) {
		String jpql = "SELECT c FROM CardDB c WHERE (c.pickable = :pickable) AND (c.game_id=:game_id)";
		return em.createQuery(jpql, CardDB.class).setParameter("pickable", true).setParameter("game_id", game_id)
				.getResultList();
	}

	public List<CardDB> getDiguardsCards(int game_id) {
		String jpql = "SELECT c FROM CardDB c WHERE c.pickable = false AND c.player = 0 AND c.game_id=:game_id";
		return em.createQuery(jpql, CardDB.class).setParameter("game_id", game_id).getResultList();
	}

	public void updateNewDeck(int game_id) {
		String jpql = "UPDATE CardDB c SET c.pickable=true WHERE c.player=0 AND c.game_id=:game_id";
		em.createQuery(jpql).setParameter("game_id", game_id).executeUpdate();
	}

	public void deleteAll(int game_id) {
		String jpql = "DELETE FROM CardDB c WHERE c.game_id=:game_id ";
		em.createQuery(jpql).setParameter("game_id", game_id).executeUpdate();
	}

}
