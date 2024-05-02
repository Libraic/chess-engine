package com.libra.move;

import com.libra.Color;
import com.libra.piece.Piece;
import com.libra.service.BoardService;
import com.libra.tile.Coordinate;
import com.libra.tile.Direction;
import com.libra.tile.Tile;

import java.util.ArrayList;
import java.util.List;

import static com.libra.tile.Direction.DOWN;
import static com.libra.tile.Direction.LEFT;
import static com.libra.tile.Direction.RIGHT;
import static com.libra.tile.Direction.UP;
import static com.libra.utils.Constants.FIRST_ELEMENT;
import static com.libra.utils.Constants.MAX_NUMBER_OF_MOVEMENTS;
import static com.libra.utils.Constants.SECOND_ELEMENT;

public class BishopMoveStrategy implements MoveStrategy {

    private final BoardService boardService;

    public BishopMoveStrategy(BoardService boardService) {
        this.boardService = boardService;
    }

    @Override
    public List<Coordinate> getPossibleMoves(Piece piece) {
        List<Coordinate> moves = new ArrayList<>();
        moves.addAll(getCoordinatesFromOneDirection(piece, List.of(UP, RIGHT)));
        moves.addAll(getCoordinatesFromOneDirection(piece, List.of(UP, LEFT)));
        moves.addAll(getCoordinatesFromOneDirection(piece, List.of(DOWN, RIGHT)));
        moves.addAll(getCoordinatesFromOneDirection(piece, List.of(DOWN, LEFT)));
        return moves;
    }

    private List<Coordinate> getCoordinatesFromOneDirection(
        Piece piece,
        List<Direction> directions
    ) {
        List<Coordinate> moves = new ArrayList<>();
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

            if (diagonalMove.equals(linearMove)) {
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
