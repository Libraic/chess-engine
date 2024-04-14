package com.libra.board;

import com.libra.piece.Bishop;
import com.libra.piece.King;
import com.libra.piece.Knight;
import com.libra.piece.Pawn;
import com.libra.piece.Queen;
import com.libra.piece.Rook;
import com.libra.tile.Coordinate;
import com.libra.tile.Tile;
import com.libra.ui.PieceLabel;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.libra.Color.BLACK;
import static com.libra.Color.WHITE;
import static com.libra.tile.Availability.FREE;
import static com.libra.tile.Availability.TAKEN;
import static com.libra.tile.ColumnIndex.A;
import static com.libra.tile.ColumnIndex.B;
import static com.libra.tile.ColumnIndex.C;
import static com.libra.tile.ColumnIndex.D;
import static com.libra.tile.ColumnIndex.E;
import static com.libra.tile.ColumnIndex.F;
import static com.libra.tile.ColumnIndex.G;
import static com.libra.tile.ColumnIndex.H;
import static com.libra.tile.RowIndex.EIGHT;
import static com.libra.tile.RowIndex.FIVE;
import static com.libra.tile.RowIndex.FOUR;
import static com.libra.tile.RowIndex.ONE;
import static com.libra.tile.RowIndex.SEVEN;
import static com.libra.tile.RowIndex.SIX;
import static com.libra.tile.RowIndex.THREE;
import static com.libra.tile.RowIndex.TWO;

/**
 * The board of the game, containing the classic game pieces for both black and white players.
 */
public class Board {

    private final Map<Coordinate, Tile> tiles;

