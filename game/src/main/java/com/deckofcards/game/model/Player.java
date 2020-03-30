package com.deckofcards.game.model;

import java.util.*;
import java.util.stream.Collectors;

public class Player {

    private String name;
    private ArrayList<Card> cards;
    private Integer addedValue = 0;

    public Player(String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
        addedValue += card.getFacevalue().getValue();
    }

    public ArrayList<Card> getCards() {
        sortCards();
        return cards;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof Player)) {
            return false;
        }
        Player player = (Player) obj;
        return Objects.equals(this.name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public Integer getAddedValue() {
        return addedValue;
    }

    public void sortCards() {
        Comparator<Card> compareByFacevalue = (Card o1, Card o2) ->
                o1.getFacevalue().getValue().compareTo(o2.getFacevalue().getValue());
        Collections.sort(cards, compareByFacevalue.reversed());
    }

}
