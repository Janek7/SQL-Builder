package de.janek.components.insert;

import de.janek.components.Component;

/**
 * Symbolizes the insert of an column value pair in INSERT statement
 *
 * @author Janek7
 */
public class Insert extends Component {

    private String column;
    private Object value;

    /**
     * creates an new column value pair which is part of an INSERT statement
     *
     * @param column column
     * @param value  value
     */
    public Insert(String column, Object value) {
        this.column = column;
        this.value = value;
    }

    @Override
    public String getString() {
        return null;
    }

    public String getColumn() {
        return column;
    }

    public Object getValue() {
        return value;
    }

}
