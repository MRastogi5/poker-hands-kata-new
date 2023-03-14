package org.poker.game.model;

public class Card implements Comparable<Card> {
    //Each card have Rank, Suit
    Rank rank;
    Suit suit;

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public int compareTo(Card o) {
        Integer rank = this.rank.getCardIntValue();
        Integer oRank = o.rank.getCardIntValue();
        //Sort from lowest to highest
        return rank.compareTo(oRank);
    }

    @Override
    public String toString(){
        return rank.getCardValue()+suit.getSuitValue();
    }

}
