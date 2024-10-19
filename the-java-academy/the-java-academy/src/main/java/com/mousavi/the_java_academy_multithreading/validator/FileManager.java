package com.mousavi.the_java_academy_multithreading.validator;

import java.sql.*;
import java.util.*;

public class FileManager {

    // متصل شدن به پایگاه داده و خواندن داده‌ها
    public List<String> readFromDatabase(String query, String dbUrl, String user, String password) throws SQLException {
        List<String> results = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.append(resultSet.getString(i)).append(",");
                }
                // Remove trailing comma
                results.add(row.substring(0, row.length() - 1));
            }
        }
        return results;
    }
}