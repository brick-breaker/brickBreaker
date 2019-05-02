package gui;

import gamepieces.Ball;
import gamepieces.Brick;
import gamepieces.Gamepiece;
import gamepieces.Paddle;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class EngineRender {
    private Canvas canvas;
    private BufferStrategy bufferStrat;
    private Graphics2D graphics;

    public EngineRender(Canvas c) {
        this.canvas = c;
        bufferStrat = canvas.getBufferStrategy();
    }

    public void renderScreen(Paddle paddle, Ball ball, Brick[][] brick) {
        graphics = (Graphics2D)bufferStrat.getDrawGraphics();

        clear();

        // Draw Stuff Here
        render(paddle);
        render(ball, true);

        for (Brick[] row : brick) {
            for (Brick col : row) {
                if (!col.isDestroyed)
                    render(col);
            }
        }

        graphics.dispose();

        bufferStrat.show();
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
     */
    private void render(Gamepiece gamepiece, boolean isCircle) {
        graphics.setColor(gamepiece.getColor());
        if (isCircle)
            graphics.fillOval(gamepiece.getX(), gamepiece.getY(), gamepiece.getWidth(), gamepiece.getHeight());
        else
            graphics.fillRect(gamepiece.getX(), gamepiece.getY(), gamepiece.getWidth(), gamepiece.getHeight());
    }

    private void clear() {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
    }
}
