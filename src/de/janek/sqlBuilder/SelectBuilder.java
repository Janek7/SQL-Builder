package de.janek.sqlBuilder;

import de.janek.DataBaseConnection;
import de.janek.components.select.OrderType;
import de.janek.exceptions.SQLStatementException;
import de.janek.components.Where;
import de.janek.components.select.From;
import de.janek.components.select.OrderBy;
import de.janek.components.select.Select;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectBuilder extends SQLBuilder {

    private List<Select> selects = new ArrayList<>();
    private From from;
    private List<Where> wheres = new ArrayList<>();
    private OrderBy orderBy;

    public SelectBuilder(DataBaseConnection dataBaseConnection) {
        super(dataBaseConnection);
    }

    @Override
    public String createStatement() throws SQLStatementException {

        final StringBuilder statement = new StringBuilder("SELECT ");

        //SELECT
        if (selects.size() > 0) selects.forEach(select -> statement.append(select.getString()));
        else statement.append("*");
        statement.append(" ");

        //FROM
        if (from != null) statement.append(from.getString()).append(" ");
        else throw new SQLStatementException();

        //WHERE
        if (wheres.size() > 0)
            wheres.forEach(where ->
                    statement.append(where == wheres.get(0) ? "WHERE " : "AND ").append(where.getString()).append(" ")
            );

        //ORDER BY
        if (orderBy != null) {
            //TODO: prüfen ob order by in selects enthalten ist
            statement.append(orderBy.getString());
        }

        return statement.toString();

    }

    public SelectBuilder select(String attr, String as) {
        selects.add(new Select(attr, as));
        return this;
    }

    public SelectBuilder select(String attr) {
        return select(attr, null);
    }

    public SelectBuilder from(String table) {
        this.from = new From(table);
        return this;
    }

    public SelectBuilder where(String attr, Object value) {
        this.wheres.add(new Where(attr, value));
        return this;
    }

    public SelectBuilder orderBy(String attr, OrderType orderType) {
        this.orderBy = new OrderBy(attr, orderType);
        return this;
    }

}
