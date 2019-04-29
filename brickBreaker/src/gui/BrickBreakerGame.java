package gui;

import gamepieces.Ball;
import gamepieces.Brick;
import gamepieces.Paddle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrickBreakerGame {

    // Game Engine
    private EngineRender renderer;
    private Canvas gameCanvas;

    // Game Pieces
    private Paddle playerPaddle;
    private Ball ball;
    private Brick brick;

    BrickBreakerGame(Canvas gameCanvas) {
        this.gameCanvas = gameCanvas;
        renderer = new EngineRender(gameCanvas);

        // Initializes Paddle
        playerPaddle = new Paddle((gameCanvas.getWidth()/2),gameCanvas.getHeight()-25,
                gameCanvas.getWidth()/10,25);
        playerPaddle.translatePosition(-(playerPaddle.getWidth()/2),0);

        Timer gameTimer = new Timer(1000/60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tick();
                render();
            }
        });

        gameTimer.start();
    }

    private void tick() {
        // Update paddle location
        if (playerPaddle.moveLeft)
            playerPaddle.translatePosition((int) -playerPaddle.movementSpeed,0);
        else if (playerPaddle.moveRight)
            playerPaddle.translatePosition((int) playerPaddle.movementSpeed,0);
    }

    private void render() {
        renderer.renderScreen(playerPaddle,ball,brick);
    }

    void setMovement(char dir) {
        playerPaddle.moveRight = (dir == 'r');
        playerPaddle.moveLeft = (dir == 'l');
    }
}
