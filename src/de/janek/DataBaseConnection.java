package de.janek;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private Connection conn;

    public DataBaseConnection(String host, String user, String password, String scheme) throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://" + host + "/" + scheme + "?autoReconnect=true";
        conn = DriverManager.getConnection(url, user, password);

    }

    public void closeConnection() throws SQLException {
        conn.close();
    }

}
