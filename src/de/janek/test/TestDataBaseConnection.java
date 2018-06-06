package de.janek.test;

import de.janek.DataBaseConnection;
import org.junit.Test;

import java.sql.SQLException;

public class TestDataBaseConnection {

    @Test
    public void testSuccessfulConnection() {

        try {
            DataBaseConnection dataBaseConnection = new DataBaseConnection("config.properties");
            assert true;
        } catch (SQLException e) {
            assert false;
        }

    }

}
