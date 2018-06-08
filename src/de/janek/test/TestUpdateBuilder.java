package de.janek.test;

import de.janek.SQLStatementException;
import de.janek.sqlBuilder.SelectBuilder;
import de.janek.sqlBuilder.UpdateBuilder;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class TestUpdateBuilder extends TestCase {

    private UpdateBuilder updateBuilder;

    @Before
    public void createSelectBuilder() {
        updateBuilder = new UpdateBuilder(dataBaseConnection);
    }

    @Test
    public void testUpdate() {

        try {
            updateBuilder.update("Menschen").set("name", "Janeek").where("id",1).execute();
            ResultSet res = new SelectBuilder(dataBaseConnection).select("name").from("Menschen").where("id", 1).execute();
            res.next();
            assertEquals("Janeek", res.getString("name"));
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
            assert false;
        }

    }

    @Test (expected = SQLStatementException.class)
    public void testMissingTable() throws SQLStatementException, SQLException {

        updateBuilder.set("name", "Janeek");
        updateBuilder.execute();

    }

    @Test (expected = SQLStatementException.class)
    public void testMissingSets() throws SQLStatementException, SQLException {

        updateBuilder.update("Menschen").where("id",1);
        updateBuilder.execute();

    }


}
