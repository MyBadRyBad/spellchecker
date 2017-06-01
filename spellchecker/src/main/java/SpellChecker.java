import info.debatty.java.stringsimilarity.Levenshtein;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * SpellChecker generates and contains a dictionary of word patterns associated with words. The dictionary is used
 * to find suggested words for spellcheck.
 *
 * Created by ryanbadilla on 6/1/17.
 */
public class SpellChecker {
    private PatternBuilder patternBuilder = null;
    HashMap<String, ArrayList<String>> dictionary = null;

    /**
     * Default Constructor for SpellChecker.
     */
    public SpellChecker() {
        patternBuilder = new PatternBuilder();
    }

    /**
     * Constructor for SpellChecker using a given filePath for a list of english words.
     *
     * @param filePath the filePath containing a list of English words.
     * @throws IOException filePath is not valid.
     */
    public SpellChecker(String filePath) throws IOException {
        patternBuilder = new PatternBuilder();
        dictionary = generateDictionary(filePath);
    }

    /**
     * generateDictionary generates a hashmap containing keys associated with a word pattern, and a list of values
     * consisting of words that match the associated word pattern.
     *
     * @param filePath the filePath containing a list of english words.
     * @return a hashmap containing keys associated with a word pattern with values consisting of a list of words that
     * match the associated word pattern.
     * @throws IOException filePath is not valid.
     */
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

    /**
     * spellCheckSuggest suggests a spell-checked version of a given word.
     *
     * @param word a word to spell-check.
     * @param noMatchResponse response to give if the method could not find a valid spell-check correction.
     * @param d a hashmap containing keys associated with a word pattern with values consisting of a list of words that
     * match the associated word pattern.
     * @return the spell-check correction if a spell-check version could be found, else return noMatchResponse string.
     */
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

    /**
     * spellCheckSuggest suggests a spell-checked version of a given word.
     *
     * @param word a word to spell-check.
     * @param noMatchResponse response to give if the method could not find a valid spell-check correction.
     * @return the spell-check correction if a spell-check version could be found, else return noMatchResponse string.
     */
    public String spellCheckSuggest(String word, String noMatchResponse) {
        return spellCheckSuggest(word, noMatchResponse, dictionary);
    }

    /**
     * Setter method for SpellChecker's dictionary.
     *
     * @param d the new dictionary to set.
     */
    public void setDictionary(HashMap<String, ArrayList<String>> d) {
        dictionary = d;
    }

    /**
     * Getter method for SpellChecker's dictionary.
     *
     * @return SpellChecker's dictionary.
     */
    public HashMap<String, ArrayList<String>> getDictionary() {
        return dictionary;
    }

}
