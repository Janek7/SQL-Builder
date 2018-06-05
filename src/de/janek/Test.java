package de.janek;

import de.janek.sqlBuilder.SelectBuilder;
import de.janek.statementElements.Where;
import de.janek.statementElements.select.From;
import de.janek.statementElements.select.Select;

import java.sql.SQLException;

public class Test {

    private static DataBaseConnection dataBaseConnection;

    public static void main(String[] args) {

        try {
            dataBaseConnection = new DataBaseConnection("", "", "", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    private static void testSelect() {

        SelectBuilder selectBuilder = new SelectBuilder(dataBaseConnection);
        selectBuilder
                .select(new Select("name"))
                .from(new From("table"))
                .where(new Where("id",1));

    }

}
