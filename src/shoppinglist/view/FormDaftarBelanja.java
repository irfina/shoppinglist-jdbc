/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppinglist.view;

import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import shoppinglist.dao.DaftarBelanjaJdbcDao;
import shoppinglist.dao.DbUtil;
import shoppinglist.entity.DaftarBelanja;
import shoppinglist.entity.DaftarBelanjaDetil;

/**
 *
 * @author irfin
 */
public class FormDaftarBelanja extends javax.swing.JFrame
{
    private List<DaftarBelanja> daftarBelanja;
    
    private static final int COL_NO_URUT = 0;
    private static final int COL_NAMA_BARANG = 1;
    private static final int COL_BYK = 2;
    private static final int COL_SATUAN = 3;
    private static final int COL_MEMO = 4;

    /**
     * Creates new form FormDaftarBelanja
     */
    public FormDaftarBelanja()
    {
        initComponents();
        daftarBelanja = new LinkedList<>();
        listBelanja.setModel(new DefaultListModel<>());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        btnBaru = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listBelanja = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetilBelanja = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Program Daftar Belanja");

        btnBaru.setText("Baru...");
        btnBaru.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnBaruActionPerformed(evt);
            }
        });

        btnReload.setText("Muat Ulang");
        btnReload.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnReloadActionPerformed(evt);
            }
        });

        listBelanja.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                listBelanjaValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listBelanja);

        tblDetilBelanja.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "No.", "Nama Barang", "Banyak", "Satuan", "Memo"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblDetilBelanja);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBaru)
                        .addGap(18, 18, 18)
                        .addComponent(btnReload)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBaru)
                    .addComponent(btnReload))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBaruActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnBaruActionPerformed
    {//GEN-HEADEREND:event_btnBaruActionPerformed
        DlgInputDaftarBelanja dlg = new DlgInputDaftarBelanja(this, true);
        dlg.setVisible(true);
        
        // Cek apakah dialog di atas ditutup user karena sudah selesai input, 
        // atau karena batal input.
        if (dlg.getReturnStatus() == DlgInputDaftarBelanja.RET_OK) {
            // Ambil data yg telah diinput user
            DaftarBelanja belanja = dlg.getInputtedData();
            
            // Tambahkan objek belanja ke linked-list daftarBelanja
            daftarBelanja.add(belanja);
            
            // Tampilkan judul belanjaan ke listBelanja.
            refreshListBelanja();
        }
    }//GEN-LAST:event_btnBaruActionPerformed

    private void listBelanjaValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_listBelanjaValueChanged
    {//GEN-HEADEREND:event_listBelanjaValueChanged
        // Ambil objek ListModel dari JList
        DefaultListModel model = (DefaultListModel) listBelanja.getModel();
        int selectedIdx = listBelanja.getSelectedIndex();
        if (selectedIdx == -1)      // tidak ada yg ditampilkan, misalnya krn user clear
            return;
        
        DaftarBelanja belanjaan = daftarBelanja.get(selectedIdx);
        tampilkanDetilBarang(belanjaan);
    }//GEN-LAST:event_listBelanjaValueChanged

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnReloadActionPerformed
    {//GEN-HEADEREND:event_btnReloadActionPerformed
        // Baca dari database
        DaftarBelanjaJdbcDao dao = new DaftarBelanjaJdbcDao(DbUtil.getKoneksi());
        List<DaftarBelanja> daftarBelanjaQueryDariDB = dao.bacaSemua();
        
        daftarBelanja = daftarBelanjaQueryDariDB;
        refreshListBelanja();
    }//GEN-LAST:event_btnReloadActionPerformed

    private void refreshListBelanja()
    {
        DefaultListModel model = (DefaultListModel) listBelanja.getModel();
        model.clear();      // kosongkan JList
        
        // Baca ulang isi linked-list daftarBelanja dan tampilkan judulnya
        for (DaftarBelanja belanjaan : daftarBelanja) {
            model.addElement(belanjaan.getJudul());
        }
    }
    
    private void tampilkanDetilBarang(DaftarBelanja daftarBelanja)
    {
        DefaultTableModel tm = (DefaultTableModel) tblDetilBelanja.getModel();
        tm.setRowCount(0);  // kosongkan dahulu isi JTable.
        
        List<DaftarBelanjaDetil> listBarang = daftarBelanja.getDaftarBarang();
        int noUrut = 1;
        for (DaftarBelanjaDetil barang : listBarang) 
        {
            Object[] barisData = new Object[5];   // 5 adl byk kolom.
            barisData[COL_NO_URUT] = noUrut;
            barisData[COL_NAMA_BARANG] = barang.getNamaBarang();
            barisData[COL_BYK] = barang.getByk();
            barisData[COL_SATUAN] = barang.getSatuan();
            barisData[COL_MEMO] = barang.getMemo();
            noUrut++;
            
            tm.addRow(barisData);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBaru;
    private javax.swing.JButton btnReload;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listBelanja;
    private javax.swing.JTable tblDetilBelanja;
    // End of variables declaration//GEN-END:variables
}
