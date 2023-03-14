package org.poker.game.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Player {
    //Poker game will have two player- Black, White
    // Each player will have 5 Cards called Hand

    private List<Card> cards;
    private Card highestCard;

    public Player(List<Card> cards) {
        this.cards = cards;
        System.out.println(cards);
        Collections.sort(this.cards, Collections.reverseOrder());
        System.out.println(cards);
    }

    public List<Card> getCards() {
        return cards;
    }
    public List<Card> getSortedCards() {
        Collections.sort(this.cards);

        return cards;
    }
}
