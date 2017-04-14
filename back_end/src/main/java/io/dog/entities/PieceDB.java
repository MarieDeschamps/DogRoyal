package io.dog.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PieceDB {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id = null;

	int player;
	int position;
	boolean ready = false;
	int game_id = 0;

	public PieceDB() {
	}

	public PieceDB(int player, int position) {
		this.player = player;
		this.position = position;
	}
	
	public PieceDB(int player, int position, int game_id) {
		this.player = player;
		this.position = position;
		this.game_id = game_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean status) {
		this.ready = status;
	}

	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}
	
	
}
