package com.libra.move;

import com.libra.Color;
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

public class KingMoveStrategy implements MoveStrategy {

    @Override
    public List<Coordinate> getPossibleMoves(Piece piece, Board board) {
        List<Coordinate> moves = new ArrayList<>();
        moves.addAll(getCoordinatesFromOneDirection(piece, board, List.of(UP, RIGHT)));
        moves.addAll(getCoordinatesFromOneDirection(piece, board, List.of(RIGHT, DOWN)));
        moves.addAll(getCoordinatesFromOneDirection(piece, board, List.of(DOWN, LEFT)));
        moves.addAll(getCoordinatesFromOneDirection(piece, board, List.of(LEFT, UP)));
        return moves;
    }

    private List<Coordinate> getCoordinatesFromOneDirection(
        Piece piece,
        Board board,
        List<Direction> directions
    ) {
        List<Coordinate> moves = new ArrayList<>();
        Color color = piece.getColor();
        Coordinate previousMove = piece.getCoordinate();
        for (Direction direction : directions) {
            Coordinate currentMove = previousMove.movePieceAccordingToDirection(color, direction);
            Tile currentTile = board.getTileByCoordinate(currentMove);
            if (!currentMove.equals(previousMove) &&
                (currentTile.isTileAvailable() || currentTile.isAvailableForAttack(color))
            ) {
                moves.add(currentMove);
            }
            previousMove = currentMove;
        }

        return moves;
    }
}
