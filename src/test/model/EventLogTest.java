package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventLogTest {
    private Deck testDeck;
    private Set testSetGeneralTrivia;
    private Set testSetMovieTrivia;
    private Set testSetAdditions;

    @BeforeEach
    void runBefore() {
        testDeck = new Deck();
        testSetGeneralTrivia = new Set("General Trivia");
        testSetMovieTrivia = new Set("Movie Trivia");
        testSetAdditions = new Set("Additions");
        EventLog.getInstance().clear();
    }

    @Test
    void testEventLogEmpty() {
        for (Event event : EventLog.getInstance()) {
            if (event.getDescription().equals("Event log cleared.")) {
                // pass
            } else {
                fail();
            }
        }
    }

    @Test
    void testLogEventAddSetOnce() {
        testDeck.addSet("General Trivia");
        for (Event event : EventLog.getInstance()) {
            if (event.getDescription().equals("Event log cleared.")) {
                System.out.println("Event 1");
            } else if (event.getDescription().equals("Added Set: General Trivia")) {
                System.out.println("Event 2 - last event for this test");
            } else {
                fail();
            }
        }
    }

    @Test
    void testLogEventAddSetMultipleTimes() {
        testDeck.addSet("General Trivia");
        testDeck.addSet("Movie Trivia");
        testDeck.addSet("KORN 102 Vocab");
        for (Event event : EventLog.getInstance()) {
            if (event.getDescription().equals("Event log cleared.")) {
                System.out.println("Event 1");
            } else if (event.getDescription().equals("Added Set: General Trivia")) {
                System.out.println("Event 2");
            } else if (event.getDescription().equals("Added Set: Movie Trivia")) {
                System.out.println("Event 3");
            } else if (event.getDescription().equals("Added Set: KORN 102 Vocab")) {
                System.out.println("Event 4 - last event for this test");
            } else {
                fail();
            }
        }
    }

    @Test
    void testLogEventAddSetMultipleTimesDifferentOrder() {
        testDeck.addSet("KORN 102 Vocab");
        testDeck.addSet("General Trivia");
        testDeck.addSet("Movie Trivia");
        for (Event event : EventLog.getInstance()) {
            if (event.getDescription().equals("Event log cleared.")) {
                System.out.println("Event 1");
            } else if (event.getDescription().equals("Added Set: General Trivia")) {
                System.out.println("Event 3");
            } else if (event.getDescription().equals("Added Set: Movie Trivia")) {
                System.out.println("Event 4 - last event for this test");
            } else if (event.getDescription().equals("Added Set: KORN 102 Vocab")) {
                System.out.println("Event 2");
            } else {
                fail();
            }
        }
    }

    @Test
    void testLogEventRemoveSetBooleanOnce() {
        testDeck.addSet("General Trivia");
        testDeck.removeSetBoolean("General Trivia");
        for (Event event : EventLog.getInstance()) {
            if (event.getDescription().equals("Event log cleared.")) {
                System.out.println("Event 1");
            } else if (event.getDescription().equals("Added Set: General Trivia")) {
                System.out.println("Event 2");
            } else if (event.getDescription().equals("Removed Set: General Trivia")) {
                System.out.println("Event 3 - last event for this test");
            } else {
                fail();
            }
        }
    }

    @Test
    void testLogEventRemoveSetBooleanMultipleTimes() {
        testDeck.addSet("General Trivia");
        testDeck.addSet("Movie Trivia");
        testDeck.addSet("KORN 102 Vocab");
        testDeck.removeSetBoolean("General Trivia");
        testDeck.removeSetBoolean("Movie Trivia");
        testDeck.removeSetBoolean("KORN 102 Vocab");
        for (Event event : EventLog.getInstance()) {
            if (event.getDescription().equals("Event log cleared.")) {
                System.out.println("Event 1");
            } else if (event.getDescription().equals("Added Set: General Trivia")) {
                System.out.println("Event 2");
            } else if (event.getDescription().equals("Added Set: Movie Trivia")) {
                System.out.println("Event 3");
            } else if (event.getDescription().equals("Added Set: KORN 102 Vocab")) {
                System.out.println("Event 4");
            } else if (event.getDescription().equals("Removed Set: General Trivia")) {
                System.out.println("Event 5");
            } else if (event.getDescription().equals("Removed Set: Movie Trivia")) {
                System.out.println("Event 6");
            } else if (event.getDescription().equals("Removed Set: KORN 102 Vocab")) {
                System.out.println("Event 7 - last event for this test");
            } else {
                fail();
            }
        }
    }

    @Test
    void testLogEventRemoveSetBooleanMultipleTimesDifferentOrder() {
        testDeck.addSet("General Trivia");
        testDeck.removeSetBoolean("General Trivia");
        testDeck.addSet("Movie Trivia");
        testDeck.removeSetBoolean("Movie Trivia");
        testDeck.addSet("KORN 102 Vocab");
        testDeck.removeSetBoolean("KORN 102 Vocab");
        for (Event event : EventLog.getInstance()) {
            if (event.getDescription().equals("Event log cleared.")) {
                System.out.println("Event 1");
            } else if (event.getDescription().equals("Added Set: General Trivia")) {
                System.out.println("Event 2");
            } else if (event.getDescription().equals("Added Set: Movie Trivia")) {
                System.out.println("Event 4");
            } else if (event.getDescription().equals("Added Set: KORN 102 Vocab")) {
                System.out.println("Event 6");
            } else if (event.getDescription().equals("Removed Set: General Trivia")) {
                System.out.println("Event 3");
            } else if (event.getDescription().equals("Removed Set: Movie Trivia")) {
                System.out.println("Event 5");
            } else if (event.getDescription().equals("Removed Set: KORN 102 Vocab")) {
                System.out.println("Event 7 - last event for this test");
            } else {
                fail();
            }
        }
    }

    @Test
    void testLogEventAddFlashcardOnce() {
        testSetGeneralTrivia.addFlashcard("What is the tallest mountain in the world?",
                "Mount Everest");
        for (Event event : EventLog.getInstance()) {
            if (event.getDescription().equals("Event log cleared.")) {
                System.out.println("Event 1");
            } else if (event.getDescription().equals("Added Flashcard to: General Trivia")) {
                System.out.println("Event 2 - last event for this test");
            } else {
                fail();
            }
        }
    }

    @Test
    void testLogEventAddFlashcardMultipleTimes() {
        testSetGeneralTrivia.addFlashcard("What is the tallest mountain in the world?",
                "Mount Everest");
        testSetMovieTrivia.addFlashcard("What Marvel movie has grossed more than 2.7 billion dollars?",
                "Avengers: Endgame");
        testSetAdditions.addFlashcard("4+3", "7");

        for (Event event : EventLog.getInstance()) {
            if (event.getDescription().equals("Event log cleared.")) {
                System.out.println("Event 1");
            } else if (event.getDescription().equals("Added Flashcard to: General Trivia")) {
                System.out.println("Event 2");
            } else if (event.getDescription().equals("Added Flashcard to: Movie Trivia")) {
                System.out.println("Event 3");
            } else if (event.getDescription().equals("Added Flashcard to: Additions")) {
                System.out.println("Event 4 - last event for this test");
            } else {
                fail();
            }
        }
    }

    @Test
    void testLogEventAddFlashcardMultipleTimesDifferentOrder() {
        testSetMovieTrivia.addFlashcard("What Marvel movie has grossed more than 2.7 billion dollars?",
                "Avengers: Endgame");
        testSetAdditions.addFlashcard("4+3", "7");
        testSetGeneralTrivia.addFlashcard("What is the tallest mountain in the world?",
                "Mount Everest");

        for (Event event : EventLog.getInstance()) {
            if (event.getDescription().equals("Event log cleared.")) {
                System.out.println("Event 1");
            } else if (event.getDescription().equals("Added Flashcard to: General Trivia")) {
                System.out.println("Event 4 - last event for this test");
            } else if (event.getDescription().equals("Added Flashcard to: Movie Trivia")) {
                System.out.println("Event 2");
            } else if (event.getDescription().equals("Added Flashcard to: Additions")) {
                System.out.println("Event 3");
            } else {
                fail();
            }
        }
    }

    @Test
    void testLogEventRemoveFlashcardOnce() {
        testSetAdditions.addFlashcard("4+3", "7");
        if (testSetAdditions.containsFlashcard("4+3")) {
            testSetAdditions.removeFlashcard("4+3");
        }

        for (Event event : EventLog.getInstance()) {
            if (event.getDescription().equals("Event log cleared.")) {
                System.out.println("Event 1");
            } else if (event.getDescription().equals("Added Flashcard to: Additions")) {
                System.out.println("Event 2");
            } else if (event.getDescription().equals("Removed Flashcard from: Additions")) {
                System.out.println("Event 3 - last event for this test");
            } else {
                fail();
            }
        }
    }

    @Test
    void testLogEventRemoveFlashcardMultipleTimes() {
        testSetAdditions.addFlashcard("4+3", "7");
        testSetMovieTrivia.addFlashcard("What Marvel movie has grossed more than 2.7 billion dollars?",
                "Avengers: Endgame");
        testSetGeneralTrivia.addFlashcard("What is the tallest mountain in the world?",
                "Mount Everest");

        if (testSetAdditions.containsFlashcard("4+3")) {
            testSetAdditions.removeFlashcard("4+3");
        }
        if (testSetMovieTrivia.containsFlashcard("What Marvel movie has grossed more than " +
                "2.7 billion dollars?")) {
            testSetMovieTrivia.removeFlashcard("What Marvel movie has grossed more than 2.7 billion dollars?");
        }
        if (testSetGeneralTrivia.containsFlashcard("What is the tallest mountain in the world?")) {
            testSetGeneralTrivia.removeFlashcard("What is the tallest mountain in the world?");
        }

        for (Event event : EventLog.getInstance()) {
            if (event.getDescription().equals("Event log cleared.")) {
                System.out.println("Event 1");
            } else if (event.getDescription().equals("Added Flashcard to: Additions")) {
                System.out.println("Event 2");
            } else if (event.getDescription().equals("Added Flashcard to: Movie Trivia")) {
                System.out.println("Event 3");
            } else if (event.getDescription().equals("Added Flashcard to: General Trivia")) {
                System.out.println("Event 4");
            } else if (event.getDescription().equals("Removed Flashcard from: Additions")) {
                System.out.println("Event 5");
            } else if (event.getDescription().equals("Removed Flashcard from: Movie Trivia")) {
                System.out.println("Event 6");
            } else if (event.getDescription().equals("Removed Flashcard from: General Trivia")) {
                System.out.println("Event 7 - last event for this test");
            } else {
                fail();
            }
        }
    }

    @Test
    void testLogEventRemoveFlashcardMultipleTimesDifferentOrder() {
        testSetAdditions.addFlashcard("4+3", "7");
        if (testSetAdditions.containsFlashcard("4+3")) {
            testSetAdditions.removeFlashcard("4+3");
        }
        testSetMovieTrivia.addFlashcard("What Marvel movie has grossed more than 2.7 billion dollars?",
                "Avengers: Endgame");
        if (testSetMovieTrivia.containsFlashcard("What Marvel movie has grossed more than " +
                "2.7 billion dollars?")) {
            testSetMovieTrivia.removeFlashcard("What Marvel movie has grossed more than 2.7 billion dollars?");
        }
        testSetGeneralTrivia.addFlashcard("What is the tallest mountain in the world?",
                "Mount Everest");
        if (testSetGeneralTrivia.containsFlashcard("What is the tallest mountain in the world?")) {
            testSetGeneralTrivia.removeFlashcard("What is the tallest mountain in the world?");
        }

        for (Event event : EventLog.getInstance()) {
            if (event.getDescription().equals("Event log cleared.")) {
                System.out.println("Event 1");
            } else if (event.getDescription().equals("Added Flashcard to: Additions")) {
                System.out.println("Event 2");
            } else if (event.getDescription().equals("Added Flashcard to: Movie Trivia")) {
                System.out.println("Event 4");
            } else if (event.getDescription().equals("Added Flashcard to: General Trivia")) {
                System.out.println("Event 6");
            } else if (event.getDescription().equals("Removed Flashcard from: Additions")) {
                System.out.println("Event 3");
            } else if (event.getDescription().equals("Removed Flashcard from: Movie Trivia")) {
                System.out.println("Event 5");
            } else if (event.getDescription().equals("Removed Flashcard from: General Trivia")) {
                System.out.println("Event 7 - last event for this test");
            } else {
                fail();
            }
        }
    }
}
