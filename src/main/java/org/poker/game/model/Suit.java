package org.poker.game.model;

public enum Suit {
    // suit have 4 values
    Club("C"),
    Spade("S"),
    Diamond("D"),
    Heart("H");

    public String getSuitValue() {
        return suitValue;
    }
    private final String suitValue;

    Suit(String suitValue) {
        this.suitValue = suitValue;
    }
    public static Suit fromValue(String s)
    {
        if (s != null) {
            for (Suit suit : Suit.values())
                if (suit.getSuitValue().equals(s))
                    return suit;
        }
        throw new IllegalArgumentException("Invalid Suit value !!!");
    }
}
