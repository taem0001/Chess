package gui;

import javax.swing.*;

public class AppGUI {
    private static void createGUI() {
        JFrame frame = new JFrame("Chess game");
        ChessPanelGUI panel = new ChessPanelGUI();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }
}