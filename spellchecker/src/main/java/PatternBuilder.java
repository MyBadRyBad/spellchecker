/**
 * Created by ryanbadilla on 6/1/17.
 */

import java.util.HashMap;
import java.util.ArrayList;

public class PatternBuilder {

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

    public boolean isVowel(char character) {
        String vowels = "aeiou";

        for (int index = 0; index < vowels.length(); index++) {
            if (vowels.charAt(index) == character)
                return true;
        }

        return false;
    }



}