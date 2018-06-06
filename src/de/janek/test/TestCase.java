package de.janek.test;

import de.janek.DataBaseConnection;
import de.janek.Utils;
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
    Utils utils;

    /**
     * creates the data base connection
     */
    @Before
    public void connect() {

        try {
            dataBaseConnection = new DataBaseConnection("config.properties");
            utils = new Utils();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * closes the data base connection
     */
    @After
    public void close() {

        try {
            dataBaseConnection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
