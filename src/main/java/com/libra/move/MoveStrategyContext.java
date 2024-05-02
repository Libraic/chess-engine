package com.libra.move;

import com.libra.piece.Rank;

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

    public MoveStrategyContext() {
        moveStrategies = new HashMap<>();
        moveStrategies.put(PAWN, new PawnMoveStrategy());
        moveStrategies.put(ROOK, new RookMoveStrategy());
        moveStrategies.put(KNIGHT, new KnightMoveStrategy());
        moveStrategies.put(BISHOP, new BishopMoveStrategy());
        moveStrategies.put(KING, new KingMoveStrategy());
        moveStrategies.put(QUEEN, new QueenMoveStrategy());
    }

    public MoveStrategy getMoveStrategy(Rank rank) {
        return moveStrategies.get(rank);
    }
}
