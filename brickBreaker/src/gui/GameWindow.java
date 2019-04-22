package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

// white = 0 blue = 5 red = 2  orange = 4 yellow = 6 green = 3 purple = 1

public class GameWindow extends JFrame {

    public GameWindow() {
            super("canvas");

            // create a empty canvas
            Canvas c = new Canvas() {

                // paint the canvas
                public void paint(Graphics g)
                {
                    // set color to red
                    //g.setColor(Color.red);

                    // set Font
                    //g.setFont(new Font("Bold", 1, 20));

                    // draw a string
                    //g.drawString("This is a canvas", 100, 100);
                }
            };

            // set background
            c.setBackground(Color.black);

            add(c);
            setSize(1440, 900);
            show();
    }

    public void level1(){
        int[][] colors = {{2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4},
                          {4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2},
                          {2, 6, 5, 6, 5, 6, 5, 6, 5, 6, 5, 6, 5, 4},
                          {1, 0, 3, 0, 1, 0, 3, 0, 1, 0, 3, 0, 1, 0},
                          {0, 1, 0, 3, 0, 1, 0, 3, 0, 1, 0, 3, 0, 1},
                          {}};


    }


}
