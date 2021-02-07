/**
 * DaftarBelanja.java
 *
 * Created on Jan 25, 2021, 2:04:41 PM
 */
package shoppinglist.entity;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author irfin
 */
public class DaftarBelanja 
{
    private long id;
    private LocalDateTime tanggal;
    private String judul;
    private List<DaftarBelanjaDetil> daftarBrg;

    public DaftarBelanja()
    {
        daftarBrg = new LinkedList<>();
    }

    public long getId()
    {
        return id;
    }

    public void setId(long _id)
    {
        this.id = _id;
    }

    public LocalDateTime getTanggal()
    {
        return tanggal;
    }

    public void setTanggal(LocalDateTime _tanggal)
    {
        this.tanggal = _tanggal;
    }

    public String getJudul()
    {
        return judul;
    }

    public void setJudul(String _judul)
    {
        this.judul = _judul;
    }

    public List<DaftarBelanjaDetil> getDaftarBarang()
    {
        return daftarBrg;
    }
    
    public void addDaftarBarang(DaftarBelanjaDetil _brg)
    {
        daftarBrg.add(_brg);
    }

    public void setDaftarBarang(List<DaftarBelanjaDetil> _daftarBrg)
    {
        this.daftarBrg = _daftarBrg;
    }
}
