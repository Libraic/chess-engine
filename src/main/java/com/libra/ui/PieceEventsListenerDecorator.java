package com.libra.ui;

import com.libra.board.Board;
import com.libra.exception.ChessEngineException;
import com.libra.tile.Tile;
import com.libra.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.libra.tile.Availability.FREE;
import static com.libra.utils.Constants.BEIGE_COLOR;
import static com.libra.utils.Constants.DARKER_BEIGE_COLOR;
import static com.libra.utils.Constants.DARKER_WALNUT_COLOR;
import static com.libra.utils.ExceptionMessages.INVALID_PIECE_PARENT;

public class PieceEventsListenerDecorator {

    private final Board board;
    private PieceLabel currentlyActivePiece;
    private Tile currentlyActiveTile;

    public PieceEventsListenerDecorator(Board board) {
        this.board = board;
    }

    public void createMouseListenerForPiece(PieceLabel pieceLabel) {
        pieceLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!(pieceLabel.getParent() instanceof Tile tile)) {
                    throw new ChessEngineException(INVALID_PIECE_PARENT);
                }

                clearFocusedColorsOnTheBoard();
                tile.setBackground(getTileBackgroundColorOnShowingMoves(tile.getColor()));
                currentlyActivePiece = pieceLabel;
                currentlyActiveTile = tile;
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void createMouseListenerForTile(Tile tile, JFrame gameWindow) {
        tile.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tile.getAvailability().equals(FREE) && currentlyActivePiece != null) {
                    currentlyActiveTile.removePieceLabel();
                    currentlyActiveTile = null;
                    tile.addPieceLabel(currentlyActivePiece);
                    currentlyActivePiece = null;
                    clearFocusedColorsOnTheBoard();
                    gameWindow.repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private Color getTileBackgroundColorOnInit(com.libra.Color color) {
        return color.equals(com.libra.Color.WHITE) ? new Color(Constants.WALNUT_COLOR) : new Color(BEIGE_COLOR);
    }

    private Color getTileBackgroundColorOnShowingMoves(com.libra.Color color) {
        return color.equals(com.libra.Color.WHITE) ? new Color(DARKER_WALNUT_COLOR) : new Color(DARKER_BEIGE_COLOR);
    }

    private void clearFocusedColorsOnTheBoard() {
        for (Tile tile : board.getTiles().values()) {
            tile.setBackground(getTileBackgroundColorOnInit(tile.getColor()));
        }
    }
}
