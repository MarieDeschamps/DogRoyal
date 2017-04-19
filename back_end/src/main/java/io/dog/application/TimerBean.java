package io.dog.application;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.dog.dao.GameDao;
import io.dog.entities.GameDB;

@Stateless
public class TimerBean {

	@PersistenceContext
	EntityManager em;

	@Schedule(second = "*/30", minute = "*", hour = "*", persistent = false)
	public void atSchedule() throws InterruptedException {
		GameDao gameDao = new GameDao(em);
		List<GameDB> gameList = gameDao.getGames();
		for (GameDB l : gameList) {
			if(!l.isFree() && l.getLastRequest().before(new Timestamp(System.currentTimeMillis()-30*1000))){
				gameDao.updateFree(l.getGame_id(),l.getPlayer(),true);
			}
		}
	}
}