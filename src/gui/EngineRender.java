package gui;

import gamepieces.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class EngineRender {
    // Graphics engine variables
    private Canvas canvas;
    private BufferStrategy bufferStrat;
    private Graphics2D graphics;

    private Star[][] backgroundPieces = new Star[20][5];

    /**
     * Initializes the renderer engine
     * @param c The canvas from the other classes
     */
    EngineRender(Canvas c) {
        this.canvas = c;
        bufferStrat = canvas.getBufferStrategy();
    }

    void updateBackground(Star[][] stars) {
        backgroundPieces = stars;
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

        for (Star[] starRow : backgroundPieces)
            for (Star star : starRow)
                render(star);

        // Renders the balls shadow
        renderShadow(ball, Color.DARK_GRAY, 1, true);
        renderShadow(ball, Color.DARK_GRAY, 2, true);

        // Renders the paddles shadow
        renderShadow(paddle, Color.WHITE, 0);
        renderShadow(paddle, Color.DARK_GRAY, -1);

        // Renders the ball
        render(ball, true);

        // Renders the paddle
        render(paddle);

        // Renders all of the bricks
        for (Brick[] row : brick) {
            for (Brick col : row) {
                if (!col.isDestroyed) {
                    renderShadow(col, Color.DARK_GRAY, 2);
                    render(col);
                }
            }
        }

        // Renders the text on the screen like the score and the live counter label
        graphics.setColor(Color.WHITE);
        graphics.drawString("Score: " + paddle.score, 10,15);
        graphics.drawString("Lives: ",(canvas.getWidth() - (5 + ((2+1)*20)))-2,canvas.getHeight() - 30);

        // Renders the lives left
        graphics.setColor(Color.BLUE);
        for (int i = 0; i < paddle.lives; i++) {
            graphics.setColor(Color.WHITE);
            graphics.fillOval((canvas.getWidth() - (5 + (((3+1)*20)-((i+1)*20))))-2,
                    canvas.getHeight() - 22, 19,19);

            graphics.setColor(Color.BLUE);
            graphics.fillOval(canvas.getWidth() - (5 + (((3+1)*20)-((i+1)*20))),
                    canvas.getHeight() - 20, 15,15);
        }

        graphics.dispose();

        bufferStrat.show();

        // Flushes out the graphics, reduces lag on some linux systems
        Toolkit.getDefaultToolkit().sync();
    }

    public void renderPauseScreen() {
        // Sets up the graphics system
        graphics = (Graphics2D)bufferStrat.getDrawGraphics();

        // Clears the screen to prepare for redrawing
        clear();

        graphics.setColor(Color.WHITE);
        graphics.drawString("Press \"Enter\" to Resume", (canvas.getWidth()/2)-100, canvas.getHeight()/2);

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
     * Renders a shadow of a gamepiece object
     * @param gamepiece The gamepiece to render a shadow for
     * @param color The color of the shadow
     * @param offset The offset of the shadow
     */
    private void renderShadow(Gamepiece gamepiece, Color color, int offset) {
        graphics.setColor(color);
        graphics.fillRect(gamepiece.getX() + offset, gamepiece.getY() + offset,
                gamepiece.getWidth() + 1, gamepiece.getHeight() + 1);
    }

    private void renderShadow(Gamepiece gamepiece, Color color, int offset, int offsetDimensions) {
        graphics.setColor(color);
        graphics.fillRect(gamepiece.getX() + offset, gamepiece.getY() + offset,
                gamepiece.getWidth() + 1 + offsetDimensions, gamepiece.getHeight() + 1 + offsetDimensions);
    }

    private void renderShadow(Gamepiece gamepiece, Color color, int offset, boolean isCircle) {
        graphics.setColor(color);
        graphics.fillOval(gamepiece.getX() + offset, gamepiece.getY() + offset,
                gamepiece.getWidth() + 1, gamepiece.getHeight() + 1);
    }

    /**
     * Clears the screen to set it up for redrawing all of the objects
     */
    private void clear() {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
    }
}
