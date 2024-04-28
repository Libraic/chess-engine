package com.libra.tile;

import com.libra.Color;

import java.util.Objects;

import static com.libra.tile.ColumnIndex.A;
import static com.libra.tile.ColumnIndex.H;
import static com.libra.tile.Direction.DOWN;
import static com.libra.tile.Direction.LEFT;
import static com.libra.tile.Direction.RIGHT;
import static com.libra.tile.Direction.UP;
import static com.libra.tile.RowIndex.EIGHT;
import static com.libra.tile.RowIndex.ONE;
import static com.libra.utils.Constants.FIRST_INDEX;

/**
 * The record that represents the coordinate of Tile/Piece that is sitting on it.
 * @param columnIndex the literal that represents the index of the column.
 * @param rowIndex    the numerical value that represents the index of the row.
 */
public record Coordinate(ColumnIndex columnIndex, RowIndex rowIndex) {

    public Coordinate up(Color color) {
        return color.equals(Color.WHITE) ? up() : down();
    }

    public Coordinate down(Color color) {
        return color.equals(Color.WHITE) ? down() : up();
    }

    public Coordinate left(Color color) {
        return color.equals(Color.WHITE) ? left() : right();
    }

    public Coordinate right(Color color) {
        return color.equals(Color.WHITE) ? right() : left();
    }

    private Coordinate up() {
        if (this.rowIndex().equals(EIGHT)) {
            return new Coordinate(columnIndex, rowIndex);
        }

        int currentRowIndex = this.rowIndex().getNumericalPosition();
        return new Coordinate(
            this.columnIndex(),
            RowIndex.getRowIndexByNumericalPosition(currentRowIndex + 1)
        );
    }

    public boolean equalsByColumnOrRowIndex(Coordinate coordinate) {
        return rowIndex == coordinate.rowIndex || columnIndex == coordinate.columnIndex;
    }

    public Coordinate movePieceAccordingToDirection(Color color, Direction direction) {
        if (direction == UP) {
            return up(color);
        }

        if (direction == LEFT) {
            return left(color);
        }

        if (direction == RIGHT) {
            return right(color);
        }

        return down(color);
    }

    public static Coordinate getStopCoordinate(Direction direction) {
        if (direction == UP) {
            return new Coordinate(null, RowIndex.EIGHT);
        }

        if (direction == DOWN) {
            return new Coordinate(null, RowIndex.ONE);
        }

        if (direction == LEFT) {
            return new Coordinate(ColumnIndex.A, null);
        }

        return new Coordinate(ColumnIndex.H, null);
    }

    private Coordinate down() {
        if (this.rowIndex().equals(ONE)) {
            return new Coordinate(columnIndex, rowIndex);
        }

        int currentRowIndex = this.rowIndex().getNumericalPosition();
        return new Coordinate(
            this.columnIndex(),
            RowIndex.getRowIndexByNumericalPosition(currentRowIndex - 1)
        );
    }

    private Coordinate left() {
        if (this.columnIndex().equals(A)) {
            return new Coordinate(columnIndex, rowIndex);
        }

        int currentColumnCharacterUnicode = this.columnIndex().name().charAt(FIRST_INDEX);
        char nextPosition = (char) (currentColumnCharacterUnicode - 1);
        return new Coordinate(
            ColumnIndex.getColumnIndexByColumnLiteral(nextPosition),
            this.rowIndex()
        );
    }

    private Coordinate right() {
        if (this.columnIndex().equals(H)) {
            return new Coordinate(columnIndex, rowIndex);
        }

        int currentColumnCharacterUnicode = this.columnIndex().name().charAt(FIRST_INDEX);
        char nextPosition = (char) (currentColumnCharacterUnicode + 1);
        return new Coordinate(
            ColumnIndex.getColumnIndexByColumnLiteral(nextPosition),
            this.rowIndex()
        );
    }

    @Override
    public String toString() {
        return columnIndex.name() + rowIndex.getNumericalPosition();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return columnIndex == that.columnIndex && rowIndex == that.rowIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(columnIndex, rowIndex);
    }
}
