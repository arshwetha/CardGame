package com.deckofcards.game.model;

import java.util.Objects;

public class Card {

    private Facevalue facevalue;
    private Suit suit;
    private int deckId;

    public Card(Facevalue facevalue, Suit suit, int deckId) {
        this.facevalue = facevalue;
        this.suit = suit;
        this.deckId = deckId;
    }

    public Facevalue getFacevalue() {
        return facevalue;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getDeckId() {
        return deckId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof Card)) {
            return false;
        }
        Card card = (Card) obj;

        return Objects.equals(suit.getValue(), card.suit.getValue()) &&
                Objects.equals(facevalue.getValue(), card.facevalue.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit.getValue(), facevalue.getValue());
    }

}
