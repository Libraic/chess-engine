package com.libra.piece;

import com.libra.Color;
import com.libra.tile.Coordinate;

import java.util.List;

import static com.libra.piece.Rank.KNIGHT;

/**
 * The Knight piece of the game.
 */
public final class Knight extends Piece {

    public Knight(Color color, Coordinate coordinate) {
        super(color, KNIGHT, (short) 3, coordinate);
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
