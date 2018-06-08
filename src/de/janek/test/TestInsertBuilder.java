package de.janek.test;

import de.janek.SQLStatementException;
import de.janek.components.select.OrderType;
import de.janek.sqlBuilder.InsertBuilder;
import de.janek.sqlBuilder.SelectBuilder;
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

    @Test
    public void testInsert() {

        insertBuilder.into("Menschen").insert("name", "Leon").insert("age", 18);
        try {
            insertBuilder.execute();
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
            assert false;
        }

        SelectBuilder selectBuilder = new SelectBuilder(dataBaseConnection);
        ResultSet res = null;
        try {
            res = selectBuilder.from("Menschen").orderBy("id", OrderType.DESC).execute();
            res.next();
            assertEquals("Leon", res.getString("name"));
            assertEquals(18, res.getInt("age"));
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
            assert false;
        }

    }

    @Test (expected = SQLStatementException.class)
    public void testMissingInto() throws SQLStatementException, SQLException {

        insertBuilder.insert("name", "Test");
        insertBuilder.execute();

    }

}
