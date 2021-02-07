/**
 * DaftarBelanjaDetil.java
 *
 * Created on Jan 25, 2021, 2:07:43 PM
 */
package shoppinglist.entity;

/**
 *
 * @author irfin
 */
public class DaftarBelanjaDetil 
{
    private int noUrut;
    private String namaBarang;
    private float byk;
    private String satuan;
    private String memo;

    public int getNoUrut()
    {
        return noUrut;
    }

    public void setNoUrut(int _noUrut)
    {
        this.noUrut = _noUrut;
    }

    public String getNamaBarang()
    {
        return namaBarang;
    }

    public void setNamaBarang(String _namaBarang)
    {
        this.namaBarang = _namaBarang;
    }

    public float getByk()
    {
        return byk;
    }

    public void setByk(float _byk)
    {
        this.byk = _byk;
    }

    public String getSatuan()
    {
        return satuan;
    }

    public void setSatuan(String _satuan)
    {
        this.satuan = _satuan;
    }

    public String getMemo()
    {
        return memo;
    }

    public void setMemo(String _memo)
    {
        this.memo = _memo;
    }
}
