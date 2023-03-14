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

        Map<String, Boolean> firstPlayerCombo = checkCombination(firstPlayer);
        Map<String, Boolean> secondPlayerCombo = checkCombination(secondPlayer);

        for (String key : firstPlayerCombo.keySet()) {
            if (firstPlayerCombo.get(key) != secondPlayerCombo.get(key)) {
                String result = firstPlayerCombo.get(key) ? "Black wins - with "+ key + " : "+ firstPlayer.getHighestCard().getRank()
                        : "White wins - with "+ key + " : "+ firstPlayer.getHighestCard().getRank();
                return result;
            }
        }
        return getWinningHandbyHighCard(firstPlayer, secondPlayer);

    }

    private static String getWinningHandbyHighCard(Player firstPlayer, Player secondPlayer) {
        if (firstPlayer.getHighestCardRankValue() > secondPlayer.getHighestCardRankValue()) {
            return "Black wins - with high card : " + firstPlayer.getHighestCard();
        } else if (firstPlayer.getHighestCardRankValue() < secondPlayer.getHighestCardRankValue()) {
            return "White wins - with high card : " + secondPlayer.getHighestCard();
        } else {
            return "Tie";
        }
    }

    public Map<String, Boolean> checkCombination(Player player) {
        List<Card> sortedCards = player.getSortedCards();
        Map<String, Boolean> combinations = new HashMap<>();

        if(isStraightflush(sortedCards)){
            combinations.put("Straight flush", isStraightflush(sortedCards));
            player.setHighestCard(sortedCards.get(sortedCards.size()-1)); // Last card
        }
        else if (isFourOfAKind(sortedCards)){
            combinations.put("Four Of A Kind", isFourOfAKind(sortedCards));
            player.setHighestCard(sortedCards.get(1)); // Second card
        }
        else if (isFullHouse(sortedCards)){
            combinations.put("Full House", isFullHouse(sortedCards));
            player.setHighestCard(sortedCards.get(2));//Middle card
        }
        else if (isFlush(sortedCards)){
            combinations.put("Flush", isFlush(sortedCards));
            player.setHighestCard(sortedCards.get(sortedCards.size()-1)); // Last card
        }
        else if (isStraight(sortedCards)){
            combinations.put("Straight", isStraight(sortedCards));
            player.setHighestCard(sortedCards.get(sortedCards.size()-1)); // Last card
        }
        else if (isThreeOfAKind(sortedCards)){
            combinations.put("Three Of A Kind", isThreeOfAKind(sortedCards));
            player.setHighestCard(sortedCards.get(2));//Middle card
        }
        else if (hasTwoPairs(player)){
            combinations.put("Two Pair", hasTwoPairs(player));
        }
        else if(hasPair(player)){
            combinations.put("Pair", hasPair(player));
        }
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

    public boolean hasTwoPairs(Player player) {
        int count = isPair(player);
        if (count == 2)
            return true;
        return false;
    }

    public boolean hasPair(Player player) {
        int count = isPair(player);
        if (count == 1)
            return true;
        return false;
    }

    private int isPair(Player player) {

        int pair_count = 0;
        List<Card> cards = player.getSortedCards();
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i - 1).getRank().equals(cards.get(i).getRank())) {
                pair_count++;
                // if higest card rank is not current card rank, then swap higestCard
                if(player.getHighestCard() != cards.get(i - 1)){
                    player.setHighestCard(cards.get(i));
                }
            }
        }
        return pair_count;
    }


}