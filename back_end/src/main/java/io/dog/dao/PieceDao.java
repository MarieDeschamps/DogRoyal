package io.dog.dao;

import java.util.List;

import javax.persistence.EntityManager;

import io.dog.entities.PieceDB;

public class PieceDao {

	EntityManager em;

	public PieceDao(EntityManager em) {
		this.em = em;
	}

	public void create(PieceDB card) {
		em.persist(card);
	}

	public void delete(PieceDB card) {
		em.remove(card);

	}

	public void update(PieceDB card) {

	}

	public PieceDB findById(int id) {
		return em.find(PieceDB.class, id);
	}

	public void updatePiece(int id, int position, boolean ready) {
		PieceDB pieces = findById(id);
		pieces.setPosition(position);
		pieces.setReady(ready);
	}

	public List<PieceDB> getPlayersPieces(int numberplayer) {
		String jpql = "SELECT p FROM PieceDB p WHERE p.player = :player";
		return em.createQuery(jpql, PieceDB.class).setParameter("player", numberplayer).getResultList();
	}

	public List<PieceDB> findAll() {
		String jpql = "SELECT p FROM PieceDB p";
		return em.createQuery(jpql, PieceDB.class).getResultList();
	}

	public int getNbPlayers() {
		String jpql = "select count(distinct p.player) FROM PieceDB p";
		Object r = em.createQuery(jpql).getSingleResult();
		return (int) (long) (Long) r;
	}

	public void deleteAll() {
		String jpql = "DELETE FROM PieceDB";
		em.createQuery(jpql).executeUpdate();
	}
	
	//Methods with Game id
	public int getGameId(int piece_id) {
		return this.findById(piece_id).getGame_id();
	}
	
	public List<PieceDB> getPlayersPieces(int numberplayer,int game_id) {
		String jpql = "SELECT p FROM PieceDB p WHERE p.player = :player AND p.game_id=:game_id";
		return em.createQuery(jpql, PieceDB.class).setParameter("player", numberplayer).setParameter("game_id", game_id).getResultList();
	}

	public List<PieceDB> findAll(int game_id) {
		String jpql = "SELECT p FROM PieceDB p WHERE p.game_id=:game_id";
		return em.createQuery(jpql, PieceDB.class).setParameter("game_id", game_id).getResultList();
	}

	public int getNbPlayers(int game_id) {
		String jpql = "select count(distinct p.player) FROM PieceDB p WHERE p.game_id=:game_id";
		Object r = em.createQuery(jpql).setParameter("game_id", game_id).getSingleResult();
		return (int) (long) (Long) r;
	}

	public void deleteAll(int game_id) {
		String jpql = "DELETE FROM PieceDB p WHERE p.game_id=:game_id";
		em.createQuery(jpql).setParameter("game_id", game_id).executeUpdate();
	}
}
