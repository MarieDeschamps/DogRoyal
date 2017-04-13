package io.dog.dto;

public class Card {
    private int id;
    private  int value;
    
    public Card() {
	}

	public Card(int id) {
    	this.id = id;
    }

    public Card(int id, int value) {
    	this.id = id;
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getId() {
		return id;
	}

	@Override
    public String toString() {
        return String.valueOf(value);
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
		Card other = (Card) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}