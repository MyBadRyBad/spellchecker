import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ryanbadilla on 6/1/17.
 */
class SpellCheckerTest {
    @Test
    void generateDictionary() throws IOException{
        /*************
         * list of test words
         * ----------
         * apple
         * banana
         * orange
         * onion
         * job
         * boat
         * beat
         * beet
         * bee
         * be
         * conspiracy
         * fire
         * flyer
         * fly
         *************/

        /************
         * expected dictionary
         * ------------
         * "-pl-" => {apple}
         * "b-n-n-" => {banana}
         * "-r-ng-" => {orange}
         * "-n-n" => {onion}
         * "j-b" => {job}
         * "b-t" => {boat, beat, beet}
         * "b-" => {bee, be}
         * "c-nsp-r-cy" => {conspiracy}
         * "f-r-" => {fire}
         * "fly-r" => {flyer}
         * "fly" => {fly}
         ***********/

        SpellChecker spellChecker = new SpellChecker();
        HashMap<String, ArrayList<String>> dictionary = spellChecker.generateDictionary("src/main/resources/test_words.txt");

        // ensure dictionary is not null
        assertNotNull(dictionary);

        // dictionary should contain 11 elements
        assertEquals(11, dictionary.size(), "Test Dictionary contains 11 elements");

        // dictionary contains key/value for "apple"
        assertTrue(dictionary.containsKey("-pl-"), "dictionary contains \"-pl-\" key");
        ArrayList<String> a1 = dictionary.get("-pl-");
        assertEquals(1, a1.size(), "\"-pl-\" key has 1 object");
        assertTrue(a1.contains("apple"), "\"-pl-\" key contains \"apple\"");

        // dictionary contains key/value for "banana"
        assertTrue(dictionary.containsKey("b-n-n-"), "dictionary contains \"b-n-n-\" key");
        ArrayList<String> a2 = dictionary.get("b-n-n-");
        assertEquals(1, a2.size(), "\"b-n-n-\" key has 1 object");
        assertTrue(a2.contains("banana"), "\"b-n-n-\" key contains \"banana\"");

        // dictionary contains key/value for "orange"
        assertTrue(dictionary.containsKey("-r-ng-"), "dictionary contains \"-r-ng-\" key");
        ArrayList<String> a3 = dictionary.get("-r-ng-");
        assertEquals(1, a3.size(), "\"-r-ng-\" key has 1 object");
        assertTrue(a3.contains("orange"), "\"-r-ng-\" key contains \"orange\"");

        // dictionary contains key/value for "onion"
        assertTrue(dictionary.containsKey("-n-n"), "dictionary contains \"-n-n\" key");
        ArrayList<String> a4 = dictionary.get("-n-n");
        assertEquals(1, a4.size(), "\"-n-n\" key has 1 object");
        assertTrue(a4.contains("onion"), "\"-n-n\" key contains \"onion\"");

        // dictionary contains key/value for "job"
        assertTrue(dictionary.containsKey("j-b"), "dictionary contains \"j-b\" key");
        ArrayList<String> a5 = dictionary.get("j-b");
        assertEquals(1, a5.size(), "\"j-b\" key has 1 object");
        assertTrue(a5.contains("job"), "\"j-b\" key contains \"job\"");

        // dictionary contains key/value for "boat", "beat", "beet"
        assertTrue(dictionary.containsKey("b-t"), "dictionary contains \"b-t\" key");
        ArrayList<String> a6 = dictionary.get("b-t");
        assertEquals(3, a6.size(), "\"b-t\" key has 3 objects");
        assertTrue(a6.contains("boat"), "\"b-t\" key contains \"boat\"");
        assertTrue(a6.contains("beat"), "\"b-t\" key contains \"beat\"");
        assertTrue(a6.contains("beet"), "\"b-t\" key contains \"beet\"");

        // dictionary contains key/value for "bee", "be"
        assertTrue(dictionary.containsKey("b-"), "dictionary contains \"b-\" key");
        ArrayList<String> a7 = dictionary.get("b-");
        assertEquals(2, a7.size(), "\"b-\" key has 2 objects");
        assertTrue(a7.contains("bee"), "\"b-\" key contains \"bee\"");
        assertTrue(a7.contains("be"), "\"b-\" key contains \"be\"");

        // dictionary contains key/value for "conspiracy"
        assertTrue(dictionary.containsKey("c-nsp-r-cy"), "dictionary contains \"c-nsp-r-cy\" key");
        ArrayList<String> a8 = dictionary.get("c-nsp-r-cy");
        assertEquals(1, a8.size(), "\"c-nsp-r-cy\" key has 1 object");
        assertTrue(a8.contains("conspiracy"), "\"c-nsp-r-cy\" key contains \"conspiracy\"");

        // dictionary contains key/value for "fire"
        assertTrue(dictionary.containsKey("f-r-"), "dictionary contains \"f-r-\" key");
        ArrayList<String> a9 = dictionary.get("f-r-");
        assertEquals(1, a9.size(), "\"f-r-\" key has 1 object");
        assertTrue(a9.contains("fire"), "\"f-r-\" key contains \"fire\"");

        // dictionary contains key/value for "flyer"
        assertTrue(dictionary.containsKey("fly-r"), "dictionary contains \"fly-r\" key");
        ArrayList<String> a10 = dictionary.get("fly-r");
        assertEquals(1, a10.size(), "\"fly-r\" key has 1 object");
        assertTrue(a10.contains("flyer"), "\"fly-r\" key contains \"flyer\"");

        // dictionary contains key/value for "fly"
        assertTrue(dictionary.containsKey("fly"), "dictionary contains \"fly\" key");
        ArrayList<String> a11 = dictionary.get("fly");
        assertEquals(1, a11.size(), "\"fly\" key has 1 object");
        assertTrue(a11.contains("fly"), "\"fly\" key contains \"fly\"");

    }

