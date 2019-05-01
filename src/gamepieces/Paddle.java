package gamepieces;

public class Paddle extends Gamepiece {
    public boolean moveRight,moveLeft;
    public double dx;
    public final double MAX_SPEED = 15;

    public Paddle() {
        super();
        initVariables();
    }

    public Paddle(int defaultX, int defaultY, int width, int height) {
        super(defaultX,defaultY,width,height);
        initVariables();
    }

    private void initVariables() {
        moveRight = false;
        moveLeft = false;
        dx = 0;
    }
}
