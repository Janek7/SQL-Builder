package de.janek.sql.builder.test;

import de.janek.sql.builder.components.select.JoinType;
import de.janek.sql.builder.components.select.OrderType;
import de.janek.sql.builder.SQLStatementException;
import de.janek.sql.builder.sqlBuilders.SelectBuilder;
import org.junit.Assert;
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

    /**
     * tests a normal select
     */
    @Test
    public void testSelect() {

        selectBuilder.select("name").from("Menschen").where("id", 1);
        try {
            ResultSet res = selectBuilder.execute();
            res.next();
            assertEquals("Janek", res.getString("name"));
        } catch (SQLException | SQLStatementException e) {
            e.printStackTrace();
            assert false;
        }

    }

    /**
     * tests the size of selected columns
     */
    @Test
    public void testSelectSize() {

        //SELECT *
        selectBuilder.from("Menschen").where("id", 1);
        try {
            ResultSet res = selectBuilder.execute();
            res.next();
            assertEquals(3, res.getMetaData().getColumnCount());
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
            assert false;
        }

        //SELCT age
        selectBuilder = new SelectBuilder(dataBaseConnection);
        selectBuilder.select("age").from("Menschen").where("id", 1);
        try {
            ResultSet res = selectBuilder.execute();
            res.next();
            assertEquals(1, res.getMetaData().getColumnCount());
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
            assert false;
        }

    }

    /**
     * tests the occurence from a SQLStatementException if no table to select from is selected
     *
     * @throws SQLStatementException statement error
     * @throws SQLException          sql error
     */
    @Test(expected = SQLStatementException.class)
    public void testMissingFrom() throws SQLStatementException, SQLException {

        selectBuilder.select("name").where("id", 1);
        selectBuilder.execute();

    }

    /**
     * tests a normal join
     */
    @Test
    public void testJoin() {

        selectBuilder.select("name").select("note").from("Menschen").join(JoinType.JOIN, "Noten", "id = mensch_id");
        try {
            ResultSet res = selectBuilder.execute();
            Assert.assertEquals(2, utils.getResultSize(res));
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
            assert false;
        }

    }

    /**
     * tests a left join
     */
    @Test
    public void testLeftJoin() {

        selectBuilder.select("name").from("Menschen").join(JoinType.LEFT_JOIN, "Noten", "id = mensch_id");
        try {
            ResultSet res = selectBuilder.execute();
            Assert.assertEquals(4, utils.getResultSize(res));
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
            assert false;
        }

    }

    /**
     * tests a right join
     */
    @Test
    public void testRightJoin() {

        selectBuilder.select("name").from("Menschen").join(JoinType.RIGHT_JOIN, "Noten", "id = mensch_id");
        try {
            ResultSet res = selectBuilder.execute();
            Assert.assertEquals(3, utils.getResultSize(res));
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
            assert false;
        }

    }

    /**
     * tests order by
     */
    @Test
    public void testOrderBy() {

        selectBuilder.select("name").select("age", "a").from("Menschen").orderBy("age", OrderType.ASC);
        try {
            ResultSet res = selectBuilder.execute();
            res.next();
            assertEquals(0, res.getInt("a"));
        } catch (SQLException | SQLStatementException e) {
            e.printStackTrace();
            assert false;
        }

        selectBuilder = new SelectBuilder(dataBaseConnection);
        selectBuilder.select("name").select("age").from("Menschen").orderBy("age", OrderType.DESC);
        try {
            ResultSet res = selectBuilder.execute();
            res.next();
            assertEquals(100, res.getInt("age"));
        } catch (SQLException | SQLStatementException e) {
            e.printStackTrace();
            assert false;
        }

    }

    /**
     * tests the occurence from a SQLStatementException if the column to order by isnt selected
     * @throws SQLStatementException statement error
     * @throws SQLException          sql error
     */
    @Test(expected = SQLStatementException.class)
    public void testOrderByFail() throws SQLStatementException, SQLException {

        selectBuilder.select("name").from("Menschen").orderBy("age", OrderType.ASC);
        selectBuilder.execute();

    }

}
