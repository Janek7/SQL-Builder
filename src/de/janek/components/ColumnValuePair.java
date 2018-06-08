package de.janek.components;

/**
 * Symbolizes a pair of an attribute / column and a value
 *
 * @author Janek7
 */
abstract class ColumnValuePair {

    private String column;
    private Object value;

    /**
     * creates an new column value pair
     *
     * @param column column
     * @param value  value
     */
    ColumnValuePair(String column, Object value) {
        this.column = column;
        this.value = value;
    }

    public String getColumn() {
        return column;
    }

    public Object getValue() {
        return value;
    }

}
