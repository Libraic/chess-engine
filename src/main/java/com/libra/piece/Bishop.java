package com.libra.piece;

import com.libra.Color;
import com.libra.tile.Coordinate;

import java.util.List;

import static com.libra.piece.Rank.BISHOP;

/**
 * The Bishop piece of the game.
 */
public class Bishop extends Piece {

    public Bishop(Color color, Coordinate coordinate) {
        super(color, BISHOP, (short) 3, coordinate);
    }

    @Override
    public List<Coordinate> getPossibleMoves() {
        return null;
    }

    @Override
    protected List<Coordinate> getPossibleMovesForWhite() {
        return null;
    }

    @Override
    protected List<Coordinate> getPossibleMovesForBlack() {
        return null;
    }
}
