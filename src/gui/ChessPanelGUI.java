package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import game.ChessBoard;
import game.ChessGame;
import piece.Piece;
import piece.PieceColor;
import piece.Position;

public class ChessPanelGUI extends JPanel {
    private final ChessSquare[][] squares = new ChessSquare[8][8];
    private final ChessGame game = new ChessGame();

    public ChessPanelGUI() {
        initBoard();
    }

    private void initBoard() {
        setLayout(new GridLayout(8, 8));

        for (int row = 0; row < squares.length; row++) {
            for (int col = 0; col < squares[row].length; col++) {
                final int finalRow = row;
                final int finalCol = col;
                ChessSquare square = new ChessSquare(row, col);
                square.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        handleSquareClick(finalRow, finalCol);
                    }
                });
                add(square);
                squares[row][col] = square;
            }
        }
        updateBoard();
    }

    private void updateBoard() {
        ChessBoard board = game.getBoard();

        for (int row = 0; row < squares.length; row++) {
            for (int col = 0; col < squares[row].length; col++) {
                Piece piece = board.getPiece(new Position(row, col));
                if (piece != null) {
                    Image icon = piece.getImage();
                    squares[row][col].setIcon(new ImageIcon(icon));
                } else {
                    squares[row][col].setIcon(null);
                }
            }
        }
    }

    private void handleSquareClick(int row, int col) {
        boolean moveMade = game.handleSquareClick(row, col);
        if (moveMade) {
            checkGameState();
            checkGameOver();
            updateBoard();
        }
        updateBoard();
    }

    private void checkGameState() {
        PieceColor currentPieceColor = game.getCurrentPieceColor();
        PieceColor otherPieceColor = currentPieceColor == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;

        boolean inCurrentCheck = game.isInCheck(currentPieceColor);
        boolean inOtherCheck = game.isInCheck(otherPieceColor);

        if (inCurrentCheck) {
            System.out.println(currentPieceColor + " is in check!");
        } else if (inOtherCheck) {
            System.out.println(otherPieceColor + " is in check!");
        }
    }

    private void checkGameOver() {
        PieceColor currentPieceColor = game.getCurrentPieceColor();
        PieceColor otherPieceColor = currentPieceColor == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;


        if (game.isCheckmate(currentPieceColor)) {
            System.out.println(currentPieceColor + " is checkmated!");
            game.resetGame();
        } else if (game.isCheckmate(otherPieceColor)) {
            System.out.println(otherPieceColor + " is checkmated!");
            game.resetGame();
        }
    }
}
