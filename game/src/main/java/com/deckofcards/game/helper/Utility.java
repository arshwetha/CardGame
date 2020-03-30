package com.deckofcards.game.helper;

import java.util.Random;

public class Utility {

    public static int getRandom(int first, int size) {
        Random random = new Random();
        boolean isSame = true;
        do {
            int rand = random.nextInt(size);
            if (first != rand) {
                isSame = false;
                return rand;
            }
        } while (isSame);
        return size - 1;
    }

    public static int getRandom(int limit) {
        Random random = new Random();
        return random.nextInt(limit);
    }

}
