/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import apiService.UsuarioApiService;
import enums.Rol;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.Usuario;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import util.RetrofitClient;

public class Ventana_InicioSesionUser extends javax.swing.JFrame implements util.ActualizaTemaVentanas {

    private UsuarioApiService apiService;

    public Ventana_InicioSesionUser() {
        initComponentsCustomizado();
        Retrofit retrofit = RetrofitClient.getClient();
        apiService = retrofit.create(UsuarioApiService.class);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        // Registrar esta ventana en el manejador de temas
        util.TemaVisual.registrarVentana(this);

        // Aplicar color actual al iniciar
        aplicarColor(util.TemaVisual.getColorFondo());

    }

    @Override
    public void aplicarColor(java.awt.Color nuevoColor) {
        jPanel1.setBackground(nuevoColor);
        btnLogin.setBackground(nuevoColor.darker());
        btnRegresar.setBackground(nuevoColor.darker());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana Inicio de sesion");

        jPanel1.setBackground(new java.awt.Color(250, 218, 221));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(139, 94, 60)), ".........................Inicio de Sesion.........................", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Gabriola", 0, 36), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Usuario");

        txtContraseña.setBackground(new java.awt.Color(255, 248, 240));

        txtUsuario.setBackground(new java.awt.Color(255, 248, 240));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Contraseña");

        btnRegresar.setBackground(new java.awt.Color(232, 74, 95));
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(232, 74, 95));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("LOGIN");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(151, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(162, 162, 162))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(btnRegresar)
                        .addGap(101, 101, 101)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(btnLogin))
                .addContainerGap(140, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initComponentsCustomizado() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana Inicio de Sesion");

        jPanel1.setBackground(new java.awt.Color(250, 218, 221));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(139, 94, 60)),
                ".........................Inicio de Sesion.........................",
                javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.ABOVE_TOP,
                new java.awt.Font("Gabriola", 0, 36),
                new java.awt.Color(0, 0, 0)
        ));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14));
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Usuario");

        txtUsuario.setBackground(new java.awt.Color(255, 248, 240));
        txtUsuario.setPreferredSize(new java.awt.Dimension(220, 34));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14));
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Contraseña");

        txtContraseña.setBackground(new java.awt.Color(255, 248, 240));
        txtContraseña.setPreferredSize(new java.awt.Dimension(220, 34));

        // --- Botones ---
        btnRegresar.setBackground(new java.awt.Color(232, 74, 95));
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("REGRESAR");
        btnRegresar.setPreferredSize(new java.awt.Dimension(140, 40));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(232, 74, 95));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("LOGIN");
        btnLogin.setPreferredSize(new java.awt.Dimension(140, 40));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = java.awt.GridBagConstraints.RELATIVE;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(15, 100, 15, 15);

        jPanel1.add(jLabel1, gbc);
        jPanel1.add(txtUsuario, gbc);
        jPanel1.add(jLabel3, gbc);
        jPanel1.add(txtContraseña, gbc);

        JPanel panelBotones = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 40, 10));
        panelBotones.setOpaque(false);
        panelBotones.add(btnRegresar);
        panelBotones.add(btnLogin);

        jPanel1.add(panelBotones, gbc);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }


    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        VentanaInicioUsuarios ventana = new VentanaInicioUsuarios();
        ventana.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        String usuario = txtUsuario.getText();
        String contraseña = txtContraseña.getText();

        if (usuario.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ingrese datos");
            return;
        }
        Usuario usu = new Usuario();
        usu.setUsuario(usuario);
        usu.setContraseña(contraseña);
        try {

            Response<Usuario> response = apiService.login(usu).execute();

            if (response.isSuccessful() && response.body() != null) {
                Usuario usuarioLogueado = response.body();
                JOptionPane.showMessageDialog(this,
                        "Bienvenido " + usuarioLogueado.getNombre()
                        + " (" + usuarioLogueado.getRol() + ")");

                Rol rol = usuarioLogueado.getRol();
                String ClienteDoc = usuarioLogueado.getDocumento();
                switch (rol) {
                    case CLIENTE -> {
                        VentanaDeCombos ventanaCliente = new VentanaDeCombos(rol, ClienteDoc);
                        ventanaCliente.setVisible(true);
                    }

                    case MESERO -> {
                        VentanaMatrizCamarero ventanaCamarero = new VentanaMatrizCamarero(rol, ClienteDoc);
                        ventanaCamarero.setVisible(true);
                        this.setVisible(false);
                    }

                    case ADMIN -> {
                        VentanaAdminCombos ventanaAdmin = new VentanaAdminCombos(rol, ClienteDoc);
                        ventanaAdmin.setVisible(true);
                    }

                    default -> {
                        JOptionPane.showMessageDialog(this, "Rol no reconocido: " + rol);
                        return;
                    }
                }
                this.setVisible(false);
            } else if (response.code() == 401) {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
            } else {
                JOptionPane.showMessageDialog(this, "Error al iniciar sesión: " + response.code());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error de conexión con el servidor");
            e.printStackTrace();
        }


    }//GEN-LAST:event_btnLoginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
