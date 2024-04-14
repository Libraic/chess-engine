package com.libra.piece;

import com.libra.Color;
import com.libra.tile.Coordinate;

import java.util.ArrayList;
import java.util.List;

import static com.libra.Color.WHITE;
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
    public List<Coordinate> getPossibleMoves() {
        return color.equals(WHITE) ? getPossibleMovesForWhite() : getPossibleMovesForBlack();
    }

    protected List<Coordinate> getPossibleMovesForWhite() {
        List<Coordinate> possibleMoves = new ArrayList<>();
        Coordinate firstMove = coordinate.up();
        possibleMoves.add(firstMove);
        if (!wasFirstMoveMade) {
            possibleMoves.add(firstMove.up());
        }
        return possibleMoves;
    }


    protected List<Coordinate> getPossibleMovesForBlack() {
        List<Coordinate> possibleMoves = new ArrayList<>();
        Coordinate firstMove = coordinate.down();
        possibleMoves.add(firstMove);
        if (!wasFirstMoveMade) {
            possibleMoves.add(firstMove.down());
        }
        return possibleMoves;
    }
}
