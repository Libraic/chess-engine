package com.libra.move;

import com.libra.Color;
import com.libra.board.Board;
import com.libra.piece.Piece;
import com.libra.tile.Coordinate;

import java.util.ArrayList;
import java.util.List;

import static com.libra.utils.Constants.FIRST_ELEMENT;
import static com.libra.utils.Constants.FOURTH_ELEMENT;
import static com.libra.utils.Constants.SECOND_ELEMENT;
import static com.libra.utils.Constants.THIRD_ELEMENT;

public class KnightMoveStrategy implements MoveStrategy {

    @Override
    public List<Coordinate> getPossibleMoves(Piece piece, Board board) {
        List<Coordinate> moves = new ArrayList<>();
        moves.addAll(moveUpwards(piece, board));
        moves.addAll(moveDownwards(piece, board));
        moves.addAll(moveLeft(piece, board));
        moves.addAll(moveRight(piece, board));
        return moves;
    }

    private List<Coordinate> moveUpwards(Piece piece, Board board) {
        Coordinate firstMove = piece.getCoordinate().up(piece.getColor());
        Coordinate secondMove = firstMove.up(piece.getColor());
        Coordinate leftMove = secondMove.left(piece.getColor());
        Coordinate rightMove = secondMove.right(piece.getColor());
        List<Coordinate> moves = List.of(firstMove, secondMove, leftMove, rightMove);
        return getValidMoves(moves, board, piece);
    }

    private List<Coordinate> moveDownwards(Piece piece, Board board) {
        Coordinate firstMove = piece.getCoordinate().down(piece.getColor());
        Coordinate secondMove = firstMove.down(piece.getColor());
        Coordinate leftMove = secondMove.left(piece.getColor());
        Coordinate rightMove = secondMove.right(piece.getColor());
        List<Coordinate> moves = List.of(firstMove, secondMove, leftMove, rightMove);
        return getValidMoves(moves, board, piece);
    }

    private List<Coordinate> moveLeft(Piece piece, Board board) {
        Coordinate firstMove = piece.getCoordinate().left(piece.getColor());
        Coordinate secondMove = firstMove.left(piece.getColor());
        Coordinate upMove = secondMove.up(piece.getColor());
        Coordinate downMove = secondMove.down(piece.getColor());
        List<Coordinate> moves = List.of(firstMove, secondMove, upMove, downMove);
        return getValidMoves(moves, board, piece);
    }

    private List<Coordinate> moveRight(Piece piece, Board board) {
        Coordinate firstMove = piece.getCoordinate().right(piece.getColor());
        Coordinate secondMove = firstMove.right(piece.getColor());
        Coordinate upMove = secondMove.up(piece.getColor());
        Coordinate downMove = secondMove.down(piece.getColor());
        List<Coordinate> moves = List.of(firstMove, secondMove, upMove, downMove);
        return getValidMoves(moves, board, piece);
    }

    private List<Coordinate> getValidMoves(List<Coordinate> moves, Board board, Piece piece) {
        List<Coordinate> validMoves = new ArrayList<>();
        Coordinate firstMove = moves.get(FIRST_ELEMENT);
        Coordinate secondMove = moves.get(SECOND_ELEMENT);
        Coordinate thirdMove = moves.get(THIRD_ELEMENT);
        Coordinate fourthMove = moves.get(FOURTH_ELEMENT);
        Color color = piece.getColor();
        if (secondMove.equals(firstMove)) {
            return validMoves;
        }

        if (board.getTileByCoordinate(thirdMove).isAvailableToTakeOrToAttack(color) &&
            !thirdMove.equals(secondMove)
        ) {
            validMoves.add(thirdMove);
        }

        if (board.getTileByCoordinate(fourthMove).isAvailableToTakeOrToAttack(color) &&
            !fourthMove.equals(secondMove)
        ) {
            validMoves.add(fourthMove);
        }

        return validMoves;
    }
}
