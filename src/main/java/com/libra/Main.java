package com.libra;

import com.libra.board.Board;
import com.libra.tile.ColumnIndex;
import com.libra.tile.Coordinate;
import com.libra.tile.RowIndex;
import com.libra.ui.GameWindow;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        GameWindow gameWindow = new GameWindow(board);
        gameWindow.show();
    }
}