package io.dog.dto;

public class Piece {
	private int id;
	private boolean status;
	private int initialPosition;
	private int position;
	private boolean arrived;
	
	public Piece() {
	}

	public Piece(int id, int initialPosition) {
		this.id = id;
		this.status = false;
		this.arrived = false;
		this.initialPosition = initialPosition;
		this.position = this.initialPosition;
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
		this.setPosition(-1);
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
		this.position = this.initialPosition;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
