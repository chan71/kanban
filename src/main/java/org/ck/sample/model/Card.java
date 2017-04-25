package org.ck.sample.model;

import java.awt.*;

/**
 * Card - An Agile User Story
 */
public class Card {

    private String title;
    private String description;
    private int estimate;
    private Column current;
    private Column last;


    public Card(String title, String description, int estimate, Column current) {
        this.title = title;
        this.description = description;
        this.estimate = estimate;
        this.current = current;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    public Column getCurrent() {
        return current;
    }

    public void setCurrent(Column current) {
        this.current = current;
    }

    public Column getLast() {
        return last;
    }

    public void setLast(Column last) {
        this.last = last;
    }
}
