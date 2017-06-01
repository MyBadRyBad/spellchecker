/**
 * Constants used spellchecker.
 *
 * Created by ryanbadilla on 6/1/17.
 */
public final class Constants {

    /**
     * list of possible file paths for a large list of english words
     */
    public final static String[] WORDS_FILE_PATHS = {
            "/src/main/resources/words.txt",
            "/usr/share/dict/words",
            "/usr/dict/words"
    };

    /**
     * Default suggestion string if spellchecker cannot find a valid word.
     */
    public final static String NO_SUGGESTION_RESPONSE = "NO SUGGESTION";

    /**
     * Default usage string if command line arguments are invalid
     */
    public final static String ERR_USAGE_STRING = "usage: java spellchecker word";

    /**
     * Default Error string if application could not find a file containing the list of english words.
     */
    public final static String ERR_WORDS_FILE_NOT_FOUND = "Error: Could not find valid words file.";


}
