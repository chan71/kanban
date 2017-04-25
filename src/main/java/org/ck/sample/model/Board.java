package org.ck.sample.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Board - A Kanban Board
 */
public class Board {

    private Iteration iteration;
    private HashMap<String, Column> columns = new HashMap<String, Column>();

    public Board addColumn(final String name, final Column column) {
        columns.put(name, column);
        return this;
    }

    public Board removeColumn(final String columnName) {
        columns.remove(columnName);
        return this;
    }

    public Column getColumn(final String columnName) {
        return columns.get(columnName);
    }

    public Iteration getIteration() {
        return iteration;
    }

    public void setIteration(Iteration iteration) {
        this.iteration = iteration;
    }
}
