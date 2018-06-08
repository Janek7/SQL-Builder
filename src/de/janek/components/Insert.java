package de.janek.components;

/**
 * Symbolizes the insert of an column value pair in INSERT statement
 *
 * @author Janek7
 */
public final class Insert extends ColumnValuePair {

    /**
     * creates an new column value pair which is part of an INSERT statement
     *
     * @param column column
     * @param value  value
     */
    public Insert(String column, Object value) {
        super(column, value);
    }

}
