package org.poker.game.model;

import java.util.Collections;
import java.util.List;

public class Player {
    //Poker game will have two player- Black, White
    // Each player will have 5 Cards called Hand

    private List<Card> cards;

    public Player(List<Card> cards) {
        this.cards = cards;
    }


}
