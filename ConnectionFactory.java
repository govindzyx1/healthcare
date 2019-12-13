
package connection;

import java.io.PrintStream;
import java.sql.*;

public class ConnectionFactory
{

    public ConnectionFactory()
    {
    }

    public static Connection createConnection()
    {
        try
        {
            Class.forName(constr);
            con = DriverManager.getConnection(url, userName, password);
        }
        catch(SQLException sqlexception)
        {
            System.out.println(sqlexception);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            System.out.println(classnotfoundexception);
        }
        return con;
    }

    private static Connection con = null;
    private static String constr = "oracle.jdbc.driver.OracleDriver";
    private static String userName = "Medicine";
    private static String password = "Medicine";
    private static String url = "Jdbc:Oracle:thin:@localhost:1521:XE";

}