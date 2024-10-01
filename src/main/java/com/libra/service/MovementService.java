package com.libra.service;

import com.libra.tile.Coordinate;
import com.libra.tile.Tile;
import com.libra.ui.PieceLabel;

import java.util.List;

public class MovementService {

    private PieceLabel currentlyActivePiece;
    private Tile currentlyActiveTile;
    private List<Coordinate> currentlyActivePiecePossibleMoves;

    public PieceLabel getCurrentlyActivePiece() {
        return currentlyActivePiece;
    }

    public Tile getCurrentlyActiveTile() {
        return currentlyActiveTile;
    }

    public List<Coordinate> getCurrentlyActivePiecePossibleMoves() {
        return currentlyActivePiecePossibleMoves;
    }

    public void removePieceLabelOfCurrentlyActiveTile() {
        if (currentlyActiveTile != null) {
            currentlyActiveTile.removePieceLabel();
            currentlyActiveTile = null;
        }
    }

    public void setCurrentlyActivePiece(PieceLabel currentlyActivePiece) {
        this.currentlyActivePiece = currentlyActivePiece;
    }

    public void setCoordinateOfCurrentlyActivePiece(Coordinate coordinate) {
        currentlyActivePiece.getPiece().setCoordinate(coordinate);
    }

    public void clearMoves() {
        currentlyActivePiecePossibleMoves.clear();
    }

    public void setCurrentlyActivePiecePossibleMoves(List<Coordinate> currentlyActivePiecePossibleMoves) {
        this.currentlyActivePiecePossibleMoves = currentlyActivePiecePossibleMoves;
    }

    public void setCurrentlyActiveTile(Tile currentlyActiveTile) {
        this.currentlyActiveTile = currentlyActiveTile;
    }
}
