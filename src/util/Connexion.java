package util;

import java.sql.*;

//import java.sql.Connection;
public class Connexion {
    public Connection getConnectionStatement(){
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","system");

            System.err.println(" Connection :Im in ");
            return connection;


        }catch (Exception e)
        {
            System.err.println(" Connection : Got an exception! ");
            System.err.println(e.getMessage());
            return null;
        }

    }
}
