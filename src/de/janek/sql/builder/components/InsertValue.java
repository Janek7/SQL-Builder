package de.janek.sql.builder.components;

/**
 * Symbolizes the value of an column value pair in INSERT statement
 *
 * @author Janek7
 */
public final class InsertValue extends ColumnValuePair {

    /**
     * creates an new column value pair which is part of an INSERT statement
     *
     * @param column column
     * @param value  value
     */
    public InsertValue(String column, Object value) {
        super(column, value);
    }

}
