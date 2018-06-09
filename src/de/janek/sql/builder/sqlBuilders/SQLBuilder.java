package de.janek.sql.builder.sqlBuilders;

import de.janek.sql.builder.DataBaseConnection;
import de.janek.sql.builder.SQLStatementException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Base class for SQL Statement Builder Classes
 *
 * @author Janek7
 */
public abstract class SQLBuilder<R> {

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
     * creates and executes the statement and provides the result as a ResultSet
     *
     * @return return type
     * @throws SQLStatementException statement error
     * @throws SQLException          sql error
     */
    public abstract R execute() throws SQLStatementException, SQLException;

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
            i++;
        }

    }

}
