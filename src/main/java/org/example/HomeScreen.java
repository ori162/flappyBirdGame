package org.example;

import javax.swing.*;
import java.awt.*;

public class HomeScreen extends JPanel {
    int widthButton = 200;
    int heightButton = 100;
    int buttonPlaceX = 70;
    Image backgroundImage;

    public HomeScreen(){
        this.setLayout(null);

        backgroundImage = new ImageIcon(getClass().getResource("/flappybirdhomescreen.jpg")).getImage();

        JButton startButton = new JButton("Start");
        startButton.setBounds(buttonPlaceX, 280, widthButton, heightButton);
        styleButton(startButton, Color.WHITE, Color.CYAN);

        this.add(startButton);

        JButton rulesButton = new JButton("Rules");
        rulesButton.setBounds(buttonPlaceX, 340, widthButton, heightButton);
        styleButton(rulesButton, Color.YELLOW, Color.ORANGE);

        this.add(rulesButton);

        startButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

            FlappyGame gamePanel = new FlappyGame();

            topFrame.setContentPane(gamePanel);
            topFrame.revalidate();
            topFrame.repaint();

            SwingUtilities.invokeLater(gamePanel::requestFocusInWindow);
        });

        rulesButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.setContentPane(new InstructionsScreen(topFrame));
            topFrame.revalidate();
            topFrame.repaint();
        });


    }

    public void paintComponent(Graphics g){
        g.drawImage(backgroundImage, 0, 0, FrameGame.WIDTH_SCREAM, FrameGame.HEIGHT_SCREAM, null);
    }

    private void styleButton(JButton button, Color defaultColor, Color hoverColor) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(defaultColor);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(defaultColor);
            }
        });
    }




}
