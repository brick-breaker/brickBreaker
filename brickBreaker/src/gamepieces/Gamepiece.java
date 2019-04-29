package gamepieces;

public abstract class Gamepiece {
    private int x;
    private int y;
    private int width;
    private int height;

    Gamepiece() {
        x = 0;
        y = 0;
        this.width = 25;
        this.height = 25;
    }

    Gamepiece(int defaultX, int defaultY, int width, int height) {
        x = defaultX;
        y = defaultY;
        this.width = width;
        this.height = height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void translatePosition(int dx, int dy) {
        this.x += dx;
        this.y += dy;
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
