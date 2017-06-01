import javafx.scene.shape.PathElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ryanbadilla on 6/1/17.
 */
class PatternBuilderTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPattern() {
        PatternBuilder patternBuilder = new PatternBuilder();

        // normal words
        assertEquals("w-rd", patternBuilder.getPattern("word"), "\"word\" => \"w-rd\"");
        assertEquals("j-b", patternBuilder.getPattern("job"), "\"job\" => \"j-b\"");
        assertEquals("b-t", patternBuilder.getPattern("beat"), "\"beat\" => \"b-t\"");
        assertEquals("b-t", patternBuilder.getPattern("beet"), "\"beet\" => \"b-t\"");
        assertEquals("b-", patternBuilder.getPattern("bee"), "\"bee => \"b-\"");
        assertEquals("b-", patternBuilder.getPattern("be"), "\"be\" => \"b-\"");

        // single letter
        assertEquals("-", patternBuilder.getPattern("a"), "\"a\" => \"-\"");
        assertEquals("b", patternBuilder.getPattern("b"), "\"b\" => \"b\"");

        // no vowels
        assertEquals("rhythm", patternBuilder.getPattern("rhythm"), "\"rhythm\" => \"rhythm\"");
        assertEquals("myrrh", patternBuilder.getPattern("myrrh"), "\"myrrh\" => \"myrrh\"");

        // capitalized words
        assertEquals("w-rd", patternBuilder.getPattern("WOrD"), "\"WOrD\" => \"w-rd\"");
        assertEquals("rhythm", patternBuilder.getPattern("RHythM"), "\"RHythM\" => \"rhythm\"");

        // repeated letters
        assertEquals("w-rd", patternBuilder.getPattern("wooooord"), "\"wooooord\" => \"w-rd\"");
        assertEquals("j-b", patternBuilder.getPattern("jjoobbb"), "\"jjoobbb\" => \"job\"");


        // incorrect vowels
        assertEquals("w-k-", patternBuilder.getPattern("weke"), "\"weke\" => \"w-k-\"");
        assertEquals("w-k-", patternBuilder.getPattern("woke"), "\"woke\" => \"w-k-\"");

        // mispelling
        // conspiracy
        assertEquals("c-nsp-r-cy", patternBuilder.getPattern("CUNsperrICY"), "\"CUNsperrICY\" => \"c-nsp-r-cy\"");

        // bibliography
        assertEquals("b-bl-gr-phy", patternBuilder.getPattern("bBaEEblLAeGgRrRoEphhy"), "\"bBaEEblLAeGgRrRoEphhy\" => \"b-bl-gr-phy\"");

        // explorer
        assertEquals("-xpl-r-r", patternBuilder.getPattern("eeEExpPLiIrrAArR"), "\"eeEExpPLiIrrAArR\" => \"-xpl-r-r\"");
    }

    @Test
    void removeDuplicate() {
        PatternBuilder patternBuilder = new PatternBuilder();
    }

    @Test
    void isVowel() {
        PatternBuilder patternBuilder = new PatternBuilder();

        // test value vowels a,e,i,o,u
        assertTrue(patternBuilder.isVowel('a'));
        assertTrue(patternBuilder.isVowel('e'));
        assertTrue(patternBuilder.isVowel('i'));
        assertTrue(patternBuilder.isVowel('o'));
        assertTrue(patternBuilder.isVowel('u'));

        // test consonants
        assertFalse(patternBuilder.isVowel('b'));
        assertFalse(patternBuilder.isVowel('z'));

        // test misc characters
        assertFalse(patternBuilder.isVowel('!'));
        assertFalse(patternBuilder.isVowel('@'));
        assertFalse(patternBuilder.isVowel('*'));
        assertFalse(patternBuilder.isVowel('-'));
    }

}