public class Card {
    int id;
    int value;
    boolean type;

    public Card() {
    }

    public Card(int value, boolean type) {
        this.value = value;
        this.type = type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public boolean isType() {
        return type;
    }

    @Override
    public String toString() {
        return value;
    }

}