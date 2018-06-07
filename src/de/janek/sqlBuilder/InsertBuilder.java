package de.janek.sqlBuilder;

import de.janek.DataBaseConnection;
import de.janek.SQLStatementException;
import de.janek.components.insert.Insert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Symbolizes the MySQL INSERT statement
 *
 * @author Janek7
 */
public class InsertBuilder extends SQLBuilder {

    private String into;
    private List<Insert> inserts = new ArrayList<>();

    /**
     * @see SQLBuilder#SQLBuilder(DataBaseConnection)
     */
    public InsertBuilder(DataBaseConnection dataBaseConnection) {
        super(dataBaseConnection);
    }

    /**
     * @see SQLBuilder#execute()
     */
    @Override
    public ResultSet execute() throws SQLStatementException, SQLException {

        final StringBuilder statement = new StringBuilder("INSERT INTO ");
        statement.append(into).append(" (");

        inserts.forEach(insert -> statement.append(insert.getColumn()).append(", "));
        statement.setLength(statement.length() - 2);
        statement.append(") VALUES (");
        final List<Object> insertValues = new ArrayList<>();
        inserts.forEach(insert -> {
            statement.append("?, ");
            insertValues.add(insert.getValue());
        });
        statement.setLength(statement.length() - 2);
        statement.append(") ");

        pStmt = dataBaseConnection.prepareStatement(statement.toString());
        setPreparedStatementParameters(pStmt, insertValues);

        pStmt.executeUpdate();
        return null;

    }

    /**
     * defines the target table
     *
     * @param into table to insert
     * @return this
     */
    public InsertBuilder into(String into) {
        this.into = into;
        return this;
    }

    /**
     * adds an new column value pair to insert
     *
     * @return this
     * @see Insert#Insert(String, Object)
     */
    public InsertBuilder insert(String column, Object value) {
        inserts.add(new Insert(column, value));
        return this;
    }

}
