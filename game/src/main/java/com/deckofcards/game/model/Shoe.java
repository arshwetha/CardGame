package com.deckofcards.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Shoe {
    private ArrayList<Deck> decks;

    public Shoe() {
        decks = new ArrayList<>();
    }

    public void addDeck(Deck deck) {
        decks.add(deck);
    }

    public void shuffle() {
        for (Deck deck : decks) {
            deck.shuffle();
        }
    }

    public Card dealCard(int deckId) {
        Card card = null;
        if (decks.size() >= deckId) {
            card = decks.get(deckId - 1).dealCard();
        }
        return card;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public Deck getDeck(int deckId) {
        return decks.get(deckId);
    }

    public Map<Suit, ArrayList<Card>> getRemainingCardsPerSuit() {
        Map<Suit, ArrayList<Card>> shoeRemainingCards = new HashMap<>();
        for (Suit s : Suit.values()) {
            ArrayList<Card> remainingCards = new ArrayList<>();
            shoeRemainingCards.put(s, remainingCards);
        }

        for (Deck d : decks) {
            Map<Suit, ArrayList<Card>> remCardsPerDeck = d.getRemainingCardsPerSuit();
            for (Suit s : remCardsPerDeck.keySet()) {
                ArrayList<Card> totalRemainingCards = shoeRemainingCards.get(s);
                totalRemainingCards.addAll(remCardsPerDeck.get(s));
                shoeRemainingCards.put(s, totalRemainingCards);
            }
        }
        return shoeRemainingCards;
    }

    public Map<Card, Integer> getSameCardCountAcrossDecks() {
        Map<Card, Integer> sameCardCount = new HashMap<>();

        for (Deck d : decks) {
            ArrayList<Card> cards = d.getCardDeck();
            for (Card c : cards) {
                if (!sameCardCount.containsKey(c)) {
                    sameCardCount.put(c, 1);
                } else {
                    sameCardCount.put(c, sameCardCount.get(c) + 1);
                }
            }
        }
        return sameCardCount;
    }
}
