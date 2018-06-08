package de.janek.sqlBuilder;

import de.janek.DataBaseConnection;
import de.janek.components.select.*;
import de.janek.SQLStatementException;
import de.janek.components.Where;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Symbolizes the MySQL SELECT statement
 *
 * @author Janek7
 */
public final class SelectBuilder extends SQLBuilder {

    private List<Select> selects = new ArrayList<>();
    private From from;
    private List<Where> wheres = new ArrayList<>();
    private List<Join> joins = new ArrayList<>();
    private OrderBy orderBy;

    /**
     * @see SQLBuilder#SQLBuilder(DataBaseConnection)
     */
    public SelectBuilder(DataBaseConnection dataBaseConnection) {
        super(dataBaseConnection);
    }

    /**
     * @see SQLBuilder#execute()
     */
    @Override
    public ResultSet execute() throws SQLStatementException, SQLException {

        final StringBuilder statement = new StringBuilder("SELECT ");

        //SELECT
        if (selects.size() > 0) {
            selects.forEach(select -> statement.append(select.getString()).append(", "));
            statement.setLength(statement.length() - 2);
        } else statement.append("*");
        statement.append(" ");

        //FROM
        if (from != null) statement.append(from.getString()).append(" ");
        else throw new SQLStatementException("please add a from statement");

        //JOIN
        if (joins.size() > 0) {
            joins.forEach(join -> statement.append(join.getString()).append(" "));
        }

        //WHERE
        List<Object> whereValues = new ArrayList<>();
        if (wheres.size() > 0)
            wheres.forEach(where -> {
                        statement.append(where == wheres.get(0) ? "WHERE " : "AND ").append(where.getColumn()).append(" = ? ");
                        whereValues.add(where.getValue());
                    }
            );

        //ORDER BY
        if (orderBy != null) {
            boolean orderByInSelect = false;
            for (Select select : selects)
                if (select.getColumn().equalsIgnoreCase(orderBy.getColumn())) orderByInSelect = true;
            if (!orderByInSelect) {
                if (selects.size() != 0) throw new SQLStatementException("order by element is not part of SELECT");
            }
            statement.append(orderBy.getString());
        }

        pStmt = dataBaseConnection.prepareStatement(statement.toString());
        setPreparedStatementParameters(pStmt, whereValues);
        System.out.println(pStmt);

        return pStmt.executeQuery();

    }

    /**
     * adds a select component
     *
     * @param column {@link Select#Select(String, String)}
     * @param as   {@link Select#Select(String, String)}
     * @return this
     */
    public SelectBuilder select(String column, String as) {
        selects.add(new Select(column, as));
        return this;
    }

    /**
     * @see SelectBuilder#select(String, String)
     */
    public SelectBuilder select(String column) {
        return select(column, null);
    }

    /**
     * adds the from component
     *
     * @param table {@link From#From(String, String)}
     * @param alias {@link From#From(String, String)}
     * @return this
     */
    public SelectBuilder from(String table, String alias) {
        this.from = new From(table, alias);
        return this;
    }

    /**
     * @see SelectBuilder#from(String, String)
     */
    public SelectBuilder from(String table) {
        return from(table, null);
    }

    /**
     * adds a WHERE component
     *
     * @param column  {@link Where#Where(String, Object)}
     * @param value {@link Where#Where(String, Object)}
     * @return this
     */
    public SelectBuilder where(String column, Object value) {
        this.wheres.add(new Where(column, value));
        return this;
    }

    /**
     * adds a table join between an existing table and a new one
     *
     * @param joinType      {@link Join#Join(JoinType, From, String)}
     * @param ohterTable    {@link Join#Join(JoinType, From, String)}
     * @param alias         {@link Join#Join(JoinType, From, String)}
     * @param joinCondition {@link Join#Join(JoinType, From, String)}
     *                      must match to the name / alias of the tables
     * @return this
     */
    public SelectBuilder join(JoinType joinType, String ohterTable, String alias, String joinCondition) {
        this.joins.add(new Join(joinType, new From(ohterTable, alias), joinCondition));
        return this;
    }

    /**
     * @see SelectBuilder#join(JoinType, String, String, String)
     */
    public SelectBuilder join(JoinType joinType, String ohterTable, String joinCondition) {
        return join(joinType, ohterTable, null, joinCondition);
    }

    /**
     * adds the ORDER BY component
     *
     * @param column      {@link OrderBy#OrderBy(String, OrderType)}
     * @param orderType {@link OrderBy#OrderBy(String, OrderType)}
     * @return this
     */
    public SelectBuilder orderBy(String column, OrderType orderType) {
        this.orderBy = new OrderBy(column, orderType);
        return this;
    }

}
