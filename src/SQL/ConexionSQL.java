package SQL;


import resources.RecursosStatics;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class ConexionSQL
{

    Connection connection=null;

    public ConexionSQL() {
    }

    public ConexionSQL(Connection connection) {
        this.connection = connection;
    }

    public  Connection getConexion()
    {

        try {
            Class.forName(RecursosStatics.NAME).newInstance();
            connection = DriverManager.getConnection(RecursosStatics.PATH,RecursosStatics.USER,RecursosStatics.PASS);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        return connection;
    }



    public void desconectar()
    {
        connection=null;
    }

}
