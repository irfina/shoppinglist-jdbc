/**
 * DaftarBelanjaDao.java
 *
 * Created on Feb 1, 2021, 2:10:49 PM
 */
package shoppinglist.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import shoppinglist.entity.DaftarBelanja;
import shoppinglist.entity.DaftarBelanjaDetil;

/**
 *
 * @author irfin
 */
public class DaftarBelanjaJdbcDao 
{
    private java.sql.Connection con;

    public DaftarBelanjaJdbcDao(java.sql.Connection c)
    {
        con = c;
    }
    
    public boolean simpan(DaftarBelanja db)
    {
        /**
         * Algoritma:
         * 1. Insert ke tabel daftarbelanja.
         * 2. Simpan PK hasil #1 ke variabel.
         * 3. Insert setiap barang belanjaan ke tabel daftarbelanjadetil dengan
         *    memberikan foreign key yg diperoleh di #2.
         * 4. Selesai.
         */
        
        try {
            // #1
            String sql = "INSERT INTO daftarbelanja(tanggal, judul) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
        
            Timestamp tstamp = Timestamp.valueOf(db.getTanggal());
            ps.setTimestamp(1, tstamp);
            ps.setString(2, db.getJudul());
            ps.executeUpdate();
            
            // #2
            sql = "SELECT currval('daftarbelanja_id_seq')";
            Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            int primaryKeyAutoInc = rs.getInt(1);
            
            // #3
            sql = "INSERT INTO daftarbelanjadetil (daftarbelanja_id, nourut, " +
                  "     namabarang, jml, satuan, memo) " +
                  " VALUES(? , ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            List<DaftarBelanjaDetil> listBarang = db.getDaftarBarang();
            for (DaftarBelanjaDetil barang : listBarang) {
                ps.setInt(1, primaryKeyAutoInc);
                ps.setInt(2, barang.getNoUrut());
                ps.setString(3, barang.getNamaBarang());
                ps.setFloat(4, barang.getByk());
                ps.setString(5, barang.getSatuan());
                ps.setString(6, barang.getMemo());
                
                ps.executeUpdate();
            }
            
            con.commit();
            return true;
        }
        catch (SQLException ex) {
            try {
                con.rollback();
            }
            catch (SQLException e) { }
            System.out.println("Error saat insert data: " + ex.getMessage());
            ex.printStackTrace(System.out);
            return false;
        }
    }
    
    public List<DaftarBelanja> bacaSemua()
    {
        List<DaftarBelanja> retval = new LinkedList<>();
        
        try {
            String queryTblInduk = "SELECT id, tanggal, judul FROM daftarbelanja ORDER BY tanggal";
            
            Statement stmtTblInduk = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            Statement stmtTblAnak = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            
            ResultSet rs = stmtTblInduk.executeQuery(queryTblInduk);
            DaftarBelanja belanjaan;
            while (rs.next()) {
                belanjaan = new DaftarBelanja();
                belanjaan.setId(rs.getInt(1));
                belanjaan.setTanggal(rs.getTimestamp(2).toLocalDateTime());
                belanjaan.setJudul(rs.getString(3));
                
                String queryTableAnak = 
                    "SELECT nourut, namabarang, jml, satuan, memo " +
                    "FROM daftarbelanjadetil WHERE daftarbelanja_id = " + belanjaan.getId() + " " +
                    "ORDER BY nourut";
                ResultSet rsTblAnak = stmtTblAnak.executeQuery(queryTableAnak);
                DaftarBelanjaDetil detil;
                while (rsTblAnak.next()) {
                    detil = new DaftarBelanjaDetil();
                    detil.setNoUrut(rsTblAnak.getInt(1));
                    detil.setNamaBarang(rsTblAnak.getString(2));
                    detil.setByk(rsTblAnak.getFloat(3));
                    detil.setSatuan(rsTblAnak.getString(4));
                    detil.setMemo(rsTblAnak.getString(5));
                    
                    belanjaan.addDaftarBarang(detil);
                }
                
                retval.add(belanjaan);
            }
            return retval;
        }
        catch (SQLException ex) {
            System.out.println("Error saat insert data: " + ex.getMessage());
            ex.printStackTrace(System.out);
            return null;
        }
    }
}
