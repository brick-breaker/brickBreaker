package main;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SaveLoad {

    /**
     * Saves the scores into a text file
     * @param updatedScores The updated highscores to be saved
     */
    public static void saveScores(int[] updatedScores) {
        try {
            PrintWriter printWriter = new PrintWriter("highscorenumbers.txt",StandardCharsets.UTF_8);

            for (int score : updatedScores) {
                printWriter.println(score);
            }

            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the previous high scores
     * @return An array of the previous high scores
     */
    public static int[] loadScores() {
        int[] highestScores = new int[10];
        try {
            FileReader fileReader = new FileReader("highscorenumbers.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            for (int i = 0; i < highestScores.length; i++) {
                line = bufferedReader.readLine();
                if (line != null)
                    highestScores[i] = Integer.parseInt(line);
                else
                    highestScores[i] = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highestScores;
    }
}
