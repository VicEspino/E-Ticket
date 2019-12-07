package SQLServer;


import resources.RecursosStatics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionSQLServer {

    Connection connection = null;

    public ConexionSQLServer() {
    }

    public ConexionSQLServer(Connection connection) {
        this.connection = connection;
    }

    public Connection getConexion() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://DESKTOP-2UPS0NF\\SQLEXPRESSVIC:1433;database=e_ticketMS",
                    "sa",
                    "Elefante2017"
            );
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        return connection;
    }


    public void desconectar() {
        connection = null;
    }

}
