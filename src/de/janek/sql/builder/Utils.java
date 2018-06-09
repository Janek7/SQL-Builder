package de.janek.sql.builder;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class Utils {

    /**
     * provides the size of a jdbc resultset
     *
     * @param resultSet resultset
     * @return size
     */
    public int getResultSize(ResultSet resultSet) {

        try {
            resultSet.last();
            return resultSet.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

}
