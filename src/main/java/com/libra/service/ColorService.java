package com.libra.service;

import com.libra.tile.Tile;
import com.libra.utils.Constants;

import java.awt.*;
import java.util.Collection;

import static com.libra.utils.Constants.DARKER_BEIGE_COLOR;
import static com.libra.utils.Constants.DARKER_WALNUT_COLOR;
import static com.libra.utils.Constants.RED_COLOR;
import static com.libra.utils.Constants.WALNUT_COLOR;

public class ColorService {

    public Color getTileBackgroundColorOnInit(com.libra.Color color) {
        return color.equals(com.libra.Color.WHITE) ? new Color(Constants.BEIGE_COLOR) : new Color(WALNUT_COLOR);
    }

    public Color getTileBackgroundColorOnShowingMoves(com.libra.Color color) {
        return color.equals(com.libra.Color.WHITE) ? new Color(DARKER_BEIGE_COLOR) : new Color(DARKER_WALNUT_COLOR);
    }

    public Color getTileBackgroundColorOnCheckOrClear(boolean isInCheck, com.libra.Color color) {
        return isInCheck ? new Color(RED_COLOR) : getTileBackgroundColorOnInit(color);
    }

    public void clearFocusedColorsOnTheBoard(Collection<Tile> tiles) {
        for (Tile tile : tiles) {
            if (!tile.getBackground().equals(new Color(RED_COLOR))) {
                tile.setBackground(getTileBackgroundColorOnInit(tile.getColor()));
            }
        }
    }
}
