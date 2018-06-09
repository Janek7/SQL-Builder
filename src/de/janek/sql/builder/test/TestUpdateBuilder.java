package de.janek.sql.builder.test;

import de.janek.sql.builder.SQLStatementException;
import de.janek.sql.builder.sqlBuilders.UpdateBuilder;
import de.janek.sql.builder.sqlBuilders.SelectBuilder;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * test cases for {@link UpdateBuilder}
 *
 * @author Janek7
 */
public class TestUpdateBuilder extends TestCase {

    private UpdateBuilder updateBuilder;

    @Before
    public void createSelectBuilder() {
        updateBuilder = new UpdateBuilder(dataBaseConnection);
    }

    /**
     * tests a normal update
     */
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

    /**
     * tests the occurence from a SQLStatementException if no table to update is selected
     *
     * @throws SQLStatementException statement error
     * @throws SQLException          sql error
     */
    @Test (expected = SQLStatementException.class)
    public void testMissingTable() throws SQLStatementException, SQLException {

        updateBuilder.set("name", "Janeek");
        updateBuilder.execute();

    }

    /**
     * tests the occurence from a SQLStatementException if nothing to update is set
     *
     * @throws SQLStatementException statement error
     * @throws SQLException          sql error
     */
    @Test (expected = SQLStatementException.class)
    public void testMissingSets() throws SQLStatementException, SQLException {

        updateBuilder.update("Menschen").where("id",1);
        updateBuilder.execute();

    }


}
