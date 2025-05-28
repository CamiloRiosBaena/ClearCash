/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Camilo Rios
 */
public class formMenuPrincipal extends javax.swing.JFrame {

    private Clases.GraficadorCircular objetoGraficaIngresos = new Clases.GraficadorCircular();
    private Clases.GraficadorCircular objetoGraficaEgresos = new Clases.GraficadorCircular();
    private Clases.GraficadorCircular objetoGraficaBalance = new Clases.GraficadorCircular();
    private Clases.MovimientoFinanciero objetoMovimiento = new Clases.MovimientoFinanciero();
    List<Clases.MovimientoFinanciero> transacciones;
    private DefaultTableModel dtm = new DefaultTableModel();
    private String NombreUsuario;
    
    public formMenuPrincipal(String usuario) {
        initComponents();
        
        NombreUsuario = usuario;
        //agrega icono
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
        btnMenuPrincipal1.setFocusPainted(false);
        btnMenuPrincipal1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        jButton4.setFocusPainted(false);
        jButton4.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        btnAñadir1.setFocusPainted(false);
        btnAñadir1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        btnCerrarSesion1.setFocusPainted(false);
        btnCerrarSesion1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        btnAyuda.setFocusPainted(false);
        btnAyuda.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        //agrega textos alternativos
        btnMenuPrincipal1.setToolTipText("Inicio");
        jButton4.setToolTipText("Modificar y eliminar gastos");
        btnAñadir1.setToolTipText("Agregar gastos");
        
        LocalDate fechaActual = LocalDate.now();
        int mes = fechaActual.getMonthValue();
        objetoMovimiento.setNombreUsuario(usuario);
        
        String[] cabecera={"ID","Fecha","Razon","Monto","Tipo"};
        dtm.setColumnIdentifiers(cabecera);
        tblTransacciones.setModel(dtm);
        
        transacciones = objetoMovimiento.cargarInfoDesdeArchivoMes(mes);
        
        List<Clases.MovimientoFinanciero> ingresos = transacciones.stream()
                .filter(t -> t.getTipo().equals("ingreso"))
                .collect(Collectors.toList());
        
        double Tingreso = ingresos.stream()
        .mapToDouble(Clases.MovimientoFinanciero::getMonto)
        .sum();
        
        txtingreso.setText(String.format("%.2f", Tingreso));
        
        JPanel chartPanelIngresos = (JPanel) objetoGraficaIngresos.generarGrafico(ingresos, "Ingresos");
        chartPanelIngresos.setPreferredSize(new java.awt.Dimension(176, 190)); 


        pnlIngresos.removeAll();
        pnlIngresos.setLayout(new java.awt.BorderLayout());
        pnlIngresos.add(chartPanelIngresos, java.awt.BorderLayout.CENTER);
        pnlIngresos.validate();
        
        
        List<Clases.MovimientoFinanciero> egresos = transacciones.stream()
                .filter(t -> t.getTipo().equals("egreso"))
                .collect(Collectors.toList());
        
        double Tegreso = egresos.stream()  
        .mapToDouble(Clases.MovimientoFinanciero::getMonto)
        .sum();
        
        txtegresos.setText(String.format("%.2f", Tegreso));
        
        JPanel chartPanelEgresos = (JPanel) objetoGraficaEgresos.generarGrafico(egresos, "Egresos");
        chartPanelEgresos.setPreferredSize(new java.awt.Dimension(176, 190)); 

        pnlEgresos.removeAll();
        pnlEgresos.setLayout(new java.awt.BorderLayout());
        pnlEgresos.add(chartPanelEgresos, java.awt.BorderLayout.CENTER);
        pnlEgresos.validate();
        
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
        
        double Tbalance = Tingreso - Tegreso;
        txtbalance.setText(String.format("%.2f", Tbalance));
        
        // Grafica Balance
        JPanel chartPanelBalance = (JPanel) objetoGraficaBalance.generarGraficoBalance(Tingreso, Tegreso);
        chartPanelBalance.setPreferredSize(new java.awt.Dimension(176, 190));
        
        pnlBalance.removeAll();
        pnlBalance.setLayout(new java.awt.BorderLayout());
        pnlBalance.add(chartPanelBalance, java.awt.BorderLayout.CENTER);
        pnlBalance.validate();
        
        
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
        pnlIngresos = new javax.swing.JPanel();
        pnlEgresos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransacciones = new javax.swing.JTable();
        pnlBalance = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtingreso = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtbalance = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        txtegresos = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnMenuPrincipal1 = new javax.swing.JButton();
        btnAñadir1 = new javax.swing.JButton();
        btnCerrarSesion1 = new javax.swing.JButton();
        lblIcono = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAyuda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jPanel3.setBackground(new java.awt.Color(141, 158, 229));

        javax.swing.GroupLayout pnlIngresosLayout = new javax.swing.GroupLayout(pnlIngresos);
        pnlIngresos.setLayout(pnlIngresosLayout);
        pnlIngresosLayout.setHorizontalGroup(
            pnlIngresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        pnlIngresosLayout.setVerticalGroup(
            pnlIngresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlEgresosLayout = new javax.swing.GroupLayout(pnlEgresos);
        pnlEgresos.setLayout(pnlEgresosLayout);
        pnlEgresosLayout.setHorizontalGroup(
            pnlEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );
        pnlEgresosLayout.setVerticalGroup(
            pnlEgresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tblTransacciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblTransacciones);

        javax.swing.GroupLayout pnlBalanceLayout = new javax.swing.GroupLayout(pnlBalance);
        pnlBalance.setLayout(pnlBalanceLayout);
        pnlBalanceLayout.setHorizontalGroup(
            pnlBalanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 189, Short.MAX_VALUE)
        );
        pnlBalanceLayout.setVerticalGroup(
            pnlBalanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(pnlIngresos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlEgresos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlIngresos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEgresos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBalance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel2.setText("INGRESOS :");

        txtingreso.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        txtingreso.setForeground(new java.awt.Color(0, 153, 51));
        txtingreso.setText("jLabel5");

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel4.setText("BALANCE  :");

        txtbalance.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        txtbalance.setText("jLabel7");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel3.setText("EGRESOS   :");

        txtegresos.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        txtegresos.setForeground(new java.awt.Color(255, 0, 0));
        txtegresos.setText("jLabel6");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtingreso, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbalance, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtegresos, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtingreso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtegresos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtbalance)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/excel.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(29, 29, 29)
                        .addComponent(jButton1)
                        .addGap(25, 25, 25))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(141, 158, 229));

        btnMenuPrincipal1.setBackground(new java.awt.Color(204, 204, 255));
        btnMenuPrincipal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/home.png"))); // NOI18N
        btnMenuPrincipal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuPrincipal1ActionPerformed(evt);
            }
        });

        btnAñadir1.setBackground(new java.awt.Color(204, 204, 255));
        btnAñadir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/add.png"))); // NOI18N
        btnAñadir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadir1ActionPerformed(evt);
            }
        });

        btnCerrarSesion1.setBackground(new java.awt.Color(141, 158, 229));
        btnCerrarSesion1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/off.png"))); // NOI18N
        btnCerrarSesion1.setBorder(null);
        btnCerrarSesion1.setBorderPainted(false);
        btnCerrarSesion1.setContentAreaFilled(false);
        btnCerrarSesion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesion1ActionPerformed(evt);
            }
        });

        lblIcono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtUsuario.setText("jLabel1");

        jButton4.setBackground(new java.awt.Color(204, 204, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/change.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblIcono, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnMenuPrincipal1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(btnAñadir1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnCerrarSesion1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))))
            .addComponent(jSeparator2)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcono, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtUsuario)
                .addGap(29, 29, 29)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnMenuPrincipal1)
                .addGap(47, 47, 47)
                .addComponent(btnAñadir1)
                .addGap(47, 47, 47)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnCerrarSesion1)
                .addGap(44, 44, 44))
        );

        jPanel2.setBackground(new java.awt.Color(111, 128, 196));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Panel de Control");

        btnAyuda.setBackground(new java.awt.Color(141, 158, 229));
        btnAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/question mark.png"))); // NOI18N
        btnAyuda.setBorderPainted(false);
        btnAyuda.setContentAreaFilled(false);
        btnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(147, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(101, 101, 101)
                .addComponent(btnAyuda)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAyuda)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Clases.ArchivoExcel objetoExcel = new Clases.ArchivoExcel();
        objetoExcel.crearArchivo(transacciones);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudaActionPerformed
        JOptionPane.showMessageDialog(this, "En esta pestaña podras observar lo siguiente:\n"
            + "1. La grafica a la izquirda representa los ingresos del mes\n"
            + "2. La grafica a la derecha representa los egresos del mes\n"
            + "3. La tabla inferiror muestra los datos tanto de los egresos como ingresos\n"
            + "4. El boton al lado de la tabla te permite descargar un excel de los movimientos del mes",
            "Ayuda", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnAyudaActionPerformed

    private void btnMenuPrincipal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuPrincipal1ActionPerformed
        formMenuPrincipal objetoMenu = new formMenuPrincipal(NombreUsuario);
        objetoMenu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuPrincipal1ActionPerformed

    private void btnAñadir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadir1ActionPerformed
        formAgregar objetoAgregar = new formAgregar(NombreUsuario);
        objetoAgregar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAñadir1ActionPerformed

    private void btnCerrarSesion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesion1ActionPerformed
        JOptionPane.showMessageDialog(this, "Cerrando Sesion...", "Saliendo", JOptionPane.INFORMATION_MESSAGE);

        FormLogin objetoLogin = new FormLogin();
        objetoLogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesion1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        formModificacion objetoMod = new formModificacion(NombreUsuario);
        objetoMod.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(formMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new formMenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAyuda;
    private javax.swing.JButton btnAñadir1;
    private javax.swing.JButton btnCerrarSesion1;
    private javax.swing.JButton btnMenuPrincipal1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblIcono;
    private javax.swing.JPanel pnlBalance;
    private javax.swing.JPanel pnlEgresos;
    private javax.swing.JPanel pnlIngresos;
    private javax.swing.JTable tblTransacciones;
    private javax.swing.JLabel txtUsuario;
    private javax.swing.JLabel txtbalance;
    private javax.swing.JLabel txtegresos;
    private javax.swing.JLabel txtingreso;
    // End of variables declaration//GEN-END:variables
}
