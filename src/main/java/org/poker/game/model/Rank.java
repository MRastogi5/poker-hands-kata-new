package org.poker.game.model;

public enum Rank {
    // Rank can have numeric values 1-14
    // or string values()
    TWO("2", 2),
    THREE("3",3),
    FOUR("4", 4),
    FIVE("5",5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13),
    ACE("A", 14);

    Rank(String s, int i) {
        this.cardValue = s;
        this.cardIntValue = i;
    }
    Rank(String s) {
        this.cardValue = s;
    }

    public int getCardIntValue() {
        return cardIntValue;
    }

    public String getCardValue() {
        return cardValue;
    }

    private int cardIntValue;
    private String cardValue;

    public static Rank fromValue(String r)
    {
        if (r != null) {
            for (Rank rank : Rank.values())
                if (rank.getCardValue().equals(r))
                    return rank;
        }

        throw new IllegalArgumentException("Invalid Card value !!!" + r);

    }
}
