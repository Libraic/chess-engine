package com.libra.move;

import com.libra.Color;
import com.libra.board.Board;
import com.libra.exception.ChessEngineException;
import com.libra.piece.Pawn;
import com.libra.piece.Piece;
import com.libra.tile.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class PawnMoveStrategy implements MoveStrategy {

    public PawnMoveStrategy() {
    }

    // TODO: Implement "En Passant" move
    @Override
    public List<Coordinate> getPossibleMoves(Piece piece, Board board) {
        if (!(piece instanceof Pawn pawn)) {
            throw new ChessEngineException("PawnMoveStrategy should only use Pawn instances.");
        }

        Color color = piece.getColor();
        List<Coordinate> possibleMoves = new ArrayList<>();
        Coordinate leftAttackMove = piece.getCoordinate().left(color).up(color);
        Coordinate rightAttackMove = piece.getCoordinate().right(color).up(color);
        if (board.getTileByCoordinate(leftAttackMove).isAvailableForAttack(piece.getColor())) {
            possibleMoves.add(leftAttackMove);
        }

        if (board.getTileByCoordinate(rightAttackMove).isAvailableForAttack(piece.getColor())) {
            possibleMoves.add(rightAttackMove);
        }

        Coordinate firstMove = piece.getCoordinate().up(color);
        if (board.getTileByCoordinate(firstMove).isTileAvailable()) {
            possibleMoves.add(firstMove);
        }

        if (pawn.isWasFirstMoveMade()) {
            Coordinate nextMove = firstMove.up(color);
            if (board.getTileByCoordinate(nextMove).isTileAvailable()) {
                possibleMoves.add(nextMove);
            }
        }

        return possibleMoves;
    }
}
