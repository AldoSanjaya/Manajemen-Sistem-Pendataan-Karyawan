package Controller;

import Model.Connector;
import Model.*;
import View.*;
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

public class C_Karyawan implements ActionListener, MouseListener {
    private Karyawan data;
    private V_Karyawan frmK;
    private V_Sertifikasi frmS;
    private V_Jabatan frmJ;
    
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    
    public C_Karyawan (Karyawan data, V_Karyawan frmK) {
        Connector DB = new Connector();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        
        this.data = data;
        this.frmK = frmK;
        
        this.frmK.sertifikasiButton.addActionListener(this);
        this.frmK.jabatanButton.addActionListener(this);
        this.frmK.karyawanButton.addActionListener(this);
        
        this.frmK.tambahButton.addActionListener(this);
        this.frmK.editButton.addActionListener(this);
        this.frmK.hapusButton.addActionListener(this);
        this.frmK.tableKaryawan.addMouseListener(this);
    }
    
    public void KosongFormKaryawan() {
        frmK.idKaryawanText.setEditable(true);
        frmK.namaKaryawanText.setText(null);
        frmK.alamatKaryawanText.setText(null);
        frmK.idJabatanText.setText(null);
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
            frmK.tableKaryawan.setModel(model);
        }catch (SQLException e){
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == frmK.tambahButton) {
            data.setId(frmK.idKaryawanText.getText());
            data.setNama(frmK.namaKaryawanText.getText());
            data.setAlamat(frmK.alamatKaryawanText.getText());
            data.setIdJabatan(frmK.idJabatanText.getText());
            
            try {
                if(data.simpanKaryawan(data)) {
                    JOptionPane.showMessageDialog(null, "Tambah data baru berhasil");
                    KosongFormKaryawan();
                    TampilDataKaryawan();
                }
            } catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else if(ae.getSource() == frmK.editButton) {
            data.setId(frmK.idKaryawanText.getText());
            data.setNama(frmK.namaKaryawanText.getText());
            data.setAlamat(frmK.alamatKaryawanText.getText());
            data.setIdJabatan(frmK.idJabatanText.getText());
            
            try {
                if(data.updateKaryawan(data)) {
                    JOptionPane.showMessageDialog(null, "Update berhasil");
                    KosongFormKaryawan();
                    TampilDataKaryawan();
                }
            } catch(SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else if(ae.getSource() == frmK.hapusButton) {
            data.setId(frmK.idKaryawanText.getText());
            
            try {
                if(data.hapusKaryawan(data)) {
                    JOptionPane.showMessageDialog(null, "Hapus data berhasil");
                    KosongFormKaryawan();
                    TampilDataKaryawan();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else if(ae.getSource() == frmK.karyawanButton) {
            JOptionPane.showMessageDialog(null, "Berhasil karyawan");
            
            this.KosongFormKaryawan();
            this.TampilDataKaryawan();
            frmS.setVisible(false);
            frmJ.setVisible(false);
            frmK.setVisible(true);
        } else if (ae.getSource() == frmK.sertifikasiButton) {
            Sertifikasi data = new Sertifikasi();
            frmS = new V_Sertifikasi();
            C_Sertifikasi ctrl = new C_Sertifikasi(data, frmS);

//            ctrl.KosongFormKaryawan();
//            ctrl.TampilDataKaryawan();
            frmS.setVisible(true);
            frmJ.setVisible(false);
            frmK.setVisible(false);
        } else if (ae.getSource() == frmK.jabatanButton) {
            Jabatan data = new Jabatan();
            frmJ = new V_Jabatan();
            C_Jabatan ctrl = new C_Jabatan(data, frmJ);

//            ctrl.KosongFormKaryawan();
//            ctrl.TampilDataKaryawan();
            frmS.setVisible(false);
            frmJ.setVisible(true);
            frmK.setVisible(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == frmK.tableKaryawan) {
            frmK.idKaryawanText.setEditable(false);
            
            int baris = frmK.tableKaryawan.rowAtPoint(e.getPoint());
            
            String id = frmK.tableKaryawan.getValueAt(baris, 0).toString();
            frmK.idKaryawanText.setText(id);
            
            String nama = frmK.tableKaryawan.getValueAt(baris, 1).toString();
            frmK.namaKaryawanText.setText(nama);
            
            String alamat = frmK.tableKaryawan.getValueAt(baris, 2).toString();
            frmK.alamatKaryawanText.setText(alamat);
            
            String jab = frmK.tableKaryawan.getValueAt(baris, 4).toString();
            frmK.idJabatanText.setText(jab);
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
