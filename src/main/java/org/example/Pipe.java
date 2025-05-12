package org.example;

import java.awt.*;

public class Pipe {
    Image imgPipe;
    int pipeX = FrameGame.WIDTH_SCREAM;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    int speed;
    boolean scored = false;


    public Pipe(Image image){
        this.imgPipe = image;
    }

    public void movePipe(){
        this.pipeX -= speed;
    }



}
