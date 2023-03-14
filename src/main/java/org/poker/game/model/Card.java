package org.poker.game.model;

public class Card {
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

}
