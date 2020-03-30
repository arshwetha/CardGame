package com.deckofcards.game.service;

import com.deckofcards.game.helper.CustomException;
import com.deckofcards.game.model.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameHandler {

    private Shoe shoe;
    private ArrayList<Player> players;
    private Deck deckCreated;

    public GameHandler() {
        shoe = new Shoe();
        players = new ArrayList<>();
    }

    public boolean createDeck() {
        deckCreated = new Deck(shoe.getDecks().size());
        return true;
    }

    public boolean addDeck() {
        boolean isAdded = false;
        if (deckCreated != null) {
            shoe.addDeck(deckCreated);
            deckCreated = null;
            isAdded = true;
        }
        return isAdded;
    }

    public int getDecks(){
        return shoe.getDecks().size();
    }

    public void addPlayer(String name) throws CustomException {
        Player player = getPlayer(name);
        if (player == null) {
            player = new Player(name);
            players.add(player);
        } else {
            throw new CustomException("Players name should not be same");
        }
    }

    public void removePlayer(String name) throws CustomException {
        Player player = null;
        for (Player p : players) {
            if (p.getName().equals(name)) {
                player = p;
                break;
            }
        }
        if (player != null) {
            players.remove(player);
            ArrayList<Card> cards = player.getCards();
            for (Card c : cards) {
                Deck deck = shoe.getDeck(c.getDeckId());
                deck.addCard(c);
            }
        } else {
            throw new CustomException(" Player doesn't exists");
        }
    }

    public Player getPlayer(String name) {
        for (Player p : players) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public Card dealCard(String playerName, int deckId) throws CustomException {
        Player player = getPlayer(playerName);
        Card card = shoe.dealCard(deckId);
        if (player == null){
            throw new CustomException("No Card dealt, player not exists");
        } else if(card == null) {
            throw new CustomException("No Card dealt, invalid_deckId/empty_deck");
        } else {
            player.addCard(card);
        }
        return card;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void shuffleGameDeck() {
        shoe.shuffle();
    }

    public void sortPlayers() {
        Comparator<Player> compareByValue = (Player o1, Player o2) ->
                o1.getAddedValue().compareTo(o2.getAddedValue());
        Collections.sort(players, compareByValue.reversed());
    }

    public Map<Suit, Integer> getCardsPerSuit() {
        Map<Suit, Integer> cardsPerSuit = new HashMap<>();
        Map<Suit, ArrayList<Card>> data = shoe.getRemainingCardsPerSuit();
        for (Suit s : data.keySet()) {
            cardsPerSuit.put(s, data.get(s).size());
        }

        Comparator<Suit> compareBySuit = (Suit o1, Suit o2) ->
                o1.getValue().compareTo(o2.getValue());

        cardsPerSuit = cardsPerSuit.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(compareBySuit))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        return cardsPerSuit;
    }

    public Map<Card, Integer> getSameCardsCount() {
        Map<Card, Integer> cards = shoe.getSameCardCountAcrossDecks();
        Comparator<Card> compareBySuit = (Card o1, Card o2) ->
                o1.getSuit().compareTo(o2.getSuit());
        Comparator<Card> compareByFacevalue = (Card o1, Card o2) ->
                o1.getFacevalue().compareTo(o2.getFacevalue());
        Comparator<Card> compareByCard = compareBySuit.thenComparing(compareByFacevalue.reversed());

        Map<String, Integer> cards1 = null;

        cards = cards.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(compareBySuit.thenComparing(compareByFacevalue.reversed())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        return cards;
    }

}
