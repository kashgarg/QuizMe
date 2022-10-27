package persistence;

import model.Deck;
import model.Flashcard;
import model.Set;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


// DISCLAIMER: class structure and tests structure based on JsonSerializationDemo:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Deck deck = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDeck() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDeck.json");
        try {
            Deck deck = reader.read();
            assertEquals(0, deck.getSetList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDeck() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDeck.json");
        try {
            Deck deck = reader.read();
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
            fail("Couldn't read from file");
        }
    }


}
