package smt2_uas_andikarizkipradana;

import Controller.*;
import Model.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SMT2_UAS_AndikaRizkiPradana implements ActionListener, MouseListener {
    private V_Karyawan frmK ;
    private V_Sertifikasi frmS;
    private V_Jabatan frmJ;
    private V_loginPage frmL;
    private C_Login ctrlL;
    private C_Karyawan ctrlK;
    private C_Sertifikasi ctrlS;
    private C_Jabatan ctrlJ;
    
    public static void main(String[] args) {
        SMT2_UAS_AndikaRizkiPradana tes = new SMT2_UAS_AndikaRizkiPradana();
        tes.loadLogin();
    }
    
    private void loadLogin(){
        frmL = new V_loginPage();
        ctrlL = new C_Login(frmL);
        frmL.loginButton.addActionListener(this);
        frmL.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == frmL.loginButton) {
            if (ctrlL.LoginUser()) {
                frmL.setVisible(false);
                frmK = new V_Karyawan();
                Karyawan data = new Karyawan();
                ctrlK = new C_Karyawan(data, frmK);
                ctrlK.KosongFormKaryawan();
                ctrlK.TampilDataKaryawan();
                frmK.setVisible(true);
            }
        } else if(ae.getSource() == frmK.karyawanButton) {
            Karyawan data = new Karyawan();
            frmK = new V_Karyawan();
            ctrlK = new C_Karyawan(data, frmK);

            ctrlK.KosongFormKaryawan();
            ctrlK.TampilDataKaryawan();
            frmS.setVisible(false);
            frmJ.setVisible(false);
            frmK.setVisible(true);
        } else if (ae.getSource() == frmK.sertifikasiButton) {
            Sertifikasi data = new Sertifikasi();
            frmS = new V_Sertifikasi();
            ctrlS = new C_Sertifikasi(data, frmS);

//            ctrl.KosongFormKaryawan();
//            ctrl.TampilDataKaryawan();
            frmS.setVisible(true);
            frmJ.setVisible(false);
            frmK.setVisible(false);
        } else if (ae.getSource() == frmK.jabatanButton) {
            Jabatan data = new Jabatan();
            frmJ = new V_Jabatan();
            ctrlJ = new C_Jabatan(data, frmJ);

//            ctrl.KosongFormKaryawan();
//            ctrl.TampilDataKaryawan();
            frmS.setVisible(false);
            frmJ.setVisible(true);
            frmK.setVisible(false);
        } else if(ae.getSource() == frmS.karyawanButton) {
            Karyawan data = new Karyawan();
            frmK = new V_Karyawan();
            ctrlK = new C_Karyawan(data, frmK);

            ctrlK.KosongFormKaryawan();
            ctrlK.TampilDataKaryawan();
            frmS.setVisible(false);
            frmJ.setVisible(false);
            frmK.setVisible(true);
        } else if (ae.getSource() == frmS.sertifikasiButton) {
            Sertifikasi data = new Sertifikasi();
            frmS = new V_Sertifikasi();
            ctrlS = new C_Sertifikasi(data, frmS);

//            ctrl.KosongFormKaryawan();
//            ctrl.TampilDataKaryawan();
            frmS.setVisible(true);
            frmJ.setVisible(false);
            frmK.setVisible(false);
        } else if (ae.getSource() == frmS.jabatanButton) {
            Jabatan data = new Jabatan();
            frmJ = new V_Jabatan();
            ctrlJ = new C_Jabatan(data, frmJ);

//            ctrl.KosongFormKaryawan();
//            ctrl.TampilDataKaryawan();
            frmS.setVisible(false);
            frmJ.setVisible(true);
            frmK.setVisible(false);
        } else if(ae.getSource() == frmJ.karyawanButton) {
            Karyawan data = new Karyawan();
            frmK = new V_Karyawan();
            ctrlK = new C_Karyawan(data, frmK);

            ctrlK.KosongFormKaryawan();
            ctrlK.TampilDataKaryawan();
            frmS.setVisible(false);
            frmJ.setVisible(false);
            frmK.setVisible(true);
        } else if (ae.getSource() == frmJ.sertifikasiButton) {
            Sertifikasi data = new Sertifikasi();
            frmS = new V_Sertifikasi();
            ctrlS = new C_Sertifikasi(data, frmS);

//            ctrl.KosongFormKaryawan();
//            ctrl.TampilDataKaryawan();
            frmS.setVisible(true);
            frmJ.setVisible(false);
            frmK.setVisible(false);
        } else if (ae.getSource() == frmJ.jabatanButton) {
            Jabatan data = new Jabatan();
            frmJ = new V_Jabatan();
            ctrlJ = new C_Jabatan(data, frmJ);

//            ctrl.KosongFormKaryawan();
//            ctrl.TampilDataKaryawan();
            frmS.setVisible(false);
            frmJ.setVisible(true);
            frmK.setVisible(false);
        } 
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
