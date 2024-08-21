package piece;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class Pawn extends Piece {
    public Pawn(Position position, PieceColor color) {
        super(position, color);
        loadImage();
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        if (position == newPosition) {
            return false;
        }

        int forwardDir = color == PieceColor.WHITE ? -1 : 1;
        int nPosRow = newPosition.getRow();
        int nPosCol = newPosition.getCol();
        int posRow = position.getRow();
        int posCol = position.getCol();
        int rowDiff = Math.abs(nPosRow - posRow);
        int colDiff = Math.abs(nPosCol - posCol);

        // Moving the pawn one square forward
        if (rowDiff == 1 && colDiff == 0 && board[nPosRow][nPosCol] == null) {
            return true;
        }

        boolean firstMove = (color == PieceColor.WHITE && posRow == 6) || (color == PieceColor.BLACK && posRow == 1);

        // Initial two squares move
        if (rowDiff == 2 && colDiff == 0 && firstMove && board[posRow + forwardDir][posCol] == null
                && board[nPosRow][nPosCol] == null) {
            return true;
        }

        // Capturing a piece
        if (rowDiff == 1 && colDiff == 1 && board[nPosRow][nPosCol] != null) {
            if (board[nPosRow][nPosCol].color != this.color) {
                return true;
            }
        }

        return false;
    }

    @Override
    protected void loadImage() {
        String path = color == PieceColor.WHITE ? "../res/Chess_plt45.png" : "../res/Chess_pdt45.png";
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
