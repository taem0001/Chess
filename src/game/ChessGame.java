package game;

import piece.King;
import piece.Piece;
import piece.PieceColor;
import piece.Position;

public class ChessGame {
    private ChessBoard board;
    private boolean whiteTurn;
    private Position clickedPosition;

    public ChessGame() {
        this.board = new ChessBoard();
        this.whiteTurn = true;
    }

    public void resetGame() {
        this.board = new ChessBoard();
        this.whiteTurn = true;
    }

    public boolean makeMove(Position start, Position end) {
        Piece piece = board.getPiece(start);

        if (piece == null) {
            return false;
        }

        if (piece.getColor() != (whiteTurn ? PieceColor.WHITE : PieceColor.BLACK)) {
            return false;
        }

        if (piece.isValidMove(end, board.getBoard())) {
            board.movePiece(start, end);
            whiteTurn = !whiteTurn;
            return true;
        }

        return false;
    }

    public boolean isInCheck(PieceColor kingColor) {
        Position kingPosition = findKingPosition(kingColor);
        Piece[][] chessBoard = board.getBoard();

        for (int row = 0; row < chessBoard.length; row++) {
            for (int col = 0; col < chessBoard[row].length; col++) {
                Piece piece = chessBoard[row][col];
                if (piece != null && piece.isValidMove(kingPosition, chessBoard) && piece.getColor() != kingColor) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isCheckmate(PieceColor kingColor) {
        if (!isInCheck(kingColor)) {
            return false;
        }

        Position kingPosition = findKingPosition(kingColor);
        Piece[][] chessBoard = board.getBoard();
        King king = (King) board.getPiece(kingPosition);

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                if (rowOffset == 0 && colOffset == 0) {
                    continue;
                }
                Position newPosition = new Position(rowOffset + kingPosition.getRow(),
                        colOffset + kingPosition.getCol());

                if (isPosition(newPosition.getRow(), newPosition.getCol())
                        && !isInCheckAfterMove(kingColor, kingPosition, newPosition)
                        && king.isValidMove(newPosition, chessBoard)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean handleSquareClick(int row, int col) {
        if (clickedPosition == null) {
            Piece piece = board.getPiece(new Position(row, col));
            if (piece != null && piece.getColor() == (whiteTurn ? PieceColor.WHITE : PieceColor.BLACK)) {
                clickedPosition = new Position(row, col);
                return false;
            }
        } else {
            boolean moveMade = makeMove(clickedPosition, new Position(row, col));
            clickedPosition = null;
            return moveMade;
        }
        return false;
    }

    private Position findKingPosition(PieceColor kingColor) {
        Piece[][] chessBoard = board.getBoard();
        for (int row = 0; row < chessBoard.length; row++) {
            for (int col = 0; col < chessBoard[row].length; col++) {
                Piece piece = chessBoard[row][col];
                if (piece instanceof King && piece.getColor() == kingColor) {
                    return new Position(row, col);
                }
            }
        }
        throw new RuntimeException("King not found");
    }

    private boolean isPosition(int row, int col) {
        return !(row > 7 || row < 0 || col > 7 || col < 0);
    }

    private boolean isInCheckAfterMove(PieceColor kingColor, Position from, Position to) {
        Piece tempPiece = board.getPiece(to);
        board.setPiece(to, board.getPiece(from));
        board.setPiece(from, null);

        boolean inCheck = isInCheck(kingColor);

        board.setPiece(from, board.getPiece(to));
        board.setPiece(to, tempPiece);

        return inCheck;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public Position getClickedPosition() {
        return clickedPosition;
    }

    public PieceColor getCurrentPieceColor() {
        return whiteTurn ? PieceColor.WHITE : PieceColor.BLACK;
    }
}