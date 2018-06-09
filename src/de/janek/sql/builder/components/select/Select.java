package de.janek.sql.builder.components.select;

import de.janek.sql.builder.components.Component;

/**
 * Symbolizes the selection of a attribute in a SELECT statement
 * MySQL: 'SELECT attr as alias'
 *
 * Note: {@link Select#as} is optional
 *
 * @author Janek7
 */
public final class Select extends Component {

    private String column;
    private String as;

    /**
     * creates a new select component
     *
     * @param column selected column
     * @param as   name of the attribute in resultset
     */
    public Select(String column, String as) {
        this.column = column;
        this.as = as;
    }

    /**
     * @see Component#getString()
     */
    @Override
    public String getString() {
        return column + (as != null ? " as " + as : "");
    }

    public String getColumn() {
        return column;
    }

}
