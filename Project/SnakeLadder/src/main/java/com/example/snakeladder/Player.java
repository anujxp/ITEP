package com.example.snakeladder;

public class Player {
    private final String name;
    private int currentPosition;

    public Player(String name) {
        this.name = name;
        this.currentPosition = 0; // Starts outside the board
    }

    public String getName() { return name; }
    public int getCurrentPosition() { return currentPosition; }
    public void setCurrentPosition(int pos) { this.currentPosition = pos; }
}