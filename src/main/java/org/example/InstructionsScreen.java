package org.example;

import javax.swing.*;
import java.awt.*;

public class InstructionsScreen extends JPanel {
    Image rulesImg;
    String title;

    public InstructionsScreen(JFrame parentFrame){

        rulesImg = new ImageIcon(getClass().getResource("/flappybirdhomescreen.jpg")).getImage();
        this.setLayout(null);
        this.setPreferredSize(new Dimension(FrameGame.WIDTH_SCREAM, FrameGame.HEIGHT_SCREAM));

        JTextArea instructions = new JTextArea(
                "  Welcome to Flappy Bird!\n  \n" +
                        "- Press SPACE to flap.\n \n" +
                        "- Avoid hitting the pipes.\n  \n" +
                        "- The game gets harder as you go!\n \n" +
                        "- Press SPACE again to restart after \n a game over."
        );

        instructions.setForeground(Color.BLACK);
        instructions.setFont(new Font("Arial", Font.ROMAN_BASELINE, 18));
        instructions.setOpaque(false);
        instructions.setEditable(false);
        instructions.setFocusable(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setBounds(40, 260, 300, 260);
        this.add(instructions);

        JButton backButton = new JButton("← Back");
        backButton.setBounds(0, 10, 130, 50);
        styleButton(backButton, Color.WHITE, Color.CYAN);
        backButton.addActionListener(e -> {
            parentFrame.setContentPane(new HomeScreen());
            parentFrame.revalidate();
            parentFrame.repaint();
        });
        this.add(backButton);
    }

    public void paintComponent(Graphics g){
        g.drawImage(rulesImg, 0, 0, FrameGame.WIDTH_SCREAM, FrameGame.HEIGHT_SCREAM, null);
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
