package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public final class SQLHandler {

    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\isaac\\IdeaProjects\\FamilyMapServer\\src\\DB\\FMS.db";
    private static Connection conn;
    private static Statement stmt;

    private SQLHandler() {
        conn = null;
        stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void connect() {
        try {
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<HashMap<String,Object>> execute(String command) throws SQLException {
        ArrayList<HashMap<String,Object>> data = null;

        ResultSet rs = stmt.executeQuery(command);

        data = new ArrayList<>();

        while (rs.next() == true) {
            HashMap<String,Object> entry = new HashMap<>();

            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                entry.put(rs.getMetaData().getColumnLabel(i), rs.getString(i));

                /*
                System.out.printf(rs.getMetaData().getColumnLabel(i));
                System.out.printf(": ");
                System.out.printf(rs.getString(i));
                System.out.printf(", ");
                */
            }
            // System.out.println();

            data.add(entry);

        }

        rs.close();

        return data;
    }

    public static void execute_no_rtn(String command) throws SQLException {
        stmt.addBatch(command);
    }

    public static void execute_batch() {
        try {
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getNumRows(String table) {
        int val = -1;

        try {
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + table + ";");

            if (rs.next() == true)
                    val = rs.getInt(1);

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return val;
    }

}
