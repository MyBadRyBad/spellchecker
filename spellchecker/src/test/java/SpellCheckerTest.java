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
    }

    @Test
    void spellCheckSuggest1() throws IOException {
    }

    @Test
    void setDictionary() throws IOException {
    }

    @Test
    void getDictionary() throws IOException {
    }

}