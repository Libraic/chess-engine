package com.libra;

import com.libra.board.Board;
import com.libra.service.BoardService;
import com.libra.service.ColorService;
import com.libra.ui.GameWindow;

public class Main {
    public static void main(String[] args) {
        BoardService boardService = new BoardService(new Board());
        GameWindow gameWindow = new GameWindow(boardService);
        gameWindow.show();
    }
}