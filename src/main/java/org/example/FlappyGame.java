package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;

public class FlappyGame extends JPanel implements KeyListener, ActionListener {
    private Image backgroundImg;
    private Image birdImage;
    private Image topPipe;
    private Image bottomPipe;

    private Bird bird;
    private ArrayList<Pipe> pipes;

    private int gap;
    private int pipeSpawnInterval = 60;
    private int currentPipeSpeed = 4;
    private int framesSinceLastPipe = 0;

    public static boolean isRunning = true;

    private int GAP_OF_PIPE = 150;

    private double score = 0;
    private Thread gameLoop;

//    private int frameCount = 0; כרגע אין לי שימוש בו אבל אולי לרעיונות בהמשך זה יעזור

    private int miniGap = 60;
    private int upLevelScore = 5;


    public FlappyGame() {

        this.setPreferredSize(new Dimension(FrameGame.WIDTH_SCREAM, FrameGame.HEIGHT_SCREAM));
        this.setFocusable(true);
        this.addKeyListener(this);

        backgroundImg = new ImageIcon(getClass().getResource("/flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/flappybird.png"))).getImage();
        topPipe = new ImageIcon(getClass().getResource("/toppipe.png")).getImage();
        bottomPipe = new ImageIcon(getClass().getResource("/bottompipe.png")).getImage();

        bird = new Bird(birdImage);
        pipes = new ArrayList<>();
        gap = GAP_OF_PIPE;


        gameLoop = new Thread(() -> {
            while (isRunning) {
                bird.move();


                for (int i = 0; i < pipes.size(); i++) {
                    Pipe p = pipes.get(i);
                    p.movePipe();

                    if(!p.scored && p.pipeX + p.pipeWidth < bird.birdX){
                        score += 0.5;
                        p.scored = true;
                    }

                    if (p.pipeX + p.pipeWidth < 0) {
                        pipes.remove(i);
                        i--;
                    }
                }
                checkCollision();


                if (gap > miniGap && (int) score >= upLevelScore) {
                    gap = Math.max(miniGap, gap - 10);
                    upLevelScore += 5;

                    if(currentPipeSpeed < 12){
                        currentPipeSpeed++;
                    }
                    pipeSpawnInterval = Math.max(40, 90 - currentPipeSpeed * 5);
                }

//                frameCount++;

                framesSinceLastPipe ++;

                if (framesSinceLastPipe % pipeSpawnInterval == 0) {
                    framesSinceLastPipe = 0;

                    int topHeight = (int) (Math.random() * 200) + 50;

                    Pipe top = new Pipe(topPipe);
                    top.pipeY = 0;
                    top.pipeHeight = topHeight;
                    top.pipeX = FrameGame.WIDTH_SCREAM;
                    top.speed = currentPipeSpeed;

                    Pipe bottom = new Pipe(bottomPipe);
                    bottom.pipeY = top.pipeHeight + gap;
                    bottom.pipeHeight = FrameGame.HEIGHT_SCREAM - bottom.pipeY;
                    bottom.pipeX = FrameGame.WIDTH_SCREAM;
                    bottom.speed = currentPipeSpeed;

                    pipes.add(top);
                    pipes.add(bottom);
                }
                repaint();

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, FrameGame.WIDTH_SCREAM, FrameGame.HEIGHT_SCREAM, null);
        g.drawImage(birdImage, bird.birdX, bird.birdY, bird.birdWidth, bird.birdHeight, null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.imgPipe, pipe.pipeX, pipe.pipeY, pipe.pipeWidth, pipe.pipeHeight, null);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.ITALIC, 32));

        if(!isRunning){
            g.drawString("Game Over: " + (int) score, 20, 40);
        }
        else {
            g.drawString("Score: " + (int) score, 20, 40);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        bird.move();
        repaint();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!isRunning) {
                restart();
            } else {
                bird.velocityB = -9;
            }
        }
    }


    private void restart() {
        bird = new Bird(birdImage);
        pipes.clear();
        score = 0;
        isRunning = true;
        currentPipeSpeed = 4;
        gap = GAP_OF_PIPE;
        upLevelScore = 5;
        framesSinceLastPipe = 0;

        if (!gameLoop.isAlive()) {
            gameLoop = new Thread(() -> {
                while (isRunning) {
                    bird.move();

                    for (int i = 0; i < pipes.size(); i++) {
                        Pipe p = pipes.get(i);
                        p.movePipe();

                        if(!p.scored && p.pipeX + p.pipeWidth < bird.birdX){
                            score += 0.5;
                            p.scored = true;
                        }

                        if (p.pipeX + p.pipeWidth < 0) {
                            pipes.remove(i);
                            i--;
                        }
                    }
                    checkCollision();

                    if (gap > miniGap && (int) score >= upLevelScore) {
                        gap = Math.max(miniGap, gap - 10);
                        upLevelScore += 5;

                        if(currentPipeSpeed < 12){
                            currentPipeSpeed++;
                        }
                        pipeSpawnInterval = Math.max(40, 90 - currentPipeSpeed * 5);
                    }

//                    frameCount++;
                    framesSinceLastPipe++;
                    if (framesSinceLastPipe % pipeSpawnInterval == 0) {
                        framesSinceLastPipe = 0;
                        int topHeight = (int) (Math.random() * 200) + 50;

                        Pipe top = new Pipe(topPipe);
                        top.pipeY = 0;
                        top.pipeHeight = topHeight;
                        top.pipeX = FrameGame.WIDTH_SCREAM;
                        top.speed = currentPipeSpeed;

                        Pipe bottom = new Pipe(bottomPipe);
                        bottom.pipeY = top.pipeHeight + gap;
                        bottom.pipeHeight = FrameGame.HEIGHT_SCREAM - bottom.pipeY;
                        bottom.pipeX = FrameGame.WIDTH_SCREAM;
                        bottom.speed = currentPipeSpeed;

                        pipes.add(top);
                        pipes.add(bottom);
                    }

                    repaint();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException k) {
                        throw new RuntimeException(k);
                    }
                }
            });
            gameLoop.start();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void checkCollision(){
        Rectangle birdRec = new Rectangle(bird.birdX, bird.birdY, bird.birdWidth, bird.birdHeight);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            Rectangle pipeRec = new Rectangle(pipe.pipeX, pipe.pipeY, pipe.pipeWidth, pipe.pipeHeight);

            if(birdRec.intersects(pipeRec)){
                isRunning = false;
                break;
            }
        }
    }

}
