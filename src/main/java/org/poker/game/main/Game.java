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

        //Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH
        List<Card> cards1 = new ArrayList<>();
        cards1.add(new Card(Rank.TWO, Suit.Heart));
        cards1.add(new Card(Rank.TWO, Suit.Diamond));
        cards1.add(new Card(Rank.FIVE, Suit.Spade));
        cards1.add(new Card(Rank.NINE, Suit.Club));
        cards1.add(new Card(Rank.KING, Suit.Diamond));
        Player firstPlayer = new Player(cards1);

        List<Card> cards2 = new ArrayList<>();
        cards2.add(new Card(Rank.TWO, Suit.Spade));
        cards2.add(new Card(Rank.ACE, Suit.Diamond));
        cards2.add(new Card(Rank.FIVE, Suit.Spade));
        cards2.add(new Card(Rank.NINE, Suit.Diamond));
        cards2.add(new Card(Rank.KING, Suit.Club));

        Player secondPlayer = new Player(cards2);
        Game game = new Game();

        System.out.println("Winning player : " + game.getWinningPokerHands(firstPlayer, secondPlayer));
    }

    public void verifyPokerHands(Player firstPlayer, Player secondPlayer) {
        List<Card> player1Cards = firstPlayer.getCards();
        List<Card> player2Cards = secondPlayer.getCards();
        verifyCards(player1Cards);
        verifyCards(player2Cards);
    }

    private void verifyCards(List<Card> cards) {
        for (Card card : cards) {
            if (!Arrays.asList(Rank.values()).contains(card.getRank())) {
                throw new IllegalArgumentException("Invalid Values");
            }
            if (!Arrays.asList(Suit.values()).contains(card.getSuit())) {
                throw new IllegalArgumentException("Invalid Values");
            }
        }
    }

    public String getWinningPokerHands(Player firstPlayer, Player secondPlayer) {
        //Sort cards - Done
        //get highest card in player cards - Done
        //check sorted cards for Straight flush, Four of a kind, Full House, Flush, Straight, Three of a Kind,
        //Two Pairs, Pair and set their flags and //setHighCard();
        //List<Card> sortedCards = firstPlayer.getSortedCards();
        Map<String, Boolean> firstPlayerCombi = checkCombination(firstPlayer.getSortedCards());
        Map<String, Boolean> secondPlayerCombi = checkCombination(secondPlayer.getSortedCards());

        for (String key : firstPlayerCombi.keySet()) {
            System.out.println(" First key : " + key + " value : " + firstPlayerCombi.get(key));
            System.out.println("Second key : " + key + " value : " + secondPlayerCombi.get(key));
            if (firstPlayerCombi.get(key) != secondPlayerCombi.get(key)) {
                String result = firstPlayerCombi.get(key) ? "first wins card" : "second wins card";
                return result;
            }
        }
        return getWinnerbyHighCard(firstPlayer, secondPlayer);

    }

    private static String getWinnerbyHighCard(Player firstPlayer, Player secondPlayer) {
        if (firstPlayer.getHighestCardRankValue() > secondPlayer.getHighestCardRankValue()) {
            return "first wins";
        } else if (firstPlayer.getHighestCardRankValue() < secondPlayer.getHighestCardRankValue()) {
            return "second wins";
        } else {
            return "tie";
        }
    }

    public Map<String, Boolean> checkCombination(List<Card> sortedCards) {
        Map<String, Boolean> combinations = new HashMap<>();
        combinations.put("isStraightflush", isStraightflush(sortedCards));
        combinations.put("isFourOfAKind", isFourOfAKind(sortedCards));
        combinations.put("isFullHouse", isFullHouse(sortedCards));
        combinations.put("isFlush", isFlush(sortedCards));//isFlush
        combinations.put("isStraight", isStraight(sortedCards));
        combinations.put("hasThreeOfAKind", isThreeOfAKind(sortedCards));
        combinations.put("hasTwoPair", hasTwoPairs(sortedCards));
        combinations.put("hasPair", hasPair(sortedCards));
        //hasHignCard(black,white);
        return combinations;
    }


    public Boolean isStraightflush(List<Card> cards) {
        //5 cards of the same suit with consecutive values : Q♥ J♥ 10♥ 9♥ 8♥
        if (isStraight(cards) && isFlush(cards))
            return true;
        return false;
    }

    public Boolean isFourOfAKind(List<Card> cards) {
        //4 cards with the same value : 9♣ 9♠ 9♦ 9♥ J♥

        int i = 0;
        if (cards.get(i).getRank() == cards.get(i + 1).getRank()
                && cards.get(i + 1).getRank() == cards.get(i + 2).getRank()
                && cards.get(i + 2).getRank() == (cards.get(i + 3).getRank())) {
            return true;
        } else if (cards.get(i + 1).getRank() == cards.get(i + 2).getRank()
                && cards.get(i + 2).getRank() == (cards.get(i + 3).getRank())
                && cards.get(i + 3).getRank() == cards.get(i + 4).getRank()) {
            return true;
        }
        return false;
    }

    public Boolean isFullHouse(List<Card> cards) {
        //3 cards of the same value, with the remaining 2 cards forming a pair.  3♣ 3♠ 3♦ 6♣ 6♥  or 8♠ 8♦ 8♥ 7♦ 7♣
        int i = 0;
        if ((cards.get(i).getRank() == cards.get(i + 1).getRank()
                && cards.get(i + 1).getRank() == cards.get(i + 2).getRank())
                && cards.get(i + 3).getRank().equals(cards.get(i + 4).getRank())) {
            return true;
        } else if ((cards.get(i).getRank().equals(cards.get(i + 1).getRank())) && (cards.get(i + 2).getRank() == cards.get(i + 3).getRank()
                && cards.get(i + 3).getRank() == cards.get(i + 4).getRank())) {
            return true;
        }


        return false;
    }

    public Boolean isFlush(List<Card> cards) {
        //Hand contains 5 cards of the same suit
        int count = 0;
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getSuit().equals(cards.get(i - 1).getSuit())) {
                count++;
            }
        }
        return count == 4;
    }

    public Boolean isStraight(List<Card> cards) {
        //Hand contains 5 cards with consecutive values.
        int count = 0;
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getRank().getCardIntValue() - cards.get(i - 1).getRank().getCardIntValue() == 1) {
                count++;
            }
        }
        return count == 4;
    }

    public boolean isThreeOfAKind(List<Card> cards) {
        //Three of the cards in the hand have the same value
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i - 1).getRank() == cards.get(i).getRank()
                    && cards.get(i).getRank() == cards.get(i + 1).getRank()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasTwoPairs(List<Card> cards) {
        int count = isPair(cards);
        if (count == 2)
            return true;
        return false;
    }

    public boolean hasPair(List<Card> cards) {
        int count = isPair(cards);
        if (count == 1)
            return true;
        return false;
    }

    private int isPair(List<Card> cards) {
        int pair_count = 0;
        System.out.println("isPair : " + cards);
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i - 1).getRank().equals(cards.get(i).getRank())) {
                pair_count++;
            }
        }
        System.out.println("pair_count " + pair_count);
        return pair_count;
    }


}