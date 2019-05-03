package gui;

import gamepieces.Ball;
import gamepieces.Brick;
import gamepieces.Paddle;
import main.SaveLoad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("FieldCanBeLocal")
public class BrickBreakerGame {

    // Game Engine
    private EngineRender renderer;
    private Canvas gameCanvas;
    private final int FPS = 120;

    // Game Pieces
    private Paddle playerPaddle;
    private Ball ball;
    private Brick[][] brick;

    private final int ROWS_OF_BRICKS = 5;
    private final int COLS_OF_BRICKS = 10;

    BrickBreakerGame(Canvas gameCanvas) {
        this.gameCanvas = gameCanvas;
        renderer = new EngineRender(gameCanvas);

        restart(true);

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
        int centerOfBall = ball.getY() + (ball.getHeight() / 2);

        // Checks for collision with the walls
        if (leftOfBall <= 0) {
            ball.dx = 3;
        } else if (rightOfBall >= gameCanvas.getWidth()) {
            ball.dx = -3;
        }

        // Checks for collision with ceiling
        if (topOfBall <= 0) {
            ball.dy = 3;
        } else if (botOfBall >= gameCanvas.getHeight())
            handleDeath();

        // Checks for contact with the paddle
        if (botOfBall >= playerPaddle.getY() &&
                leftOfBall < playerPaddle.getX() + playerPaddle.getWidth() &&
                rightOfBall > playerPaddle.getX()) {
            ball.dy = -3;
            ball.dx = ((ball.dx > 0 && playerPaddle.dx > 0)||
                    (ball.dx < 0 && playerPaddle.dx < 0) ||
                    playerPaddle.dx == 0 ?ball.dx:-ball.dx);
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
                            // Destroys the cube that the ball is in contact with
                            col.isDestroyed = true;

                            // Maths to figure out which way the ball should bounce
                            if (centerOfBall > col.getY() && centerOfBall < col.getY() + col.getHeight()) {
                                if (leftOfBall > col.getX()) { // The ball is on the right side
                                    ball.dx = 3;
                                } else { // The ball is on the left side
                                    ball.dx = -3;
                                }
                            } else if (topOfBall > col.getY()) {
                                // If the top of the ball is > the top of the cube it hit the bottom
                                ball.dy = 3;
                            } else {
                                // Otherwise the ball hit the top of the cube
                                ball.dy = -3;
                            }

                            // Updates the score
                            playerPaddle.score++;
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
        ball.dx = 3;
        ball.dy = 3;
    }

    private void handleDeath() {
        if (playerPaddle.lives < 2)
            restart(true);
        else
            restart(false);
    }

    /**
     * Resets the board for when the character dies
     * @param isDead Whether the character has lost every life
     *               TRUE - resets the lives and the score counter along with positions
     *               FALSE - resets just the positioning of the paddle and ball
     */
    void restart(boolean isDead) {

        // Initializes Ball
        ball = new Ball();
        ball.setPosition((gameCanvas.getWidth()/2) - ball.getWidth()/2,
                gameCanvas.getHeight()-50);

        // Handles fully resetting the game if the player has lost all of their lives
        if (isDead) {
            // Saves the highscores
            updateHighScores();

            // Initializes Bricks
            brick = new Brick[COLS_OF_BRICKS][ROWS_OF_BRICKS];
            for (int i = 0; i < brick.length; i++) {
                for (int x = 0; x < brick[i].length; x++) {
                    brick[i][x] = new Brick(0, 0,
                            (gameCanvas.getWidth() / COLS_OF_BRICKS), gameCanvas.getHeight() / 20);
                    brick[i][x].setPosition(1 + i + (brick[i][x].getWidth() * i),
                            x + brick[i][x].getHeight() * x);
                    brick[i][x].setColor(Color.RED);
                }
            }
        }

        // Initializes Paddle
        if (isDead) {
            playerPaddle = new Paddle((gameCanvas.getWidth() / 2), gameCanvas.getHeight() - 25,
                    gameCanvas.getWidth() / 5, 25);
            playerPaddle.translatePosition(-(playerPaddle.getWidth() / 2), 0);
        } else {
            playerPaddle.setPosition((gameCanvas.getWidth() / 2), gameCanvas.getHeight() - 25);
            playerPaddle.translatePosition(-(playerPaddle.getWidth() / 2), 0);

            playerPaddle.lives--;
        }
    }

    private void updateHighScores() {
        // Checks to make sure the paddle object is created
        if (playerPaddle != null) {
            int[] highscores = SaveLoad.loadScores();
            int[] updatedHighscores = new int[10];

            // Iterates through the current high scores
            for (int i = 0; i < highscores.length; i++) {
                // Checks if the current score is greater than the score being checked
                if (playerPaddle.score > highscores[i]) {
                    // Sets the updated score in the parallel array
                    updatedHighscores[i] = playerPaddle.score;
                    // Bumps the rest of the scores down
                    for (int x = i+1; x < updatedHighscores.length; x++) {
                        updatedHighscores[x] = highscores[x-1];
                    }
                    break;
                } else {
                    updatedHighscores[i] = highscores[i];
                }
            }

            // Actually pushes the update to the file
            SaveLoad.saveScores(updatedHighscores);

            // Displays the highscores
            showHighscores();
        }
    }

    void showHighscores() {
        int[] highscores = SaveLoad.loadScores();
        String scores = "";

        for (int score : highscores) {
            scores += score + "\n";
        }

        JOptionPane.showMessageDialog(gameCanvas, "The Highscores!\n" + scores);
    }
}
