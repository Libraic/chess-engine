package com.libra.tile;

import com.libra.Color;
import com.libra.ui.PieceLabel;
import com.libra.utils.Constants;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.List;

import static com.libra.tile.Availability.FREE;
import static com.libra.tile.Availability.TAKEN;
import static com.libra.utils.Constants.WALNUT_COLOR;

public final class Tile extends JPanel {

    private final Color color;
    private final Coordinate coordinate;
    private Availability availability;
    private PieceLabel pieceLabel;

    public Tile(
        Color color,
        Availability availability,
        Coordinate coordinate,
        PieceLabel pieceLabel
    ) {
        this.color = color;
        this.availability = availability;
        this.pieceLabel = pieceLabel;
        this.coordinate = coordinate;
        this.add(this.pieceLabel);
        this.setPreferredSize(new Dimension(100, 100));
        this.setBackground(getTileBackgroundColorOnInit());
    }

    public Color getColor() {
        return color;
    }

    public PieceLabel getPieceLabel() {
        return pieceLabel;
    }

    public void addPieceLabel(PieceLabel pieceLabel) {
        this.pieceLabel = pieceLabel;
        availability = TAKEN;
        this.add(this.pieceLabel);
    }

    public void removePieceLabel() {
        this.remove(this.pieceLabel);
        availability = FREE;
        this.pieceLabel = null;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean isTileCoordinatesInPossibleMoves(List<Coordinate> possibleMoves) {
        for (Coordinate possibleMove : possibleMoves) {
            if (coordinate.equals(possibleMove)) {
                return true;
            }
        }

        return false;
    }

    public boolean isTileAvailable(PieceLabel pieceLabel) {
        if (pieceLabel == null) {
            return false;
        }
        return (availability == TAKEN && pieceLabel.getPiece().getColor() != pieceLabel.getPiece().getColor()) ||
            availability == FREE;
    }

    public boolean isTileAvailable() {
        return availability == FREE;
    }

    public boolean isAvailableForAttack(Color color) {
        return availability == TAKEN && pieceLabel.getPieceColor() != color;
    }

    private java.awt.Color getTileBackgroundColorOnInit() {
        return color.equals(com.libra.Color.BLACK)
            ? new java.awt.Color(Constants.BEIGE_COLOR)
            : new java.awt.Color(WALNUT_COLOR);
    }
}
