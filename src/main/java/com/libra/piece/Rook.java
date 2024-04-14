package com.libra.piece;

import com.libra.Color;
import com.libra.tile.Coordinate;

import java.util.ArrayList;
import java.util.List;

import static com.libra.piece.Rank.ROOK;
import static com.libra.tile.ColumnIndex.A;
import static com.libra.tile.ColumnIndex.H;
import static com.libra.tile.RowIndex.EIGHT;
import static com.libra.tile.RowIndex.ONE;

/**
 * The Rook piece of the game.
 */
public final class Rook extends Piece {

    public Rook(Color color, Coordinate coordinate) {
        super(color, ROOK, (short) 5, coordinate);
    }

    @Override
    protected List<Coordinate> getPossibleMovesForWhite() {
        return getMoves();
    }

    @Override
    protected List<Coordinate> getPossibleMovesForBlack() {
        return getMoves();
    }

    private List<Coordinate> getMoves() {
        List<Coordinate> possibleMoves = new ArrayList<>();
        Coordinate currentPosition = coordinate;
        while (!currentPosition.rowIndex().equals(EIGHT)) {
            currentPosition = currentPosition.up();
            possibleMoves.add(currentPosition);
        }

        currentPosition = coordinate;
        while (!currentPosition.rowIndex().equals(ONE)) {
            currentPosition = currentPosition.down();
            possibleMoves.add(currentPosition);
        }

        currentPosition = coordinate;
        while (!currentPosition.columnIndex().equals(H)) {
            currentPosition = currentPosition.right();
            possibleMoves.add(currentPosition);
        }


        currentPosition = coordinate;
        while (!currentPosition.columnIndex().equals(A)) {
            currentPosition = currentPosition.left();
            possibleMoves.add(currentPosition);
        }

        return possibleMoves;
    }
}
