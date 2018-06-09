package de.janek.sql.builder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * A Connection to a database
 *
 * @author Janek7
 */
public class DataBaseConnection {

    private Connection conn;

    /**
     * @see DataBaseConnection#createConn
     */
    public DataBaseConnection(String host, String user, String password, String scheme) throws SQLException {
        createConn(host, user, password, scheme);
    }

    /**
     * creates a database connection with a properties object based on a config file
     * the config must provide the properties 'server', 'user', 'password' and 'scheme'
     *
     * @param configPath path to config file
     * @throws SQLException          sql connection error
     */
    public DataBaseConnection(String configPath) throws SQLException {

        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(configPath)) {
            prop.load(input);
            createConn(prop.getProperty("server"),
                    prop.getProperty("user"),
                    prop.getProperty("password"),
                    prop.getProperty("scheme")
            );
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * creates a new database connection
     *
     * @param host     host of the database
     * @param user     username
     * @param password password
     * @param scheme   target scheme
     * @throws SQLException sql error
     */
    private void createConn(String host, String user, String password, String scheme) throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://" + host + "/" + scheme + "?autoReconnect=true";
        conn = DriverManager.getConnection(url, user, password);

    }

    /**
     * creates a prepared statement
     *
     * @param statement statement as string
     * @return prepared statement
     * @throws SQLException sql error
     */
    public PreparedStatement prepareStatement(String statement) throws SQLException {
        return conn.prepareStatement(statement);
    }

    /**
     * closes the connection
     *
     * @throws SQLException sql error
     */
    public void closeConnection() throws SQLException {
        conn.close();
    }

}
