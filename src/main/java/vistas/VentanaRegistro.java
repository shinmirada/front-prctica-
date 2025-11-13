/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import apiService.UsuarioApiService;
import enums.Rol;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;
import retrofit2.Response;
import retrofit2.Retrofit;
import util.RetrofitClient;


/**
 *
 * @author MI PC
 */
public class VentanaRegistro extends javax.swing.JFrame implements util.ActualizaTemaVentanas {

  private UsuarioApiService apiService;

    public VentanaRegistro() {
        initComponents3();

        Retrofit retrofit = RetrofitClient.getClient();
        apiService = retrofit.create(UsuarioApiService.class);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Registro de Clientes");
        setLocationRelativeTo(null);
        // Registrar esta ventana en el manejador de temas
        util.TemaVisual.registrarVentana(this);

        // Aplicar color actual al iniciar
        aplicarColor(util.TemaVisual.getColorFondo());

    }  

      @Override
    public void aplicarColor(Color nuevoColor) {
        panelRegistroClientes.setBackground(nuevoColor);
        jTabbedPane1.setBackground(nuevoColor.darker());
        jPanelFormulario.setBackground(nuevoColor);
        jPanelTabla.setBackground(nuevoColor);
        btnRegistro.setBackground(nuevoColor.darker());
        btnRegresar.setBackground(nuevoColor.darker());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRegistroClientes = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelFormulario = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombrePropietario = new javax.swing.JTextField();
        cbTipoDocumento = new javax.swing.JComboBox<>();
        txtTelefono = new javax.swing.JTextField();
        txtNumeroDocumento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JTextField();
        lblContraeña = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnRegistro = new javax.swing.JButton();
        jPanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana Registro\n");

        panelRegistroClientes.setBackground(new java.awt.Color(250, 218, 221));
        panelRegistroClientes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(139, 94, 60)), "         ......................Registro de clientes.........................", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Gabriola", 0, 36))); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(168, 198, 134));

        jPanelFormulario.setBackground(new java.awt.Color(168, 198, 134));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setText("Nombre:");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel2.setText("Tipo de Documento:");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setText("Numero de Documento:");

        txtNombrePropietario.setToolTipText("Ingresa nombre del propietario");

        cbTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Cedula", "Tarjeta Identidad", "Cedula extranjera" }));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Telefono:");

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel5.setText("Direccion:");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel6.setText("Usuario:");

        lblContraeña.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblContraeña.setText("Contraseña:");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/conejopelion.jpg"))); // NOI18N
        jLabel7.setText("jLabel7");

        btnRegistro.setBackground(new java.awt.Color(232, 74, 95));
        btnRegistro.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistro.setText("REGISTRARSE");
        btnRegistro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFormularioLayout = new javax.swing.GroupLayout(jPanelFormulario);
        jPanelFormulario.setLayout(jPanelFormularioLayout);
        jPanelFormularioLayout.setHorizontalGroup(
            jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelFormularioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelFormularioLayout.createSequentialGroup()
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(lblContraeña))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                .addComponent(txtDireccion)
                                .addComponent(txtUsuario))
                            .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelFormularioLayout.createSequentialGroup()
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombrePropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbTipoDocumento, 0, 217, Short.MAX_VALUE)
                                .addComponent(txtNumeroDocumento)))))
                .addGap(31, 31, 31))
        );
        jPanelFormularioLayout.setVerticalGroup(
            jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombrePropietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(19, 19, 19)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContraeña)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormularioLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addContainerGap(23, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFormularioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );

        jTabbedPane1.addTab("Registro", jPanelFormulario);

        jPanelTabla.setBackground(new java.awt.Color(168, 198, 134));
        jPanelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "..........................................................Clientes...........................................................................", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre ", "Tipo de documento", "Numero de documento", "Telefono", "Direccion", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        javax.swing.GroupLayout jPanelTablaLayout = new javax.swing.GroupLayout(jPanelTabla);
        jPanelTabla.setLayout(jPanelTablaLayout);
        jPanelTablaLayout.setHorizontalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelTablaLayout.setVerticalGroup(
            jPanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(275, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tabla", jPanelTabla);

        btnRegresar.setBackground(new java.awt.Color(232, 74, 95));
        btnRegresar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar ");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRegistroClientesLayout = new javax.swing.GroupLayout(panelRegistroClientes);
        panelRegistroClientes.setLayout(panelRegistroClientesLayout);
        panelRegistroClientesLayout.setHorizontalGroup(
            panelRegistroClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistroClientesLayout.createSequentialGroup()
                .addGroup(panelRegistroClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegistroClientesLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRegistroClientesLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        panelRegistroClientesLayout.setVerticalGroup(
            panelRegistroClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistroClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegresar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelRegistroClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRegistroClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initComponents3() {

    panelRegistroClientes = new javax.swing.JPanel();
    jTabbedPane1 = new javax.swing.JTabbedPane();
    jPanelFormulario = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    txtNombrePropietario = new javax.swing.JTextField();
    txtTelefono = new javax.swing.JTextField();
    txtNumeroDocumento = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    txtDireccion = new javax.swing.JTextField();
    jLabel5 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    txtContraseña = new javax.swing.JTextField();
    lblContraeña = new javax.swing.JLabel();
    txtUsuario = new javax.swing.JTextField();
    jLabel7 = new javax.swing.JLabel();
    jPanelTabla = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    tblClientes = new javax.swing.JTable();
    btnRegresar = new javax.swing.JButton();
    btnRegistro= new javax.swing.JButton(); 

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Ventana Registro");

    // ===== PANEL PRINCIPAL =====
    panelRegistroClientes.setBackground(new java.awt.Color(250, 218, 221));
    panelRegistroClientes.setBorder(javax.swing.BorderFactory.createTitledBorder(
        javax.swing.BorderFactory.createLineBorder(new java.awt.Color(139, 94, 60)),
        ".........................Registro de Clientes.........................",
        javax.swing.border.TitledBorder.CENTER,
        javax.swing.border.TitledBorder.ABOVE_TOP,
        new java.awt.Font("Gabriola", 0, 36),
        new java.awt.Color(0, 0, 0)
    ));
    panelRegistroClientes.setLayout(new java.awt.BorderLayout(10,10)); // BorderLayout con márgenes

    // ===== PESTAÑA REGISTRO =====
    jPanelFormulario.setBackground(new java.awt.Color(168, 198, 134));

    jLabel1.setFont(new java.awt.Font("Verdana", 1, 14));
    jLabel1.setText("Nombre:");
    jLabel3.setFont(new java.awt.Font("Verdana", 1, 14));
    jLabel3.setText("Número de Documento:");
    jLabel4.setFont(new java.awt.Font("Verdana", 1, 14));
    jLabel4.setText("Teléfono:");
    jLabel5.setFont(new java.awt.Font("Verdana", 1, 14));
    jLabel5.setText("Dirección:");
    jLabel6.setFont(new java.awt.Font("Verdana", 1, 14));
    jLabel6.setText("Usuario:");
    lblContraeña.setFont(new java.awt.Font("Verdana", 1, 14));
    lblContraeña.setText("Contraseña:");

    jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/conejopelion.jpg")));

    // === Boton Registrar ===
    btnRegistro.setBackground(new java.awt.Color(232,74,95));
    btnRegistro.setFont(new java.awt.Font("Dialog", 1, 14));
    btnRegistro.setForeground(new java.awt.Color(255, 255, 255));
    btnRegistro.setText("Registrar");
    btnRegistro.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRegistroActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanelFormulario);
    jPanelFormulario.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGap(30)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(lblContraeña))
                    .addGap(30)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNombrePropietario)
                        .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                        .addComponent(txtTelefono)
                        .addComponent(txtDireccion)
                        .addComponent(txtUsuario)
                        .addComponent(txtContraseña)
                        .addComponent(btnRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addGap(200, 200, 200)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(50, Short.MAX_VALUE))
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGap(20)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(txtNombrePropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(15)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(15)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(15)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(15)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(15)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblContraeña)
                .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(20)
            .addComponent(btnRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE) // 👈 el botón queda justo debajo
            .addGap(20)
            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(20, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab("Registro", jPanelFormulario);

    // ===== PESTAÑA TABLA =====
    jPanelTabla.setBackground(new java.awt.Color(168, 198, 134));
    jPanelTabla.setLayout(new java.awt.BorderLayout());

    tblClientes.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {},
        new String [] {
            "Nombre", "Número de documento", "Teléfono", "Dirección", "Usuario"
        }
    ));
    jScrollPane1.setViewportView(tblClientes);

    jPanelTabla.add(jScrollPane1, java.awt.BorderLayout.CENTER);

    jTabbedPane1.addTab("Tabla", jPanelTabla);

    // ===== BOTÓN REGRESAR ABAJO =====
    btnRegresar.setBackground(new java.awt.Color(232, 74, 95));
    btnRegresar.setFont(new java.awt.Font("Dialog", 1, 14));
    btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
    btnRegresar.setText("Regresar");
    btnRegresar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRegresarActionPerformed(evt);
        }
    });

    panelRegistroClientes.add(jTabbedPane1, java.awt.BorderLayout.CENTER);
    panelRegistroClientes.add(btnRegresar, java.awt.BorderLayout.SOUTH);

    // ===== LAYOUT PRINCIPAL DE LA VENTANA =====
    getContentPane().setLayout(new java.awt.BorderLayout());
    getContentPane().add(panelRegistroClientes, java.awt.BorderLayout.CENTER);

    pack();
}

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        VentanaInicioUsuarios ventana= new VentanaInicioUsuarios();
        ventana.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroActionPerformed
        // TODO add your handling code here:
        String nombre= txtNombrePropietario.getText();
        String documento= txtNumeroDocumento.getText();
        String telefono= txtTelefono.getText();
        String direccion= txtDireccion.getText();
        String usuario=txtUsuario.getText();
        String contraseña=txtContraseña.getText();
        
        if (nombre.isEmpty() || documento.isEmpty() ||telefono.isEmpty()
                ||direccion.isEmpty() || usuario.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(null, "todos los campos  son obligatorios");
            return;
        }
        
        Usuario usuario1= new Usuario(nombre, documento, telefono, direccion, usuario, contraseña, Rol.CLIENTE);
        
            try {
        Response<Usuario> response = apiService.createUsuario(usuario1).execute();
        if (response.isSuccessful()) {
            JOptionPane.showMessageDialog(this, " Usuario creado con éxito : " +response.code());
            llenartabla();
        } else {
            JOptionPane.showMessageDialog(this, "️ Error al crear usuario: " + response.code());
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, " Fallo la  conexcion con el servidor");
    }

    }//GEN-LAST:event_btnRegistroActionPerformed

    public void cambiarColorPanel(Color nuevoColor) {
        if (panelRegistroClientes != null) {
            panelRegistroClientes.setBackground(nuevoColor);
        }
    }

private void llenartabla() {
    try {
      Response<List<Usuario>> response = apiService.getUsuariosByRol("CLIENTE").execute();


        if (response.isSuccessful() && response.body() != null) {
            List<Usuario> usuarios = response.body();

           
            DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();

         
            model.setRowCount(0);

         
            for (Usuario u : usuarios) {
                model.addRow(new Object[]{
                    u.getNombre(),
                    u.getDocumento(),
                    u.getTelefono(),
                    u.getDireccion(),
                    u.getUsuario(),
                    u.getContraseña()
                });
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al obtener los usuarios: " + response.code());
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Fallo la conexión con el servidor");
    }
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistro;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbTipoDocumento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanelFormulario;
    private javax.swing.JPanel jPanelTabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblContraeña;
    private javax.swing.JPanel panelRegistroClientes;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombrePropietario;
    private javax.swing.JTextField txtNumeroDocumento;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
