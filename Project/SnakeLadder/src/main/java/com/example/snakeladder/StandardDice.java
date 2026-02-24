package com.example.snakeladder;

import java.util.Random;

public class StandardDice implements IDice {
    private final Random random = new Random();
    @Override
    public int roll() {
        return random.nextInt(6) + 1;
    }
}