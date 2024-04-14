package com.libra.piece;

import com.libra.Color;
import com.libra.tile.Coordinate;

import java.util.ArrayList;
import java.util.List;

import static com.libra.Color.WHITE;

/**
 * The general blueprint for a piece.
 */
public abstract class Piece {

    protected final Color color;

    protected final Rank rank;

    protected final short value;

    protected Coordinate coordinate;

    public Piece(
        Color color,
        Rank rank,
        short value,
        Coordinate coordinate
    ) {
        this.color = color;
        this.rank = rank;
        this.value = value;
        this.coordinate = coordinate;
    }


    public Color getColor() {
        return color;
    }

    public Rank getRank() {
        return rank;
    }

    public List<Coordinate> getPossibleMoves() {
        return color.equals(WHITE) ? getPossibleMovesForWhite() : getPossibleMovesForBlack();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    protected abstract List<Coordinate> getPossibleMovesForWhite();

    protected abstract List<Coordinate> getPossibleMovesForBlack();
}
