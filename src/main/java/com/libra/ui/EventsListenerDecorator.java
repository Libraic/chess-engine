package com.libra.ui;

import com.libra.exception.ChessEngineException;
import com.libra.move.MoveStrategyContext;
import com.libra.piece.Rank;
import com.libra.service.BoardService;
import com.libra.service.ColorService;
import com.libra.service.LoggerService;
import com.libra.service.MovementService;
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
    private final MovementService movementService;

    public EventsListenerDecorator(BoardService boardService) {
        this.boardService = boardService;
        this.colorService = new ColorService();
        this.movementService = new MovementService();
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
                LoggerService.info("A tile was clicked.");
                List<Coordinate> currentlyActivePiecePossibleMoves = movementService
                    .getCurrentlyActivePiecePossibleMoves();
                PieceLabel currentlyActivePiece = movementService.getCurrentlyActivePiece();
                if (tile.isTileCoordinatesInPossibleMoves(currentlyActivePiecePossibleMoves) &&
                    tile.isTileAvailableForSelectedPiece(currentlyActivePiece)
                ) {
                    movementService.removePieceLabelOfCurrentlyActiveTile();
                    currentlyActivePiece.getPiece().setCoordinate(tile.getCoordinate());
                    if (currentlyActivePiece.getPiece().getRank() == Rank.KING) {
                        boardService.setKingPositionBasedOnColor(
                            tile.getCoordinate(),
                            currentlyActivePiece.getPieceColor()
                        );
                    }
                    handleCheck();
                    tile.addPieceLabel(currentlyActivePiece);
                    movementService.setCurrentlyActivePiece(null);
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
        if (!movementService.getCurrentlyActivePiecePossibleMoves().contains(tile.getCoordinate())) {
            return;
        }

        movementService.removePieceLabelOfCurrentlyActiveTile();
        movementService.setCoordinateOfCurrentlyActivePiece(tile.getCoordinate());
        tile.removePieceLabel();
        tile.addPieceLabel(movementService.getCurrentlyActivePiece());
        movementService.setCurrentlyActivePiece(null);
        colorService.clearFocusedColorsOnTheBoard(boardService.getTiles());
        boardService.changeTurn();
        movementService.clearMoves();
        gameWindow.repaint();
    }

    private void handlePieceSelection(PieceLabel pieceLabel, Tile tile) {
        LoggerService.info(String.format("%s %s was selected.", pieceLabel.getPiece().getColor(), pieceLabel.getPiece().getRank()));
        colorService.clearFocusedColorsOnTheBoard(boardService.getTiles());
        movementService.setCurrentlyActivePiecePossibleMoves(moveStrategyContext
            .getMoveStrategy(pieceLabel.getPiece().getRank())
            .getPossibleMoves(pieceLabel.getPiece())
        );
        for (Coordinate move : movementService.getCurrentlyActivePiecePossibleMoves()) {
            Tile potentialTile = boardService.getTileByCoordinate(move);
            potentialTile.setBackground(colorService.getTileBackgroundColorOnShowingMoves(potentialTile.getColor()));
        }
        tile.setBackground(colorService.getTileBackgroundColorOnShowingMoves(tile.getColor()));
        movementService.setCurrentlyActivePiece(pieceLabel);
        movementService.setCurrentlyActiveTile(tile);
    }

    private void handleCheck() {
        System.out.println("Potential future moves");
        List<Coordinate> potentialFutureMoves = moveStrategyContext
            .getMoveStrategy(movementService.getCurrentlyActivePiece().getPiece().getRank())
            .getPossibleMoves(movementService.getCurrentlyActivePiece().getPiece());
        System.out.println("Finished Potential future moves");
        Coordinate kingPosition = boardService.getKingPositionBasedOnColor(movementService.getCurrentlyActivePiece().getPieceColor());
        Tile tile = boardService.getTileByCoordinate(kingPosition);
        tile.setBackground(colorService.getTileBackgroundColorOnCheckOrClear(
            potentialFutureMoves.contains(kingPosition),
            tile.getColor())
        );
    }
}
