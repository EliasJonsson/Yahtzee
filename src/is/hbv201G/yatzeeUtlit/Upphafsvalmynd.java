/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hbv201G.yatzeeUtlit;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * 
 * UtlitYatzee sýnir notendaviðmót fyrir yatzee leikinn.
 * @author Elías Jónsson, Háskóli Íslands, elj31@hi.is.
 * @version 4
 * 
 * Keyrsla: ég bjó til .jar skrá sem er í dist með 
 * Clean og build og síðan exportaði úr Netbeans.
 * Notast við OS X Yosemite stýrikerfi og 
 * Java útgáfu 1.7.0_51
 * 
 * Dagsetning: 17/04/2015
 * 
 * Forritið: Sígildi leikurinn, Yatzee.
 * 
 */

/**
 * 
 * Hlutverk Upphafsvalmynd er að sjá um notendaviðmót
 * hluta leiksins þegar notandi opnar leikinn, það er upphafsvalmyndina.
 * Inniheldur JFrame.
 * 
 */
public class Upphafsvalmynd extends javax.swing.JFrame {

    /**
     * Creates new form Upphafsvalmynd
     */
    Fjolspilunarvalmynd vFjolspilunarValmynd;
    public Upphafsvalmynd() {
        initComponents();
        initMidjustilla();
    }

    /**
     * Miðjustillir jFrame-inn á skjá notanda.
     */
    private void initMidjustilla()
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getWidth()/2,
                dim.height/2-this.getHeight()/2);        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jNewGame = new javax.swing.JButton();
        jTop10 = new javax.swing.JButton();
        jQuit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Yatzee");

        jNewGame.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jNewGame.setText("Nýr leikur");
        jNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNewGameActionPerformed(evt);
            }
        });

        jTop10.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jTop10.setText("Topp 10 listi");
        jTop10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTop10ActionPerformed(evt);
            }
        });

        jQuit.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jQuit.setText("Hætta");
        jQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jQuitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jQuit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTop10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jNewGame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
                .addGap(58, 58, 58))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(114, Short.MAX_VALUE)
                .addComponent(jNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTop10, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Kallar á Fjolspilunarvalmynd jFrame-inn ef smellt er á "Nýr leikur" hnappinn
     * sem leyfir notanda að hefja nýjan leik.
     * @param evt er atburðurinn sem var búinn til.
     */
    private void jNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNewGameActionPerformed
        this.setVisible(false);
        vFjolspilunarValmynd = new Fjolspilunarvalmynd();
        vFjolspilunarValmynd.setVisible(true);
    }//GEN-LAST:event_jNewGameActionPerformed
    /**
     * Kallar á Topp10Listi jFrame-inn sem birtir topp 10 listann fyrir
     * notendanum.
     * @param evt er atburðurinn sem var búinn til.
     */
    private void jTop10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTop10ActionPerformed
        Topp10Listi topp10Vidmot = new Topp10Listi(false, 0);
        topp10Vidmot.setVisible(true);
        topp10Vidmot.setAlwaysOnTop (true);
        this.setVisible(false);
    }//GEN-LAST:event_jTop10ActionPerformed
    /**
     * Slökkvir á forritinu
     * @param evt er atburðurinn sem var búinn til.
     */
    private void jQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jQuitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jQuitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Upphafsvalmynd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Upphafsvalmynd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Upphafsvalmynd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Upphafsvalmynd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Upphafsvalmynd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jNewGame;
    private javax.swing.JButton jQuit;
    private javax.swing.JButton jTop10;
    // End of variables declaration//GEN-END:variables
}