package com.example.snakeladder;

public class Cell {
    private final int position;
    private Jump jump; // Optional hazard

    public Cell(int position) { this.position = position; }
    
    public void setJump(Jump jump) { this.jump = jump; }
    public Jump getJump() { return jump; }
    public int getPosition() { return position; }
}