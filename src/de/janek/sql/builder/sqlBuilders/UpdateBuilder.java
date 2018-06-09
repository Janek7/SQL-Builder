package de.janek.sql.builder.sqlBuilders;

import de.janek.sql.builder.DataBaseConnection;
import de.janek.sql.builder.SQLStatementException;
import de.janek.sql.builder.components.Update;
import de.janek.sql.builder.components.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Symbolizes the MySQL UPDATE statement
 *
 * @author Janek7
 */
public class UpdateBuilder extends SQLBuilder<Void> {

    private String table;
    private List<Update> updates = new ArrayList<>();
    private List<Where> filters = new ArrayList<>();

    /**
     * creates a new Update Builder
     *
     * @param dataBaseConnection used connection to a MySQL database
     */
    public UpdateBuilder(DataBaseConnection dataBaseConnection) {
        super(dataBaseConnection);
    }

    /**
     * @see SQLBuilder#execute()
     */
    @Override
    public Void execute() throws SQLStatementException, SQLException {

        if (table == null) throw new SQLStatementException("no table to update selected");
        final StringBuilder statement = new StringBuilder("UPDATE ").append(table).append(" SET ");
        List<Object> values = new ArrayList<>();

        //SET
        if (updates.size() > 0) {
            updates.forEach(update -> {
                statement.append(update.getColumn()).append(" = ?, ");
                values.add(update.getValue());
            });
            statement.setLength(statement.length() - 2);
        } else {
            throw new SQLStatementException("no columns to update selected");
        }
        statement.append(" ");

        //WHERE
        if (filters.size() > 0) {
            filters.forEach(filter -> {
                        statement.append(filter == filters.get(0) ? "WHERE " : "AND ")
                                .append(filter.getColumn()).append(" = ? ");
                        values.add(filter.getValue());
                    }
            );
        }

        pStmt = dataBaseConnection.prepareStatement(statement.toString());
        setPreparedStatementParameters(pStmt, values);

        pStmt.executeUpdate();
        return null;
    }

    /**
     * updates the target table
     *
     * @param table table to update
     * @return this
     */
    public UpdateBuilder update(String table) {
        this.table = table;
        return this;
    }

    /**
     * add an attribute which get updated with the given value
     *
     * @param column {@link Update#Update(String, Object)}
     * @param value  {@link Update#Update(String, Object)}
     * @return this
     */
    public UpdateBuilder set(String column, Object value) {
        updates.add(new Update(column, value));
        return this;
    }

    /**
     * adds a WHERE component
     *
     * @param column {@link Where#Where(String, Object)}
     * @param value  {@link Where#Where(String, Object)}
     * @return this
     */
    public UpdateBuilder where(String column, Object value) {
        filters.add(new Where(column, value));
        return this;
    }

}
