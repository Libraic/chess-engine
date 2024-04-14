package com.libra.tile;

import com.libra.Color;
import com.libra.ui.PieceLabel;
import com.libra.utils.Constants;

import javax.swing.*;
import java.awt.*;

import static com.libra.tile.Availability.FREE;
import static com.libra.tile.Availability.TAKEN;
import static com.libra.utils.Constants.WALNUT_COLOR;

public final class Tile extends JPanel {

    private final Color color;
    private Availability availability;
    private PieceLabel pieceLabel;

    public Tile(Color color, Availability availability, PieceLabel pieceLabel) {
        this.color = color;
        this.availability = availability;
        this.pieceLabel = pieceLabel;
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

    public Availability getAvailability() {
        return availability;
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

    private java.awt.Color getTileBackgroundColorOnInit() {
        return color.equals(com.libra.Color.BLACK)
            ? new java.awt.Color(Constants.BEIGE_COLOR)
            : new java.awt.Color(WALNUT_COLOR);
    }
}
