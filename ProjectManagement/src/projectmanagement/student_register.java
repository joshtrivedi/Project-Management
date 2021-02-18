/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanagement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Josh Trivedi
 */
public class student_register {
    public void insert(String name,String enroll,String pass){
        java.sql.Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
                ps = con.prepareStatement("INSERT INTO register(name,enrollment,password) VALUES (?,?,?)");
                ps.setString(1, name);
                ps.setString(2, enroll);
                ps.setString(3, pass);
                
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "New Credential Added");
                    RegisterStudent s = new RegisterStudent();
                    s.dispose();
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
