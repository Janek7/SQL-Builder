package de.janek.sql.builder.test;

import de.janek.sql.builder.SQLStatementException;
import de.janek.sql.builder.components.select.OrderType;
import de.janek.sql.builder.sqlBuilders.InsertBuilder;
import de.janek.sql.builder.sqlBuilders.SelectBuilder;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * test cases for {@link InsertBuilder}
 *
 * @author Janek7
 */
public class TestInsertBuilder extends TestCase {

    private InsertBuilder insertBuilder;

    @Before
    public void createInsertBuilder() {
        insertBuilder = new InsertBuilder(dataBaseConnection);
    }

    /**
     * tests a normal value
     */
    @Test
    public void testInsert() {

        insertBuilder.into("Menschen").value("name", "Leon").value("age", 18);
        try {
            insertBuilder.execute();
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
            assert false;
        }

        SelectBuilder selectBuilder = new SelectBuilder(dataBaseConnection);
        try {
            ResultSet res = selectBuilder.from("Menschen").orderBy("id", OrderType.DESC).execute();
            res.next();
            assertEquals("Leon", res.getString("name"));
            assertEquals(18, res.getInt("age"));
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
            assert false;
        }

    }

    /**
     * tests the occurence from a SQLStatementException if no table to value in is selected
     *
     * @throws SQLStatementException statement error
     * @throws SQLException          sql error
     */
    @Test (expected = SQLStatementException.class)
    public void testMissingInto() throws SQLStatementException, SQLException {

        insertBuilder.value("name", "Test");
        insertBuilder.execute();

    }

}
