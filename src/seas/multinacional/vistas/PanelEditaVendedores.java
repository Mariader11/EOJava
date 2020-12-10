/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seas.multinacional.vistas;

import seas.multinacional.modelo.Vendedor;
import seas.multinacional.controlador.ServiceVendedor;
import seas.multinacional.principal.MultinacionalFrame;

/**
 *
 * @author maria
 */
public class PanelEditaVendedores extends javax.swing.JPanel {

    private MultinacionalFrame frame;
    private ServiceVendedor acciones;
    /**
     * Creates new form PanelEditaVendedores
     */
    public PanelEditaVendedores(MultinacionalFrame frame) {
        this.frame = frame;
        acciones = new ServiceVendedor(this);
        initComponents(); 
        //Asociar el modelo de lista al JList
        lstVendedores.setModel(acciones.getVendedoresList());
    }
    
    public MultinacionalFrame getFrame() {
        return frame;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstVendedores = new javax.swing.JList<>();
        btnEditar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        txtApellido1 = new javax.swing.JTextField();
        txtApellido2 = new javax.swing.JTextField();
        txtClave = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        lblApellido1 = new javax.swing.JLabel();
        lblApellido2 = new javax.swing.JLabel();
        lblClave = new javax.swing.JLabel();

        lstVendedores.setSelectedIndex(0);
        lstVendedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstVendedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstVendedores);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        lblNombre.setText("Nombre:");

        lblApellido1.setText("Apellido1:");

        lblApellido2.setText("Apellido2:");

        lblClave.setText("Clave:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombre)
                                    .addComponent(lblApellido1)
                                    .addComponent(lblApellido2)
                                    .addComponent(lblClave))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                                    .addComponent(txtClave)
                                    .addComponent(txtApellido1)
                                    .addComponent(txtApellido2))))))
                .addGap(115, 115, 115))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellido1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellido2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClave))
                .addGap(18, 18, 18)
                .addComponent(btnEditar)
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Botón para editar al vendedor y restablecer los campos de textos
     * @param evt 
     */
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if(!lstVendedores.isSelectionEmpty()) {
            Vendedor vendedor = new Vendedor(acciones.getIdSelected(lstVendedores.getSelectedIndex()),txtNombre.getText(),txtApellido1.getText(),txtApellido2.getText(),txtClave.getText());
            acciones.updateVendedor(vendedor);
            txtNombre.setText(""); txtApellido1.setText(""); txtApellido2.setText(""); txtClave.setText("");
            lstVendedores.setModel(acciones.getVendedoresList());  
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    /**
     * Método que rellena los cuadros de texto al clickar el vendedor de la lista
     * @param evt 
     */
    private void lstVendedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstVendedoresMouseClicked
       Vendedor vendedor;
        vendedor = acciones.setVendedorText(lstVendedores.getSelectedIndex());
        txtNombre.setText(vendedor.getNombre());
        txtApellido1.setText(vendedor.getApellido1());
        txtApellido2.setText(vendedor.getApellido2());
        txtClave.setText(vendedor.getClave());
    }//GEN-LAST:event_lstVendedoresMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblApellido1;
    private javax.swing.JLabel lblApellido2;
    private javax.swing.JLabel lblClave;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JList<String> lstVendedores;
    private javax.swing.JTextField txtApellido1;
    private javax.swing.JTextField txtApellido2;
    private javax.swing.JTextField txtClave;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}