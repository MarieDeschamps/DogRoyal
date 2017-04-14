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

	public PieceDB() {
	}

	public PieceDB(int player, int position) {
		this.player = player;
		this.position = position;
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
}
