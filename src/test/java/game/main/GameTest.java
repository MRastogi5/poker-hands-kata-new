package game.main;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.poker.game.main.Game;
import org.poker.game.model.Card;
import org.poker.game.model.Player;
import org.poker.game.model.Rank;
import org.poker.game.model.Suit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class GameTest {
    Game game = new Game();


    @ParameterizedTest
    @CsvFileSource(resources = "/poker-hands-test-data.csv", numLinesToSkip = 1)
    //@CsvSource({"2H 4S 4C 2D 4H","2S 8S AS QS 3S","Black wins. - with full house: 4 over 2"})
    public void testGame(String firstHand, String secondHand, String result){

        Player firstPlayer = getPlayerObjFromInput(firstHand);
        Player secondPlayer = getPlayerObjFromInput(secondHand);

        Game game = new Game();
        assertEquals(result, game.getWinningPokerHands(firstPlayer, secondPlayer));

    }

    private static Player getPlayerObjFromInput(String firstPlayer) {
        List<Card> inputCards = new ArrayList<>();
        String[] cardsArr = firstPlayer.split(" ");//[2H, 3D, 5S, 9C, KD]
        for(String s : cardsArr){
            Card card = new Card(Rank.fromValue(s.substring(0,s.length()-1)), Suit.fromValue(s.substring(s.length()-1)));
            inputCards.add(card);
        }
        Player player = new Player(inputCards);
        System.out.println(player.getCards()+ " : "+player.getSortedCards());
        return player;
    }


    @Test
    void testIsStraightFlush(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TEN, Suit.Heart));
        cards.add(new Card(Rank.JACK, Suit.Heart));
        cards.add(new Card(Rank.QUEEN, Suit.Heart));
        cards.add(new Card(Rank.KING, Suit.Heart));
        cards.add(new Card(Rank.ACE, Suit.Heart));
        Player p = new Player(cards);

        assertEquals(true,  game.isStraightflush(p.getSortedCards()));
    }
    @Test
    void testIsNotStraightFlush(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TEN, Suit.Heart));
        cards.add(new Card(Rank.JACK, Suit.Heart));
        cards.add(new Card(Rank.QUEEN, Suit.Heart));
        cards.add(new Card(Rank.KING, Suit.Heart));
        cards.add(new Card(Rank.ACE, Suit.Diamond));
        Player p = new Player(cards);
        assertEquals(false,  game.isStraightflush(p.getSortedCards()));
    }

    @Test
    void testIsFourOfAKind(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.KING, Suit.Heart));
        cards.add(new Card(Rank.KING, Suit.Diamond));
        cards.add(new Card(Rank.TEN, Suit.Spade));
        cards.add(new Card(Rank.KING, Suit.Club));
        cards.add(new Card(Rank.KING, Suit.Diamond));
        Player p = new Player(cards);
        assertEquals(true,
                game.isFourOfAKind(p.getSortedCards()));

    }
    @Test
    void testIsFourOfAKind2(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TEN, Suit.Heart));
        cards.add(new Card(Rank.TEN, Suit.Diamond));
        cards.add(new Card(Rank.TEN, Suit.Spade));
        cards.add(new Card(Rank.TEN, Suit.Club));
        cards.add(new Card(Rank.KING, Suit.Diamond));
        Player p = new Player(cards);
        assertEquals(true,
                game.isFourOfAKind(p.getSortedCards()));

    }

    @Test
    void testIsNotFourOfAKind(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TEN, Suit.Heart));
        cards.add(new Card(Rank.TEN, Suit.Diamond));
        cards.add(new Card(Rank.TEN, Suit.Spade));
        cards.add(new Card(Rank.KING, Suit.Club));
        cards.add(new Card(Rank.KING, Suit.Diamond));
        Player p = new Player(cards);
        assertEquals(false,
                game.isFourOfAKind(p.getSortedCards()));

    }
    @Test
    void testIsFullHouse(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TEN, Suit.Heart));
        cards.add(new Card(Rank.TEN, Suit.Diamond));
        cards.add(new Card(Rank.TEN, Suit.Spade));
        cards.add(new Card(Rank.KING, Suit.Club));
        cards.add(new Card(Rank.KING, Suit.Diamond));
        Player p = new Player(cards);
        assertEquals(true,  game.isFullHouse(p.getSortedCards()));
    }
    @Test
    void testIsFullHouse2(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TEN, Suit.Heart));
        cards.add(new Card(Rank.TEN, Suit.Diamond));
        cards.add(new Card(Rank.KING, Suit.Spade));
        cards.add(new Card(Rank.KING, Suit.Club));
        cards.add(new Card(Rank.KING, Suit.Diamond));
        Player p = new Player(cards);
        assertEquals(true,
                game.isFullHouse(p.getSortedCards()));

    }
    @Test
    void testIsNotFullHouse(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TEN, Suit.Heart));
        cards.add(new Card(Rank.TEN, Suit.Diamond));
        cards.add(new Card(Rank.KING, Suit.Spade));
        cards.add(new Card(Rank.QUEEN, Suit.Club));
        cards.add(new Card(Rank.KING, Suit.Diamond));
        Player p = new Player(cards);
        assertEquals(false,
                game.isFullHouse(p.getSortedCards()));

    }

    @Test
    void testIsFlush(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TWO, Suit.Heart));
        cards.add(new Card(Rank.THREE, Suit.Heart));
        cards.add(new Card(Rank.FIVE, Suit.Heart));
        cards.add(new Card(Rank.SIX, Suit.Heart));
        cards.add(new Card(Rank.FOUR, Suit.Heart));
        Player p = new Player(cards);
        assertEquals(true, game.isFlush(p.getSortedCards()));
    }

    @Test
    void testIsNotFlush(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TWO, Suit.Heart));
        cards.add(new Card(Rank.THREE, Suit.Heart));
        cards.add(new Card(Rank.FIVE, Suit.Heart));
        cards.add(new Card(Rank.SIX, Suit.Heart));
        cards.add(new Card(Rank.FOUR, Suit.Club));
        Player p = new Player(cards);
        assertEquals(false,game.isFlush(p.getSortedCards()));
    }

    @Test
    void testIsStraight(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TWO, Suit.Heart));
        cards.add(new Card(Rank.THREE, Suit.Diamond));
        cards.add(new Card(Rank.FIVE, Suit.Spade));
        cards.add(new Card(Rank.SIX, Suit.Club));
        cards.add(new Card(Rank.FOUR, Suit.Diamond));
        Player p = new Player(cards);
        assertEquals(true,
                game.isStraight(p.getSortedCards()));

    }

    @Test
    void testIsNotStraight(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TWO, Suit.Heart));
        cards.add(new Card(Rank.TWO, Suit.Diamond));
        cards.add(new Card(Rank.SIX, Suit.Spade));
        cards.add(new Card(Rank.SEVEN, Suit.Club));
        cards.add(new Card(Rank.FOUR, Suit.Diamond));
        Player p = new Player(cards);
        assertEquals(false, game.isStraight(p.getSortedCards()));
    }

    @Test
    void testHasThreeOfAKind(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.FIVE, Suit.Heart));
        cards.add(new Card(Rank.THREE, Suit.Diamond));
        cards.add(new Card(Rank.FIVE, Suit.Spade));
        cards.add(new Card(Rank.FIVE, Suit.Club));
        cards.add(new Card(Rank.KING, Suit.Diamond));
        Player p = new Player(cards);
        assertEquals(true,
                game.isThreeOfAKind(p.getSortedCards()));
    }

    @Test
    void testNotHasThreeOfAKind(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.FIVE, Suit.Heart));
        cards.add(new Card(Rank.THREE, Suit.Diamond));
        cards.add(new Card(Rank.JACK, Suit.Spade));
        cards.add(new Card(Rank.FIVE, Suit.Club));
        cards.add(new Card(Rank.KING, Suit.Diamond));
        Player p = new Player(cards);
        assertEquals(false,
                game.isThreeOfAKind(p.getSortedCards()));
    }
    @Test
    void testHasTwoPair(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TWO, Suit.Heart));
        cards.add(new Card(Rank.THREE, Suit.Diamond));
        cards.add(new Card(Rank.FIVE, Suit.Spade));
        cards.add(new Card(Rank.TWO, Suit.Club));
        cards.add(new Card(Rank.FIVE, Suit.Diamond));
        Player p = new Player(cards);
        assertEquals(true, game.hasTwoPairs(p));
    }

    @Test
    void testNotHasTwoPair(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.FIVE, Suit.Heart));
        cards.add(new Card(Rank.THREE, Suit.Diamond));
        cards.add(new Card(Rank.KING, Suit.Spade));
        cards.add(new Card(Rank.SEVEN, Suit.Club));
        cards.add(new Card(Rank.ACE, Suit.Diamond));
        Player p = new Player(cards);
        assertEquals(false, game.isThreeOfAKind(p.getSortedCards()));
    }
    @Test
    void testNotHasPair(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TWO, Suit.Heart));
        cards.add(new Card(Rank.THREE, Suit.Diamond));
        cards.add(new Card(Rank.QUEEN, Suit.Spade));
        cards.add(new Card(Rank.FIVE, Suit.Club));
        cards.add(new Card(Rank.KING, Suit.Diamond));
        Player p = new Player(cards);
        assertEquals(false, game.hasPair(p));
    }

    @Test
    void testHasPair(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TWO, Suit.Heart));
        cards.add(new Card(Rank.QUEEN, Suit.Diamond));
        cards.add(new Card(Rank.ACE, Suit.Spade));
        cards.add(new Card(Rank.FIVE, Suit.Club));
        cards.add(new Card(Rank.KING, Suit.Diamond));
        Player p = new Player(cards);
        assertEquals(false, game.hasPair(p));

    }






}