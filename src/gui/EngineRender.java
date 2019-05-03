package gui;

import gamepieces.Ball;
import gamepieces.Brick;
import gamepieces.Gamepiece;
import gamepieces.Paddle;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class EngineRender {
    // Graphics engine variables
    private Canvas canvas;
    private BufferStrategy bufferStrat;
    private Graphics2D graphics;

    /**
     * Initializes the renderer engine
     * @param c The canvas from the other classes
     */
    EngineRender(Canvas c) {
        this.canvas = c;
        bufferStrat = canvas.getBufferStrategy();
    }

    /**
     * The overall method that renders everything on the game screen
     * @param paddle The paddle object to be rendered
     * @param ball The ball object to be rendered
     * @param brick The array of bricks to be rendered
     */
    void renderScreen(Paddle paddle, Ball ball, Brick[][] brick) {
        // Sets up the graphics system
        graphics = (Graphics2D)bufferStrat.getDrawGraphics();

        // Clears the screen to prepare for redrawing
        clear();

        /////////////////////
        // Draw Stuff Here //
        /////////////////////

        // Renders the paddle
        render(paddle);

        // Renders the ball
        render(ball, true);

        // Renders all of the bricks
        for (Brick[] row : brick) {
            for (Brick col : row) {
                if (!col.isDestroyed)
                    render(col);
            }
        }

        // Renders the text on the screen like the score and the live counter
        graphics.setColor(Color.WHITE);
        graphics.drawString("Score: " + paddle.score, 10,15);
        graphics.drawString("Lives: " + paddle.lives,10,40);

        graphics.dispose();

        bufferStrat.show();

        // Flushes out the graphics, reduces lag on some linux systems
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Renders a gamepiece as a square by default
     * @param gamepiece The gamepiece to be rendered
     */
    private void render(Gamepiece gamepiece) {
        render(gamepiece, false);
    }

    /**
     * Renders a gamepiece as either a circle or a square
     * @param gamepiece The gamepiece to be rendered
     * @param isCircle If it should be rendered as a circle or square
     *                 TRUE - renders the object as a circle
     *                 FALSE - renders the object as a square
     */
    private void render(Gamepiece gamepiece, boolean isCircle) {
        graphics.setColor(gamepiece.getColor());
        if (isCircle)
            graphics.fillOval(gamepiece.getX(), gamepiece.getY(), gamepiece.getWidth(), gamepiece.getHeight());
        else
            graphics.fillRect(gamepiece.getX(), gamepiece.getY(), gamepiece.getWidth(), gamepiece.getHeight());
    }

    /**
     * Clears the screen to set it up for redrawing all of the objects
     */
    private void clear() {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
    }
}
