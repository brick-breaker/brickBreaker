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

    public void renderScreen(Paddle paddle, Ball ball, Brick brick) {
        graphics = (Graphics2D)bufferStrat.getDrawGraphics();

        clear();

        // Draw Stuff Here
        render(paddle);

        graphics.dispose();

        bufferStrat.show();
    }

    public void render(Gamepiece gamepiece) {
        graphics.setColor(Color.BLUE);
        graphics.fillRect(gamepiece.getX(), gamepiece.getY(), gamepiece.getWidth(), gamepiece.getHeight());
    }

    public void clear() {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
    }
}
