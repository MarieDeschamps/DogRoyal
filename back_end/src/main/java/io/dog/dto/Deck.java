public class Deck {
    int id;
    List<Card> pickable;
    List<Card> disguard;



    public int size() {
        return this.cards.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public Card pick() {
        Card c = this.pickable.get(0);
        this.pickable.remove(0);
        return c;
    }

    public List<Card> pick(int nbCards) {
        List<Card> cardsPicked = new ArrayList<>();
        for (int i = 1; i <= nbCards; i++) {
            if(this.isEmpty()){
                this.suffle();
            }
            cardsPicked.add(this.pick());
        }
        return cardsPicked;
    }

    public void addToDisguard(Card c) {
        this.disguard.add(c);
    }


}