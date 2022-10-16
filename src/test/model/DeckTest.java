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
    void testRemoveSetSuccessOnce() {
        testDeck.addSet("General Trivia");
        assertTrue(testDeck.removeSet("General Trivia"));
        assertTrue(testDeck.getSetList().isEmpty());
    }

    @Test
    void testRemoveSetSuccessMultipleTimes() {
        // first adding three flashcard sets
        testDeck.addSet("General Trivia");
        testDeck.addSet("Movie Trivia");
        testDeck.addSet("KORN 102 Vocab");

        // removing the second set
        assertTrue(testDeck.removeSet("Movie Trivia"));
        assertEquals(2, testDeck.getSetList().size());

        // removing the first set
        assertTrue(testDeck.removeSet("General Trivia"));
        assertEquals(1, testDeck.getSetList().size());
    }

    @Test
    void testRemoveSetFailOnce() {
        // adding "General Trivia" set
        testDeck.addSet("General Trivia");

        // failing to remove "Movie Trivia" set
        assertFalse(testDeck.removeSet("Movie Trivia"));

    }

    @Test
    void testRemoveSetFailMultipleTimes() {
        // failing to remove "General Trivia" set
        assertFalse(testDeck.removeSet("General Trivia"));

        // failing to remove "Movie Trivia" set
        assertFalse(testDeck.removeSet("Movie Trivia"));
    }

    @Test
    void testRemoveSetSuccessAndFailMultipleTimes() {
        // first adding three flashcard sets
        testDeck.addSet("General Trivia");
        testDeck.addSet("Movie Trivia");
        testDeck.addSet("KORN 102 Vocab");
        assertEquals(3, testDeck.getSetList().size());

        // removing the "KORN 102 Vocab" set successfully
        assertTrue(testDeck.removeSet("KORN 102 Vocab"));
        assertEquals(2, testDeck.getSetList().size());

        // failing to remove the "General Trivia" set by misspelling "Trivia" as "Trivvia"
        assertFalse(testDeck.removeSet("General Trivvia"));
        assertEquals(2, testDeck.getSetList().size());

        // removing the "General Trivia" set successfully
        assertTrue(testDeck.removeSet("General Trivia"));
        assertEquals(1, testDeck.getSetList().size());

        // failing to remove the "Movie Trivia" set by inputting an empty string
        assertFalse(testDeck.removeSet(""));
        assertEquals(1, testDeck.getSetList().size());

    }

    @Test
    void testGetSetList() {
        assertTrue(testDeck.getSetList().isEmpty());
    }
}