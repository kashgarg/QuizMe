package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void testRemoveSetOnce() {
        testDeck.addSet("General Trivia");
        testDeck.removeSet(0);
        assertTrue(testDeck.getSetList().isEmpty());
    }

    @Test
    void testRemoveSetMultipleTimes() {
        // first adding three flashcard sets
        testDeck.addSet("General Trivia");
        testDeck.addSet("Movie Trivia");
        testDeck.addSet("KORN 102 Vocab");

        // removing the second set
        testDeck.removeSet(1);
        assertEquals(2, testDeck.getSetList().size());

        // removing the first set
        testDeck.removeSet(0);
        assertEquals(1, testDeck.getSetList().size());
    }

    @Test
    void testGetSetList() {
        assertTrue(testDeck.getSetList().isEmpty());
    }
}