    @Test
    void spellCheckSuggest() throws IOException {
        SpellChecker spellChecker = new SpellChecker(Main.findWordsFilePath());
        String noSuggestion = Constants.NO_SUGGESTION_RESPONSE;

        // test uppercase / lowercase
        assertEquals("inside", spellChecker.spellCheckSuggest("inSIDE", noSuggestion));
        assertEquals("inside", spellChecker.spellCheckSuggest("Inside", noSuggestion));
        assertEquals("inside", spellChecker.spellCheckSuggest("insidE", noSuggestion));
        assertEquals("inside", spellChecker.spellCheckSuggest("INSIDE", noSuggestion));

        // test repeated letters
        assertEquals("job", spellChecker.spellCheckSuggest("jjoobbb", noSuggestion));
        assertEquals("job", spellChecker.spellCheckSuggest("joob", noSuggestion));
        assertEquals("job", spellChecker.spellCheckSuggest("jjob", noSuggestion));
        assertEquals("job", spellChecker.spellCheckSuggest("jobb", noSuggestion));
        assertEquals("job", spellChecker.spellCheckSuggest("joobbbb", noSuggestion));
        assertEquals("sheep", spellChecker.spellCheckSuggest("sheeeeep", noSuggestion));

        // test incorrect vowels
        assertEquals("job", spellChecker.spellCheckSuggest("jub", noSuggestion));
        assertEquals("inside", spellChecker.spellCheckSuggest("insode", noSuggestion));

        // test combinations
        assertEquals("conspiracy", spellChecker.spellCheckSuggest("CUNsperrICY", noSuggestion));
        assertEquals("conspiracy", spellChecker.spellCheckSuggest("ConspiriCY", noSuggestion));
        assertEquals("explore", spellChecker.spellCheckSuggest("eeEExpPLiIrreeee", noSuggestion));
        assertEquals("explore", spellChecker.spellCheckSuggest("ExploreE", noSuggestion));
        assertEquals("people", spellChecker.spellCheckSuggest("peeople", noSuggestion));

        // test correct words
        assertEquals("inside", spellChecker.spellCheckSuggest("inside", noSuggestion));
        assertEquals("job", spellChecker.spellCheckSuggest("job", noSuggestion));
        assertEquals("wake", spellChecker.spellCheckSuggest("wake", noSuggestion));
        assertEquals("bibliography", spellChecker.spellCheckSuggest("bibliography", noSuggestion));
        assertEquals("conspiracy", spellChecker.spellCheckSuggest("conspiracy", noSuggestion));
        assertEquals("explore", spellChecker.spellCheckSuggest("explore", noSuggestion));
        assertEquals("lantern", spellChecker.spellCheckSuggest("lantern", noSuggestion));

        // test no suggestions
        assertEquals(noSuggestion, spellChecker.spellCheckSuggest("sheeple", noSuggestion));
        assertEquals(noSuggestion, spellChecker.spellCheckSuggest("frenemies", noSuggestion));
        assertEquals(noSuggestion, spellChecker.spellCheckSuggest("jorbs", noSuggestion));
        assertEquals(noSuggestion, spellChecker.spellCheckSuggest("floogle", noSuggestion));
    }

