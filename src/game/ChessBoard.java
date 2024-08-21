package game;

import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.PieceColor;
import piece.Position;
import piece.Queen;
import piece.Rook;

public class ChessBoard {
    private Piece[][] board;

    public ChessBoard() {
        this.board = new Piece[8][8];
        initBoard();
    }

    private void initBoard() {
        // Place rooks on board
        board[0][0] = new Rook(new Position(0, 0), PieceColor.BLACK);
        board[0][7] = new Rook(new Position(0, 7), PieceColor.BLACK);
        board[7][0] = new Rook(new Position(7, 0), PieceColor.WHITE);
        board[7][7] = new Rook(new Position(7, 7), PieceColor.WHITE);

        // Place Knights on board
        board[0][1] = new Knight(new Position(0, 1), PieceColor.BLACK);
        board[0][6] = new Knight(new Position(0, 6), PieceColor.BLACK);
        board[7][1] = new Knight(new Position(7, 1), PieceColor.WHITE);
        board[7][6] = new Knight(new Position(7, 6), PieceColor.WHITE);

        // Place bishops on board
        board[0][2] = new Bishop(new Position(0, 2), PieceColor.BLACK);
        board[0][5] = new Bishop(new Position(0, 5), PieceColor.BLACK);
        board[7][2] = new Bishop(new Position(7, 2), PieceColor.WHITE);
        board[7][5] = new Bishop(new Position(7, 5), PieceColor.WHITE);

        // Place queens on board
        board[0][3] = new Queen(new Position(0, 3), PieceColor.BLACK);
        board[7][3] = new Queen(new Position(7, 3), PieceColor.WHITE);

        // Place kings on board
        board[0][4] = new King(new Position(0, 4), PieceColor.BLACK);
        board[7][4] = new King(new Position(7, 4), PieceColor.WHITE);

        // Place pawns on board
        for (int i = 0; i < board.length; i++) {
            board[1][i] = new Pawn(new Position(1, i), PieceColor.BLACK);
            board[6][i] = new Pawn(new Position(6, i), PieceColor.WHITE);
        }
    }

    public void movePiece(Position start, Position end) {
        int sRow = start.getRow();
        int sCol = start.getCol();
        int eRow = end.getRow();
        int eCol = end.getCol();

        if (board[sRow][sCol] != null && board[sRow][sCol].isValidMove(end, board)) {
            board[eRow][eCol] = board[sRow][sCol];
            board[eRow][eCol].setPosition(end);
            board[sRow][sCol] = null;
        }
    }

    public Piece getPiece(Position position) {
        return board[position.getRow()][position.getCol()];
    } 

    public Piece[][] getBoard() {
        return board;
    }

    public void setPiece(Position position, Piece piece) {
        board[position.getRow()][position.getCol()] = piece;
        if (piece != null) {
            piece.setPosition(position);
        }
    }
}
