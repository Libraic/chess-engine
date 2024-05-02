package com.libra.move;

import com.libra.Color;
import com.libra.exception.ChessEngineException;
import com.libra.piece.Pawn;
import com.libra.piece.Piece;
import com.libra.service.BoardService;
import com.libra.tile.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class PawnMoveStrategy implements MoveStrategy {

    private final BoardService boardService;

    public PawnMoveStrategy(BoardService boardService) {
        this.boardService = boardService;
    }

    // TODO: Implement "En Passant" move
    @Override
    public List<Coordinate> getPossibleMoves(Piece piece) {
        if (!(piece instanceof Pawn pawn)) {
            throw new ChessEngineException("PawnMoveStrategy should only use Pawn instances.");
        }

        Color color = piece.getColor();
        List<Coordinate> possibleMoves = new ArrayList<>();
        Coordinate leftAttackMove = piece.getCoordinate().left(color).up(color);
        Coordinate rightAttackMove = piece.getCoordinate().right(color).up(color);
        if (boardService.getTileByCoordinate(leftAttackMove).isAvailableForAttack(piece.getColor())) {
            possibleMoves.add(leftAttackMove);
        }

        if (boardService.getTileByCoordinate(rightAttackMove).isAvailableForAttack(piece.getColor())) {
            possibleMoves.add(rightAttackMove);
        }

        Coordinate firstMove = piece.getCoordinate().up(color);
        if (boardService.getTileByCoordinate(firstMove).isTileAvailable()) {
            possibleMoves.add(firstMove);
        }

        if (pawn.isWasFirstMoveMade()) {
            Coordinate nextMove = firstMove.up(color);
            if (boardService.getTileByCoordinate(nextMove).isTileAvailable()) {
                possibleMoves.add(nextMove);
            }
        }

        return possibleMoves;
    }
}
