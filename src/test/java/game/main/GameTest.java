package game.main;

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

    /*@Test
    void testSimilarPokerHands(){
        List<Card> cards1 = new ArrayList<>();
        cards1.add(new Card(Rank.FIVE, Suit.Spade));
        cards1.add(new Card(Rank.THREE, Suit.Spade));
        cards1.add(new Card(Rank.QUEEN, Suit.Club));
        cards1.add(new Card(Rank.TEN, Suit.Heart));
        cards1.add(new Card(Rank.TWO, Suit.Diamond));
        Player firstPlayer = new Player(cards1);

        List<Card> cards2 = new ArrayList<>();
        cards2.add(new Card(Rank.FIVE, Suit.Club));
        cards2.add(new Card(Rank.TWO, Suit.Spade));
        cards2.add(new Card(Rank.QUEEN, Suit.Heart));
        cards2.add(new Card(Rank.TEN, Suit.Club));
        cards2.add(new Card(Rank.THREE, Suit.Diamond));
        Player secondPlayer = new Player(cards2);

        assertEquals("Tie",
                game.getWinningPokerHands(firstPlayer, secondPlayer));
    }*/

    /*@Test
    void testSamePokerHands(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TWO, Suit.Heart));
        cards.add(new Card(Rank.THREE, Suit.Diamond));
        cards.add(new Card(Rank.FIVE, Suit.Spade));
        cards.add(new Card(Rank.NINE, Suit.Club));
        cards.add(new Card(Rank.KING, Suit.Diamond));
        Player p = new Player(cards);
        Player firstPlayer = new Player(cards);
        Player secondPlayer = new Player(cards);

        assertEquals("Invalid",
                game.getWinningPokerHands(firstPlayer, secondPlayer));

    }*/

    /*@Test
    void testSamePokerHands(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.TWO, Suit.Heart));
        cards.add(new Card(Rank.THREE, Suit.Diamond));
        cards.add(new Card(Rank.FIVE, Suit.Spade));
        cards.add(new Card(Rank.NINE, Suit.Club));
        cards.add(new Card(Rank.KING, Suit.Diamond));
        Player p = new Player(cards);
        Player firstPlayer = new Player(cards);
        Player secondPlayer = new Player(cards);

        assertEquals("Invalid",
                game.getWinningPokerHands(firstPlayer, secondPlayer));

    }*/




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