package gamepieces;

public class Paddle extends Gamepiece {
    public boolean moveRight,moveLeft;
    public double movementSpeed;

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
        movementSpeed = 5;
    }
}
