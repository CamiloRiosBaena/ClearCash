/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Camilo Rios
 */
public class formModificacion extends javax.swing.JFrame {

    private Clases.MovimientoFinanciero objetoMovimiento = new Clases.MovimientoFinanciero();
    private DefaultTableModel dtm = new DefaultTableModel();
    private List<Clases.MovimientoFinanciero> transacciones;
    private String NombreUsuario;
    private int Id = 0;
    
    public formModificacion(String usuario) {
        initComponents();
        
        NombreUsuario = usuario;
        txtUsuario.setText(usuario);
        String Img = "src/main/resources/Imagenes/" + usuario.toLowerCase() + ".png";
        File archivo = new File(Img);
        if (archivo.exists()) {
            ImageIcon icon = new ImageIcon(Img);
            Image img = icon.getImage();
            lblIcono.setIcon(new ImageIcon(img));
        }
        else{
            ImageIcon icon = new ImageIcon("src/main/resources/Imagenes/default.png");
            Image img = icon.getImage();
            lblIcono.setIcon(new ImageIcon(img));
        }
        //eliminar bordes a los botones
        btnMenuPrincipal.setFocusPainted(false);
        btnMenuPrincipal.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        jButton3.setFocusPainted(false);
        jButton3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        btnAñadir.setFocusPainted(false);
        btnAñadir.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        jButton1.setFocusPainted(false);
        jButton1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        btnActualizar.setFocusPainted(false);
        btnActualizar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        btnAyuda.setFocusPainted(false);
        btnAyuda.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        //agrega textos alternativos
        btnMenuPrincipal.setToolTipText("Inicio");
        jButton3.setToolTipText("Modificar y eliminar gastos");
        btnAñadir.setToolTipText("Agregar gastos");
        
        LocalDate fechaActual = LocalDate.now();
        int mes = fechaActual.getMonthValue();
        
        objetoMovimiento.setNombreUsuario(usuario);
        
        transacciones = objetoMovimiento.cargarInfoDesdeArchivoMes(mes);
        
        String[] cabecera={"ID","Fecha","Razon","Monto","Tipo"};
        dtm.setColumnIdentifiers(cabecera);
        tblTransacciones.setModel(dtm);
        
        actualizarTabla();
    }
    
    private void  actualizarTabla(){

      dtm.setRowCount(0);

         for (Clases.MovimientoFinanciero aux : transacciones) {
            Object[] fila={
                  aux.getID(),
                  aux.getFecha(),
                  aux.getRazon(),
                  aux.getMonto(),
                  aux.getTipo(),
              };
            
            dtm.addRow(fila);
        }
    }
    
    private void ActualizarMovimiento(){
        try{
            Clases.MovimientoFinanciero MovimientoModificar = null;
            
            for (Clases.MovimientoFinanciero aux : transacciones) {
                if(aux.getID() == Id){
                   MovimientoModificar = aux;
                   break;
                }
            }
          
            String tipo = (String) cbxTipo.getSelectedItem();
            String razon = txtRazon.getText();
            double monto = Double.parseDouble(txtMonto.getText());
            
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            String fecha = formato.format(cldFecha.getDate());

            MovimientoModificar.setTipo(tipo);
            MovimientoModificar.setRazon(razon);
            MovimientoModificar.setMonto(monto);
            MovimientoModificar.setFecha(fecha);
            
            Clases.CManejoArchivos objetoArchivos = new Clases.CManejoArchivos();
            objetoArchivos.setMovimiento(objetoMovimiento);
            objetoArchivos.guardarMovimientosEnArchivo(transacciones);
            
            actualizarTabla();
            JOptionPane.showMessageDialog(null, "Movimiento modificado correctamente");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al ingresar los datos.\nIntentar nuevamente");
        }
    }
    
