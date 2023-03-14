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
}
