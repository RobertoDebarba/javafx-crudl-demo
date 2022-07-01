package br.com.cedup.javafxinfo21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

    private static Connection connection;

    private ConnectionSingleton() {
        // Singleton class
    }

    public static Connection getConnection() throws SQLException {

        if (connection == null) {
            connection = DriverManager.getConnection( //
                    "jdbc:mysql://localhost:3306/produtojavafx", //
                    "root", //
                    "root");
        }

        return connection;
    }

}
