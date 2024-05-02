package com.libra.ui;

import com.libra.service.BoardService;
import com.libra.service.ColorService;
import com.libra.tile.Tile;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.MatteBorder;

import java.awt.GridLayout;

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

    private final EventsListenerDecorator eventsListenerDecorator;
    private final BoardService boardService;
    private final ColorService colorService;
    public GameWindow(BoardService boardService, ColorService colorService) {
        this.boardService = boardService;
        this.colorService = colorService;
        eventsListenerDecorator = new EventsListenerDecorator(boardService, colorService);
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

        for (Tile tile : boardService.getTiles()) {
            eventsListenerDecorator.createMouseListenerForPiece(tile.getPieceLabel(), gameWindow);
            eventsListenerDecorator.createMouseListenerForTile(tile, gameWindow);
            gameWindow.add(tile);
        }
        gameWindow.setVisible(true);
    }
}
