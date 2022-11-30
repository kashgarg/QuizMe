# My Personal Project

## Flashcard Application

#### **What will this application do?**
This application will allow you to create flashcards and 
organize them into sets. The application will let you 
enter information about the question and answer of each
flashcard, as well as the name of the set. The application
will allow you to quiz yourself by being presented the 
question side of the flashcard and have a space for 
you to input the answer.

#### **Who will use it?**
This application is for anyone who wants to create their
own flashcards and memorize terms and/or questions. 
This flashcard application would be especially useful
for students learning new languages, as they can use this
application to memorize vocabulary.

#### **Why is this project of interest to you?**
I am currently taking a foreign language course at UBC
as I am interested in learning languages, and there are
many vocabulary words I need to memorize. Since I enjoy
learning languages, this project would allow me to build
something related to language learning.

**User Story**:
- As a user, I want to be able to quiz myself using a flashcard set
- As a user, I want to be able to add sets
- As a user, I want to be able to add a title to each set
- As a user, I want to be able to remove sets
- As a user, I want to be able to add a flashcard to a set
- As a user, I want to be able to remove a flashcard from a set
- As a user, I want to be able to view the title and number of flashcards in each set
- As a user, I want to be able to view the question and answer of each flashcard in each set
- As a user, I want to be able to save my created sets and flashcards to file
- As a user, I want to be able to load my created sets and flashcards from file

# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y (Adding a set) by
  1. Running Main
  2. Clicking the "Add Sets" button
  3. Typing a set name in the text field (ex. SetOne)
  4. Clicking the "Add Set" button
  5. To verify that the set has indeed been added:
     1. Click the "View Sets" button
     2. A JList will be displayed with one entry (ex. SetOne -- Number of Flashcards: 0)
- You can generate the second required event related to adding Xs to a Y (Removing a set) by
  1. Running Main
  2. Adding two sets with the instructions above (ex. SetOne and SetTwo) 
     1. Note: Two sets must be added prior to removing a set because once one set has been added, there must always be at least one set in the application
  3. Clicking the "Remove Sets" button
  4. Typing the name of one of the two previously added sets in the text field (ex. SetTwo)
  5. Clicking the "Remove Set" button
  6. To verify that the set has indeed been removed:
     1. Click the "View Sets button"
     2. A JList will be displayed with one entry (the set that you did not remove)
- You can locate my visual component by
  1. Running Main
  2. The visual component (an image displaying the name of the application: QuizMe) will be displayed on the main menu of the application
- You can save the state of my application by 
  1. Running Main
  2. Clicking the "Save" button
- You can reload the state of my application by
  1. Running Main
  2. Clicking the "Load" button

# Phase 4: Task 2

Tue Nov 29 12:04:03 PST 2022
Event log cleared.

Tue Nov 29 12:04:13 PST 2022
Added Set: Movie Trivia

Tue Nov 29 12:04:26 PST 2022
Added Flashcard to: Movie Trivia

Tue Nov 29 12:04:36 PST 2022
Added Set: Animal Trivia

Tue Nov 29 12:04:49 PST 2022
Removed Flashcard from: Movie Trivia

Tue Nov 29 12:05:02 PST 2022
Added Flashcard to: Animal Trivia

Tue Nov 29 12:05:11 PST 2022
Added Flashcard to: Animal Trivia

Tue Nov 29 12:05:20 PST 2022
Removed Flashcard from: Animal Trivia

Tue Nov 29 12:05:29 PST 2022
Removed Set: Movie Trivia

# Phase 4: Task 3

- To improve cohesion, I would separate each class created in the GUI class
(ex. SetAdder, SetRemover, etc.) into its own class file in the ui folder.
Since these classes make use of the userDeck field in the GUI class, I would 
change this field from private to protected. I would also make this field static
to allow other classes to import the userDeck field. 
- To improve cohesion, I would make a separate class file in the ui folder (ex. Button) for the 
initialization of the nine buttons in the GUI class. In this class, each button would have its own 
initialization method. 
- To reduce coupling, I would reduce the amount of dependencies by refactoring the classes
that represent pop-up menus for successful actions (ex. SuccessfulSetAdd, SuccessfulSetRemoval, etc.)
to be incorporated into the classes that handle the actions themselves (ex. SuccessfulSetAdd would be 
incorporated into the SetAdder class, SuccessfulSetRemoval would be incorporated into the 
SetRemover class, etc.)
