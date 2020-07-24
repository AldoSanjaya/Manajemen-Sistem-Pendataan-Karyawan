package Controller;

import Model.Connector;
import Model.Karyawan;
import View.V_loginPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class C_Login implements ActionListener, MouseListener {
    private V_loginPage frm;
    
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    
    public C_Login (V_loginPage frm) {
        Connector DB = new Connector();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        
        this.frm = frm;
//        this.frm.loginButton.addActionListener(this);
    }
    
    public boolean LoginUser(){
        try {
            sql = "SELECT * FROM user WHERE username='"+frm.usernameField.getText()+"' AND password='"+frm.passwordField.getText()+"'";
            rs = stat.executeQuery(sql);
            if(rs.next()){
                if(frm.usernameField.getText().equals(rs.getString("username")) && frm.passwordField.getText().equals(rs.getString("password"))){
                    return true;
                } else {
                    return false;
                }
            }else{
                JOptionPane.showMessageDialog(null, "Username or Password is wrong");
                return false;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
//        if(ae.getSource() == frm.loginButton) {
//            if (LoginUser()) {
//                frm.setVisible(false);
//                V_Karyawan frmk = new V_Karyawan();
//                Karyawan data = new Karyawan();
//                C_Karyawan ctrl = new C_Karyawan(data, frmk);
//                ctrl.KosongFormKaryawan();
//                ctrl.TampilDataKaryawan();
//                frmk.setVisible(true);
//            }
//        } 
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
