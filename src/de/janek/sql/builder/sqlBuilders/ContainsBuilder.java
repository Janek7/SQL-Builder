package de.janek.sql.builder.sqlBuilders;

import de.janek.sql.builder.DataBaseConnection;
import de.janek.sql.builder.SQLStatementException;

import java.sql.SQLException;

/**
 * Symbolizes a table request
 * Just an simple use of the selectbuilder
 *
 * @author Janek7
 */
public class ContainsBuilder extends SQLBuilder<Boolean> {

    private SelectBuilder selectBuilder;

    /**
     * creates a new SQL Builder
     *
     * @param dataBaseConnection used connection to a MySQL database
     */
    public ContainsBuilder(DataBaseConnection dataBaseConnection) {
        super(dataBaseConnection);
        selectBuilder = new SelectBuilder(dataBaseConnection);
    }

    @Override
    public Boolean execute() throws SQLStatementException, SQLException {
        if (!selectBuilder.hasWhere()) throw new SQLStatementException("no filters to check for exist selected");
        return selectBuilder.execute().next();
    }

    /**
     * defines the table to check for the dataset
     *
     * @param table table to search
     * @return this
     */
    public ContainsBuilder table(String table) {
        selectBuilder.from(table);
        return this;
    }

    /**
     * adds an filter to search for
     *
     * @param column {@link SelectBuilder#where(String, Object)}
     * @param value  {@link SelectBuilder#where(String, Object)}
     * @return this
     */
    public ContainsBuilder filter(String column, Object value) {
        selectBuilder.where(column, value);
        return this;
    }

}
