package de.janek.components.select;

import de.janek.components.Component;

/**
 * Symbolizes the data source / table in SELECT statement
 * MySQL: 'FROM table'
 *
 * Note: {@link From#alias} is optional
 *
 * @author Janek7
 */
public final class From extends Component {

    private String table;
    private String alias;

    /**
     * creates a new From Component
     *
     * @param table Table
     * @param alias table alias
     */
    public From(String table, String alias) {
        this.table = table;
        this.alias = alias;
    }

    /**
     * @see Component#getString()
     */
    @Override
    public String getString() {
        return "FROM " + table + (alias != null ? " " + alias : "");
    }

    public String getTable() {
        return table;
    }

    public String getAlias() {
        return alias;
    }
}
