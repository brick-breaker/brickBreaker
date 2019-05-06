package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BrickBreakerWindow extends JFrame implements KeyListener {

    private BrickBreakerGame game;

    public BrickBreakerWindow() {
        // Creates and sets up parameters for the window
        setTitle("Brick Breaker");
        setSize(new Dimension(1024,576));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        // Creates the canvas
        Canvas gameCanvas = new Canvas();
        gameCanvas.addKeyListener(this);
        add(gameCanvas);
        gameCanvas.createBufferStrategy(3);
        gameCanvas.requestFocus();

        // Initializes the game
        game = new BrickBreakerGame(gameCanvas);
    }


    ///////////////////
    // INPUT HANDLER //
    ///////////////////

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyPressed = keyEvent.getKeyCode();
        if (keyPressed == KeyEvent.VK_A || keyPressed == KeyEvent.VK_KP_LEFT) // Move Left
            game.setMovement('l');
        else if (keyPressed == KeyEvent.VK_D || keyPressed == KeyEvent.VK_KP_RIGHT) // Move Right
            game.setMovement('r');
        if (keyPressed == KeyEvent.VK_W) // Launches the ball
            game.launchBall();
        if (keyPressed == KeyEvent.VK_R) // Restarts the game
            game.restart(true);
        if (keyPressed == KeyEvent.VK_H) // Brings up the highscore menu
            game.showHighscores();
        if (keyPressed == KeyEvent.VK_ENTER) // Enter pressed
            game.unpause();
        if (keyPressed == KeyEvent.VK_ESCAPE) // ESC pressed pause and unpause
            game.pausing();
        if (keyPressed == KeyEvent.VK_P)
            game.growSize();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 65) // Move Left
            game.setMovement('g');
        if (keyEvent.getKeyCode() == 68) // Move Right
            game.setMovement('g');
    }
}