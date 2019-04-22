package gui;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow() {
            super("canvas");

            // create a empty canvas
            Canvas c = new Canvas() {

                // paint the canvas
                public void paint(Graphics g)
                {
                    // set color to red
                    g.setColor(Color.red);

                    // set Font
                    g.setFont(new Font("Bold", 1, 20));

                    // draw a string
                    g.drawString("This is a canvas", 100, 100);
                }
            };

            // set background
            c.setBackground(Color.blue);

            add(c);
            setSize(1000, 300);
            show();
    }
}
