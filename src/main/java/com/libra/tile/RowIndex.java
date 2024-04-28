package com.libra.tile;

import com.libra.exception.ChessEngineException;

import static com.libra.utils.ExceptionMessages.INVALID_ROW_INDEX;

/**
 * Indicates the index of a row.
 */
public enum RowIndex {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    UNDEFINED(-1);

    private final int numericalPosition;

    RowIndex(int numericalPosition) {
        this.numericalPosition = numericalPosition;
    }

    public int getNumericalPosition() {
        return numericalPosition;
    }

    public static RowIndex getRowIndexByNumericalPosition(int numericalPosition) {
        if (numericalPosition == 1) {
            return ONE;
        } else if (numericalPosition == 2) {
            return TWO;
        } else if (numericalPosition == 3) {
            return THREE;
        } else if (numericalPosition == 4) {
            return FOUR;
        } else if (numericalPosition == 5) {
            return FIVE;
        } else if (numericalPosition == 6) {
            return SIX;
        } else if (numericalPosition == 7) {
            return SEVEN;
        } else if (numericalPosition == 8) {
            return EIGHT;
        } else if (numericalPosition == -1) {
            return UNDEFINED;
        } else {
            throw new ChessEngineException(INVALID_ROW_INDEX);
        }
    }
}
