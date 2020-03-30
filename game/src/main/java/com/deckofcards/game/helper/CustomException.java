package com.deckofcards.game.helper;

public class CustomException extends Exception {
    private String errMsg;

    public CustomException(String msg) {
        super(msg);
        this.errMsg = msg;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
