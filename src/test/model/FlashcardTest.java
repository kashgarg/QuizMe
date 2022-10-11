package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlashcardTest {
    private Flashcard testFlashcard;

    @BeforeEach
    void runBefore() {
        testFlashcard = new Flashcard(
                "What is the tallest mountain in the world?", "Mount Everest");
    }

    @Test
    void testConstructor() {
        assertEquals("What is the tallest mountain in the world?", testFlashcard.getQuestion());
        assertEquals("Mount Everest", testFlashcard.getAnswer());
    }


}