package org.ck.sample.model;

import org.ck.sample.exception.ColumnLimitExceededException;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Column - Swimlane of a Kanban board
 */
public class Column {

    private Collection<Card> cards = new LinkedList<Card>();
    private String name;

    public Column(String name, int maxCardsAllowed) {
        this.name = name;
        this.maxCardsAllowed = maxCardsAllowed;
    }

    private int maxCardsAllowed;

    public Column addCard(final Card card) throws ColumnLimitExceededException {
        if (cards.size() == maxCardsAllowed) {
            throw new ColumnLimitExceededException();
        }
        cards.add(card);
        return this;
    }

    public void removeCard(final Card card) {
        cards.remove(card);
    }

    public Collection<Card> getAllCards() {
        return cards;
    }

    public int getMaxCardsAllowed() {
        return maxCardsAllowed;
    }

    public void setMaxCardsAllowed(int maxCardsAllowed) {
        this.maxCardsAllowed = maxCardsAllowed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
