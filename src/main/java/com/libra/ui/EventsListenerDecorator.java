package com.libra.ui;

import com.libra.exception.ChessEngineException;
import com.libra.move.MoveStrategyContext;
import com.libra.piece.Rank;
import com.libra.service.BoardService;
import com.libra.service.ColorService;
import com.libra.tile.Coordinate;
import com.libra.tile.Tile;

import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import static com.libra.utils.ExceptionMessages.INVALID_PIECE_PARENT;

public class EventsListenerDecorator {

    private final MoveStrategyContext moveStrategyContext;
    private final BoardService boardService;
    private final ColorService colorService;
    private PieceLabel currentlyActivePiece;
    private Tile currentlyActiveTile;
    private List<Coordinate> currentlyActivePiecePossibleMoves;

    public EventsListenerDecorator(
        BoardService boardService,
        ColorService colorService
    ) {
        this.boardService = boardService;
        this.colorService = colorService;
        moveStrategyContext = new MoveStrategyContext(boardService);
    }

    public void createMouseListenerForPiece(PieceLabel pieceLabel, JFrame gameWindow) {
        pieceLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!(pieceLabel.getParent() instanceof Tile tile)) {
                    throw new ChessEngineException(INVALID_PIECE_PARENT);
                }

                if (pieceLabel.getPiece().getColor() != boardService.getTurn()) {
                    handleCaptureMove(tile, gameWindow);
                } else {
                    handlePieceSelection(pieceLabel, tile);
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
                    if (currentlyActivePiece.getPiece().getRank() == Rank.KING) {
                        boardService.setKingPositionBasedOnColor(
                            tile.getCoordinate(),
                            currentlyActivePiece.getPieceColor()
                        );
                    }
                    handleCheck();
                    tile.addPieceLabel(currentlyActivePiece);
                    currentlyActivePiece = null;
                    colorService.clearFocusedColorsOnTheBoard(boardService.getTiles());
                    boardService.changeTurn();
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

    private void handleCaptureMove(Tile tile, JFrame gameWindow) {
        if (!currentlyActivePiecePossibleMoves.contains(tile.getCoordinate())) {
            return;
        }
        currentlyActiveTile.removePieceLabel();
        currentlyActiveTile = null;
        currentlyActivePiece.getPiece().setCoordinate(tile.getCoordinate());
        tile.removePieceLabel();
        tile.addPieceLabel(currentlyActivePiece);
        currentlyActivePiece = null;
        colorService.clearFocusedColorsOnTheBoard(boardService.getTiles());
        boardService.changeTurn();
        currentlyActivePiecePossibleMoves.clear();
        gameWindow.repaint();
    }

    private void handlePieceSelection(PieceLabel pieceLabel, Tile tile) {
        colorService.clearFocusedColorsOnTheBoard(boardService.getTiles());
        currentlyActivePiecePossibleMoves = moveStrategyContext
            .getMoveStrategy(pieceLabel.getPiece().getRank())
            .getPossibleMoves(pieceLabel.getPiece());
        for (Coordinate move : currentlyActivePiecePossibleMoves) {
            Tile potentialTile = boardService.getTileByCoordinate(move);
            potentialTile.setBackground(colorService.getTileBackgroundColorOnShowingMoves(potentialTile.getColor()));
        }
        tile.setBackground(colorService.getTileBackgroundColorOnShowingMoves(tile.getColor()));
        currentlyActivePiece = pieceLabel;
        currentlyActiveTile = tile;
    }

    private void handleCheck() {
        List<Coordinate> potentialFutureMoves = moveStrategyContext
            .getMoveStrategy(currentlyActivePiece.getPiece().getRank())
            .getPossibleMoves(currentlyActivePiece.getPiece());
        Coordinate kingPosition = boardService.getKingPositionBasedOnColor(currentlyActivePiece.getPieceColor());
        Tile tile = boardService.getTileByCoordinate(kingPosition);
        tile.setBackground(colorService.getTileBackgroundColorOnCheckOrClear(
            potentialFutureMoves.contains(kingPosition),
            tile.getColor())
        );
    }
}
