package de.janek;

import de.janek.components.select.OrderType;
import de.janek.exceptions.SQLStatementException;
import de.janek.sqlBuilder.SelectBuilder;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Test {

    private static DataBaseConnection dataBaseConnection;

    public static void main(String[] args) {

        try {
            dataBaseConnection = new DataBaseConnection("config.properties");
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }

        testSelect();

    }

    private static void testSelect() {

        SelectBuilder selectBuilder = new SelectBuilder(dataBaseConnection);
        selectBuilder.select("name").from("table").where("id", 3).orderBy("name", OrderType.ASC);
        try {
            System.out.println(selectBuilder.createStatement());
        } catch (SQLStatementException e) {
            e.printStackTrace();
        }

    }

}
