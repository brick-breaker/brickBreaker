package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

// white = 0 blue = 5 red = 2  orange = 4 yellow = 6 green = 3 purple = 1
// length of each rectangle 102.857
// width of each rectangle 50
public class GameWindow extends JFrame {

    int xTopLeft = 0;
    int yTopLeft = 0;
    int[][] blockLocations = new int[14][6];

    public GameWindow() {
            super("canvas");

            // create a empty canvas
            Canvas c = new Canvas() {

                // paint the canvas
                public void paint(Graphics g)
                {
                    for(int i = 0; i < level1().length; i++){
                        for(int j = 0; j < level1()[0].length; j++){

                            if(level1()[i][j] == 0)
                                g.setColor(Color.white);
                            if(level1()[i][j] == 1)
                                g.setColor(Color.magenta);
                            if(level1()[i][j] == 2)
                                g.setColor(Color.red);
                            if(level1()[i][j] == 3)
                                g.setColor(Color.green);
                            if(level1()[i][j] == 4)
                                g.setColor(Color.orange);
                            if(level1()[i][j] == 5)
                                g.setColor(Color.blue);
                            if(level1()[i][j] == 6)
                                g.setColor(Color.yellow);

                            g.fillRect(xTopLeft, yTopLeft, 102, 50);
                            xTopLeft = xTopLeft + 102;
                            // Goal is to create the length of each block with


                        }
                        yTopLeft = yTopLeft + 50;
                        xTopLeft = 0;
                    }



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
            setSize(1428, 900);
            show();
    }

    public int[][] level1(){
        int[][] colors = {{2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4},
                          {4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4, 2},
                          {2, 6, 5, 6, 5, 6, 5, 6, 5, 6, 5, 6, 5, 4},
                          {1, 0, 3, 0, 1, 0, 3, 0, 1, 0, 3, 0, 1, 0},
                          {0, 1, 0, 3, 0, 1, 0, 3, 0, 1, 0, 3, 0, 1},
                          {2, 6, 5, 6, 5, 6, 5, 6, 5, 6, 5, 6, 5, 4}};
        return colors;


    }


}
