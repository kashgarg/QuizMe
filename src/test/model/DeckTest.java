package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    private Deck testDeck;

    @BeforeEach
    void runBefore() {
        testDeck = new Deck();
    }

    @Test
    void testConstructor() {
        assertTrue(testDeck.getSetList().isEmpty());
    }

    @Test
    void testAddSetOnce() {
        testDeck.addSet("General Trivia");
        assertEquals(1, testDeck.getSetList().size());
    }

    @Test
    void testAddSetMultipleTimes() {
        testDeck.addSet("General Trivia");
        assertEquals(1, testDeck.getSetList().size());

        testDeck.addSet("Movie Trivia");
        assertEquals(2, testDeck.getSetList().size());
    }

    @Test
    void testRemoveSetBooleanSuccessOnce() {
        testDeck.addSet("General Trivia");
        assertTrue(testDeck.removeSetBoolean("General Trivia"));
        assertTrue(testDeck.getSetList().isEmpty());
    }

    @Test
    void testRemoveSetSuccessOnce() {
        testDeck.addSet("General Trivia");
        testDeck.removeSet("General Trivia");
        assertTrue(testDeck.getSetList().isEmpty());
    }

    @Test
    void testRemoveSetBooleanSuccessMultipleTimes() {
        // first adding three flashcard sets
        testDeck.addSet("General Trivia");
        testDeck.addSet("Movie Trivia");
        testDeck.addSet("KORN 102 Vocab");

        // removing the second set
        assertTrue(testDeck.removeSetBoolean("Movie Trivia"));
        assertEquals(2, testDeck.getSetList().size());

        // removing the first set
        assertTrue(testDeck.removeSetBoolean("General Trivia"));
        assertEquals(1, testDeck.getSetList().size());
    }

    @Test
    void testRemoveSetSuccessMultipleTimes() {
        // first adding three flashcard sets
        testDeck.addSet("General Trivia");
        testDeck.addSet("Movie Trivia");
        testDeck.addSet("KORN 102 Vocab");

        // removing the second set
        testDeck.removeSet("Movie Trivia");
        assertEquals(2, testDeck.getSetList().size());

        // removing the first set
        testDeck.removeSet("General Trivia");
        assertEquals(1, testDeck.getSetList().size());
    }

    @Test
    void testRemoveSetBooleanFailOnce() {
        // adding "General Trivia" set
        testDeck.addSet("General Trivia");

        // failing to remove "Movie Trivia" set
        assertFalse(testDeck.removeSetBoolean("Movie Trivia"));
    }

    @Test
    void testRemoveSetFailOnce() {
        // adding "General Trivia" set
        testDeck.addSet("General Trivia");
        assertEquals(1, testDeck.getSetList().size());

        // failing to remove "Movie Trivia" set
        testDeck.removeSet("Movie Trivia");
        assertEquals(1, testDeck.getSetList().size());
    }

    @Test
    void testRemoveSetBooleanFailMultipleTimes() {
        // failing to remove "General Trivia" set
        assertFalse(testDeck.removeSetBoolean("General Trivia"));

        // failing to remove "Movie Trivia" set
        assertFalse(testDeck.removeSetBoolean("Movie Trivia"));
    }

    @Test
    void testRemoveSetFailMultipleTimes() {
        // failing to remove "General Trivia" set
        testDeck.removeSet("General Trivia");
        assertEquals(0, testDeck.getSetList().size());

        // failing to remove "Movie Trivia" set
        testDeck.removeSet("Movie Trivia");
        assertEquals(0, testDeck.getSetList().size());
    }

    @Test
    void testRemoveSetBooleanSuccessAndFailMultipleTimes() {
        // first adding three flashcard sets
        testDeck.addSet("General Trivia");
        testDeck.addSet("Movie Trivia");
        testDeck.addSet("KORN 102 Vocab");
        assertEquals(3, testDeck.getSetList().size());

        // removing the "KORN 102 Vocab" set successfully
        assertTrue(testDeck.removeSetBoolean("KORN 102 Vocab"));
        assertEquals(2, testDeck.getSetList().size());

        // failing to remove the "General Trivia" set by misspelling "Trivia" as "Trivvia"
        assertFalse(testDeck.removeSetBoolean("General Trivvia"));
        assertEquals(2, testDeck.getSetList().size());

        // removing the "General Trivia" set successfully
        assertTrue(testDeck.removeSetBoolean("General Trivia"));
        assertEquals(1, testDeck.getSetList().size());

        // failing to remove the "Movie Trivia" set by inputting an empty string
        assertFalse(testDeck.removeSetBoolean(""));
        assertEquals(1, testDeck.getSetList().size());
    }

    @Test
    void testRemoveSetSuccessAndFailMultipleTimes() {
        // first adding three flashcard sets
        testDeck.addSet("General Trivia");
        testDeck.addSet("Movie Trivia");
        testDeck.addSet("KORN 102 Vocab");
        assertEquals(3, testDeck.getSetList().size());

        // removing the "KORN 102 Vocab" set successfully
        testDeck.removeSet("KORN 102 Vocab");
        assertEquals(2, testDeck.getSetList().size());

        // failing to remove the "General Trivia" set by misspelling "Trivia" as "Trivvia"
        testDeck.removeSet("General Trivvia");
        assertEquals(2, testDeck.getSetList().size());

        // removing the "General Trivia" set successfully
        testDeck.removeSet("General Trivia");
        assertEquals(1, testDeck.getSetList().size());

        // failing to remove the "Movie Trivia" set by inputting an empty string
        testDeck.removeSet("");
        assertEquals(1, testDeck.getSetList().size());
    }

    @Test
    void testContainsSetSuccessOnce() {
        testDeck.addSet("General Trivia");
        assertTrue(testDeck.containsSet("General Trivia"));
    }

    @Test
    void testContainsSetSuccessMultipleTimes() {
        testDeck.addSet("General Trivia");
        testDeck.addSet("Movie Trivia");
        testDeck.addSet("KORN 102 Vocab");

        assertTrue(testDeck.containsSet("General Trivia"));
        assertTrue(testDeck.containsSet("Movie Trivia"));
        assertTrue(testDeck.containsSet("KORN 102 Vocab"));
    }

    @Test
    void testContainsSetFailOnce() {
        assertFalse(testDeck.containsSet("General Trivia"));
    }

    @Test
    void testContainsSetFailMultipleTimes() {
        assertFalse(testDeck.containsSet("General Trivia"));
        assertFalse(testDeck.containsSet("Movie Trivia"));
        assertFalse(testDeck.containsSet("KORN 102 Vocab"));
    }

    @Test
    void testContainsSetSuccessAndFailMultipleTimes() {
        assertFalse(testDeck.containsSet("General Trivia"));
        testDeck.addSet("General Trivia");
        assertTrue(testDeck.containsSet("General Trivia"));

        assertFalse(testDeck.containsSet("Movie Trivia"));
        testDeck.addSet("Movie Trivia");
        assertTrue(testDeck.containsSet("Movie Trivia"));

        assertFalse(testDeck.containsSet("KORN 102 Vocab"));
        testDeck.addSet("KORN 102 Vocab");
        assertTrue(testDeck.containsSet("KORN 102 Vocab"));
    }

    @Test
    void testGetSetList() {
        assertTrue(testDeck.getSetList().isEmpty());
    }
}
