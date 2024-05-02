package com.libra.move;

import com.libra.Color;
import com.libra.piece.Piece;
import com.libra.service.BoardService;
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

    private final BoardService boardService;

    public QueenMoveStrategy(BoardService boardService) {
        this.boardService = boardService;
    }

    // TODO: Review Performance
    @Override
    public List<Coordinate> getPossibleMoves(Piece piece) {
        Set<Coordinate> moves = new HashSet<>();
        moves.addAll(getCoordinatesFromOneDiagonalDirection(piece, List.of(UP, RIGHT)));
        moves.addAll(getCoordinatesFromOneDiagonalDirection(piece, List.of(UP, LEFT)));
        moves.addAll(getCoordinatesFromOneDiagonalDirection(piece, List.of(DOWN, RIGHT)));
        moves.addAll(getCoordinatesFromOneDiagonalDirection(piece, List.of(DOWN, LEFT)));
        moves.addAll(getCoordinatesFromOneLinearDirection(piece, UP));
        moves.addAll(getCoordinatesFromOneLinearDirection(piece, DOWN));
        moves.addAll(getCoordinatesFromOneLinearDirection(piece, LEFT));
        moves.addAll(getCoordinatesFromOneLinearDirection(piece, RIGHT));
        return moves.stream().toList();
    }

    private Set<Coordinate> getCoordinatesFromOneLinearDirection(
        Piece piece,
        Direction direction
    ) {
        Set<Coordinate> moves = new HashSet<>();
        Coordinate currentCoordinate = piece.getCoordinate();
        Coordinate stopCoordinate = Coordinate.getStopCoordinate(direction);
        while (true) {
            currentCoordinate = currentCoordinate.movePieceAccordingToDirection(piece.getColor(), direction);
            Tile currentTile = boardService.getTileByCoordinate(currentCoordinate);
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

            if (linearMove.equals(diagonalMove)) {
                break;
            }

            Tile diagonalTile = boardService.getTileByCoordinate(diagonalMove);
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
