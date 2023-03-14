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
        System.out.println("original : "+ cards);


    }

    public List<Card> getCards() {
        return cards;
    }
    public List<Card> getSortedCards() {
        Collections.sort(this.cards);
        highestCard = cards.get(cards.size()-1);
        System.out.println("sorted : "+cards);
        System.out.println("highestCard : "+highestCard);
        return cards;
    }
}
