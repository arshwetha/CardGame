package com.deckofcards.game.model;

public enum Suit {
    HEART(1),
    SPADE(2),
    CLUB(3),
    DIAMOND(4);

    private Integer value = 0;

    Suit(final int value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

}
