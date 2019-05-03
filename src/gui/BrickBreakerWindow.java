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
        if (keyPressed == 65) // Move Left
            game.setMovement('l');
        else if (keyPressed == 68) // Move Right
            game.setMovement('r');
        if (keyPressed == 87) // Launches the ball
            game.launchBall();
        if (keyPressed == 82) // Restarts the game
            game.restart(true);
        if (keyPressed == 72) // Brings up the highscore menu
            game.showHighscores();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 65) // Move Left
            game.setMovement('g');
        if (keyEvent.getKeyCode() == 68) // Move Right
            game.setMovement('g');
    }
}