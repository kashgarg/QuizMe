package persistence;


import model.Deck;
import model.Flashcard;
import model.Set;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// DISCLAIMER: class structure and tests structure based on JsonSerializationDemo:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Deck deck = new Deck();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDeck() {
        try {
            Deck deck = new Deck();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDeck.json");
            writer.open();
            writer.write(deck);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDeck.json");
            deck = reader.read();
            assertEquals(0, deck.getSetList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDeck() {
        try {
            Deck deck = new Deck();
            deck.addSet(new Set("General Trivia"));
            for (Set set : deck.getSetList()) {
                set.addFlashcard(new Flashcard("What is the tallest mountain in the world?", "Mount Everest"));
                set.addFlashcard(new Flashcard("What is the fastest animal in the world?", "Cheetah"));
            }
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDeck.json");
            writer.open();
            writer.write(deck);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDeck.json");
            deck = reader.read();
            List<Set> setList = deck.getSetList();
            assertEquals(1, setList.size());
            checkSet("General Trivia", setList.get(0));
            for (Set set : setList) {
                List<Flashcard> flashcardList = set.getFlashcardList();
                assertEquals(2, flashcardList.size());
                checkFlashcard("What is the tallest mountain in the world?", "Mount Everest", flashcardList.get(0));
                checkFlashcard("What is the fastest animal in the world?", "Cheetah", flashcardList.get(1));
            }
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
