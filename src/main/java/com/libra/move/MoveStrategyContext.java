package com.libra.move;

import com.libra.piece.Rank;
import com.libra.service.BoardService;

import java.util.HashMap;
import java.util.Map;

import static com.libra.piece.Rank.BISHOP;
import static com.libra.piece.Rank.KING;
import static com.libra.piece.Rank.KNIGHT;
import static com.libra.piece.Rank.PAWN;
import static com.libra.piece.Rank.QUEEN;
import static com.libra.piece.Rank.ROOK;

public class MoveStrategyContext {

    private final Map<Rank, MoveStrategy> moveStrategies;

    public MoveStrategyContext(BoardService boardService) {
        moveStrategies = new HashMap<>();
        moveStrategies.put(PAWN, new PawnMoveStrategy(boardService));
        moveStrategies.put(ROOK, new RookMoveStrategy(boardService));
        moveStrategies.put(KNIGHT, new KnightMoveStrategy(boardService));
        moveStrategies.put(BISHOP, new BishopMoveStrategy(boardService));
        moveStrategies.put(KING, new KingMoveStrategy(boardService));
        moveStrategies.put(QUEEN, new QueenMoveStrategy(boardService));
    }

    public MoveStrategy getMoveStrategy(Rank rank) {
        return moveStrategies.get(rank);
    }
}
