package com.libra.piece;

import com.libra.Color;
import com.libra.tile.Coordinate;

import static com.libra.piece.Rank.KING;

/**
 * The King piece of the game.
 */
public final class King extends Piece {

    public King(Color color, Coordinate coordinate) {
        super(color, KING, (short) -1, coordinate);
    }
}
