package piece;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class Queen extends Piece {
    public Queen(Position position, PieceColor color) {
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
        int rowDiff = nPosRow - posRow;
        int colDiff = nPosCol - posCol;

        if (rowDiff == 0) { // Check horizontal movement
            int startCol = Math.min(nPosCol, posCol) + 1;
            int endCol = Math.max(nPosCol, posCol);

            for (int col = startCol; col < endCol; col++) {
                if (board[nPosRow][col] != null) {
                    return false;
                }
            }
        } else if (colDiff == 0) { // Check vertical movement
            int startRow = Math.min(nPosRow, posRow) + 1;
            int endRow = Math.max(nPosRow, posRow);

            for (int row = startRow; row < endRow; row++) {
                if (board[row][nPosCol] != null) {
                    return false;
                }
            }
        } else if (Math.abs(rowDiff) == Math.abs(colDiff)) { // Check diagonal movement
            int rowDir = rowDiff > 0 ? 1 : -1;
            int colDir = colDiff > 0 ? 1 : -1;
            int steps = Math.abs(rowDiff);

            for (int i = 1; i < steps; i++) {
                if (board[i * rowDir + posRow][i * colDir + posCol] != null) {
                    return false;
                }
            }
        } else {
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
        String path = color == PieceColor.WHITE ? "../res/Chess_qlt45.png" : "../res/Chess_qdt45.png";
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}