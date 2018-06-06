package de.janek.sqlBuilder;

import de.janek.DataBaseConnection;
import de.janek.SQLStatementException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Base class for Statement Builder Classes
 *
 * @author Janek7
 */
public abstract class SQLBuilder {

    DataBaseConnection dataBaseConnection;
    PreparedStatement pStmt;

    /**
     * creates a new SQL Builder
     *
     * @param dataBaseConnection used connection to a MySQL database
     */
    SQLBuilder(DataBaseConnection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
    }

    /**
     * creates the sql statement from added components
     *
     * @return statement as string
     * @throws SQLStatementException statement error
     */
    public abstract String createStatement() throws SQLStatementException;

    /**
     * executes the statement and provides the result as a ResultSet
     *
     * @return resultset
     * @throws SQLStatementException statement error
     * @throws SQLException          sql error
     */
    public abstract ResultSet execute() throws SQLStatementException, SQLException;

    /**
     * fills the ? parameters of a given prepared statement with values
     *
     * @param pStmt  statement with unfilled parameters
     * @param values values for ? parameters
     * @throws SQLException sql error
     */
    void setPreparedStatementParameters(PreparedStatement pStmt, List<Object> values) throws SQLException {

        int i = 1;
        for (Object value : values) {
            pStmt.setObject(i, value);
        }

    }

}
