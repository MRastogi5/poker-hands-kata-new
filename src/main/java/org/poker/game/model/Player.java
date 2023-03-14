package org.poker.game.model;


import java.util.Collections;
import java.util.List;

public class Player {
    //Poker game will have two player- Black, White
    // Each player will have 5 Cards called Hand

    private List<Card> cards;

    public Card getHighestCard() {
        return highestCard;
    }

    public int getHighestCardRankValue() {
        return highestCard.rank.getCardIntValue();
    }

    public void setHighestCard(Card highestCard) {
        this.highestCard = highestCard;
    }

    private Card highestCard;

    public Player(List<Card> cards) {
        this.cards = cards;
        getSortedCards();
        highestCard = cards.get(cards.size()-1);
    }

    public List<Card> getCards() {
        return cards;
    }
    public List<Card> getSortedCards() {
        Collections.sort(this.cards);
        return cards;
    }
}
