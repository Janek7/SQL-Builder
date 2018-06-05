package de.janek.sqlBuilder;

import de.janek.DataBaseConnection;
import de.janek.exceptions.MissingStatementElementException;
import de.janek.statementElements.Where;
import de.janek.statementElements.select.From;
import de.janek.statementElements.select.OrderBy;
import de.janek.statementElements.select.Select;

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
    public String createStatement() throws MissingStatementElementException{

        StringBuilder statement = new StringBuilder("SELECT ");
        if (selects.size() > 0) {
            selects.forEach(select -> statement.append(select.toString()));
        } else {
            statement.append("*");
        }
        statement.append(" ");
        if (from != null) {

        } else {
            throw new MissingStatementElementException();
        }
        return null;
    }

    @Override
    public void runStatement() throws SQLException {

    }

    public SelectBuilder select(Select select) {
        selects.add(select);
        return this;
    }

    public SelectBuilder from(From from) {
        this.from = from;
        return this;
    }

    public SelectBuilder where(Where where) {
        this.wheres.add(where);
        return this;
    }

    public SelectBuilder orderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
        return this;
    }



}
