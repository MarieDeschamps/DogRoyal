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

	public void updateStatus(int id) {
		PieceDB pieces = findById(id);
		pieces.setStatus(true);
	}

	public void updatePosition(int id, int position) {
		PieceDB pieces = findById(id);
		pieces.setPosition(position);
	}

	public List<PieceDB> getPlayersPieces(int numberplayer) {
		String jpql = "SELECT p FROM PieceDB p WHERE p.player = :player";
		return em.createQuery(jpql, PieceDB.class).setParameter("player", numberplayer).getResultList();
	}

	public List<PieceDB> findAll() {
		String jpql = "SELECT p FROM PieceDB";
		return em.createQuery(jpql, PieceDB.class).getResultList();
	}

	public int getNbPlayers() {
		String jpql = "select count(distinct p.player) FROM PieceDB p";
		Object r = em.createQuery(jpql).getSingleResult();
		return (int) (long) (Long) r;
	}
}