    private void EliminarMovimiento(){
        Clases.MovimientoFinanciero EliminarMovimiento = null;
        Clases.CManejoArchivos objetoArchivos = new Clases.CManejoArchivos();
            
            for (Clases.MovimientoFinanciero aux : transacciones) {
                if(aux.getID() == Id){
                   EliminarMovimiento = aux;
                   break;
                }
            }
            
            if(EliminarMovimiento != null){
                objetoArchivos.setMovimiento(EliminarMovimiento);
                transacciones.remove(EliminarMovimiento);
                
                for(int i = 0; i < transacciones.size(); i++){
                    transacciones.get(i).setID(i+1);
                }
                
                objetoArchivos.guardarMovimientosEnArchivo(transacciones);

                actualizarTabla();

                JOptionPane.showMessageDialog(null, "Movimiento eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this,"Movimiento no encontrado","Error",JOptionPane.ERROR_MESSAGE);
            }
            
            
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblTipo = new javax.swing.JLabel();
        lblRazon = new javax.swing.JLabel();
        lblMonto = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        cbxTipo = new javax.swing.JComboBox<>();
        txtRazon = new javax.swing.JTextField();
        txtMonto = new javax.swing.JTextField();
        cldFecha = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransacciones = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnMenuPrincipal = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnAñadir = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        lblIcono = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAyuda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jPanel3.setBackground(new java.awt.Color(111, 128, 196));

        jPanel4.setBackground(new java.awt.Color(141, 158, 229));

        lblTipo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTipo.setForeground(new java.awt.Color(255, 255, 255));
        lblTipo.setText("Tipo");

        lblRazon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblRazon.setForeground(new java.awt.Color(255, 255, 255));
        lblRazon.setText("Razon");

        lblMonto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblMonto.setForeground(new java.awt.Color(255, 255, 255));
        lblMonto.setText("Monto");

        lblFecha.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("Fecha");

        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ingreso", "egreso" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipo))
                .addGap(51, 51, 51)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRazon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(lblMonto)
                        .addGap(56, 56, 56))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha)
                    .addComponent(cldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo)
                    .addComponent(lblRazon)
                    .addComponent(lblMonto)
                    .addComponent(lblFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        tblTransacciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTransacciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransaccionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTransacciones);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton1.setBackground(new java.awt.Color(255, 102, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/trash.png"))); // NOI18N
        jButton1.setText("ELIMINAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(0, 255, 255));
        btnActualizar.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/refresh.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jPanel2.setBackground(new java.awt.Color(141, 158, 229));

        btnMenuPrincipal.setBackground(new java.awt.Color(204, 204, 255));
        btnMenuPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/home.png"))); // NOI18N
        btnMenuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuPrincipalActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(204, 204, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/change.png"))); // NOI18N

        btnAñadir.setBackground(new java.awt.Color(204, 204, 255));
        btnAñadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/add.png"))); // NOI18N
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });

        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/off.png"))); // NOI18N
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setContentAreaFilled(false);
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        lblIcono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtUsuario.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIcono, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAñadir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMenuPrincipal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)))
                .addContainerGap(15, Short.MAX_VALUE))
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcono, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtUsuario)
                .addGap(28, 28, 28)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnMenuPrincipal)
                .addGap(51, 51, 51)
                .addComponent(btnAñadir)
                .addGap(45, 45, 45)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnCerrarSesion)
                .addGap(51, 51, 51))
        );

        jPanel5.setBackground(new java.awt.Color(111, 128, 196));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Modificacion ");

        btnAyuda.setBackground(new java.awt.Color(141, 158, 229));
        btnAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/question mark.png"))); // NOI18N
        btnAyuda.setBorderPainted(false);
        btnAyuda.setContentAreaFilled(false);
        btnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(108, 108, 108)
                .addComponent(btnAyuda)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(btnAyuda))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudaActionPerformed
        JOptionPane.showMessageDialog(this, "En esta pestaña podras realizar lo siguiente:\n"
            + "1. Selecciona la fila que desees modificar\n"
            + "2. Una vez los datos cargados cambialos a conveniencia\n"
            + "3. Presiona el boton para guardar los cambios",
            "Ayuda", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnAyudaActionPerformed

    private void btnMenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuPrincipalActionPerformed
        formMenuPrincipal objetoMenu = new formMenuPrincipal(NombreUsuario);
        objetoMenu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuPrincipalActionPerformed

    private void tblTransaccionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaccionesMouseClicked
        int filaSeleccionada = tblTransacciones.getSelectedRow();

        if (filaSeleccionada != -1) {
            String fechaStr = tblTransacciones.getValueAt(filaSeleccionada, 1).toString();
            String razon = tblTransacciones.getValueAt(filaSeleccionada, 2).toString();
            String monto = tblTransacciones.getValueAt(filaSeleccionada, 3).toString();
            String tipo = tblTransacciones.getValueAt(filaSeleccionada, 4).toString(); 
            
            Id = Integer.parseInt(tblTransacciones.getValueAt(filaSeleccionada, 0).toString());
            txtRazon.setText(razon);
            txtMonto.setText(monto);
            
            if(cbxTipo.getItemAt(1).equals(tipo)){
                cbxTipo.setSelectedIndex(1);
            } else {
                cbxTipo.setSelectedIndex(0);
            }
            
            try {
                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                Date fecha = formato.parse(fechaStr);
                cldFecha.setDate(fecha);
            } catch (ParseException e) {
                System.out.println("Error al convertir la fecha: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_tblTransaccionesMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        ActualizarMovimiento();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EliminarMovimiento();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        formAgregar objetoAgregar = new formAgregar(NombreUsuario);
        objetoAgregar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        JOptionPane.showMessageDialog(this, "Cerrando Sesion...", "Saliendo", JOptionPane.INFORMATION_MESSAGE);
            
        FormLogin objetoLogin = new FormLogin();
        objetoLogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

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
            java.util.logging.Logger.getLogger(formModificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formModificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formModificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formModificacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new formModificacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAyuda;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JComboBox<String> cbxTipo;
    private com.toedter.calendar.JDateChooser cldFecha;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblIcono;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblRazon;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JTable tblTransacciones;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtRazon;
    private javax.swing.JLabel txtUsuario;
    // End of variables declaration//GEN-END:variables
}
