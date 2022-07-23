/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import Modelo.EnumTipoDocumento;

/**
 *
 * @author HarryFora
 */
public class PersonTable extends javax.swing.JPanel {

    private ComboBoxModel enumTipoDocumento;

    public PersonTable() {
        enumTipoDocumento = new DefaultComboBoxModel(EnumTipoDocumento.values());
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRegisterPerson = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        comboDocumentType = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        textFirtsName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textLastName = new javax.swing.JTextField();
        textDocument = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textMail = new javax.swing.JTextField();
        buttonCreate = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        buttonCancel = new javax.swing.JButton();
        comboSucursal = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        buttonUploadImg = new javax.swing.JButton();

        setBackground(new java.awt.Color(19, 60, 72));

        panelRegisterPerson.setBackground(new java.awt.Color(19, 60, 72));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Img.png"))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FORMULARIO DE REGISTRO PERSONA");

        comboDocumentType.setModel(enumTipoDocumento);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre");

        textFirtsName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFirtsNameActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellidos");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Correo");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tipo documento");

        buttonCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/confirmIcon.png"))); // NOI18N
        buttonCreate.setText("CREAR");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Documento");

        buttonCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/cancelIcon.png"))); // NOI18N
        buttonCancel.setText("CANCELAR");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        comboSucursal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Sucursal");

        buttonUploadImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/img2.png"))); // NOI18N

        javax.swing.GroupLayout panelRegisterPersonLayout = new javax.swing.GroupLayout(panelRegisterPerson);
        panelRegisterPerson.setLayout(panelRegisterPersonLayout);
        panelRegisterPersonLayout.setHorizontalGroup(
            panelRegisterPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegisterPersonLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelRegisterPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelRegisterPersonLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addGroup(panelRegisterPersonLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textFirtsName, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegisterPersonLayout.createSequentialGroup()
                        .addGroup(panelRegisterPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addGap(57, 57, 57)
                        .addGroup(panelRegisterPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboSucursal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelRegisterPersonLayout.createSequentialGroup()
                                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(textMail)
                            .addComponent(textDocument)
                            .addComponent(comboDocumentType, 0, 251, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(panelRegisterPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(buttonUploadImg, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
            .addGroup(panelRegisterPersonLayout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRegisterPersonLayout.setVerticalGroup(
            panelRegisterPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegisterPersonLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(panelRegisterPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(textFirtsName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(panelRegisterPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(textLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRegisterPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(textMail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRegisterPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboDocumentType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRegisterPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textDocument, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(panelRegisterPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(panelRegisterPersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegisterPersonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonUploadImg, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRegisterPerson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRegisterPerson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void textFirtsNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFirtsNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFirtsNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton buttonCancel;
    public javax.swing.JButton buttonCreate;
    public javax.swing.JButton buttonUploadImg;
    public javax.swing.JComboBox<Object> comboDocumentType;
    public javax.swing.JComboBox<String> comboSucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public javax.swing.JPanel panelRegisterPerson;
    public javax.swing.JTextField textDocument;
    public javax.swing.JTextField textFirtsName;
    public javax.swing.JTextField textLastName;
    public javax.swing.JTextField textMail;
    // End of variables declaration//GEN-END:variables
}
