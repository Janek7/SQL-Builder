package de.janek.components;

/**
 * Symbolizes a filter of the dataset
 * MySQL: 'WHERE attr = value'
 *
 * @author Janek7
 */
public final class Where extends ColumnValuePair {

    /**
     * creates a new where component
     *
     * @param column  filter attribute
     * @param value filter value
     */
    public Where(String column, Object value) {
        super(column, value);
    }

}
