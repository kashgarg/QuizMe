package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void testRemoveFlashcardOnce() {
        testSet.addFlashcard("What is the tallest mountain in the world?", "Mount Everest");
        assertEquals(1, testSet.getFlashcardList().size());

        testSet.removeFlashcard(0);
        assertTrue(testSet.getFlashcardList().isEmpty());

    }

    @Test
    void testRemoveFlashcardMultipleTimes() {
        // adding two flashcards
        testSet.addFlashcard("What is the tallest mountain in the world?", "Mount Everest");
        testSet.addFlashcard("What is the longest river in the world?", "Nile River");
        assertEquals(2,testSet.getFlashcardList().size());

        // removing the first flashcard
        testSet.removeFlashcard(0);

        // testing that the second flashcard has moved to index 0
        assertEquals(1, testSet.getFlashcardList().size());
        assertEquals("What is the longest river in the world?",
                testSet.getFlashcardList().get(0).getQuestion());
        assertEquals("Nile River", testSet.getFlashcardList().get(0).getAnswer());

        // removing the remaining flashcard
        testSet.removeFlashcard(0);
        assertTrue(testSet.getFlashcardList().isEmpty());

    }

    @Test
    void testRemoveFlashcardMiddleIndexOfThreeFlashcards() {
        // adding three flashcards
        testSet.addFlashcard("What is the tallest mountain in the world?", "Mount Everest");
        testSet.addFlashcard("What is the longest river in the world?", "Nile River");
        testSet.addFlashcard("What is the fastest animal in the world?", "Cheetah");
        assertEquals(3, testSet.getFlashcardList().size());

        // removing middle index of set
        testSet.removeFlashcard(1);
        assertEquals(2, testSet.getFlashcardList().size());

        // testing the third index has moved to index 1
        assertEquals("What is the fastest animal in the world?",
                testSet.getFlashcardList().get(1).getQuestion());
        assertEquals("Cheetah", testSet.getFlashcardList().get(1).getAnswer());

    }

    @Test
    void testAddFlashcardAndRemoveFlashcardMultipleTimes() {
        // adding two flashcards
        testSet.addFlashcard("What is the tallest mountain in the world?", "Mount Everest");
        assertEquals(1,testSet.getFlashcardList().size());
        testSet.addFlashcard("What is the longest river in the world?", "Nile River");
        assertEquals(2,testSet.getFlashcardList().size());

        // removing two flashcards
        testSet.removeFlashcard(1);
        assertEquals(1, testSet.getFlashcardList().size());
        testSet.removeFlashcard(0);
        assertTrue(testSet.getFlashcardList().isEmpty());

        // adding one flashcard
        testSet.addFlashcard("What is the fastest animal in the world?", "Cheetah");
        assertEquals(1, testSet.getFlashcardList().size());

        // removing one flashcard
        testSet.removeFlashcard(0);
        assertTrue(testSet.getFlashcardList().isEmpty());
    }


}
