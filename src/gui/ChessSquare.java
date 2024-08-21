package gui;

import java.awt.*;
import javax.swing.*;

public class ChessSquare extends JButton {
    private int row;
    private int col;

    public ChessSquare(int row, int col) {
        this.row = row;
        this.col = col;
        initButton();
    }

    private void initButton() {
        setPreferredSize(new Dimension(64, 64));

        if ((row + col) % 2 == 0) {
            setBackground(new Color(238, 238, 210));
        } else {
            setBackground(new Color(118, 150, 86));
        }
    }
}
