package de.janek.sql.builder.sqlBuilders;

import de.janek.sql.builder.DataBaseConnection;
import de.janek.sql.builder.SQLStatementException;
import de.janek.sql.builder.components.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Symbolizes the MySQL DELETE statement
 *
 * @author Janek7
 */
public class DeleteBuilder extends SQLBuilder<Void> {

    private String table;
    private List<Where> filters = new ArrayList<>();

    /**
     * creates a new Delete Builder
     *
     * @param dataBaseConnection used connection to a MySQL database
     */
    public DeleteBuilder(DataBaseConnection dataBaseConnection) {
        super(dataBaseConnection);
    }

    @Override
    public Void execute() throws SQLStatementException, SQLException {

        final StringBuilder statement = new StringBuilder("DELETE FROM ");
        if (table == null) throw new SQLStatementException("no table selected to delete from");
        else statement.append(table);

        List<Object> values = new ArrayList<>();
        if (filters.size() > 0) {
            statement.append(" ");
            filters.forEach(filter -> {
                statement.append(filter == filters.get(0) ? "WHERE " : "AND ").append(filter.getColumn()).append(" = ? ");
                values.add(filter.getValue());
            });
        }

        pStmt = dataBaseConnection.prepareStatement(statement.toString());
        setPreparedStatementParameters(pStmt, values);
        pStmt.executeUpdate();

        return null;
    }

    /**
     * defines the table to delete from
     *
     * @param table table to delete from
     * @return this
     */
    public DeleteBuilder from(String table) {
        this.table = table;
        return this;
    }

    /**
     * adds a Where component to filter the dataset for delete
     *
     * @param column {@link Where#Where(String, Object)}
     * @param value  {@link Where#Where(String, Object)}
     * @return this
     */
    public DeleteBuilder where(String column, Object value) {
        filters.add(new Where(column, value));
        return this;
    }

}
