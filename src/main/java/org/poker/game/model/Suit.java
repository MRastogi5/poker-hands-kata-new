package org.poker.game.model;

public enum Suit {
    // suit have 4 values
    Club("C"),
    Spade("S"),
    Diamond("D"),
    Heart("D");

    private final String suitValue;

    Suit(String suitValue) {
        this.suitValue = suitValue;
    }
}
