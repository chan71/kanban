package org.ck.sample.model;

import org.ck.sample.exception.ColumnLimitExceededException;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Iteration - A Sprint in Agile Scrum
 */
public class Iteration {

    private Collection<Card> cards = new LinkedList<Card>();
    private Card lastMove;

    public int calculateVelocity() {
        int velocity = 0;
        for (final Card card: cards) {
            if ("DONE".equalsIgnoreCase(card.getCurrent().getName())) {
                velocity += card.getEstimate();
            }
        }
        return velocity;
    }

    public Iteration addCard(final Card c) {
        cards.add(c);
        return this;
    }

    public Card getCardByTitle(final String title) {
        if (title == null || title.length() == 0) {
            throw new IllegalArgumentException();
        }

        for (Card card: cards) {
            if (title.equals(card.getTitle())) return card;
        }
        return null;
    }

    public void moveCard(final Card card, final Column toColumn) throws ColumnLimitExceededException {
        if (card == null || toColumn == null) {
            throw new IllegalArgumentException();
        }

        card.setLast(card.getCurrent());
        card.setCurrent(toColumn);
        toColumn.addCard(card);
        lastMove = card;
    }

    public void undoLastMove() {
        if (lastMove != null) {
            lastMove.getCurrent().removeCard(lastMove);
            lastMove.setCurrent(lastMove.getLast());
            lastMove.setLast(null);
            lastMove = null;
        }
    }
}
