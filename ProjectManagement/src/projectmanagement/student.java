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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Josh Trivedi
 */
public class student {
    public void inserUpdateDeleteStudent(char operation,Integer id, String fname, 
                                        String lname, String enrollment, String gender, String bdate, 
                                        String phone, String address){
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        //i for insert
        if(operation == 'i'){
            try {
                ps = con.prepareStatement("INSERT INTO student(first_name,last_name,enrollment,gender,birthdate,phone,address,Project,duedate) VALUES (?,?,?,?,?,?,?,?,?)");
                ps.setString(1, fname);
                ps.setString(2, lname);
                ps.setString(3, enrollment);
                ps.setString(4, gender);
                ps.setString(5, bdate);
                ps.setString(6, phone);
                ps.setString(7, address);
                ps.setString(8, "");
                ps.setString(9, bdate);
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "New Student Added");
                }
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void fillStudentJTable(JTable table, String valueToSearch){
        Connection con = MyConnection.getConnection();
        PreparedStatement p ;
        try {
            p = con.prepareStatement("SELECT * FROM student WHERE CONCAT(first_name,last_name,phone,address)LIKE ?");
            p.setString(1, "%"+valueToSearch+"%");
            ResultSet rs = p.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            Object[] row;
            
            while(rs.next()){
                row = new Object[8];
                
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                
                model.addRow(row);
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
