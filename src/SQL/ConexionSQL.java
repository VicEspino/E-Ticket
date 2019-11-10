package SQL;


import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class ConexionSQL
{
    private final String  PATH="jdbc:mysql://localhost:3306/eticket";
    private final String  NAME="com.mysql.jdbc.Driver";
    private final String  USER="root";
    private final String  PASS="";
    Connection connection=null;

    public  Connection getConexion()
    {

        try {
            Class.forName(NAME).newInstance();
            connection = DriverManager.getConnection(PATH,USER,PASS);
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
