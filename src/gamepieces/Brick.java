package gamepieces;

public class Brick extends Gamepiece {
    public boolean isDestroyed;
    public Brick() {
        super();
        isDestroyed = false;
    }

    public Brick(int defaultX, int defaultY, int width, int height) {
        super(defaultX,defaultY,width,height);
        isDestroyed = false;
    }
}
