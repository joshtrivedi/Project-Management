
package projectmanagement;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class MyConnection {
    public static Connection getConnection(){
        Connection con = null;
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/stdmgdb2","root","");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return con ;
    }

}
