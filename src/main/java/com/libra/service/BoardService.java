package com.libra.service;

import com.libra.Color;
import com.libra.board.Board;
import com.libra.tile.Coordinate;
import com.libra.tile.Tile;

import java.util.Collection;

public class BoardService {

    private final Board board;

    public BoardService(Board board) {
        this.board = board;
    }

    public Color getTurn() {
        return board.getTurn();
    }

    public void changeTurn() {
        board.changeTurn();
    }

    public Tile getTileByCoordinate(Coordinate coordinate) {
        return board.getTileByCoordinate(coordinate);
    }

    public Collection<Tile> getTiles() {
        return board.getTiles().values();
    }
}
