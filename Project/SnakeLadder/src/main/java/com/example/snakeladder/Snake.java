package com.example.snakeladder;

class Snake extends Jump {
    public Snake(int start, int end) {
        super(start, end);
        if (start <= end) throw new IllegalArgumentException("Snake head must be above tail");
    }
    @Override
    public String getHazardType() { return "Snake"; }
}
