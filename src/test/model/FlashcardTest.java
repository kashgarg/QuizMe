package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlashcardTest {
    private Flashcard testFlashcardEverest;
    private Flashcard testFlashcardNile;
    private Flashcard testFlashcardCheetah;

    @BeforeEach
    void runBefore() {
        testFlashcardEverest = new Flashcard(
                "What is the tallest mountain in the world?", "Mount Everest");
        testFlashcardNile = new Flashcard("What is the longest river in the world?", "Nile River");
        testFlashcardCheetah = new Flashcard("What is the fastest animal in the world?", "Cheetah");
    }

    @Test
    void testConstructor() {
        assertEquals("What is the tallest mountain in the world?", testFlashcardEverest.getQuestion());
        assertEquals("Mount Everest", testFlashcardEverest.getAnswer());
    }

    @Test
    void testGetQuestion() {
        assertEquals("What is the tallest mountain in the world?", testFlashcardEverest.getQuestion());
        assertEquals("What is the longest river in the world?", testFlashcardNile.getQuestion());
        assertEquals("What is the fastest animal in the world?", testFlashcardCheetah.getQuestion());

    }

    @Test
    void testGetAnswer() {
        assertEquals("Mount Everest", testFlashcardEverest.getAnswer());
        assertEquals("Nile River", testFlashcardNile.getAnswer());
        assertEquals("Cheetah", testFlashcardCheetah.getAnswer());
    }

}