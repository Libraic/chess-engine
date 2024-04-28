package com.libra.piece;

import com.libra.Color;
import com.libra.tile.Coordinate;

import static com.libra.piece.Rank.PAWN;

/**
 * The Pawn piece of the game.
 */
public final class Pawn extends Piece {

    private boolean wasFirstMoveMade;

    public Pawn(Color color, Coordinate coordinate) {
        super(color, PAWN, (short) 1, coordinate);
    }

    @Override
    public void setCoordinate(Coordinate coordinate) {
        super.setCoordinate(coordinate);
        wasFirstMoveMade = true;
    }

    public boolean isWasFirstMoveMade() {
        return !wasFirstMoveMade;
    }
}
