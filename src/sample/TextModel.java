package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class TextModel {
    private HashMap<String, StatsObj> map;

    public TextModel() {
        map = new HashMap<String, StatsObj>();
    }

    private StatsObj getStatsObjForWord(String word) {
        return null;
    }

    // Adds the observed word pair word and followingWord to the Markov model data
    public void addWordPair(String word, String followingWord) {

    }

    public void loadData(File file) {
        loadData(file.getAbsolutePath());
    }

    public void loadData(String filename) {
        String text = getFileAsString(filename);
        text.replaceAll("\\s\\s+", "");
        String[] words = text.split(" ");

    }

    public String simulateNextWord(String word) {
        return null;
    }

    public static String getFileAsString(String path) {
        StringBuilder b = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();
            while (line != null) {
                b.append(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("Something wrong: " + e.getMessage());
        }

        return b.toString();
    }

    public String[] predictNext(String lastWord) {
        // These will be your predictions!

        String[] out = {"choice 1", "choice 2", "choice 3", "choice 4"};
        return out;
    }
}
