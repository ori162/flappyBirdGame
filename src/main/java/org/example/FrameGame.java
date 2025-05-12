package org.example;
import javax.swing.*;
import java.util.Objects;


public class FrameGame extends JFrame {
    ImageIcon img;

    public static int WIDTH_SCREAM = 360;
    public static int HEIGHT_SCREAM = 640;

    public FrameGame(){
        this.setTitle("FLAPPY BIRD");
        this.setSize(WIDTH_SCREAM,HEIGHT_SCREAM );
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/flappybird.png")));
        this.setIconImage(img.getImage());

        this.add(new HomeScreen());

        this.requestFocusInWindow(true);

        this.setVisible(true);


    }
}
