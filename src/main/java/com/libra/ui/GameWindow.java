package com.libra.ui;

import com.libra.board.Board;
import com.libra.tile.Tile;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.MatteBorder;

import java.awt.GridLayout;
import java.util.ArrayList;

import static com.libra.utils.Constants.BORDER_THICKNESS;
import static com.libra.utils.Constants.COLUMNS_GAP;
import static com.libra.utils.Constants.GAME_WINDOW_HEIGHT;
import static com.libra.utils.Constants.GAME_WINDOW_WIDTH;
import static com.libra.utils.Constants.NUMBER_OF_COLUMNS;
import static com.libra.utils.Constants.NUMBER_OF_ROWS;
import static com.libra.utils.Constants.ROWS_GAP;
import static java.awt.Color.BLACK;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GameWindow {

    private final Board board;
    private final PieceEventsListenerDecorator pieceEventsListenerDecorator;

    public GameWindow(Board board) {
        this.board = board;
        pieceEventsListenerDecorator = new PieceEventsListenerDecorator(board);
    }

    public void show() {
        MatteBorder windowBorder = BorderFactory.createMatteBorder(
            BORDER_THICKNESS,
            BORDER_THICKNESS,
            BORDER_THICKNESS,
            BORDER_THICKNESS,
            BLACK
        );
        JFrame gameWindow = new JFrame();
        gameWindow.setSize(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
        gameWindow.getRootPane().setBorder(windowBorder);
        gameWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameWindow.setLayout(new GridLayout(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, ROWS_GAP, COLUMNS_GAP));

        for (Tile tile : board.getTiles().values()) {
            pieceEventsListenerDecorator.createMouseListenerForPiece(tile.getPieceLabel());
            pieceEventsListenerDecorator.createMouseListenerForTile(tile, gameWindow);
            gameWindow.add(tile);
        }
        gameWindow.setVisible(true);
    }
}
