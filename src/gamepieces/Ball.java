package gamepieces;

public class Ball extends Gamepiece {
    public boolean isLaunched;
    public double dx,dy;

    public Ball() {
        super();
        initVariables();
    }

    public Ball(int defaultX, int defaultY, int width, int height) {
        super(defaultX,defaultY,width,height);
        initVariables();
    }

    private void initVariables() {
        dx = 0;
        dy = 0;
        isLaunched = false;
    }

    public void translatePosition() {
        translatePosition((int) dx,(int) dy);
    }
}
