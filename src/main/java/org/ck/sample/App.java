package org.ck.sample;

import org.ck.sample.exception.ColumnLimitExceededException;
import org.ck.sample.model.Board;
import org.ck.sample.model.Card;
import org.ck.sample.model.Column;
import org.ck.sample.model.Iteration;

/**
 * Main application to demo an Kanban Board
 */
public class App 
{
    public static void main(String[] args)
    {
        // create new sprint
        Iteration iteration = new Iteration();

        // create agile board and add the sprint
        Board board = new Board();
        board.setIteration(iteration);

        // define the columns in the board
        // For simplicity only 4 columns considered - Backlog, Analyze, Develop, Test and Done
        board.addColumn("backlog", new Column("Backlog", 20))
                .addColumn("analyze", new Column("Analyze", 3))
                .addColumn("develop", new Column("Develop", 3))
                .addColumn("test", new Column("Test", 3))
                .addColumn("done", new Column("Done", 20));

        // add stories (cards) to current sprint
        Column backlog = board.getColumn("backlog");
        iteration.addCard(new Card("Skeleton Authorize API", "description goes here", 8, backlog))
                .addCard(new Card("Skeleton Refund API", "description goes here", 13, backlog))
                .addCard(new Card("Skeleton Confirm API", "description goes here", 8, backlog))
                .addCard(new Card("Write simulator for Payment Gateway", "description goes here", 13, backlog));

        Column analyze = board.getColumn("analyze");
        try {
            // start spring by moving 3 stories to analyze column
            iteration.moveCard(iteration.getCardByTitle("Skeleton Authorize API"), analyze);
            iteration.moveCard(iteration.getCardByTitle("Write simulator for Payment Gateway"), analyze);
            iteration.moveCard(iteration.getCardByTitle("Skeleton Confirm API"), analyze);

            // move another story to analyze column
            iteration.moveCard(iteration.getCardByTitle("Skeleton Refund API"), analyze);
        }
        catch (ColumnLimitExceededException e) {
            System.out.printf("Cannot add story : Exception is %s \n\n", e.getMessage());
        }

        try {
            // move a story to done
            iteration.moveCard(iteration.getCardByTitle("Skeleton Authorize API"), board.getColumn("done"));
        }
        catch (ColumnLimitExceededException e) {
            System.out.printf("Cannot add story : Exception is %s \n\n", e.getMessage());
        }

        // calculate sprint velocity
        System.out.printf("Sprint velocity is : %d \n\n", iteration.calculateVelocity());

        // check undo move
        iteration.undoLastMove();
        Card card = iteration.getCardByTitle("Skeleton Authorize API");
        System.out.printf("Column name is : %s\n", card.getCurrent().getName());
    }
}
