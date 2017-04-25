package org.ck.sample.model;

import junit.framework.AssertionFailedError;
import org.ck.sample.exception.ColumnLimitExceededException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by kitha on 4/24/2017.
 */
public class IterationTest {

    //@InjectMocks
    private Iteration iteration = new Iteration();

    //@Mock
    //private Card card = mock(Card.class, RETURNS_DEEP_STUBS);

    @Before
    public void setup() {
        Card card = mock(Card.class, RETURNS_DEEP_STUBS);
        iteration.addCard(card);
        when(card.getTitle()).thenReturn("sample title");
        when(card.getCurrent()).thenReturn(mock(Column.class));
        when(card.getEstimate()).thenReturn(20);
        when(card.getCurrent().getName()).thenReturn("Analyze");

        Card card1 = mock(Card.class, RETURNS_DEEP_STUBS);
        iteration.addCard(card1);
        when(card1.getEstimate()).thenReturn(8);
        when(card1.getCurrent().getName()).thenReturn("DONE");

        Card card2 = mock(Card.class, RETURNS_DEEP_STUBS);
        iteration.addCard(card2);
        when(card2.getEstimate()).thenReturn(13);
        when(card2.getCurrent().getName()).thenReturn("DONE");
    }

    @Test
    public void getCardByTitleTest() {

        Assert.assertNotNull("get card failed", iteration.getCardByTitle("sample title"));
    }


    @Test
    public void calculateVelocityTest() {

        Assert.assertEquals("velocity calculation failed", 21, iteration.calculateVelocity());
    }

    @Test
    public void moveCardTest() {

        Column column = new Column("Backlog", 3);
        Card card = mock(Card.class);
        iteration.addCard(card);

        try {
            iteration.moveCard(card, column);
            Assert.assertNotNull("card move failed", column.getAllCards());
            Assert.assertEquals("card move failed", 1, column.getAllCards().size());
            Assert.assertEquals("card move failed", column.getName(), card.getCurrent().getName());
        }
        catch (Exception e) {
            new AssertionFailedError();
        }
    }


    @Test(expected = ColumnLimitExceededException.class)
    public void moveCardLimitExceedTest() throws ColumnLimitExceededException {

        Column column = new Column("Backlog", 1);
        Card card = mock(Card.class);
        iteration.addCard(card);

        Card card2 = mock(Card.class);
        iteration.addCard(card2);

        iteration.moveCard(card, column);
        iteration.moveCard(card2, column);
    }

    @Test
    public void undoLastMoveTest() {

        Column column = new Column("Backlog", 2);
        Card card = mock(Card.class, RETURNS_DEEP_STUBS);
        iteration.addCard(card);

        try {
            iteration.moveCard(card, column);
        }
        catch (Exception e) {
            new AssertionFailedError();
        }
        Assert.assertNotNull("card move failed", column.getAllCards());
        Assert.assertEquals("card move failed", 1, column.getAllCards().size());

        when(card.getCurrent()).thenReturn(column);

        iteration.undoLastMove();

        Assert.assertNotNull("undo card move failed", column.getAllCards());
        Assert.assertEquals("undo card move failed", 0, column.getAllCards().size());
    }
}
