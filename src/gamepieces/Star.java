package gamepieces;

import java.awt.*;
import java.util.Random;

public class Star extends Gamepiece {
    public Star(Canvas canvas) {
        super(0,0,5,5);
        randomizePositioning(canvas);
        setColor(Color.WHITE);
    }

    private void randomizePositioning(Canvas canvas) {
        Random random = new Random();
        int xPos = random.nextInt(canvas.getWidth());
        int yPos = random.nextInt(canvas.getHeight());

        setPosition(xPos, yPos);
    }
}
