package persistence;

import model.Flashcard;
import model.Set;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

// DISCLAIMER: class structure based on JsonSerializationDemo:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {

    protected void checkSet(String title, Set set) {
        assertEquals(title, set.getTitle());
    }

    protected void checkFlashcard(String question, String answer, Flashcard flashcard) {
        assertEquals(question, flashcard.getQuestion());
        assertEquals(answer, flashcard.getAnswer());
    }
}
