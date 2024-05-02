package com.libra.service;

import com.libra.tile.Tile;

public class TileService {

    public void clearTile(Tile tile) {
        tile.removePieceLabel();
        tile = null;
    }
}
