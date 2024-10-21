package com.mousavi.the_java_academy_multithreading.thread;


import java.sql.*;
import java.util.concurrent.BlockingQueue;

public class DBWriterThread implements Runnable {
    private BlockingQueue<String[]> queue;
    private boolean isAccount;

    public DBWriterThread(BlockingQueue<String[]> queue, boolean isAccount) {
        this.queue = queue;
        this.isAccount = isAccount;
    }

    @Override
    public void run() {

        String dbUrl = "jdbc:mysql://localhost:3306/customer";
        String user = "root";
        String password = "123456";

        try (Connection connection = DriverManager.getConnection(dbUrl, user, password)) {
            while (true) {
                String[] line = queue.take();
                if (isAccount) {
                    insertAccount(connection, line);
                } else {
                    insertCustomer(connection, line);
                }
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void insertAccount(Connection connection, String[] line) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO accounts (record_number, account_number, customer_id, account_type, customer_name, account_customer_id, account_limit, customer_zip_code, account_open_date, customer_national_id, account_balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, line[0]);
            stmt.setString(2, line[1]);
            stmt.setString(3, line[2]);
            stmt.setString(4, line[3]);
            stmt.setString(5, line[4]);
            stmt.setString(6, line[5]);
            stmt.setDouble(7, Double.parseDouble(line[6]));
            stmt.setString(8, line[7]);
            stmt.setDate(9, Date.valueOf(line[8]));
            stmt.setString(10, line[9]);
            stmt.setDouble(11, Double.parseDouble(line[10]));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertCustomer(Connection connection, String[] line) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO customers (customer_birth_date, customer_zip_code, customer_address, customer_surname, customer_name, customer_id, record_number) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setDate(1, Date.valueOf(line[0]));
            stmt.setString(2, line[1]);
            stmt.setString(3, line[2]);
            stmt.setString(4, line[3]);
            stmt.setString(5, line[4]);

        }
    }
}