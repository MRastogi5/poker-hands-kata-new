package org.poker.game.main;

import org.poker.game.model.Card;
import org.poker.game.model.Player;
import org.poker.game.model.Rank;
import org.poker.game.model.Suit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    Player player1;
    Player player2;
    //Poker game will have two player- Black, White
    public static void main(String[] args) {
           System.out.println("Hello world!");

        List<Card> cards2 = new ArrayList<>();
        cards2.add(new Card(Rank.FIVE, Suit.Club));
        cards2.add(new Card(Rank.TWO, Suit.Spade));
        cards2.add(new Card(Rank.QUEEN, Suit.Heart));
        cards2.add(new Card(Rank.TEN, Suit.Club));
        cards2.add(new Card(Rank.THREE, Suit.Diamond));
        Player secondPlayer = new Player(cards2);

        //System.out.println();
    }

    public void verifyPokerHands(Player firstPlayer, Player secondPlayer) {
        List<Card> player1Cards= firstPlayer.getCards();
        List<Card> player2Cards= secondPlayer.getCards();
        verifyCards(player1Cards);
        verifyCards(player2Cards);
    }

    private void verifyCards(List<Card> cards) {
        for(Card card : cards){
            if(!Arrays.asList(Rank.values()).contains(card.getRank())){
                throw new IllegalArgumentException("Invalid Values");
            }
            if(!Arrays.asList(Suit.values()).contains(card.getSuit())){
                throw new IllegalArgumentException("Invalid Values");
            }
        }

    }


    public String getWinningPokerHands(Player firstPlayer, Player secondPlayer) {
        //Sort cards
        //get highest card in player cards
        //check sorted cards for Straight flush, Four of a kind, Full House, Flush, Straight, Three of a Kind,
        //Two Pairs, Pair and set their flags



        return "";
    }
}