package de.janek.sql.builder.sqlBuilders;

import de.janek.sql.builder.DataBaseConnection;
import de.janek.sql.builder.SQLStatementException;
import de.janek.sql.builder.components.InsertValue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Symbolizes the MySQL INSERT statement
 *
 * @author Janek7
 */
public class InsertBuilder extends SQLBuilder<Void> {

    private String into;
    private List<InsertValue> insertValues = new ArrayList<>();

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
    public Void execute() throws SQLStatementException, SQLException {

        final StringBuilder statement = new StringBuilder("INSERT INTO ");
        if (into == null) throw new SQLStatementException("no table for value selected");
        statement.append(into).append(" (");

        insertValues.forEach(insertValue -> statement.append(insertValue.getColumn()).append(", "));
        statement.setLength(statement.length() - 2);
        statement.append(") VALUES (");
        final List<Object> insertValues = new ArrayList<>();
        this.insertValues.forEach(insertValue -> {
            statement.append("?, ");
            insertValues.add(insertValue.getValue());
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
     * @param into table to value
     * @return this
     */
    public InsertBuilder into(String into) {
        this.into = into;
        return this;
    }

    /**
     * adds an new column value pair to value
     *
     * @param column {@link InsertValue#InsertValue(String, Object)}
     * @param value  {@link InsertValue#InsertValue(String, Object)}
     * @return this
     */
    public InsertBuilder value(String column, Object value) {
        insertValues.add(new InsertValue(column, value));
        return this;
    }

}
