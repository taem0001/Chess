package piece;

import java.awt.*;

public abstract class Piece {
    protected Position position;
    protected PieceColor color;
    protected Image image;
    
    public Piece(Position position, PieceColor color) {
        this.position = position;
        this.color = color;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public PieceColor getColor() {
        return color;
    }

    public Image getImage() {
        return image;
    }

    protected abstract void loadImage();

    public abstract boolean isValidMove(Position newPosition, Piece[][] board);
}
