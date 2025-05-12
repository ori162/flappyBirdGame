package org.example;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Bird {
    Image birdImage;
    int birdX = FrameGame.WIDTH_SCREAM / 8;
    int birdY = FrameGame.HEIGHT_SCREAM / 2;
    int birdWidth = 34;
    int birdHeight = 24;
    int velocityB = 0;
    int gravity = 2;

    public Bird(Image img){
        this.birdImage = img;
    }

    public void move(){
        this.velocityB += this.gravity;
        this.birdY += this.velocityB;
        this.birdY = Math.max(this.birdY, 0);
        if(this.birdY > FrameGame.HEIGHT_SCREAM + this.birdHeight || this.birdY == 0){
            FlappyGame.isRunning = false;
        }

    }


}
