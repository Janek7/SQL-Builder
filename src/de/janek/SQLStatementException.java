package de.janek;

public class SQLStatementException extends Exception {

    public SQLStatementException(String message) {
        super("Statement Exception: " + message);
    }

}
