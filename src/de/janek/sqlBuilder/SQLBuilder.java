package de.janek.sqlBuilder;

import de.janek.DataBaseConnection;
import de.janek.exceptions.MissingStatementElementException;

import java.sql.SQLException;

public abstract class SQLBuilder {

    DataBaseConnection dataBaseConnection;

    SQLBuilder(DataBaseConnection dataBaseConnection) {
        this.dataBaseConnection = dataBaseConnection;
    }

    public abstract String createStatement() throws MissingStatementElementException;

    public abstract void runStatement() throws SQLException;

}
