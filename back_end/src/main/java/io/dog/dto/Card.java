package io.dog.dto;

public class Card {
    private int id;
    private  int value;
    private  boolean special;

    public Card(int id) {
    	this.id = id;
    }

    public Card(int id, int value, boolean special) {
    	this.id = id;
        this.value = value;
        this.special = special;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public int getValue() {
        return value;
    }

    public boolean isSpecial() {
        return special;
    }

    public int getId() {
		return id;
	}

	@Override
    public String toString() {
        return String.valueOf(value);
    }

}