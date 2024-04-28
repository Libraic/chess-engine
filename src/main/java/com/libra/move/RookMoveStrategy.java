package com.libra.move;

import com.libra.board.Board;
import com.libra.piece.Piece;
import com.libra.tile.Coordinate;
import com.libra.tile.Direction;
import com.libra.tile.Tile;

import java.util.ArrayList;
import java.util.List;

import static com.libra.tile.Direction.DOWN;
import static com.libra.tile.Direction.LEFT;
import static com.libra.tile.Direction.RIGHT;
import static com.libra.tile.Direction.UP;

public class RookMoveStrategy implements MoveStrategy {

    @Override
    public List<Coordinate> getPossibleMoves(Piece piece, Board board) {
        List<Coordinate> moves = new ArrayList<>();
        moves.addAll(getCoordinatesFromOneDirection(piece, board, UP));
        moves.addAll(getCoordinatesFromOneDirection(piece, board, DOWN));
        moves.addAll(getCoordinatesFromOneDirection(piece, board, LEFT));
        moves.addAll(getCoordinatesFromOneDirection(piece, board, RIGHT));
        return moves;
    }

    private List<Coordinate> getCoordinatesFromOneDirection(Piece piece, Board board, Direction direction) {
        List<Coordinate> moves = new ArrayList<>();
        Coordinate currentCoordinate = piece.getCoordinate();
        Coordinate stopCoordinate = Coordinate.getStopCoordinate(direction);
        while (true) {
            currentCoordinate = currentCoordinate.movePieceAccordingToDirection(piece.getColor(), direction);
            Tile currentTile = board.getTileByCoordinate(currentCoordinate);
            if (stopCoordinate.equalsByColumnOrRowIndex(currentCoordinate) ||
                currentTile.isAvailableForAttack(piece.getColor())
            ) {
                moves.add(currentCoordinate);
                break;
            }

            if (!currentTile.isTileAvailable()) {
                break;
            }

            moves.add(currentCoordinate);
        }

        return moves;
    }
}
