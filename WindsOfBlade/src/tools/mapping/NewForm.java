/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tools.mapping;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

/**
 *
 * @author Brandon
 */
public class NewForm extends javax.swing.JDialog {

    /**
     * Creates new form NewJDialog
     */
    public NewForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        mapNameTxtField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rowTxtField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        colTxtField = new javax.swing.JTextField();
        createBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        spriteTxtField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Map Name");

        jLabel2.setText("Grid Height (Rows)");

        jLabel3.setText("Grid Width (Columns)");

        createBtn.setText("Create");
        createBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        jLabel4.setText("Sprite Sheet");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spriteTxtField)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(colTxtField, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(rowTxtField)
                                    .addComponent(mapNameTxtField))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(createBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clearBtn)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mapNameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rowTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(colTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(spriteTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createBtn)
                    .addComponent(clearBtn))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>                        

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        mapNameTxtField.setText("");
        colTxtField.setText("");
        rowTxtField.setText("");
        spriteTxtField.setText("");
    }                                        

    private void createBtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        int row,col;
        if(mapNameTxtField.getText().length()<=0){
            javax.swing.JOptionPane.showMessageDialog(null, "Insert a map name!");
            return;
        }
        if(spriteTxtField.getText().length()<=0){
            javax.swing.JOptionPane.showMessageDialog(null, "Insert a sprite sheet!");
            return;
        }
        try{
            row = Integer.parseInt(rowTxtField.getText().trim());
            col = Integer.parseInt(colTxtField.getText().trim());
            MapMaker.mapRow = row;
            MapMaker.mapCol = col;
            MapMaker.file = new java.io.File(mapNameTxtField.getText()+".map");
            MapMaker.maxLayer = 0;
            MapMaker.listModel.addElement(new Map(row,col,MapMaker.maxLayer));
            MapMaker.layerList.setSelectedIndex(0);
            MapMaker.spritesheet = ImageIO.read(new File(spriteTxtField.getText()));
            MapMaker.spritesheetFileLocation = spriteTxtField.getText();
           // MapMaker.spriteSheetPanel.setSize(MapMaker.spritesheet.getWidth(), MapMaker.spritesheet.getHeight());
            MapMaker.spriteSheetPanel.setPreferredSize(new java.awt.Dimension(MapMaker.spritesheet.getWidth(), MapMaker.spritesheet.getHeight()));
            this.dispose();
        }catch (NumberFormatException e){
            javax.swing.JOptionPane.showMessageDialog(null, "Insert numeric values for dimensions!");
            rowTxtField.setText("");
            colTxtField.setText("");
        }catch (IOException e){
            javax.swing.JOptionPane.showMessageDialog(null, "Cannot find spritesheet");
        }
    }                                         

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
            java.util.logging.Logger.getLogger(NewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NewForm dialog = new NewForm(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton clearBtn;
    private javax.swing.JTextField colTxtField;
    private javax.swing.JButton createBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField mapNameTxtField;
    private javax.swing.JTextField rowTxtField;
    private javax.swing.JTextField spriteTxtField;
    // End of variables declaration                   
}