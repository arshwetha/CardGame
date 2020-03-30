package com.deckofcards.game.service;

import com.deckofcards.game.helper.CustomException;
import com.deckofcards.game.model.Card;
import com.deckofcards.game.model.Player;
import com.deckofcards.game.model.Suit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Games {

    private Map<Integer, GameHandler> noOfGames = new HashMap<>();

    private GameHandler activeGameHandler;

    private int activeGameId = -1;

    private int getMax() {
        int max = 0;
        for (Integer i : noOfGames.keySet()) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    public int createGame() {
        GameHandler gameHandler = new GameHandler();
        int gameId = getMax() + 1;
        noOfGames.put(gameId, gameHandler);
        return gameId;
    }

    public boolean deleteGame(int id) {
        if (noOfGames.get(id) != null) {
            return noOfGames.remove(id, noOfGames.get(id));
        } else {
            return false;
        }
    }

    public void setCurrentGame(int currentGame) {
        activeGameId = currentGame;
        this.activeGameHandler = noOfGames.get(currentGame);
    }

    public boolean createDeck() {
        return activeGameHandler.createDeck();
    }

    public boolean addDeck() {
        return activeGameHandler.addDeck();
    }

    public int getDecks() {
        return activeGameHandler.getDecks();
    }

    public int getActiveGameId(){
        return activeGameId;
    }

    public boolean isValidGame(int id) {
        if (noOfGames.containsKey(id)) {
            return true;
        } else {
            return false;
        }
    }

    public List<Integer> getGamesList() {
        List<Integer> gamesList = new ArrayList<>();
        gamesList.addAll(noOfGames.keySet());
        return gamesList;
    }

    public void addPlayer(String name) throws CustomException {
        activeGameHandler.addPlayer(name);
    }

    public void removePlayer(String name) throws CustomException {
        activeGameHandler.removePlayer(name);
    }

    public Card dealCard(String playerName, int deckId) throws CustomException {
        return activeGameHandler.dealCard(playerName, deckId);
    }

    public Player getPlayer(String playerName) {
        return activeGameHandler.getPlayer(playerName);
    }

    public ArrayList<Player> getPlayers() {
        activeGameHandler.sortPlayers();
        return activeGameHandler.getPlayers();
    }

    public Map<Suit, Integer> getCardsPerSuit() {
        return activeGameHandler.getCardsPerSuit();
    }

    public Map<Card, Integer> getSameCardsCount() {
        return activeGameHandler.getSameCardsCount();
    }

    public void shuffle() {
        activeGameHandler.shuffleGameDeck();
    }
}
