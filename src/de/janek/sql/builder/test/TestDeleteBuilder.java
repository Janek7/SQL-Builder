package de.janek.sql.builder.test;

import de.janek.sql.builder.SQLStatementException;
import de.janek.sql.builder.sqlBuilders.DeleteBuilder;
import de.janek.sql.builder.sqlBuilders.SelectBuilder;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * test cases for {@link DeleteBuilder}
 *
 * @author Janek7
 */
public class TestDeleteBuilder extends TestCase {

    private DeleteBuilder deleteBuilder;

    @Before
    public void createDeleteBuilder() {
        deleteBuilder = new DeleteBuilder(dataBaseConnection);
    }

    /**
     * tests if the whole dataset of the table gets deleted if no filters were selected
     */
    @Test
    public void testCompleteDelete() {

        deleteBuilder.from("test");
        try {
            deleteBuilder.execute();
            SelectBuilder selectBuilder = new SelectBuilder(dataBaseConnection);
            ResultSet res = selectBuilder.from("test").execute();
            assertEquals(false, res.next());
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * tests if a filtered delete
     */
    @Test
    public void testFilteredDelete() {

        try {
            SelectBuilder proofExist = new SelectBuilder(dataBaseConnection);
            ResultSet res = proofExist.from("test").where("id", 3).execute();
            assertEquals(true, res.next());

            deleteBuilder.from("test").where("id", 3);
            deleteBuilder.execute();

            SelectBuilder proofDelete = new SelectBuilder(dataBaseConnection);
            ResultSet res1 = proofDelete.from("test").where("id", 3).execute();
            assertEquals(false, res1.next());
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * tests the occurence from a SQLStatementException if no table to delete from is selected
     *
     * @throws SQLStatementException statement error
     * @throws SQLException          sql error
     */
    @Test(expected = SQLStatementException.class)
    public void testMissingFrom() throws SQLStatementException, SQLException {

        deleteBuilder.where("id", 1);
        deleteBuilder.execute();

    }

}
