/**
 * MainApp.java
 *
 * Created on Jan 25, 2021, 2:44:28 PM
 */
package shoppinglist.app;

import shoppinglist.view.FormDaftarBelanja;

/**
 *
 * @author irfin
 */
public class MainApp 
{
    public static void main(String[] args)
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
        catch (InstantiationException ex) {
            ex.printStackTrace(System.out);
        }
        catch (IllegalAccessException ex) {
            ex.printStackTrace(System.out);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex) {
            ex.printStackTrace(System.out);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new FormDaftarBelanja().setVisible(true);
            }
        });
    }
}
