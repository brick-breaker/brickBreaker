package gamepieces;

import java.awt.*;

public abstract class Gamepiece {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    Gamepiece() {
        x = 0;
        y = 0;
        this.width = 25;
        this.height = 25;
        color = Color.BLUE;
    }

    Gamepiece(int defaultX, int defaultY, int width, int height) {
        x = defaultX;
        y = defaultY;
        this.width = width;
        this.height = height;
        color = Color.BLUE;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void translatePosition(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
