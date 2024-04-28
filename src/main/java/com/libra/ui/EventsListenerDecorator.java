package com.libra.ui;

import com.libra.board.Board;
import com.libra.exception.ChessEngineException;
import com.libra.move.MoveStrategyContext;
import com.libra.tile.Coordinate;
import com.libra.tile.Tile;
import com.libra.utils.Constants;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import static com.libra.utils.Constants.WALNUT_COLOR;
import static com.libra.utils.Constants.DARKER_WALNUT_COLOR;
import static com.libra.utils.Constants.DARKER_BEIGE_COLOR;
import static com.libra.utils.ExceptionMessages.INVALID_PIECE_PARENT;

public class EventsListenerDecorator {

    private final Board board;
    private final MoveStrategyContext moveStrategyContext;
    private PieceLabel currentlyActivePiece;
    private Tile currentlyActiveTile;
    private List<Coordinate> currentlyActivePiecePossibleMoves;

    public EventsListenerDecorator(Board board) {
        this.board = board;
        moveStrategyContext = new MoveStrategyContext();
    }

    public void createMouseListenerForPiece(PieceLabel pieceLabel, JFrame gameWindow) {
        pieceLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!(pieceLabel.getParent() instanceof Tile tile)) {
                    throw new ChessEngineException(INVALID_PIECE_PARENT);
                }

                if (pieceLabel.getPiece().getColor() != board.getTurn()) {
                    if (!currentlyActivePiecePossibleMoves.contains(tile.getCoordinate())) {
                        return;
                    }
                    currentlyActiveTile.removePieceLabel();
                    currentlyActiveTile = null;
                    currentlyActivePiece.getPiece().setCoordinate(tile.getCoordinate());
                    tile.removePieceLabel();
                    tile.addPieceLabel(currentlyActivePiece);
                    currentlyActivePiece = null;
                    clearFocusedColorsOnTheBoard();
                    board.changeTurn();
                    currentlyActivePiecePossibleMoves.clear();
                    gameWindow.repaint();
                    return;
                }

                clearFocusedColorsOnTheBoard();
                currentlyActivePiecePossibleMoves = moveStrategyContext
                    .getMoveStrategy(pieceLabel.getPiece().getRank())
                    .getPossibleMoves(pieceLabel.getPiece(), board);

                for (Coordinate move : currentlyActivePiecePossibleMoves) {
                    Tile potentialTile = board.getTileByCoordinate(move);
                    potentialTile.setBackground(getTileBackgroundColorOnShowingMoves(potentialTile.getColor()));
                }
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
                if (tile.isTileCoordinatesInPossibleMoves(currentlyActivePiecePossibleMoves) &&
                    tile.isTileAvailable(currentlyActivePiece)
                ) {
                    currentlyActiveTile.removePieceLabel();
                    currentlyActiveTile = null;
                    currentlyActivePiece.getPiece().setCoordinate(tile.getCoordinate());
                    tile.addPieceLabel(currentlyActivePiece);
                    currentlyActivePiece = null;
                    clearFocusedColorsOnTheBoard();
                    board.changeTurn();
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
        return color.equals(com.libra.Color.WHITE) ? new Color(Constants.BEIGE_COLOR) : new Color(WALNUT_COLOR);
    }

    private Color getTileBackgroundColorOnShowingMoves(com.libra.Color color) {
        return color.equals(com.libra.Color.WHITE) ? new Color(DARKER_BEIGE_COLOR) : new Color(DARKER_WALNUT_COLOR);
    }

    private void clearFocusedColorsOnTheBoard() {
        for (Tile tile : board.getTiles().values()) {
            tile.setBackground(getTileBackgroundColorOnInit(tile.getColor()));
        }
    }
}
