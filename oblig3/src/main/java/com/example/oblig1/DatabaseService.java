package com.example.oblig1;
// DatabaseService.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;

public class DatabaseService {
    private static String url = "jdbc:sqlite:mydatabase.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void createNewTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS tickets (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	film text NOT NULL,\n"
                + "	antall integer,\n"
                + "	fornavn VARCHAR(255),\n"
                + "	etternavn VARCHAR(255),\n"
                + "	telefon VARCHAR(255),\n"
                + "	epost VARCHAR(255)\n"
                + ");";

        try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}