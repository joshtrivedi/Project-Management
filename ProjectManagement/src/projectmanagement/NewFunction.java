/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Josh Trivedi
 */
public class NewFunction {
    public static boolean flag = false;
    public void check(String enroll_1,String enroll_2){
        Connection con = MyConnection.getConnection();
        PreparedStatement p,l;
        try {
            p = con.prepareStatement("SELECT * from student WHERE enrollment = ?");
            l = con.prepareStatement("SELECT * from register WHERE enrollment = ?");
            p.setString(1, enroll_1);
            l.setString(1, enroll_2);
            ResultSet rs = p.executeQuery();
            ResultSet ls = l.executeQuery();
            if(rs.next() && ls.next()){
                Student_MainForm.jLabel_name.setText("Welcome "+rs.getString(2)+" "+rs.getString(3));
                Student_MainForm.jLabel_enroll.setText(rs.getString(4));
                Student_MainForm.jTextField_phone.setText(rs.getString(7));
                Student_MainForm.jDateChooser_bdate.setDate(rs.getDate(6));
                Student_MainForm.jTextField_address.setText(rs.getString(8));
                Student_MainForm.jTextArea_project.setText(rs.getString(9));
                Student_MainForm.jDateChooser_duedate.setDate(rs.getDate(10));
                flag=true;
            }
            else{
                flag=false;
                JOptionPane.showMessageDialog(null, "Student Not Enrolled");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addtoProject(String enroll,String project){
        Connection con = MyConnection.getConnection();
        PreparedStatement p1;
        try {
            p1 = con.prepareStatement("INSERT INTO project(enrollment,project) VALUES(?,?)");
            p1.setString(1,enroll);
            p1.setString(2,project);
            if(p1.executeUpdate() > 0 && flag == true){
                JOptionPane.showMessageDialog(null,"Project Added");
            }
            else{
                JOptionPane.showMessageDialog(null,"Project Not Assigned");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