    public Board() {
        tiles = new LinkedHashMap<>();
        tiles.put(
            new Coordinate(A, EIGHT),
            new Tile(WHITE, TAKEN, new PieceLabel(new Rook(BLACK, new Coordinate(A, EIGHT))))
        );
        tiles.put(
            new Coordinate(B, EIGHT),
            new Tile(BLACK, TAKEN, new PieceLabel(new Knight(BLACK, new Coordinate(B, EIGHT))))
        );
        tiles.put(
            new Coordinate(C, EIGHT),
            new Tile(WHITE, TAKEN, new PieceLabel(new Bishop(BLACK, new Coordinate(C, EIGHT))))
        );
        tiles.put(
            new Coordinate(D, EIGHT),
            new Tile(BLACK, TAKEN, new PieceLabel(new Queen(BLACK, new Coordinate(D, EIGHT))))
        );
        tiles.put(
            new Coordinate(E, EIGHT),
            new Tile(WHITE, TAKEN, new PieceLabel(new King(BLACK, new Coordinate(E, EIGHT))))
        );
        tiles.put(
            new Coordinate(F, EIGHT),
            new Tile(BLACK, TAKEN, new PieceLabel(new Bishop(BLACK, new Coordinate(F, EIGHT))))
        );
        tiles.put(
            new Coordinate(G, EIGHT),
            new Tile(WHITE, TAKEN, new PieceLabel(new Knight(BLACK, new Coordinate(G, EIGHT))))
        );
        tiles.put(
            new Coordinate(H, EIGHT),
            new Tile(BLACK, TAKEN, new PieceLabel(new Rook(BLACK, new Coordinate(H, EIGHT))))
        );

        tiles.put(
            new Coordinate(A, SEVEN),
            new Tile(BLACK, TAKEN, new PieceLabel(new Pawn(BLACK, new Coordinate(A, SEVEN))))
        );
        tiles.put(
            new Coordinate(B, SEVEN),
            new Tile(WHITE, TAKEN, new PieceLabel(new Pawn(BLACK, new Coordinate(B, SEVEN))))
        );
        tiles.put(
            new Coordinate(C, SEVEN),
            new Tile(BLACK, TAKEN, new PieceLabel(new Pawn(BLACK, new Coordinate(C, SEVEN))))
        );
        tiles.put(
            new Coordinate(D, SEVEN),
            new Tile(WHITE, TAKEN, new PieceLabel(new Pawn(BLACK, new Coordinate(D, SEVEN))))
        );
        tiles.put(
            new Coordinate(E, SEVEN),
            new Tile(BLACK, TAKEN, new PieceLabel(new Pawn(BLACK, new Coordinate(E, SEVEN))))
        );
        tiles.put(
            new Coordinate(F, SEVEN),
            new Tile(WHITE, TAKEN, new PieceLabel(new Pawn(BLACK, new Coordinate(F, SEVEN))))
        );
        tiles.put(
            new Coordinate(G, SEVEN),
            new Tile(BLACK, TAKEN, new PieceLabel(new Pawn(BLACK, new Coordinate(G, SEVEN))))
        );
        tiles.put(
            new Coordinate(H, SEVEN),
            new Tile(WHITE, TAKEN, new PieceLabel(new Pawn(BLACK, new Coordinate(H, SEVEN))))
        );

        tiles.put(
            new Coordinate(A, SIX),
            new Tile(WHITE, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(B, SIX),
            new Tile(BLACK, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(C, SIX),
            new Tile(WHITE, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(D, SIX),
            new Tile(BLACK, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(E, SIX),
            new Tile(WHITE, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(F, SIX),
            new Tile(BLACK, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(G, SIX),
            new Tile(WHITE, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(H, SIX),
            new Tile(BLACK, FREE, new PieceLabel(null))
        );

        tiles.put(
            new Coordinate(A, FIVE),
            new Tile(BLACK, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(B, FIVE),
            new Tile(WHITE, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(C, FIVE),
            new Tile(BLACK, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(D, FIVE),
            new Tile(WHITE, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(E, FIVE),
            new Tile(BLACK, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(F, FIVE),
            new Tile(WHITE, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(G, FIVE),
            new Tile(BLACK, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(H, FIVE),
            new Tile(WHITE, FREE, new PieceLabel(null))
        );

        tiles.put(
            new Coordinate(A, FOUR),
            new Tile(WHITE, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(B, FOUR),
            new Tile(BLACK, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(C, FOUR),
            new Tile(WHITE, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(D, FOUR),
            new Tile(BLACK, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(E, FOUR),
            new Tile(WHITE, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(F, FOUR),
            new Tile(BLACK, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(G, FOUR),
            new Tile(WHITE, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(H, FOUR),
            new Tile(BLACK, FREE, new PieceLabel(null))
        );

        tiles.put(
            new Coordinate(A, THREE),
            new Tile(BLACK, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(B, THREE),
            new Tile(WHITE, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(C, THREE),
            new Tile(BLACK, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(D, THREE),
            new Tile(WHITE, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(E, THREE),
            new Tile(BLACK, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(F, THREE),
            new Tile(WHITE, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(G, THREE),
            new Tile(BLACK, FREE, new PieceLabel(null))
        );
        tiles.put(
            new Coordinate(H, THREE),
            new Tile(WHITE, FREE, new PieceLabel(null))
        );

        tiles.put(
            new Coordinate(A, TWO),
            new Tile(WHITE, TAKEN, new PieceLabel(new Pawn(WHITE, new Coordinate(A, TWO))))
        );
        tiles.put(
            new Coordinate(B, TWO),
            new Tile(BLACK, TAKEN, new PieceLabel(new Pawn(WHITE, new Coordinate(B, TWO))))
        );
        tiles.put(
            new Coordinate(C, TWO),
            new Tile(WHITE, TAKEN, new PieceLabel(new Pawn(WHITE, new Coordinate(C, TWO))))
        );
        tiles.put(
            new Coordinate(D, TWO),
            new Tile(BLACK, TAKEN, new PieceLabel(new Pawn(WHITE, new Coordinate(D, TWO))))
        );
        tiles.put(
            new Coordinate(E, TWO),
            new Tile(WHITE, TAKEN, new PieceLabel(new Pawn(WHITE, new Coordinate(E, TWO))))
        );
        tiles.put(
            new Coordinate(F, TWO),
            new Tile(BLACK, TAKEN, new PieceLabel(new Pawn(WHITE, new Coordinate(F, TWO))))
        );
        tiles.put(
            new Coordinate(G, TWO),
            new Tile(WHITE, TAKEN, new PieceLabel(new Pawn(WHITE, new Coordinate(G, TWO))))
        );
        tiles.put(
            new Coordinate(H, TWO),
            new Tile(BLACK, TAKEN, new PieceLabel(new Pawn(WHITE, new Coordinate(H, TWO))))
        );

        tiles.put(
            new Coordinate(A, ONE),
            new Tile(BLACK, TAKEN, new PieceLabel(new Rook(WHITE, new Coordinate(A, ONE))))
        );
        tiles.put(
            new Coordinate(B, ONE),
            new Tile(WHITE, TAKEN, new PieceLabel(new Knight(WHITE, new Coordinate(B, ONE))))
        );
        tiles.put(
            new Coordinate(C, ONE),
            new Tile(BLACK, TAKEN, new PieceLabel(new Bishop(WHITE, new Coordinate(C, ONE))))
        );
        tiles.put(
            new Coordinate(D, ONE),
            new Tile(WHITE, TAKEN, new PieceLabel(new Queen(WHITE, new Coordinate(D, ONE))))
        );
        tiles.put(
            new Coordinate(E, ONE),
            new Tile(BLACK, TAKEN, new PieceLabel(new King(WHITE, new Coordinate(E, ONE))))
        );
        tiles.put(
            new Coordinate(F, ONE),
            new Tile(WHITE, TAKEN, new PieceLabel(new Bishop(WHITE, new Coordinate(F, ONE))))
        );
        tiles.put(
            new Coordinate(G, ONE),
            new Tile(BLACK, TAKEN, new PieceLabel(new Knight(WHITE, new Coordinate(G, ONE))))
        );
        tiles.put(
            new Coordinate(H, ONE),
            new Tile(WHITE, TAKEN, new PieceLabel(new Rook(WHITE, new Coordinate(H, ONE))))
        );
    }

    public Map<Coordinate, Tile> getTiles() {
        return tiles;
    }

    public Tile getTileByCoordinate(Coordinate coordinate) {
        return tiles.get(coordinate);
    }
}
