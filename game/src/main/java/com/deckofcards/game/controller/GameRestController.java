package com.deckofcards.game.controller;

import com.deckofcards.game.helper.CustomException;
import com.deckofcards.game.model.Card;
import com.deckofcards.game.model.Player;
import com.deckofcards.game.model.Suit;
import com.deckofcards.game.service.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class GameRestController {

    @Autowired
    Games games;

    @GetMapping("/createGame")
    public ModelAndView createGame() {
        int gameId = games.createGame();
        String message = "Successfully created a game with id: " + gameId;
        return new ModelAndView("init_done", "message", message);
    }

    @GetMapping("/getGamesList")
    public ModelAndView getGamesList() {
        ModelAndView modelAndView;
        if (games.getGamesList().size() < 1) {
            modelAndView = new ModelAndView("init_error", "message", "No games played");
        } else {
            modelAndView = new ModelAndView("init_done", "gamesList", games.getGamesList());
        }
        return modelAndView;
    }

    @PostMapping("/deleteGame")
    public ModelAndView deleteGame(@RequestParam(value = "gameId") int gameId) {
        boolean isDeleted = games.deleteGame(gameId);
        if (isDeleted) {
            String message = "successfully deleted a game with id: " + gameId;
            return new ModelAndView("init_done", "message", message);
        } else {
            StringBuilder message = new StringBuilder();
            message.append("Invalid game Id. \n\n");
            if (games.getGamesList().size() > 0) {
                message.append(" Valid list of ids: ");
                for (Integer i : games.getGamesList()) {
                    message.append(i + ", ");
                }
                message = message.delete(message.length()-2,message.length());
            } else {
                message.append("No games played");
            }
            return new ModelAndView("init_error", "message", message);
        }
    }

    @PostMapping("/chooseGame")
    public ModelAndView chooseGame(@RequestParam(value = "gameId") int gameId) {
        boolean isValid = games.isValidGame(gameId);
        if (isValid) {
            games.setCurrentGame(gameId);
            String message = "Current game " + gameId;
            return new ModelAndView("play", "message", message);
        } else {
            String message = "Invalid game Id: " + gameId;
            return new ModelAndView("init_error", "message", message);
        }
    }

    @GetMapping("/gamePage")
    public ModelAndView gamePage() {
        String message = "Current game " + games.getActiveGameId();
        return new ModelAndView("play", "message", message);
    }

    @GetMapping("/createDeck")
    public ModelAndView createDeck() {
        boolean isCreated = games.createDeck();
        String message = "Deck created ";
        return new ModelAndView("done", "message", message);
    }

    @GetMapping("/addDeck")
    public ModelAndView addDeck() {
        boolean isValid = games.addDeck();
        if (isValid) {
            String message = "Successfully added a deck to game";
            return new ModelAndView("done", "message", message);
        } else {
            String message = "Create deck first";
            return new ModelAndView("error", "message", message);
        }
    }

    @GetMapping("/getDecks")
    public ModelAndView getDecksList() {

        int noOfDecks = games.getDecks();
        ModelAndView modelAndView;
        if (noOfDecks < 1) {
            modelAndView = new ModelAndView("error", "message", "No Decks created");
        } else {
            String message = "No of decks in a suit: "+noOfDecks;
            modelAndView = new ModelAndView("done", "message", message);
        }
        return modelAndView;
    }

    @PostMapping("/addPlayer")
    public ModelAndView addPlayer(@RequestParam(value = "name") String name) {
        ModelAndView modelAndView;
        if (name == null || name.isEmpty()) {
            modelAndView = new ModelAndView("error", "message", "Player name must not be empty");
        } else {
            try {
                games.addPlayer(name);
                String message = "Successfully added a player: '" + name + "' to game";
                modelAndView = new ModelAndView("done", "message", message);
            } catch (CustomException e) {
                modelAndView = new ModelAndView("error", "message", e.getErrMsg());
            }
        }
        return modelAndView;
    }

    @PostMapping("/removePlayer")
    public ModelAndView removePlayer(@RequestParam(value = "name") String name) {
        ModelAndView modelAndView;
        if (name == null || name.isEmpty()) {
            modelAndView = new ModelAndView("error", "message", "Player name must not be empty");
        } else {
            try {
                games.removePlayer(name);
                String message = "Successfully removed a player: '" + name + "' from game";
                modelAndView = new ModelAndView("done", "message", message);
            } catch (CustomException e) {
                modelAndView = new ModelAndView("error", "message", e.getErrMsg());
            }
        }
        return modelAndView;
    }

    @PostMapping("/dealCard")
    public ModelAndView dealCard(@RequestParam(value = "playerName") String playerName,
                                 @RequestParam(value = "deckId") int deckId) {
        ModelAndView modelAndView;
        if (playerName == null || playerName.isEmpty()) {
            modelAndView = new ModelAndView("error", "message", "Player name must not be empty");
        } else {
            try {
                Card card = games.dealCard(playerName, deckId);
                String message = "Successfully dealt a card with Facevalue = " + card.getFacevalue() + "" +
                        ", Suit=" + card.getSuit() + " from a deck=" + (card.getDeckId()+1) + " for a player : " + playerName;
                modelAndView = new ModelAndView("done", "message", message);
            } catch (CustomException e) {
                modelAndView = new ModelAndView("error", "message", e.getErrMsg());
            }
        }
        return modelAndView;
    }

    @PostMapping("/getPlayerCards")
    public ModelAndView getCards(@RequestParam(value = "playerName") String playerName) {
        ModelAndView modelAndView;
        if (playerName == null || playerName.isEmpty()) {
            modelAndView = new ModelAndView("error", "message", "Player name must not be empty");
        } else {
            Player player = games.getPlayer(playerName);
            return new ModelAndView("playerInfo", "player", player);
        }
        return modelAndView;
    }

    @GetMapping("/getPlayers")
    public ModelAndView getPlayers() {
        List<Player> players = games.getPlayers();
        return new ModelAndView("players", "players", players);
    }

    @GetMapping("/getCardsPerSuit")
    public ModelAndView getCardsPerSuit() {
        Map<Suit, Integer> cardsPerSuit = games.getCardsPerSuit();
        return new ModelAndView("cards_per_suit", "cardsPerSuit", cardsPerSuit);
    }

    @GetMapping("/getSameCardsCount")
    public ModelAndView getSameCardsCount() {
        Map<Card, Integer> sameCardsCount = games.getSameCardsCount();
        return new ModelAndView("same_card_count", "sameCardsCount", sameCardsCount);
    }

    @GetMapping("/shuffle")
    public ModelAndView shuffle() {
        games.shuffle();
        return new ModelAndView("done", "message", "Successfully shuffled");
    }
}
