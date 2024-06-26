package com.libra.move;

import com.libra.Color;
import com.libra.board.Board;
import com.libra.piece.Piece;
import com.libra.tile.Coordinate;
import com.libra.tile.Direction;
import com.libra.tile.Tile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.libra.tile.Direction.DOWN;
import static com.libra.tile.Direction.LEFT;
import static com.libra.tile.Direction.RIGHT;
import static com.libra.tile.Direction.UP;
import static com.libra.utils.Constants.FIRST_ELEMENT;
import static com.libra.utils.Constants.MAX_NUMBER_OF_MOVEMENTS;
import static com.libra.utils.Constants.SECOND_ELEMENT;

public class QueenMoveStrategy implements MoveStrategy {

    // TODO: Review Performance
    @Override
    public List<Coordinate> getPossibleMoves(Piece piece, Board board) {
        Set<Coordinate> moves = new HashSet<>();
        moves.addAll(getCoordinatesFromOneDiagonalDirection(piece, board, List.of(UP, RIGHT)));
        moves.addAll(getCoordinatesFromOneDiagonalDirection(piece, board, List.of(UP, LEFT)));
        moves.addAll(getCoordinatesFromOneDiagonalDirection(piece, board, List.of(DOWN, RIGHT)));
        moves.addAll(getCoordinatesFromOneDiagonalDirection(piece, board, List.of(DOWN, LEFT)));
        moves.addAll(getCoordinatesFromOneLinearDirection(piece, board, UP));
        moves.addAll(getCoordinatesFromOneLinearDirection(piece, board, DOWN));
        moves.addAll(getCoordinatesFromOneLinearDirection(piece, board, LEFT));
        moves.addAll(getCoordinatesFromOneLinearDirection(piece, board, RIGHT));
        return moves.stream().toList();
    }

    private Set<Coordinate> getCoordinatesFromOneLinearDirection(
        Piece piece,
        Board board,
        Direction direction
    ) {
        Set<Coordinate> moves = new HashSet<>();
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

    private Set<Coordinate> getCoordinatesFromOneDiagonalDirection(
        Piece piece,
        Board board,
        List<Direction> directions
    ) {
        Set<Coordinate> moves = new HashSet<>();
        Coordinate currentCoordinate = piece.getCoordinate();
        Color color = piece.getColor();
        for (int i = 0; i < MAX_NUMBER_OF_MOVEMENTS; ++i) {
            Coordinate linearMove = currentCoordinate.movePieceAccordingToDirection(
                color,
                directions.get(FIRST_ELEMENT)
            );
            Coordinate diagonalMove = linearMove.movePieceAccordingToDirection(
                color,
                directions.get(SECOND_ELEMENT)
            );

            Tile diagonalTile = board.getTileByCoordinate(diagonalMove);
            if (diagonalTile.isAvailableForAttack(color)) {
                moves.add(diagonalMove);
                break;
            }

            if (linearMove.equals(currentCoordinate) ||
                diagonalMove.equals(linearMove) ||
                !diagonalTile.isTileAvailable()
            ) {
                break;
            }

            currentCoordinate = diagonalMove;
            moves.add(diagonalMove);
        }

        return moves;
    }
}
