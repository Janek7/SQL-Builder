package de.janek.sql.builder.components;

public final class Update extends ColumnValuePair {

    /**
     * creates an new attr value pair to update
     *
     * @param column  attr
     * @param value value
     */
    public Update(String column, Object value) {
        super(column, value);
    }

}
