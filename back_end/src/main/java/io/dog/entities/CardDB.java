package io.dog.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CardDB {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id = null;

	int value;
	int game_id = 0;
	boolean pickable = true;
	int player = 0;

	public CardDB() {

	}

	public CardDB(int value, int game_id) {
		this.value = value;
		this.game_id = game_id;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public boolean isPickable() {
		return pickable;
	}

	public void setPickable(boolean pickable) {
		this.pickable = pickable;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

}
