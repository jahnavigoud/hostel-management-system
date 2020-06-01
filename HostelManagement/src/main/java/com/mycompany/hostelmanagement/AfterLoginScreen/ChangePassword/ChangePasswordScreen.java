/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hostelmanagement.AfterLoginScreen.ChangePassword;

import com.mycompany.hostelmanagement.DBConnection.DBConnection;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pranay
 */
public class ChangePasswordScreen extends javax.swing.JInternalFrame {

    /**
     * Creates new form ChangePasswordScre
     * en
     */
    private static final Logger logger = Logger.getLogger(ChangePasswordScreen.class.getName());
    String loginIdStatic=null;
     public Connection connection =null;
    public ChangePasswordScreen(String loginId) {
        initComponents();
        loginIdStatic=loginId;
        connection = DBConnection.getConnection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        oldPassword = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        newPassword = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        confirmPassword = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        cpButton = new javax.swing.JButton();

        jTextField2.setEditable(false);
        jTextField2.setText("New Password");

        jTextField4.setEditable(false);
        jTextField4.setText("Confirm Password");

        jTextField6.setEditable(false);
        jTextField6.setText("Old  Password");

        cpButton.setText("Change Password");
        cpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(confirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(newPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(oldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(cpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(cpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpButtonActionPerformed
        try {
            // TODO add your handling code here:
            String oldPasswordText = oldPassword.getText();
            String newPasswordText = newPassword.getText();
            String confirmPasswordText = confirmPassword.getText();
            
            if(oldPasswordText.equals(newPasswordText) ||
                    oldPasswordText.equals(confirmPasswordText)){
                logger.log(Level.SEVERE, "Old and new passwords cannot be same");
                cpButton.setForeground(Color.red);
                cpButton.setText("Same as Old Password");
                throw new Exception();
            }
            
            if(!newPasswordText.equals(confirmPasswordText)){
                logger.log(Level.SEVERE, "new and confirm passwords mismatch");
                cpButton.setText("Passwords mismatch");
                cpButton.setForeground(Color.red);
                throw new Exception();
            }
             PreparedStatement readFromStudentsTable=connection.prepareStatement("select id from students where (email=? or mobile=?)");
            readFromStudentsTable.setString(1,loginIdStatic);
            readFromStudentsTable.setString(2,loginIdStatic);
            ResultSet profileIdResultSet = readFromStudentsTable.executeQuery();
             profileIdResultSet.next();
            String profileId = profileIdResultSet.getString(1);
            logger.log(Level.INFO,"Profile Id read Successfuly from students table {}",profileId);
           
            
            PreparedStatement insertIntoPasswordTable=connection.prepareStatement("update password set password=? where profileId=?");
                    
            insertIntoPasswordTable.setString(1, confirmPasswordText);
            insertIntoPasswordTable.setString(2, profileId);
            insertIntoPasswordTable.executeUpdate();
            logger.log(Level.INFO,"Data inserted Successfuly from password table");
            cpButton.setForeground(Color.green);
            cpButton.setText("Changed Successfully");
            
        } catch (SQLException ex) {
            Logger.getLogger(ChangePasswordScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ChangePasswordScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        
    }//GEN-LAST:event_cpButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField confirmPassword;
    private javax.swing.JButton cpButton;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField newPassword;
    private javax.swing.JTextField oldPassword;
    // End of variables declaration//GEN-END:variables
}