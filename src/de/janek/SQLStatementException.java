package de.janek;

/**
 * Exception for logical errors while building the MySQL statements
 *
 * @author Janek7
 */
public final class SQLStatementException extends Exception {

    /**
     * creates a new SQLStatementException
     *
     * @param message custom message
     */
    public SQLStatementException(String message) {
        super("Statement Exception: " + message);
    }

}
