package com.libra.tile;

import com.libra.exception.ChessEngineException;

import static com.libra.utils.ExceptionMessages.INVALID_COLUMN_INDEX;
import static com.libra.utils.ExceptionMessages.INVALID_ROW_INDEX;

/**
 * Indicates the index of a column.
 */
public enum ColumnIndex {
    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H;

    public static ColumnIndex getColumnIndexByColumnLiteral(char columnLiteral) {
        if (columnLiteral == 'A') {
            return A;
        } else if (columnLiteral == 'B') {
            return B;
        } else if (columnLiteral == 'C') {
            return C;
        } else if (columnLiteral == 'D') {
            return D;
        } else if (columnLiteral == 'E') {
            return E;
        } else if (columnLiteral == 'F') {
            return F;
        } else if (columnLiteral == 'G') {
            return G;
        } else if (columnLiteral == 'H') {
            return H;
        } else {
            throw new ChessEngineException(INVALID_COLUMN_INDEX);
        }
    }
}