    @Test
    void spellCheckSuggest1() throws IOException {
        SpellChecker spellChecker = new SpellChecker();
        HashMap<String, ArrayList<String>> dictionary = spellChecker.generateDictionary(Main.findWordsFilePath());
        String noSuggestion = Constants.NO_SUGGESTION_RESPONSE;

        // test uppercase / lowercase
        assertEquals("inside", spellChecker.spellCheckSuggest("inSIDE", noSuggestion, dictionary));
        assertEquals("inside", spellChecker.spellCheckSuggest("Inside", noSuggestion, dictionary));
        assertEquals("inside", spellChecker.spellCheckSuggest("insidE", noSuggestion, dictionary));
        assertEquals("inside", spellChecker.spellCheckSuggest("INSIDE", noSuggestion, dictionary));

        // test repeated letters
        assertEquals("job", spellChecker.spellCheckSuggest("jjoobbb", noSuggestion, dictionary));
        assertEquals("job", spellChecker.spellCheckSuggest("joob", noSuggestion, dictionary));
        assertEquals("job", spellChecker.spellCheckSuggest("jjob", noSuggestion, dictionary));
        assertEquals("job", spellChecker.spellCheckSuggest("jobb", noSuggestion, dictionary));
        assertEquals("job", spellChecker.spellCheckSuggest("joobbbb", noSuggestion, dictionary));
        assertEquals("sheep", spellChecker.spellCheckSuggest("sheeeeep", noSuggestion, dictionary));

        // test incorrect vowels
        assertEquals("job", spellChecker.spellCheckSuggest("jub", noSuggestion, dictionary));
        assertEquals("inside", spellChecker.spellCheckSuggest("insode", noSuggestion, dictionary));

        // test combinations
        assertEquals("conspiracy", spellChecker.spellCheckSuggest("CUNsperrICY", noSuggestion, dictionary));
        assertEquals("conspiracy", spellChecker.spellCheckSuggest("ConspiriCY", noSuggestion, dictionary));
        assertEquals("explore", spellChecker.spellCheckSuggest("eeEExpPLiIrreeee", noSuggestion, dictionary));
        assertEquals("explore", spellChecker.spellCheckSuggest("ExploreE", noSuggestion, dictionary));
        assertEquals("people", spellChecker.spellCheckSuggest("peeople", noSuggestion, dictionary));

        // test correct words
        assertEquals("inside", spellChecker.spellCheckSuggest("inside", noSuggestion, dictionary));
        assertEquals("job", spellChecker.spellCheckSuggest("job", noSuggestion, dictionary));
        assertEquals("wake", spellChecker.spellCheckSuggest("wake", noSuggestion, dictionary));
        assertEquals("bibliography", spellChecker.spellCheckSuggest("bibliography", noSuggestion, dictionary));
        assertEquals("conspiracy", spellChecker.spellCheckSuggest("conspiracy", noSuggestion, dictionary));
        assertEquals("explore", spellChecker.spellCheckSuggest("explore", noSuggestion, dictionary));
        assertEquals("lantern", spellChecker.spellCheckSuggest("lantern", noSuggestion, dictionary));

        // test no suggestions
        assertEquals(noSuggestion, spellChecker.spellCheckSuggest("sheeple", noSuggestion, dictionary));
        assertEquals(noSuggestion, spellChecker.spellCheckSuggest("frenemies", noSuggestion, dictionary));
        assertEquals(noSuggestion, spellChecker.spellCheckSuggest("jorbs", noSuggestion, dictionary));
        assertEquals(noSuggestion, spellChecker.spellCheckSuggest("floogle", noSuggestion, dictionary));
    }

    @Test
    void setDictionary() throws IOException {
        SpellChecker spellChecker = new SpellChecker();
        HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();

        ArrayList<String> a1 = new ArrayList<String>();
        a1.add("a1_test1");

        ArrayList<String> a2 = new ArrayList<String>();
        a1.add("a2_test1");
        a1.add("a2_test2");

        ArrayList<String> a3 = new ArrayList<String>();
        a1.add("a3_test1");
        a1.add("a3_test2");
        a1.add("a3_test3");

        ArrayList<String> a4 = new ArrayList<String>();
        a1.add("a4_test1");
        a1.add("a4_test2");
        a1.add("a4_test3");
        a1.add("a4_test4");

        dictionary.put("a1_key", a1);
        dictionary.put("a2_key", a2);
        dictionary.put("a3_key", a3);
        dictionary.put("a4_key", a4);

        spellChecker.setDictionary(dictionary);
        assertEquals(dictionary, spellChecker.getDictionary());
    }

    @Test
    void getDictionary() throws IOException {
        SpellChecker spellChecker = new SpellChecker();
        HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();

        ArrayList<String> a1 = new ArrayList<String>();
        a1.add("a1_test1");

        ArrayList<String> a2 = new ArrayList<String>();
        a1.add("a2_test1");
        a1.add("a2_test2");

        ArrayList<String> a3 = new ArrayList<String>();
        a1.add("a3_test1");
        a1.add("a3_test2");
        a1.add("a3_test3");

        ArrayList<String> a4 = new ArrayList<String>();
        a1.add("a4_test1");
        a1.add("a4_test2");
        a1.add("a4_test3");
        a1.add("a4_test4");

        dictionary.put("a1_key", a1);
        dictionary.put("a2_key", a2);
        dictionary.put("a3_key", a3);
        dictionary.put("a4_key", a4);

        spellChecker.setDictionary(dictionary);
        assertEquals(dictionary, spellChecker.getDictionary());

    }

}