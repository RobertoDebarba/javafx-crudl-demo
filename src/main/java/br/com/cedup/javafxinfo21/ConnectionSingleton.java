package br.com.cedup.javafxinfo21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Conexão com o banco de dados usada por toda a aplicação.
 * Utiliza o padrão de projeto Singleton, garantindo que só exista uma conexão 
 * aberta em toda a aplicação e ela sempre seja utilizada quando necessário.
 */
public class ConnectionSingleton {

    private static Connection connection;

    private ConnectionSingleton() {
        // Singleton class
    }

    /** 
     * Obtém a conexão ativa com o banco.
     * Caso não exista nenhuma conexão ativa ainda, cria uma nova.
     */
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
