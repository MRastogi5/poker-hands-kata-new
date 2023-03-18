# Poker Hand Kata

#### This Poker hand kata solved as a solution to https://codingdojo.org/kata/PokerHands/ kata.

#### The task is to compare several pairs of poker hands and to indicate which, if either, has a higher rank.
#### Poker hands are ranked by the following partial order from lowest to highest.
	- High Card
	- Pair
	- Two Pairs
	- Three of a Kind
	- Straight
	- Flush
	- Full House
	- Four of a kind
	- Straight flush

## Assumptions:
### Poker game will have two players - Black, White
### firstPlayer is Black
### secondPlayer is White
 - Each player will have 5 Cards called Hand
	 - Each card have Rank, Suit
	 - Rank can have values  
		 - TWO(2), 
		 - THREE(3),
		 - FOUR(4),
		 - FIVE(5),
		 - SIX(6),
		 - SEVEN(7),
		 - EIGHT(8),
		 - NINE(9),
		 - TEN(10),
		 - JACK(J),
		 - QUEEN(Q),
		 - KING(K),
		 - ACE(A)
	 - Suit can have values     
		 - Club(C), 
		 - Spade(S), 
		 - Diamond(D),  
		 - Heart(H)


### Sample valid cards :

"AH 2D 3S 4C 5H" ,  "2C 3H 4S 5C 6H"

### Invalid cards :
#### Both player can not have same cards
"2H 4S 4C 2D 4H" "2H 4S 4C 2D 4H"
