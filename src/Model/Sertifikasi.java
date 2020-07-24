package Model;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Sertifikasi extends Connector {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    
    private String idKaryawan;
    private String namaKaryawan;
    private String alamatKaryawan;
    private String tanggalBergabung;
    private String idJabatan;
    
    public String getId() {
        return idKaryawan;
    }
    
    public void setId(String idKaryawan) {
        this.idKaryawan = idKaryawan;
    }
    
    public String getNama() {
        return namaKaryawan;
    }
    
    public void setNama(String namaKaryawan) {
        this.namaKaryawan = namaKaryawan;
    }
    
    public String getAlamat() {
        return alamatKaryawan;
    }
    
    public void setAlamat(String alamatKaryawan) {
        this.alamatKaryawan = alamatKaryawan;
    }
    
    public String getBergabung() {
        return tanggalBergabung;
    }
    
    public void setBergabung(String tanggalBergabung) {
        this.tanggalBergabung = tanggalBergabung;
    }
    
    public String getIdJabatan() {
        return idJabatan;
    }
    
    public void setIdJabatan(String idJabatan) {
        this.idJabatan = idJabatan;
    }
    
    public boolean simpanKaryawan(Sertifikasi data) throws SQLException {
        Connector DB = new Connector();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        PreparedStatement pstm = null;
        
        sql = "INSERT INTO karyawan (idKaryawan, namaKaryawan, alamatKaryawan, idJabatan) VALUES (?,?,?,?)";
        
        try{
            pstm = con.prepareStatement(sql);
            pstm.setString(1, data.getId());
            pstm.setString(2, data.getNama());
            pstm.setString(3, data.getAlamat());
            pstm.setString(4, data.getIdJabatan());
            pstm.execute();
            return true;
        }catch (SQLException e){
            System.out.println("Error " + e.getMessage());
            return false;
        }     
    }
    
    public boolean updateKaryawan(Sertifikasi data) throws SQLException {
        Connector DB = new Connector();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        PreparedStatement pstm = null;
        
        sql = "UPDATE karyawan SET namaKaryawan = ?, alamatKaryawan = ?, idJabatan = ? WHERE idKaryawan = ?";
        
        try{
            pstm = con.prepareStatement(sql);
            pstm.setString(1, data.getNama());
            pstm.setString(2, data.getAlamat());
            pstm.setString(3, data.getIdJabatan());
            pstm.setString(4, data.getId());
            pstm.execute();
            return true;
        }catch (SQLException e){
            System.out.println("Error " + e.getMessage());
            return false;
        }     
    }
    
    public boolean hapusKaryawan(Sertifikasi data) throws SQLException {
        Connector DB = new Connector();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        PreparedStatement pstm = null;
        
        sql = "DELETE FROM karyawan WHERE idKaryawan = ?";
        
        try{
            pstm = con.prepareStatement(sql);
            pstm.setString(1, data.getId());
            pstm.execute();
            return true;
        }catch (SQLException e){
            System.out.println("Error " + e.getMessage());
            return false;
        }     
    }
}
