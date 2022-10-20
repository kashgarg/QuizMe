package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SetTest {
    private Set testSet;

    @BeforeEach
    void runBefore() {
        testSet = new Set("General Trivia");
    }

    @Test
    void testConstructor() {
        assertEquals("General Trivia", testSet.getTitle());
        assertTrue(testSet.getFlashcardList().isEmpty());
    }

    @Test
    void testAddFlashcardOnce() {
        testSet.addFlashcard("What is the tallest mountain in the world?", "Mount Everest");
        assertEquals(1, testSet.getFlashcardList().size());
        assertEquals("What is the tallest mountain in the world?",
                testSet.getFlashcardList().get(0).getQuestion());
        assertEquals("Mount Everest", testSet.getFlashcardList().get(0).getAnswer());
    }

    @Test
    void testAddFlashcardMultipleTimes() {
        testSet.addFlashcard("What is the tallest mountain in the world?", "Mount Everest");
        assertEquals(1, testSet.getFlashcardList().size());
        assertEquals("What is the tallest mountain in the world?",
                testSet.getFlashcardList().get(0).getQuestion());
        assertEquals("Mount Everest", testSet.getFlashcardList().get(0).getAnswer());

        testSet.addFlashcard("What is the longest river in the world?", "Nile River");
        assertEquals(2, testSet.getFlashcardList().size());
        assertEquals("What is the longest river in the world?",
                testSet.getFlashcardList().get(1).getQuestion());
        assertEquals("Nile River", testSet.getFlashcardList().get(1).getAnswer());
    }

    @Test
    void testRemoveFlashcardFailOnce() {
        testSet.addFlashcard("What is the tallest mountain in the world?", "Mount Everest");
        assertEquals(1, testSet.getFlashcardList().size());

        // failing to remove flashcard by inputting 'shortest' instead of 'tallest'
        assertFalse(testSet.removeFlashcard("What is the shortest mountain in the world?"));
        assertEquals(1, testSet.getFlashcardList().size());
    }

    @Test
    void testRemoveFlashcardFailMultipleTimes() {
        // failing to remove a flashcard from an empty set
        assertFalse(testSet.removeFlashcard(""));

        testSet.addFlashcard("What is the tallest mountain in the world?", "Mount Everest");

        // failing to remove the added flashcard by adding an extra space after 'What'
        assertFalse(testSet.removeFlashcard("What  is the tallest mountain in the world?"));
    }

    @Test
    void testRemoveFlashcardSuccessOnce() {
        testSet.addFlashcard("What is the fastest animal in the world?", "Cheetah");

        // removing the added flashcard
        assertTrue(testSet.removeFlashcard("What is the fastest animal in the world?"));
    }

    @Test
    void testRemoveFlashcardSuccessMultipleTimes() {
        // adding two flashcards
        testSet.addFlashcard("What is the tallest mountain in the world?", "Mount Everest");
        testSet.addFlashcard("What is the longest river in the world?", "Nile River");
        assertEquals(2,testSet.getFlashcardList().size());

        // removing the first flashcard
        assertTrue(testSet.removeFlashcard("What is the tallest mountain in the world?"));

        // testing that the second flashcard has moved to index 0
        assertEquals(1, testSet.getFlashcardList().size());
        assertEquals("What is the longest river in the world?",
                testSet.getFlashcardList().get(0).getQuestion());
        assertEquals("Nile River", testSet.getFlashcardList().get(0).getAnswer());

        // removing the remaining flashcard
        assertTrue(testSet.removeFlashcard("What is the longest river in the world?"));
        assertTrue(testSet.getFlashcardList().isEmpty());
    }

    @Test
    void testRemoveFlashcardSuccessMiddleIndexOfThreeFlashcards() {
        // adding three flashcards
        testSet.addFlashcard("What is the tallest mountain in the world?", "Mount Everest");
        testSet.addFlashcard("What is the longest river in the world?", "Nile River");
        testSet.addFlashcard("What is the fastest animal in the world?", "Cheetah");
        assertEquals(3, testSet.getFlashcardList().size());

        // removing middle index of set
        assertTrue(testSet.removeFlashcard("What is the longest river in the world?"));
        assertEquals(2, testSet.getFlashcardList().size());

        // testing the third index has moved to index 1
        assertEquals("What is the fastest animal in the world?",
                testSet.getFlashcardList().get(1).getQuestion());
        assertEquals("Cheetah", testSet.getFlashcardList().get(1).getAnswer());
    }

    @Test
    void testAddFlashcardAndRemoveFlashcardSuccessMultipleTimes() {
        // adding two flashcards
        testSet.addFlashcard("What is the tallest mountain in the world?", "Mount Everest");
        assertEquals(1,testSet.getFlashcardList().size());
        testSet.addFlashcard("What is the longest river in the world?", "Nile River");
        assertEquals(2,testSet.getFlashcardList().size());

        // removing two flashcards
        assertTrue(testSet.removeFlashcard("What is the longest river in the world?"));
        assertEquals(1, testSet.getFlashcardList().size());
        assertTrue(testSet.removeFlashcard("What is the tallest mountain in the world?"));
        assertTrue(testSet.getFlashcardList().isEmpty());

        // adding one flashcard
        testSet.addFlashcard("What is the fastest animal in the world?", "Cheetah");
        assertEquals(1, testSet.getFlashcardList().size());

        // removing one flashcard
        assertTrue(testSet.removeFlashcard("What is the fastest animal in the world?"));
        assertTrue(testSet.getFlashcardList().isEmpty());
    }

    @Test
    void testContainsFlashcardFailOnce() {
        assertFalse(testSet.containsFlashcard("What is the longest river in the world?"));
    }

    @Test
    void testContainsFlashcardSuccessOnce() {
        testSet.addFlashcard("What is the tallest mountain in the world?", "Mount Everest");
        assertTrue(testSet.containsFlashcard("What is the tallest mountain in the world?"));
    }

    @Test
    void testContainsFlashcardFailAndSuccessMultipleTimes() {
        assertFalse(testSet.containsFlashcard("What is the longest river in the world?"));

        testSet.addFlashcard("What is the tallest mountain in the world?", "Mount Everest");
        assertTrue(testSet.containsFlashcard("What is the tallest mountain in the world?"));

        assertFalse(testSet.containsFlashcard("What is the fastest animal in the world?"));

        testSet.addFlashcard("What is the fastest animal in the world?", "Cheetah");
        assertTrue(testSet.containsFlashcard("What is the fastest animal in the world?"));

    }

    @Test
    void testGetTitle() {
        assertEquals("General Trivia", testSet.getTitle());
    }

    @Test
    void testGetFlashcardList() {
        // testing empty flashcard list
        assertTrue(testSet.getFlashcardList().isEmpty());

        // testing with one flashcard
        testSet.addFlashcard("What is the tallest mountain in the world?", "Mount Everest");
        assertEquals(1, testSet.getFlashcardList().size());

        // testing with two flashcards
        testSet.addFlashcard("What is the longest river in the world?", "Nile River");
        assertEquals(2,testSet.getFlashcardList().size());
    }


}
