/**
 * DbUtil.java
 *
 * Created on Feb 1, 2021, 2:12:42 PM
 */
package shoppinglist.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author irfin
 */
public class DbUtil 
{
    private static java.sql.Connection jdbcConn;
    
    public static void inisialisasiKoneksi()
    {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Driver tidak ditemukan: " + ex.getMessage());
            return;
        }
        
        String jdbcUrl = "jdbc:postgresql://localhost:5432/demo_pbp";
        try {
            jdbcConn = DriverManager.getConnection(jdbcUrl, "userdemo", "demo");
            jdbcConn.setAutoCommit(false);
        }
        catch (SQLException ex) {
            System.out.println("Error JDBC koneksi ke database: " + ex.getMessage());
            return;
        }
    }
    
    public static java.sql.Connection getKoneksi()
    {
        if (jdbcConn == null)
            inisialisasiKoneksi();
        
        return jdbcConn;
    }
}
