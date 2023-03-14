package org.poker.game.main;

import org.poker.game.model.Card;
import org.poker.game.model.Player;
import org.poker.game.model.Rank;
import org.poker.game.model.Suit;

import java.util.*;

public class Game {

    Player player1;
    Player player2;
    //Poker game will have two player- Black, White
    public static void main(String[] args) {
           System.out.println("Hello world!");

        List<Card> cards2 = new ArrayList<>();
        cards2.add(new Card(Rank.QUEEN, Suit.Heart));
        cards2.add(new Card(Rank.ACE, Suit.Club));
        cards2.add(new Card(Rank.KING, Suit.Spade));
        cards2.add(new Card(Rank.THREE, Suit.Diamond));
        cards2.add(new Card(Rank.TEN, Suit.Club));

        Player secondPlayer = new Player(cards2);
        secondPlayer.getSortedCards();
        /*List<Card> sortedCards = secondPlayer.getSortedCards();
        System.out.println("sortedCards : "+ sortedCards);*/
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
        //Sort cards - Done
        //get highest card in player cards - Done
        //check sorted cards for Straight flush, Four of a kind, Full House, Flush, Straight, Three of a Kind,
        //Two Pairs, Pair and set their flags
        List<Card> sortedcards = firstPlayer.getSortedCards();
        checkCombination(firstPlayer);




        return "";
    }

    public void checkCombination(Player firstPlayer) {
        Map<String, Boolean> combinations = new HashMap<>();
        //straightFlush
        //isStraightflush
        //isFourOfAKind
        //isFullHouse
        combinations.put("isFlush",isFlush(firstPlayer));//isFlush
        combinations.put("isStraight",isStraight(firstPlayer));
        combinations.put("hasThreeOfAKind",isThreeOfAKind(firstPlayer));
        combinations.put("hasTwoPair",hasTwoPairs(firstPlayer) );
        combinations.put("hasPair",hasPair(firstPlayer) );
        //hasHignCard(black,white);
    }

    public Boolean isFlush(Player firstPlayer) {
        //Hand contains 5 cards of the same suit

        return false;
    }

    public Boolean isStraight(Player player) {
        boolean straightFlag = false;
        List<Card> cards = player.getCards();
       // cards.get(4).getRank().getCardIntValue()
        for(int i = 1; i<cards.size();i++){
            if(cards.get(i).getRank().getCardIntValue()-cards.get(i-1).getRank().getCardIntValue() == 1){
                straightFlag = true;
            }
        }
        return straightFlag;
    }

    public boolean isThreeOfAKind(Player player) {
        //Three of the cards in the hand have the same value
        List<Card> cards = player.getCards();
        for(int i = 1; i<cards.size();i++){
            if(cards.get(i-1).getRank()==cards.get(i).getRank()
                    && cards.get(i).getRank()==cards.get(i+1).getRank()){
                return true;
            }
        }
        return false;
    }

    public boolean  hasTwoPairs(Player firstPlayer) {
        int count = isPair(firstPlayer);
        if(count == 2)
            return true;
        return false;
    }
    public boolean hasPair(Player firstPlayer) {
        int count = isPair(firstPlayer);
        if(count == 1)
            return true;
        return false;
    }

    private int isPair(Player player) {
        List<Card> cards = player.getCards();
        int pair_count = 0;
        System.out.println("isPair : " + cards);
        for(int i = 1; i<cards.size();i++){
            if(cards.get(i-1).getRank().equals(cards.get(i).getRank())){
                pair_count++ ;
            }
        }
        System.out.println("pair_count " + pair_count);
        return pair_count;
    }





}