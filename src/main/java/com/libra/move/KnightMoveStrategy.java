package com.libra.move;

import com.libra.Color;
import com.libra.piece.Piece;
import com.libra.service.BoardService;
import com.libra.tile.Coordinate;

import java.util.ArrayList;
import java.util.List;

import static com.libra.utils.Constants.FIRST_ELEMENT;
import static com.libra.utils.Constants.FOURTH_ELEMENT;
import static com.libra.utils.Constants.SECOND_ELEMENT;
import static com.libra.utils.Constants.THIRD_ELEMENT;

public class KnightMoveStrategy implements MoveStrategy {

    private final BoardService boardService;

    public KnightMoveStrategy(BoardService boardService) {
        this.boardService = boardService;
    }

    @Override
    public List<Coordinate> getPossibleMoves(Piece piece) {
        List<Coordinate> moves = new ArrayList<>();
        moves.addAll(moveUpwards(piece));
        moves.addAll(moveDownwards(piece));
        moves.addAll(moveLeft(piece));
        moves.addAll(moveRight(piece));
        return moves;
    }

    private List<Coordinate> moveUpwards(Piece piece) {
        Coordinate firstMove = piece.getCoordinate().up(piece.getColor());
        Coordinate secondMove = firstMove.up(piece.getColor());
        Coordinate leftMove = secondMove.left(piece.getColor());
        Coordinate rightMove = secondMove.right(piece.getColor());
        List<Coordinate> moves = List.of(firstMove, secondMove, leftMove, rightMove);
        return getValidMoves(moves, piece);
    }

    private List<Coordinate> moveDownwards(Piece piece) {
        Coordinate firstMove = piece.getCoordinate().down(piece.getColor());
        Coordinate secondMove = firstMove.down(piece.getColor());
        Coordinate leftMove = secondMove.left(piece.getColor());
        Coordinate rightMove = secondMove.right(piece.getColor());
        List<Coordinate> moves = List.of(firstMove, secondMove, leftMove, rightMove);
        return getValidMoves(moves, piece);
    }

    private List<Coordinate> moveLeft(Piece piece) {
        Coordinate firstMove = piece.getCoordinate().left(piece.getColor());
        Coordinate secondMove = firstMove.left(piece.getColor());
        Coordinate upMove = secondMove.up(piece.getColor());
        Coordinate downMove = secondMove.down(piece.getColor());
        List<Coordinate> moves = List.of(firstMove, secondMove, upMove, downMove);
        return getValidMoves(moves, piece);
    }

    private List<Coordinate> moveRight(Piece piece) {
        Coordinate firstMove = piece.getCoordinate().right(piece.getColor());
        Coordinate secondMove = firstMove.right(piece.getColor());
        Coordinate upMove = secondMove.up(piece.getColor());
        Coordinate downMove = secondMove.down(piece.getColor());
        List<Coordinate> moves = List.of(firstMove, secondMove, upMove, downMove);
        return getValidMoves(moves, piece);
    }

    private List<Coordinate> getValidMoves(List<Coordinate> moves, Piece piece) {
        List<Coordinate> validMoves = new ArrayList<>();
        Coordinate firstMove = moves.get(FIRST_ELEMENT);
        Coordinate secondMove = moves.get(SECOND_ELEMENT);
        Coordinate thirdMove = moves.get(THIRD_ELEMENT);
        Coordinate fourthMove = moves.get(FOURTH_ELEMENT);
        Color color = piece.getColor();
        if (secondMove.equals(firstMove)) {
            return validMoves;
        }

        if (boardService.getTileByCoordinate(thirdMove).isAvailableToTakeOrToAttack(color) &&
            !thirdMove.equals(secondMove)
        ) {
            validMoves.add(thirdMove);
        }

        if (boardService.getTileByCoordinate(fourthMove).isAvailableToTakeOrToAttack(color) &&
            !fourthMove.equals(secondMove)
        ) {
            validMoves.add(fourthMove);
        }

        return validMoves;
    }
}
