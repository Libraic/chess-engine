package com.libra.move;

import com.libra.piece.Piece;
import com.libra.tile.Coordinate;

import java.util.List;

public interface MoveStrategy {

    List<Coordinate> getPossibleMoves(Piece piece);
}
