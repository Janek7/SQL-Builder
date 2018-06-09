package de.janek.sql.builder.test;

import de.janek.sql.builder.SQLStatementException;
import de.janek.sql.builder.sqlBuilders.ContainsBuilder;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * test cases for {@link ContainsBuilder}
 *
 * @author Janek7
 */
public class TestContainsBuilder extends TestCase {

    private ContainsBuilder containsBuilder;

    @Before
    public void createContainsBuilder() {
        containsBuilder = new ContainsBuilder(dataBaseConnection);
    }

    /**
     * tests a positive contains
     */
    @Test
    public void testPositiveContains() {

        containsBuilder.table("Menschen").filter("name", "Janek").filter("age", 20);
        try {
            assertEquals(true, containsBuilder.execute());
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * tests a negative contains
     */
    @Test
    public void testNegativeContains() {

        containsBuilder.table("Menschen").filter("name", "Janek").filter("age", 21);
        try {
            assertEquals(false, containsBuilder.execute());
        } catch (SQLStatementException | SQLException e) {
            e.printStackTrace();
        }

    }

}
