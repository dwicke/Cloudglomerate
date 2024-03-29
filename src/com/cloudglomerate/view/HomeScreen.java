/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloudglomerate.view;

import com.cloudglomerate.connection.GoogleResponse;
import com.cloudglomerate.connection.Response;
import com.cloudglomerate.drive.AbstractFile;
import com.cloudglomerate.drive.CloudFolder;
import com.cloudglomerate.drive.LocalFile;
import com.cloudglomerate.interfaces.Cloud;
import com.cloudglomerate.interfaces.CloudManager;
import com.cloudglomerate.util.Browser;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.tree.DefaultTreeCellEditor;

/**
 *
 * @author drew
 */
public class HomeScreen extends javax.swing.JFrame {

    CloudFolder fold;

    /**
     * Creates new form HomeScreen
     */
    public HomeScreen() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        fileList = new javax.swing.JList();
        boxLogin = new javax.swing.JButton();
        Listbt = new javax.swing.JButton();
        downloadBt = new javax.swing.JButton();
        parentDirbt = new javax.swing.JButton();
        uploadBt = new javax.swing.JButton();
        gDriveBt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane2.setViewportView(fileList);

        boxLogin.setText("box login");
        boxLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxLoginActionPerformed(evt);
            }
        });

        Listbt.setText("list");
        Listbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListbtActionPerformed(evt);
            }
        });

        downloadBt.setText("download");
        downloadBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadBtActionPerformed(evt);
            }
        });

        parentDirbt.setText("parent directory");
        parentDirbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parentDirbtActionPerformed(evt);
            }
        });

        uploadBt.setText("upload");
        uploadBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadBtActionPerformed(evt);
            }
        });

        gDriveBt.setText("google login");
        gDriveBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gDriveBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(boxLogin)
                        .addGap(18, 18, 18)
                        .addComponent(gDriveBt))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(downloadBt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(uploadBt)
                        .addGap(18, 18, 18)
                        .addComponent(Listbt)
                        .addGap(18, 18, 18)
                        .addComponent(parentDirbt)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Listbt)
                            .addComponent(parentDirbt)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(boxLogin)
                            .addComponent(gDriveBt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(downloadBt)
                            .addComponent(uploadBt))))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boxLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxLoginActionPerformed
        // TODO add your handling code here:
        Response resp = CloudManager.getConnectionManager().requestConnection(Cloud.BOX);
        if (resp.getStatus() == Response.Status.INITIATED) {
            final JOptionPane optionPane = new JOptionPane("Please go to: " + resp.getConfirmURL(),
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.YES_NO_OPTION);





            // for copying style
            JLabel label = new JLabel();
            Font font = label.getFont();

            // create some css from the label's font
            StringBuffer style = new StringBuffer("font-family:" + font.getFamily() + ";");
            style.append("font-weight:" + (font.isBold() ? "bold" : "normal") + ";");
            style.append("font-size:" + font.getSize() + "pt;");

            // html content
            JEditorPane ep = new JEditorPane("text/html", "<html><body style=\"" + style + "\">" //
                    + "URL: <a href=\"" + resp.getConfirmURL() + "\">" + resp.getConfirmURL() + "</a>" //
                    + "</body></html>");

            // handle link events
            ep.addHyperlinkListener(new HyperlinkListener() {

                @Override
                public void hyperlinkUpdate(HyperlinkEvent e) {
                    if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
                        
                        Browser.browse(e.getURL().toString());
                        
                    }
                }
            });
            ep.setEditable(false);
            ep.setBackground(label.getBackground());




            final JDialog dialog = new JDialog(this,
                    "Login",
                    true);
            dialog.setContentPane(optionPane);
            dialog.add(ep);
            dialog.setDefaultCloseOperation(
                    JDialog.DO_NOTHING_ON_CLOSE);
            dialog.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent we) {
                }
            });
            optionPane.addPropertyChangeListener(
                    new PropertyChangeListener() {

                        public void propertyChange(PropertyChangeEvent e) {
                            String prop = e.getPropertyName();

                            if (dialog.isVisible()
                                    && (e.getSource() == optionPane)
                                    && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
                                //If you were going to check something
                                //before closing the window, you'd do
                                //it here.
                                dialog.setVisible(false);
                            }
                        }
                    });

            dialog.pack();
            dialog.setVisible(true);

            int value = ((Integer) optionPane.getValue()).intValue();
            if (value == JOptionPane.YES_OPTION) {
                CloudManager.getConnectionManager().connect(resp);
                fold = new CloudFolder();
                CloudManager.getDrive().list(fold);
                DefaultListModel fileModel = new DefaultListModel();
                for (AbstractFile file : fold.getContents()) {
                    fileModel.addElement(file);
                }
                fileList.setModel(fileModel);


            } else if (value == JOptionPane.NO_OPTION) {
            }

        }



    }//GEN-LAST:event_boxLoginActionPerformed

    private void ListbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListbtActionPerformed
        // TODO add your handling code here:

        AbstractFile folder = (AbstractFile) fileList.getSelectedValue();
        if (folder.isFolder()) {
            fold = (CloudFolder) folder;
            CloudManager.getDrive().list(fold);
            DefaultListModel fileModel = new DefaultListModel();
            for (AbstractFile file : fold.getContents()) {
                fileModel.addElement(file);
            }
            fileList.setModel(fileModel);

        }

    }//GEN-LAST:event_ListbtActionPerformed

    private void downloadBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadBtActionPerformed
        // TODO add your handling code here:
        List vals = fileList.getSelectedValuesList();
        final JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File loc = fc.getSelectedFile();
            System.out.println("The directory to dowonload to is " + loc.toPath());
            for (Object val : vals) {
                AbstractFile file = (AbstractFile) val;
                CloudManager.getDrive().download(file, loc);
            }
        }
    }//GEN-LAST:event_downloadBtActionPerformed

    private void parentDirbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parentDirbtActionPerformed
        // TODO add your handling code here:
        fold = CloudManager.getDrive().listParentDirectory(fold);
        DefaultListModel fileModel = new DefaultListModel();
        for (AbstractFile file : fold.getContents()) {
            fileModel.addElement(file);
        }
        fileList.setModel(fileModel);
    }//GEN-LAST:event_parentDirbtActionPerformed

    private void uploadBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadBtActionPerformed
        // TODO add your handling code here:
        final JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled (true);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File files[] = fc.getSelectedFiles();
            System.out.println("The number of files is " + files.length);
            
            for (int i = 0; i < files.length; i++) {
                System.out.println("File in list "  + "-" + files[i].getPath());
                CloudManager.getDrive().upload(new LocalFile(files[i]), fold);
                DefaultListModel fileModel = new DefaultListModel();
                for (AbstractFile file : fold.getContents()) {
                    fileModel.addElement(file);
                }
                fileList.setModel(fileModel);
            }

        }
    }//GEN-LAST:event_uploadBtActionPerformed

    private void gDriveBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gDriveBtActionPerformed
        // TODO add your handling code here:
        
        GoogleResponse resp = (GoogleResponse) CloudManager.getConnectionManager().requestConnection(Cloud.GOOGLE);
        if (resp.getStatus() == Response.Status.INITIATED) {
            final JOptionPane optionPane = new JOptionPane("Please go to: " + resp.getConfirmURL(),
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.YES_NO_OPTION);



            JTextField userInput = new JTextField("Insert here.               ");

            // for copying style
            JLabel label = new JLabel();
            Font font = label.getFont();

            // create some css from the label's font
            StringBuffer style = new StringBuffer("font-family:" + font.getFamily() + ";");
            style.append("font-weight:" + (font.isBold() ? "bold" : "normal") + ";");
            style.append("font-size:" + font.getSize() + "pt;");

            // html content
            JEditorPane ep = new JEditorPane("text/html", "<html><body style=\"" + style + "\">" //
                    + "URL: <a href=\"" + resp.getConfirmURL() + "\">" + resp.getConfirmURL() + "</a>" //
                    + "</body></html>");

            // handle link events
            ep.addHyperlinkListener(new HyperlinkListener() {

                @Override
                public void hyperlinkUpdate(HyperlinkEvent e) {
                    if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
                        Browser.browse(e.getURL().toString());
                    }
                }
            });
            ep.setEditable(false);
            ep.setBackground(label.getBackground());




            final JDialog dialog = new JDialog(this,
                    "Login",
                    true);
            
            dialog.setContentPane(optionPane);
            dialog.add(ep);
            dialog.add(userInput);
            dialog.setDefaultCloseOperation(
                    JDialog.DO_NOTHING_ON_CLOSE);
            dialog.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent we) {
                }
            });
            optionPane.addPropertyChangeListener(
                    new PropertyChangeListener() {

                        public void propertyChange(PropertyChangeEvent e) {
                            String prop = e.getPropertyName();

                            if (dialog.isVisible()
                                    && (e.getSource() == optionPane)
                                    && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
                                //If you were going to check something
                                //before closing the window, you'd do
                                //it here.
                                dialog.setVisible(false);
                            }
                        }
                    });

            dialog.pack();
            dialog.setVisible(true);

            int value = ((Integer) optionPane.getValue()).intValue();
            if (value == JOptionPane.YES_OPTION) {
                resp.setCode(userInput.getText());
                CloudManager.getConnectionManager().connect(resp);
                fold = new CloudFolder();
                CloudManager.getDrive().list(fold);
                DefaultListModel fileModel = new DefaultListModel();
                for (AbstractFile file : fold.getContents()) {
                    fileModel.addElement(file);
                }
                fileList.setModel(fileModel);


            } else if (value == JOptionPane.NO_OPTION) {
            }

        }


        
        
    }//GEN-LAST:event_gDriveBtActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new HomeScreen().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Listbt;
    private javax.swing.JButton boxLogin;
    private javax.swing.JButton downloadBt;
    private javax.swing.JList fileList;
    private javax.swing.JButton gDriveBt;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton parentDirbt;
    private javax.swing.JButton uploadBt;
    // End of variables declaration//GEN-END:variables
}
