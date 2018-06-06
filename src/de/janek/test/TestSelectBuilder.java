package de.janek.test;

import de.janek.components.select.JoinType;
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

        selectBuilder.select("name").from("Menschen").where("id", 1);
        ResultSet res = null;
        try {
            res = selectBuilder.execute();
        } catch (SQLException | SQLStatementException e) {
            assert false;
        }
        try {
            res.next();
            assertEquals("Janek", res.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

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

    @Test(expected = SQLStatementException.class)
    public void testMissingFrom() throws SQLStatementException, SQLException {

        selectBuilder.select("name").where("id", 1);
        selectBuilder.execute();

    }

    @Test
    public void testJoin() {

        selectBuilder.select("name").select("note").from("Menschen").join(JoinType.JOIN, "Noten", "id = mensch_id");
        try {
            ResultSet res = selectBuilder.execute();
            assertEquals(2, utils.getResultSize(res));
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
            assert false;
        }

    }

    @Test
    public void testLeftJoin() {

        selectBuilder.select("name").from("Menschen").join(JoinType.LEFT_JOIN, "Noten", "id = mensch_id");
        try {
            ResultSet res = selectBuilder.execute();
            assertEquals(4, utils.getResultSize(res));
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
            assert false;
        }

    }

    @Test
    public void testRightJoin() {

        selectBuilder.select("name").from("Menschen").join(JoinType.RIGHT_JOIN, "Noten", "id = mensch_id");
        try {
            ResultSet res = selectBuilder.execute();
            assertEquals(3, utils.getResultSize(res));
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
            assert false;
        }

    }

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

    @Test(expected = SQLStatementException.class)
    public void testOrderByFail() throws SQLStatementException, SQLException {

        selectBuilder.select("name").from("Menschen").orderBy("age", OrderType.ASC);
        selectBuilder.execute();

    }

}
