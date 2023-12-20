package by.itclass.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ConnectionManager {
    public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/2301_final";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    private static Connection cn;

    public static void init() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (Objects.isNull(cn) || cn.isClosed()) {
            cn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return cn;
    }
}
