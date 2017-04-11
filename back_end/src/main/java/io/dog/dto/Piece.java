package io.dog.dto;

public class Piece {
	int id;
	boolean status;
	int initialPosition;
	int position;
	boolean arrived;
	
	public Piece(int id, int initialPosition) {
		this.id = id;
		this.status = false;
		this.arrived = false;
		this.position = 0;
		this.initialPosition = initialPosition;
	}
	
	public Piece(int id, boolean status, int position, boolean arrived, int initialPosition) {
		this.id = id;
		this.status = status;
		this.arrived = arrived;
		this.position = position;
		this.initialPosition = initialPosition;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
		this.position = this.initialPosition;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getId() {
		return id;
	}

	public boolean isArrived() {
		return arrived;
	}

	public void setArrived(boolean arrived) {
		this.arrived = arrived;
	}

	public int getInitialPosition() {
		return initialPosition;
	}

	public void setInitialPosition(int initialPosition) {
		this.initialPosition = initialPosition;
	}
	
	public void resetToBeginning(){
		this.status = false;
		this.arrived = false;
		this.position = 0;
	}
	
}
