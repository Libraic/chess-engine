package com.libra.piece;

import com.libra.Color;
import com.libra.tile.Coordinate;

import java.util.List;

import static com.libra.piece.Rank.QUEEN;

/**
 * The Queen piece of the game.
 */
public final class Queen extends Piece {

    public Queen(Color color, Coordinate coordinate) {
        super(color, QUEEN, (short) 9, coordinate);
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
