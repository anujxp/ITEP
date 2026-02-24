package com.example.snakeladder;

public class Ladder extends Jump {
    public Ladder(int start, int end) {
        super(start, end);
        if (start >= end) throw new IllegalArgumentException("Ladder base must be below top");
    }
    @Override
    public String getHazardType() { return "Ladder"; }
}