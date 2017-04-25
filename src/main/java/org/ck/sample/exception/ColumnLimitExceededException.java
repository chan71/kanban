package org.ck.sample.exception;

/**
 * Throws when cards in a column exceeds the maximum limit
 */
public class ColumnLimitExceededException extends Exception {
    private String message = "Column card limit has exceeded";

    @Override
    public String getMessage() {
        return message;
    }
}
