package de.janek.sqlBuilder;

import de.janek.DataBaseConnection;
import de.janek.SQLStatementException;
import de.janek.components.Set;
import de.janek.components.Where;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Symbolizes the MySQL UPDATE statement
 *
 * @author Janek7
 */
public final class UpdateBuilder extends SQLBuilder {

    private String table;
    private List<Set> sets = new ArrayList<>();
    private List<Where> wheres = new ArrayList<>();

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
    public ResultSet execute() throws SQLStatementException, SQLException {

        if (table == null) throw new SQLStatementException("no table to update selected");
        final StringBuilder statement = new StringBuilder("UPDATE ").append(table).append(" SET ");
        List<Object> values = new ArrayList<>();

        //SET
        if (sets.size() > 0) {
            sets.forEach(set -> {
                statement.append(set.getColumn()).append(" = ?, ");
                values.add(set.getValue());
            });
            statement.setLength(statement.length() - 2);
        } else {
            throw new SQLStatementException("no columns to update selected");
        }
        statement.append(" ");

        //WHERE
        if (wheres.size() > 0) {
            wheres.forEach(where -> {
                        statement.append(where == wheres.get(0) ? "WHERE " : "AND ").append(where.getColumn()).append(" = ? ");
                        values.add(where.getValue());
                    }
            );
        }

        pStmt = dataBaseConnection.prepareStatement(statement.toString());
        setPreparedStatementParameters(pStmt, values);
        System.out.println(pStmt);

        pStmt.executeUpdate();
        return null;
    }

    /**
     * sets the target table
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
     * @param column {@link Set#Set(String, Object)}
     * @param value  {@link Set#Set(String, Object)}
     * @return this
     */
    public UpdateBuilder set(String column, Object value) {
        sets.add(new Set(column, value));
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
        wheres.add(new Where(column, value));
        return this;
    }

}
