package io.dog.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;

import io.dog.entities.GameDB;

public class GameDao {

	EntityManager em;

	public GameDao(EntityManager em) {
		this.em = em;
	}

	public void create(GameDB game) {
		em.persist(game);
	}

	public void deleteAll() {
		String jpql = "DELETE FROM GameDB g";
		em.createQuery(jpql).executeUpdate();
	}

	public void updateFree(int game_id, int player, boolean free) {
		String jpql = "UPDATE GameDB g SET g.free=:free, g.lastRequest=:lastRequest WHERE g.game_id=:game_id AND g.player=:player";
		em.createQuery(jpql).setParameter("free", free)
				.setParameter("lastRequest", new Timestamp(System.currentTimeMillis())).setParameter("game_id", game_id)
				.setParameter("player", player).executeUpdate();
	}

	public List<GameDB> getGameStatus(int game_id) {
		String jpql = "SELECT g FROM GameDB g WHERE g.game_id =:game_id";
		return em.createQuery(jpql, GameDB.class).setParameter("game_id", game_id).getResultList();
	}

	public List<GameDB> getGames() {
		String jpql = "SELECT g FROM GameDB g ORDER BY g.game_id";
		return em.createQuery(jpql, GameDB.class).getResultList();
	}

	public void deleteAll(int game_id) {
		String jpql = "DELETE FROM GameDB g WHERE g.game_id =:game_id";
		em.createQuery(jpql).setParameter("game_id", game_id).executeUpdate();
	}

	public GameDB findById(int id) {
		return em.find(GameDB.class, id);
	}

	public List<GameDB> getFreeGames() {
		String jpql = "SELECT g FROM GameDB g WHERE g.free=true ORDER BY g.game_id";
		return em.createQuery(jpql, GameDB.class).getResultList();
	}

	public List<Integer> getGameIds() {
		String jpql = "Select DISTINCT g.game_id FROM GameDB g";
		return em.createQuery(jpql, Integer.class).getResultList();
	}

	public List<GameDB> getFreeGame(int game_id) {
		String jpql = "SELECT g FROM GameDB g WHERE g.free=true AND g.game_id=:game_id ORDER BY g.game_id";
		return em.createQuery(jpql, GameDB.class).setParameter("game_id", game_id).getResultList();
	}

}
