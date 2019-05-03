package gamepieces;

import java.awt.*;

public abstract class Gamepiece {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    /**
     * Initializes the values of the object to the default values
     */
    Gamepiece() {
        x = 0;
        y = 0;
        this.width = 25;
        this.height = 25;
        color = Color.BLUE;
    }

    /**
     * Initializes all of the required values of the object
     * @param defaultX The starting x position to set the object to
     * @param defaultY The starting y position to set the object to
     * @param width The width of the object
     * @param height The height of the object
     */
    Gamepiece(int defaultX, int defaultY, int width, int height) {
        x = defaultX;
        y = defaultY;
        this.width = width;
        this.height = height;
        color = Color.BLUE;
    }

    /**
     * Sets the position of the item on the canvas
     * @param x The x position of the object
     * @param y The y position of the object
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Moves the object in the direction of the values given
     * @param dx The amount to move the object on the x axis
     * @param dy The amount to move the object on the y axis
     */
    public void translatePosition(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Sets the color of the object
     * @param color The color to set the object
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the color of the object
     * Used when displaying the object on a canvas
     * @return The color of the object
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets the x value of the object
     * @return The x value of the object
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y value of the object
     * @return The y value of the object
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the width of the object
     * @return The width of the object
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the object
     * @return The height of the object
     */
    public int getHeight() {
        return height;
    }
}
