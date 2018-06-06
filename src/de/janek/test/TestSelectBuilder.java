package de.janek.test;

import de.janek.components.select.OrderType;
import de.janek.SQLStatementException;
import de.janek.sqlBuilder.SelectBuilder;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * test cases for {@link SelectBuilder}
 *
 * @author Janek7
 */
public class TestSelectBuilder extends TestCase {

    private SelectBuilder selectBuilder;

    @Before
    public void createSelectBuilder() {
        selectBuilder = new SelectBuilder(dataBaseConnection);
    }

    @Test
    public void testSelect() {

        selectBuilder.select("name").from("test").where("id", 1);
        ResultSet res = null;
        try {
            res = selectBuilder.execute();
        } catch (SQLException | SQLStatementException e) {
            assert false;
        }
        try {
            res.next();
            assertEquals(res.getString("name"), "Janek");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testSelectSize() {

        //SELECT *
        selectBuilder.from("test").where("id", 1);
        try {
            ResultSet res = selectBuilder.execute();
            res.next();
            assertEquals(res.getMetaData().getColumnCount(), 3);
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
            assert false;
        }

        //SELCT age
        selectBuilder = new SelectBuilder(dataBaseConnection);
        selectBuilder.select("age").from("test").where("id", 1);
        try {
            ResultSet res = selectBuilder.execute();
            res.next();
            assertEquals(res.getMetaData().getColumnCount(), 1);
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
            assert false;
        }

    }

    @Test(expected = SQLStatementException.class)
    public void testMissingFrom() throws SQLStatementException, SQLException {

        selectBuilder.select("name").where("id", 1);
        selectBuilder.execute();

    }

    @Test
    public void testOrderBy() {

        selectBuilder.select("name").select("age", "a").from("test").orderBy("age", OrderType.ASC);
        try {
            ResultSet res = selectBuilder.execute();
            res.next();
            assertEquals(res.getInt("a"), 0);
        } catch (SQLException | SQLStatementException e) {
            e.printStackTrace();
            assert false;
        }

        selectBuilder = new SelectBuilder(dataBaseConnection);
        selectBuilder.select("name").select("age").from("test").orderBy("age", OrderType.DESC);
        try {
            ResultSet res = selectBuilder.execute();
            res.next();
            assertEquals(res.getInt("age"), 100);
        } catch (SQLException | SQLStatementException e) {
            e.printStackTrace();
            assert false;
        }

    }

    @Test(expected = SQLStatementException.class)
    public void testOrderByFail() throws SQLStatementException, SQLException {

        selectBuilder.select("name").from("test").orderBy("age", OrderType.ASC);
        selectBuilder.execute();

    }

}
