# kanban board
Demo of the Kanban board functionality using Java.
This can be extended to expose a REST API and also the state of the board can be persisted.
Moreover, A single page angular application can be developed to demostrate the Kanban board functionality
The focus of this project is to expose Java API to demo the following functionality
- create kanban board
- add states (aka columns) to a kanban board
- create an iteration
- create user stories
- adding user stories (aka cards) to the iteration
- move user stories from one state to another 
- undo last move
- calculate sprint current velocity
- retrieve all user stories in a given state (aka column)
- throw exception when no of user stories in a given state exceed the maximum

Source code includes the following files.

pom.xml contains dependencies required to run the project

/src/main/java/org/ck/sample/
- App.java : a sample program to validate the kanban board functionality 
- model/Board.java : agile kanban board 
- model/Card.java : agile user story
- model/Iteration.java : agile sprint
- model/Column.java : swim lane in a kanban board
- exception/ : the exceptions such as ColumnLimitExceededException

/src/test/java/org/ck/sample/
- model/IterationTest.java : unit tests for the functionality in Iteration class (JUnit as unit testing framework and Mockito is used as mock framework)

To test the application, use following commands

to run the app, 
$ java -jar kanban.jar

to run the tests, use maven or your IDE
