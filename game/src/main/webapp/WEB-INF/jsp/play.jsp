<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${message}</title>
</head>
<body>
<h1> Play Game </h1>
<script type="text/javascript">
    function btnActivation(textId, buttonId){
        if(!document.getElementById(textId).value.length){
            document.getElementById(buttonId).disabled = true;
        }else{
            document.getElementById(buttonId).disabled = false;
        }
    }
</script>

<form action="createDeck" method="GET">
    Create a new Deck:<input type="submit" value="createDeck" />
</form>

<form action="addDeck" method="GET">
    Add deck to a game:<input type="submit" value="addDeck" />
</form>

<form action="getDecks" method="GET">
    Get decks size: <input type="submit" value="noOfDecks" />
</form>

<form action="addPlayer" method="POST">
    Player Name: <input type="text" name="name" id="addPlayer" onkeyup="btnActivation('addPlayer', 'addPlayerButton')">
    <input type="submit" value="addPlayer" id="addPlayerButton" disabled/>
</form>

<form action="removePlayer" method="POST">
    Player Name: <input type="text" name="name" id="removePlayer" onkeyup="btnActivation('removePlayer', 'removePlayerButton')">
    <input type="submit" value="removePlayer" id="removePlayerButton" disabled/>
</form>

<form action="dealCard" method="POST">
    Player Name: <input type="text" name="playerName" id="dealCardPlayer">
    Deck Id: <input type="text" name="deckId" id="dealCardId" onkeyup="btnActivation('dealCardId', 'dealCardButton')">
    <input type="submit" value="dealCard" id="dealCardButton" disabled/>
</form>

<form action="getPlayerCards" method="POST">
    Player Name: <input type="text" name="playerName" id="getPlayerCards"
                        onkeyup="btnActivation('getPlayerCards', 'getPlayerCardsButton')">
    <input type="submit" value="getCards" id="getPlayerCardsButton" disabled/>
</form>

<form action="getPlayers" method="GET">
    Get Players List: <input type="submit" value="getPlayers" />
</form>

<form action="getCardsPerSuit" method="GET">
    Gets cards count per suit: <input type="submit" value="getCardsPerSuit" />
</form>

<form action="getSameCardsCount" method="GET">
    Get same card count per shoe: <input type="submit" value="getSameCardsCount" />
</form>

<form action="shuffle" method="GET">
    Shuffle cards: <input type="submit" value="shuffle" />
</form>
<input type="button" value="Back" onclick="javascript:history.back()"/>
<br><br>
<p>
<a href="/game"> home page </a>
</p>
</body>
</html>
