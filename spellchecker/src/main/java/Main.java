/**
 * Main.
 *
 * Created by ryanbadilla on 6/1/17.
 */
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        // invalid argument
        if (args.length != 1) {
            System.out.println(Constants.ERR_USAGE_STRING);
        } else {

            // get the words list.
            String filePath = findWordsFilePath();

            // could not find a valid file name for words. Exit.
            if (filePath == null) {
                System.out.println(Constants.ERR_WORDS_FILE_NOT_FOUND);
                System.exit(-1);
            }

            // found a valid file name. Create SpellChecker and make a word suggestion.
            try {
                SpellChecker spellChecker = new SpellChecker(filePath);
                System.out.println(spellChecker.spellCheckSuggest(args[0], Constants.NO_SUGGESTION_RESPONSE));

            } catch (IOException e) {
                System.out.println("Error: " + e.getLocalizedMessage());
                System.exit(-1);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: SpellCheckSuggest method received null values.");
                System.exit(-1);
            }
        }
    }

    /**
     * findWordsFilePath iterates through Constants.WORDS_FILE_PATH to find a possible filePath containing
     * a list of English words.
     *
     * @return a valid filePath if exists, else null.
     */
    protected static String findWordsFilePath() {
        for (int index = 0; index < Constants.WORDS_FILE_PATHS.length; index++) {
            File file = new File(Constants.WORDS_FILE_PATHS[index]);
            if (file.exists())
                return Constants.WORDS_FILE_PATHS[index];
        }

        return null;
    }
}
