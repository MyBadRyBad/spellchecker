import info.debatty.java.stringsimilarity.Levenshtein;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ryanbadilla on 6/1/17.
 */
public class SpellChecker {
    private PatternBuilder patternBuilder = null;
    HashMap<String, ArrayList<String>> dictionary = null;

    public SpellChecker() {
        patternBuilder = new PatternBuilder();
    }

    public SpellChecker(String filePath) throws IOException {
        patternBuilder = new PatternBuilder();
        dictionary = generateDictionary(filePath);
    }

    public HashMap<String, ArrayList<String>> generateDictionary(String filePath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        HashMap<String, ArrayList<String>> newDictionary = new HashMap<String, ArrayList<String>>();

        String word;

        while ((word = bufferedReader.readLine()) != null) {
            String pattern = patternBuilder.getPattern(word);
            ArrayList<String> list = newDictionary.get(pattern);

            if (list == null) {
                list = new ArrayList<String>();
            }

            list.add(word);
            newDictionary.put(pattern, list);
        }

        bufferedReader.close();

        return newDictionary;
    }

    public String spellCheckSuggest(String word, String noMatchResponse, HashMap<String, ArrayList<String>> d) {

        if (word == null)
            throw new IllegalArgumentException("word parameter cannot be null.");

        if (d == null) {
            throw new IllegalArgumentException("dictionary parameter cannot be null.");
        }

        Levenshtein lev = new Levenshtein();
        String suggestedWord = noMatchResponse;
        String pattern = patternBuilder.getPattern(word);
        ArrayList<String> possibleWords = d.get(pattern);

        if (possibleWords != null) {
            word = word.toLowerCase();

            double currentLevenshteinDist = Double.MAX_VALUE;
            for (String possibleWord : possibleWords) {
                double foundLevenshteinDist = lev.distance(possibleWord, word);
                if (currentLevenshteinDist >= foundLevenshteinDist) {
                    currentLevenshteinDist = foundLevenshteinDist;
                    suggestedWord = possibleWord;

                    if (currentLevenshteinDist == 0) {
                        return suggestedWord;
                    }
                }
            }
        }

        return suggestedWord;
    }

    public String spellCheckSuggest(String word, String noMatchResponse) {
        return spellCheckSuggest(word, noMatchResponse, dictionary);
    }

    public void setDictionary(HashMap<String, ArrayList<String>> d) {
        dictionary = d;
    }

    public HashMap<String, ArrayList<String>> getDictionary() {
        return dictionary;
    }

}
