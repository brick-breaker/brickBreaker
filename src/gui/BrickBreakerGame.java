package gui;

import gamepieces.Ball;
import gamepieces.Brick;
import gamepieces.Paddle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("FieldCanBeLocal")
public class BrickBreakerGame {

    // Game Engine
    private EngineRender renderer;
    private Canvas gameCanvas;
    private final int FPS = 30;

    // Game Pieces
    private Paddle playerPaddle;
    private Ball ball;
    private Brick[][] brick;

    private final int ROWS_OF_BRICKS = 5;
    private final int COLS_OF_BRICKS = 10;

    BrickBreakerGame(Canvas gameCanvas) {
        this.gameCanvas = gameCanvas;
        renderer = new EngineRender(gameCanvas);

        restart();

        Timer gameTimer = new Timer(1000/FPS, new ActionListener() {
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
        movePaddle();
        playerPaddle.translatePosition((int) playerPaddle.dx,0);

        // Update Ball Location
        if (ball.isLaunched) { // Handle movement if not connected to paddle
            moveBall();
            ball.translatePosition();
        } else { // Handle movement if connected to paddle
            ball.translatePosition((int) playerPaddle.dx, 0);
        }
    }

    private void movePaddle() {
        if (playerPaddle.moveLeft && playerPaddle.dx > -playerPaddle.MAX_SPEED) {
            playerPaddle.dx -= 1;
        } else if (playerPaddle.moveRight && playerPaddle.dx < playerPaddle.MAX_SPEED) {
            playerPaddle.dx += 1;
        } else if (playerPaddle.dx > 0) {
            playerPaddle.dx -= 1;
        } else if (playerPaddle.dx < 0) {
            playerPaddle.dx += 1;
        }

        if (playerPaddle.getX() <= 0 && playerPaddle.dx < 0) {
            playerPaddle.dx = 0;
        } else if (playerPaddle.getX() + playerPaddle.getWidth() >= gameCanvas.getWidth() && playerPaddle.dx > 0) {
            playerPaddle.dx = 0;
        }
    }

    private void moveBall() {
        int topOfBall = ball.getY();
        int botOfBall = ball.getY() + ball.getHeight();
        int leftOfBall = ball.getX();
        int rightOfBall = ball.getX() + ball.getWidth();

        // Checks for collision with the walls
        if (leftOfBall <= 0) {
            ball.dx = -ball.dx;
        } else if (rightOfBall >= gameCanvas.getWidth()) {
            ball.dx = -ball.dx;
        }

        // Checks for collision with ceiling
        if (topOfBall <= 0) {
            ball.dy = -ball.dy;
        } else if (botOfBall >= gameCanvas.getHeight())
            restart();

        // Checks for contact with the paddle
        if (botOfBall >= playerPaddle.getY() &&
                leftOfBall < playerPaddle.getX() + playerPaddle.getWidth() &&
                rightOfBall > playerPaddle.getX()) {
            ball.dy = -ball.dy;
            ball.dx = ((ball.dx > 0 && playerPaddle.dx > 0)||(ball.dx < 0 && playerPaddle.dx < 0)?ball.dx:-ball.dx);
        }

        // Checks for collision with the ball and the bricks
        if (ball.getY() <= (brick[COLS_OF_BRICKS - 1][ROWS_OF_BRICKS - 1].getY() + 50)) {
            for (Brick[] row : brick) {
                for(Brick col : row) {
                    if (!col.isDestroyed) {
                        if (topOfBall <= col.getY() + col.getHeight() &&
                                botOfBall >= col.getY() &&
                                leftOfBall < col.getX() + col.getWidth() &&
                                rightOfBall > col.getX()) {
                            col.isDestroyed = true;

                            if (topOfBall > col.getY()) {
                                // If the top of the ball is > the top of the cube it hit the bottom
                                ball.dy = 0.3 * FPS;
                            } else {
                                // Otherwise the ball hit the top of the cube
                                ball.dy = -0.3 * FPS;
                            }

                            if (leftOfBall > col.getX()) {
                                // If the left of the ball is farther to the right than the left of the cube
                                // The ball hit the right side of the cube
                            }
                        }
                    }
                }
            }
        }
    }

    private void render() {
        renderer.renderScreen(playerPaddle,ball,brick);
    }

    void setMovement(char dir) {
        playerPaddle.moveRight = (dir == 'r');
        playerPaddle.moveLeft = (dir == 'l');
    }

    void launchBall() {
        ball.isLaunched = true;
        ball.dx = 0.3 * FPS;
        ball.dy = 0.3 * FPS;
    }

    void restart() {
        // Initializes Paddle
        playerPaddle = new Paddle((gameCanvas.getWidth()/2),gameCanvas.getHeight()-25,
                gameCanvas.getWidth()/5,25);
        playerPaddle.translatePosition(-(playerPaddle.getWidth()/2),0);

        // Initializes Ball
        ball = new Ball();
        ball.setPosition((gameCanvas.getWidth()/2) - ball.getWidth()/2,
                gameCanvas.getHeight()-50);

        // Initializes Bricks
        brick = new Brick[COLS_OF_BRICKS][ROWS_OF_BRICKS];
        for (int i = 0; i < brick.length; i++) {
            for (int x = 0; x < brick[i].length; x++) {
                brick[i][x] = new Brick(0,0,
                        (gameCanvas.getWidth()/COLS_OF_BRICKS),gameCanvas.getHeight()/20);
                brick[i][x].setPosition(1+i+(brick[i][x].getWidth()*i),
                        x+brick[i][x].getHeight()*x);
                brick[i][x].setColor(Color.RED);
            }
        }
    }
}
