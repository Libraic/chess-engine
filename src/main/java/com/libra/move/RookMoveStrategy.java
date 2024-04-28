package com.libra.move;

import com.libra.Color;
import com.libra.board.Board;
import com.libra.piece.Piece;
import com.libra.tile.ColumnIndex;
import com.libra.tile.Coordinate;
import com.libra.tile.Direction;
import com.libra.tile.RowIndex;
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
        Coordinate stopCoordinate = getStopCoordinate(direction);
        while (true) {
            currentCoordinate = movePieceAccordingToDirection(piece.getColor(), currentCoordinate, direction);
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

    private Coordinate movePieceAccordingToDirection(Color color, Coordinate coordinate, Direction direction) {
        if (direction == UP) {
            return coordinate.up(color);
        }

        if (direction == LEFT) {
            return coordinate.left(color);
        }

        if (direction == RIGHT) {
            return coordinate.right(color);
        }

        return coordinate.down(color);
    }

    private Coordinate getStopCoordinate(Direction direction) {
        if (direction == UP) {
            return new Coordinate(null, RowIndex.EIGHT);
        }

        if (direction == DOWN) {
            return new Coordinate(null, RowIndex.ONE);
        }

        if (direction == LEFT) {
            return new Coordinate(ColumnIndex.A, null);
        }

        return new Coordinate(ColumnIndex.H, null);
    }
}
