import java.util.HashMap;
import java.util.ArrayList;

/**
 * PatternBuilder is a helper object that generates a pattern for a given word
 * via ignoring vowels and duplicate characters.
 *
 * Created by ryanbadilla on 6/1/17.
 */
public class PatternBuilder {

    /**
     * getPattern generates a pattern for a given word by first substituting all vowels with the common character '-',
     * then removing duplicate characters. For example, the word "word" will be generated as "w-rd" by going through the
     * process of "word" -> "w-rd". The word "people" will be generated as "p-pl-" by going through the process of
     * "people" -> "p--pl-" -> "p-pl-";
     *
     * @param word the given word to generate a pattern.
     * @return a pattern representation of the given word.
     */
    public String getPattern(String word) {
        // set word to lowercase
        word = word.toLowerCase();

        // use stringbuilder to create mutable string
        StringBuilder newWord = new StringBuilder(word);

        // replace vowels with '-'
        for (int index = 0; index < newWord.length(); index++) {
            if (isVowel(newWord.charAt(index)))
                newWord.setCharAt(index, '-');
        }

        return removeDuplicate(newWord.toString());
    }

    /**
     * removeDuplicate removes duplicate characters occurring characters within a given word.
     *
     * @param word a word to remove duplicates character from.
     * @return a word with duplicates removed.
     */
    public String removeDuplicate(String word) {
        // use stringbuilder to create mutable string
        StringBuilder newWord = new StringBuilder(word);

        // iterate through string and remove duplicate characters
        for (int index = 0; index < newWord.length(); index++) {
            if (index > 0 && newWord.charAt(index) == newWord.charAt(index - 1)) {
                newWord.deleteCharAt(index);
                index--;
            }
        }

        return newWord.toString();
    }

    /**
     * isVowel determines whether or not a character is a vowel.
     *
     * @param character the character to check if it is a vowel.
     * @return true if a vowel, else false
     */
    public boolean isVowel(char character) {
        String vowels = "aeiou";

        for (int index = 0; index < vowels.length(); index++) {
            if (vowels.charAt(index) == character)
                return true;
        }

        return false;
    }



}