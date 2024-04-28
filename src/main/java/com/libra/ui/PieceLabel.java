package com.libra.ui;

import com.libra.Color;
import com.libra.piece.Piece;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import static com.libra.utils.Constants.BASE_PATH;
import static com.libra.utils.Constants.BLANK_STRING;
import static com.libra.utils.Constants.DASH_LITERAL;
import static com.libra.utils.Constants.IMAGE_EXTENSION;
import static com.libra.utils.Constants.PIECE_HEIGHT;
import static com.libra.utils.Constants.PIECE_WIDTH;
import static java.awt.Image.SCALE_AREA_AVERAGING;

public class PieceLabel extends JLabel {

    private final Piece piece;

    public PieceLabel(Piece piece) {
        super();
        this.setIcon(new ImageIcon(
            new ImageIcon(getImagePath(piece))
                .getImage()
                .getScaledInstance(PIECE_WIDTH, PIECE_HEIGHT, SCALE_AREA_AVERAGING)
            )
        );
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public Color getPieceColor() {
        return piece.getColor();
    }

    private String getImagePath(Piece piece) {
        if (piece == null) {
            return BLANK_STRING;
        }
        String colorName = piece.getColor().name().toLowerCase();
        String pieceRank = piece.getRank().name().toLowerCase();
        return BASE_PATH + colorName + DASH_LITERAL + pieceRank + IMAGE_EXTENSION;
    }


}
