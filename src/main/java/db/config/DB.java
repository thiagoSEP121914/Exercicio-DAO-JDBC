package db.config;

import javax.swing.*;
import java.net.Socket;
import java.net.URL;
import java.sql.*;

public class DB {

    private static final String ALERT = "NÃO FOI POSSIVEL CONECTAR COM O BANCO DE DADOS";
    private static Connection conn = null;

    public static Connection getConn () {

        String URL = "jdbc:postgresql://localhost:5432/produtodb";
        String USER = "postgres";
        String PASSWORD = "123";

        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);

            } catch (SQLException e) {
                throw new RuntimeException(ALERT + e.getMessage());
            }
        }
        System.out.println("BANCO DE DADOS CONECTADO COM SUCESSO!");
        return conn;
    }

    public static void closeConection () {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("NAO FOI POSSIVEL ENCERRAR A CONEXÃO" +e.getMessage());
            }
        }
    }

    public static void closeStatement (PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException("NAO FOI POSSIVEL FECHAR O STATEMENT " + e.getMessage());
            }
        }
    }

    public static void closeResultSet (ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException("NAO FOI POSSIVEL FECHAR O RESULT SET" + e.getMessage());
            }
        }
    }
}
