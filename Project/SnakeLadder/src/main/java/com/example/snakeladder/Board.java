package com.example.snakeladder;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int size;
    private final Map<Integer, Cell> cells;

    public Board(int size) {
        this.size = size;
        this.cells = new HashMap<>();
        for (int i = 0; i <= size; i++) {
            cells.put(i, new Cell(i));
        }
    }

    public void addHazard(Jump jump) {
        cells.get(jump.getStart()).setJump(jump);
    }

    public Cell getCell(int position) {
        return cells.get(position);
    }

    public int getSize() { return size; }
}