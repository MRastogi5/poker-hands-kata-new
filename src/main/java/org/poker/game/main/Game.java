package org.poker.game.main;

import org.poker.game.model.Card;
import org.poker.game.model.Player;
import org.poker.game.model.Rank;
import org.poker.game.model.Suit;
import java.util.*;

public class Game {

    //Poker game will have two player- Black, White
    // Assumptions :
    // firstPlayer is Black
    // secondPlayer is White
    public static void main(String[] args) {
        
    }

    public void verifyPokerHands(Player firstPlayer, Player secondPlayer) {
        List<Card> player1Cards = firstPlayer.getCards();
        List<Card> player2Cards = secondPlayer.getCards();
        verifyCards(player1Cards);
        verifyCards(player2Cards);
        
        //Check for same cards
        verifySameCards(player1Cards, player2Cards );
        
        //check for similar cards
        verifySimilarCards(player1Cards, player2Cards );
    }

    private void verifySimilarCards(List<Card> player1Cards, List<Card> player2Cards) {
    }

    private void verifySameCards(List<Card> player1Cards, List<Card> player2Cards) {
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

    //Sort cards - Done
    //get highest card in player cards - Done
    //check sorted cards for Straight flush, Four of a kind, Full House, Flush, Straight, Three of a Kind,
    //Two Pairs, Pair and set their flags and //setHighCard();
    public String getWinningPokerHands(Player firstPlayer, Player secondPlayer) {
        Map<String, Boolean> firstPlayerCombo = checkCombination(firstPlayer);
        Map<String, Boolean> secondPlayerCombo = checkCombination(secondPlayer);
        for (String key : firstPlayerCombo.keySet()) {
            if (firstPlayerCombo.get(key) != secondPlayerCombo.get(key)) {
                return firstPlayerCombo.get(key) ? ("Black wins - with " + key) : ("White wins - with " + key);
            }
            else if (true == firstPlayerCombo.get(key) && firstPlayerCombo.get(key) == secondPlayerCombo.get(key)) {
                if(firstPlayer.getHighestCard().compareTo(secondPlayer.getHighestCard()) == 1){
                    return "Black wins - with " + key;
                }
                else if(firstPlayer.getHighestCard().compareTo(secondPlayer.getHighestCard()) == -1){
                    return "White wins - with " + key;
                }
                return "Tie";
            }
        }
        return getWinningHandByHighCard(firstPlayer, secondPlayer);
    }

    private String getWinningHandByHighCard(Player firstPlayer, Player secondPlayer) {

        for (int i = (firstPlayer.getSortedCards().size() - 1); i > 0; i--) {
            System.out.println("i " + i);
            System.out.println("firstPlayer.getSortedCards().get(i).compareTo(secondPlayer.getSortedCards().get(i))==0 " + (firstPlayer.getSortedCards().get(i).compareTo(secondPlayer.getSortedCards().get(i)) == 0));
            System.out.println("(firstPlayer.getSortedCards().get(i).compareTo(secondPlayer.getSortedCards().get(i))>0) " + (firstPlayer.getSortedCards().get(i).compareTo(secondPlayer.getSortedCards().get(i)) > 0));
            System.out.println("i " + i);
            if (firstPlayer.getSortedCards().get(i).compareTo(secondPlayer.getSortedCards().get(i)) == 0) {
                System.out.println("continue ");
                continue;
            } else if (firstPlayer.getSortedCards().get(i).compareTo(secondPlayer.getSortedCards().get(i)) > 0) {
                return "Black wins - with High Card";//+ firstPlayer.getHighestCard();
            } else {
                return "White wins - with High Card";
            }
        }
        return "Tie";
    }

    public Map<String, Boolean> checkCombination(Player player) {
        List<Card> sortedCards = player.getSortedCards();
        Map<String, Boolean> combinations = getCombinationsObj();

        //System.out.println("(isStraight(sortedCards) : " + isStraight(sortedCards));
        if (isStraightflush(sortedCards)) {
            combinations.put("Straight Flush", true);
            player.setHighestCard(sortedCards.get(sortedCards.size() - 1)); // Last card
        } else if (isFourOfAKind(sortedCards)) {
            combinations.put("Four of a Kind", true);
            player.setHighestCard(sortedCards.get(1)); // Second card
        } else if (isFullHouse(sortedCards)) {
            combinations.put("Full House", true);
            player.setHighestCard(sortedCards.get(sortedCards.size() - 1));//Middle card
        } else if (isFlush(sortedCards)) {
            combinations.put("Flush", true);
            player.setHighestCard(sortedCards.get(sortedCards.size() - 1)); // Last card
        } else if (isStraight(sortedCards)) {
            combinations.put("Straight", true);
            player.setHighestCard(sortedCards.get(sortedCards.size() - 1)); // Last card
        } else if (isThreeOfAKind(sortedCards)) {
            combinations.put("Three Of a Kind", true);
            player.setHighestCard(sortedCards.get(2));//Middle card
        } else if (hasTwoPairs(player)) {
            combinations.put("Two Pairs", true);
        } else if (hasPair(player)) {
            combinations.put("Pair", true);
        }

        return combinations;
    }

    private Map<String, Boolean> getCombinationsObj() {
        Map<String, Boolean> map = new LinkedHashMap <String, Boolean>();
        map.put("Straight Flush", false);
        map.put("Four of a Kind", false);
        map.put("Full House", false);
        map.put("Flush", false);
        map.put("Straight", false);
        map.put("Three Of a Kind", false);
        map.put("Two Pairs", false);
        map.put("Pair", false);

        return map;
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
        for (int i = 1; i < cards.size(); i++) {//[2H, 2D, 2S, 4C, 5H]
            if (cards.get(i).getRank().getCardIntValue() - cards.get(i - 1).getRank().getCardIntValue() == 1) {
                count++;
            }
        }
        return (count == 4);
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
                if (player.getHighestCard() != cards.get(i - 1)) {
                    player.setHighestCard(cards.get(i));
                }
            }
        }
        return pair_count;
    }


}