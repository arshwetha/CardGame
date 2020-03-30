package com.deckofcards.game.model;

import com.deckofcards.game.helper.CustomException;
import com.deckofcards.game.helper.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Deck {

    private ArrayList<Card> cardDeck;

    public Deck(int deckId) {
        cardDeck = new ArrayList<>();
        for (Facevalue facevalue : Facevalue.values()) {
            for (Suit suit : Suit.values()) {
                cardDeck.add(new Card(facevalue, suit, deckId));
            }
        }
    }

    public void shuffle() {
        for (int i = 0; i < cardDeck.size(); i++) {
            Collections.swap(cardDeck, i, Utility.getRandom(i, cardDeck.size()));
        }
    }

    public Card dealCard() {
        Card card = null;
        if (cardDeck.size() > 0) {
            card = cardDeck.remove(0);
        }
        return card;
    }

    public ArrayList<Card> getCardDeck() {
        return cardDeck;
    }

    public void addCard(Card card) throws CustomException {
        if (cardDeck.contains(card)) {
            throw new CustomException("Duplicate card");
        } else {
            cardDeck.add(card);
        }
    }

    public Map<Suit, ArrayList<Card>> getRemainingCardsPerSuit() {

        Map<Suit, ArrayList<Card>> suitRemainingCards = new HashMap<>();
        for (Suit s : Suit.values()) {
            ArrayList<Card> remainingCards = new ArrayList<>();
            suitRemainingCards.put(s, remainingCards);
        }

        for (Card c : cardDeck) {
            ArrayList<Card> cards = suitRemainingCards.get(c.getSuit());
            cards.add(c);
            suitRemainingCards.put(c.getSuit(), cards);
        }
        return suitRemainingCards;
    }

}
