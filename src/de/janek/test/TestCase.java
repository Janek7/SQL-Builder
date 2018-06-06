package de.janek.test;

import de.janek.DataBaseConnection;
import de.janek.sqlBuilder.SelectBuilder;
import org.junit.After;
import org.junit.Before;

import java.sql.SQLException;

/**
 * Base class for test classes
 * prepares the test by creating and closing the data base connection
 *
 * @author Janek7
 */
public class TestCase {

    DataBaseConnection dataBaseConnection;

    @Before
    public void connect() {

        try {
            dataBaseConnection = new DataBaseConnection("config.properties");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @After
    public void close() {

        try {
            dataBaseConnection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
