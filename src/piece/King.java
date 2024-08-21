package piece;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class King extends Piece {
    public King(Position position, PieceColor color) {
        super(position, color);
        loadImage();
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        if (position == newPosition) {
            return false;
        }

        int nPosRow = newPosition.getRow();
        int nPosCol = newPosition.getCol();
        int posRow = position.getRow();
        int posCol = position.getCol();
        int rowDiff = Math.abs(nPosRow - posRow);
        int colDiff = Math.abs(nPosCol - posCol);

        if (rowDiff > 1 || colDiff > 1) {
            return false;
        }

        Piece piece = board[nPosRow][nPosCol];

        if (piece == null) {
            return true;
        } else if (piece.color != this.color) {
            return true;
        }

        return false;
    }

    @Override
    protected void loadImage() {
        String path = color == PieceColor.WHITE ? "../res/Chess_klt45.png" : "../res/Chess_kdt45.png";
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
