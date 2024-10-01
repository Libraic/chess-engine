package com.libra.move;

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

public class RookMoveStrategy implements MoveStrategy {

    private final BoardService boardService;

    public RookMoveStrategy(BoardService boardService) {
        this.boardService = boardService;
    }

    @Override
    public List<Coordinate> getPossibleMoves(Piece piece) {
        List<Coordinate> moves = new ArrayList<>();
        moves.addAll(getCoordinatesFromOneDirection(piece, UP));
        moves.addAll(getCoordinatesFromOneDirection(piece, DOWN));
        moves.addAll(getCoordinatesFromOneDirection(piece, LEFT));
        moves.addAll(getCoordinatesFromOneDirection(piece, RIGHT));
        return moves;
    }

    private List<Coordinate> getCoordinatesFromOneDirection(Piece piece, Direction direction) {
        List<Coordinate> moves = new ArrayList<>();
        Coordinate currentCoordinate = piece.getCoordinate();
        Coordinate stopCoordinate = Coordinate.getStopCoordinate(direction);
        // TODO: Change while(true) loop for a classic for
        while (true) {
            currentCoordinate = currentCoordinate.movePieceAccordingToDirection(piece.getColor(), direction);
            Tile currentTile = boardService.getTileByCoordinate(currentCoordinate);
            if (stopCoordinate.equalsByColumnOrRowIndex(currentCoordinate) ||
                currentTile.isAvailableForAttack(piece.getColor())
            ) {
                moves.add(currentCoordinate);
                break;
            }

            if (!currentTile.isTileAvailableForSelectedPiece()) {
                break;
            }

            moves.add(currentCoordinate);
        }

        return moves;
    }
}
