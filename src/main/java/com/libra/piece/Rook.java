package com.libra.piece;

import com.libra.Color;
import com.libra.tile.Coordinate;

import static com.libra.piece.Rank.ROOK;

/**
 * The Rook piece of the game.
 */
public final class Rook extends Piece {

    public Rook(Color color, Coordinate coordinate) {
        super(color, ROOK, (short) 5, coordinate);
    }
}
