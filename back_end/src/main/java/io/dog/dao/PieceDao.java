package io.dog.dao;

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

	public PieceDB updateCard(PieceDB piece) {
		PieceDB pieces = findById(piece.getId());
		piece.setPosition(piece.getPosition());
		piece.setStatus(piece.isStatus());
		return pieces;
	}

}
