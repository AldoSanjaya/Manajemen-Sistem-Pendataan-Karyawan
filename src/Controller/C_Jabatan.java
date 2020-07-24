package Controller;

import Model.Connector;
import Model.Jabatan;
import View.V_Jabatan;
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

public class C_Jabatan implements ActionListener, MouseListener {
    private Jabatan data;
    private V_Jabatan frm;
    
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    
    public C_Jabatan (Jabatan data, V_Jabatan frm) {
        Connector DB = new Connector();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        
        this.data = data;
        this.frm = frm;
        this.frm.tambahButton.addActionListener(this);
        this.frm.editButton.addActionListener(this);
        this.frm.hapusButton.addActionListener(this);
        this.frm.tableKaryawan.addMouseListener(this);
    }
    
    public void KosongFormKaryawan() {
        frm.idKaryawanText.setEditable(true);
        frm.namaKaryawanText.setText(null);
        frm.alamatKaryawanText.setText(null);
        frm.idJabatanText.setText(null);
    }
    
    public void TampilDataKaryawan() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID KARYAWAN");
        model.addColumn("NAMA KARYAWAN");
        model.addColumn("ALAMAT KARYAWAN");
        model.addColumn("TANGGAL BERGABUNG");
        model.addColumn("ID JABATAN");

        try{
            sql = "SELECT * FROM karyawan";
            rs = stat.executeQuery(sql);
            while(rs.next()){
                model.addRow(new Object[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                });
            }
            frm.tableKaryawan.setModel(model);
        }catch (SQLException e){
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == frm.tambahButton) {
            data.setId(frm.idKaryawanText.getText());
            data.setNama(frm.namaKaryawanText.getText());
            data.setAlamat(frm.alamatKaryawanText.getText());
            data.setIdJabatan(frm.idJabatanText.getText());
            
            try {
                if(data.simpanKaryawan(data)) {
                    JOptionPane.showMessageDialog(null, "Tambah data baru berhasil");
                    KosongFormKaryawan();
                    TampilDataKaryawan();
                }
            } catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else if(ae.getSource() == frm.editButton) {
            data.setId(frm.idKaryawanText.getText());
            data.setNama(frm.namaKaryawanText.getText());
            data.setAlamat(frm.alamatKaryawanText.getText());
            data.setIdJabatan(frm.idJabatanText.getText());
            
            try {
                if(data.updateKaryawan(data)) {
                    JOptionPane.showMessageDialog(null, "Update berhasil");
                    KosongFormKaryawan();
                    TampilDataKaryawan();
                }
            } catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else {
            data.setId(frm.idKaryawanText.getText());
            
            try {
                if(data.hapusKaryawan(data)) {
                    JOptionPane.showMessageDialog(null, "Hapus data berhasil");
                    KosongFormKaryawan();
                    TampilDataKaryawan();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == frm.tableKaryawan) {
            frm.idKaryawanText.setEditable(false);
            
            int baris = frm.tableKaryawan.rowAtPoint(e.getPoint());
            
            String id = frm.tableKaryawan.getValueAt(baris, 0).toString();
            frm.idKaryawanText.setText(id);
            
            String nama = frm.tableKaryawan.getValueAt(baris, 1).toString();
            frm.namaKaryawanText.setText(nama);
            
            String alamat = frm.tableKaryawan.getValueAt(baris, 2).toString();
            frm.alamatKaryawanText.setText(alamat);
            
            String jab = frm.tableKaryawan.getValueAt(baris, 4).toString();
            frm.idJabatanText.setText(jab);
        }
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
