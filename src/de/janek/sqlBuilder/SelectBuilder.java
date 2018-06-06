package de.janek.sqlBuilder;

import de.janek.DataBaseConnection;
import de.janek.components.select.OrderType;
import de.janek.SQLStatementException;
import de.janek.components.Where;
import de.janek.components.select.From;
import de.janek.components.select.OrderBy;
import de.janek.components.select.Select;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Symbolizes the MySQL SELECT statement
 *
 * @author Janek7
 */
public class SelectBuilder extends SQLBuilder {

    private List<Select> selects = new ArrayList<>();
    private From from;
    private List<Where> wheres = new ArrayList<>();
    private OrderBy orderBy;

    /**
     * @see SQLBuilder#SQLBuilder(DataBaseConnection)
     */
    public SelectBuilder(DataBaseConnection dataBaseConnection) {
        super(dataBaseConnection);
    }

    /**
     * @see SQLBuilder#createStatement()
     */
    @Override
    public String createStatement() throws SQLStatementException {

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

        //WHERE
        if (wheres.size() > 0)
            wheres.forEach(where ->
                    statement.append(where == wheres.get(0) ? "WHERE " : "AND ").append(where.getString()).append(" = ? ")
            );

        //ORDER BY
        if (orderBy != null) {
            boolean orderByInSelect = false;
            for (Select select : selects)
                if (select.getAttr().equalsIgnoreCase(orderBy.getAttr())) orderByInSelect = true;
            if (!orderByInSelect) throw new SQLStatementException("order by element is not part of SELECT ...");
            statement.append(orderBy.getString());
        }

        return statement.toString();

    }

    /**
     * @see SQLBuilder#execute()
     */
    @Override
    public ResultSet execute() throws SQLStatementException, SQLException {

        pStmt = dataBaseConnection.prepareStatement(createStatement());
        final List<Object> whereValues = new ArrayList<>();
        wheres.forEach(where -> whereValues.add(where.getValue()));
        setPreparedStatementParameters(pStmt, whereValues);
        return pStmt.executeQuery();

    }

    /**
     * adds a select component
     *
     * @param attr @see {@link Select#attr}
     * @param as   @see {@link Select#as}
     * @return this
     */
    public SelectBuilder select(String attr, String as) {
        selects.add(new Select(attr, as));
        return this;
    }

    /**
     * @see SelectBuilder#select(String, String)
     */
    public SelectBuilder select(String attr) {
        return select(attr, null);
    }

    /**
     * adds the from component
     *
     * @param table @see {@link From#table}
     * @param alias @see {@link From#alias}
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
     * @param attr  @see {@link Where#attr}
     * @param value @see {@link Where#value}
     * @return this
     */
    public SelectBuilder where(String attr, Object value) {
        this.wheres.add(new Where(attr, value));
        return this;
    }

    /**
     * adds the ORDER BY component
     *
     * @param attr      @see {@link OrderBy#attr}
     * @param orderType @see {@link OrderBy#orderType}
     * @return this
     */
    public SelectBuilder orderBy(String attr, OrderType orderType) {
        this.orderBy = new OrderBy(attr, orderType);
        return this;
    }

}
