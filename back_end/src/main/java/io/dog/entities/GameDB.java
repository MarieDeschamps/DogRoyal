package io.dog.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GameDB {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id = null;

	int game_id = 0;
	int player;
	boolean free = true;

	public GameDB() {
	}

	public GameDB(int game_id, int player) {
		this.game_id = game_id;
		this.player = player;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}
	
	

}
