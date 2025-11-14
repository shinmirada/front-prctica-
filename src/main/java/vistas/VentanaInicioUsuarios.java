package vistas;


import java.awt.Color;
import javax.swing.Box;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author MI PC
 */
public class VentanaInicioUsuarios extends javax.swing.JFrame {


    private VentanaRegistro vRegistro;

    public VentanaInicioUsuarios() {
        initComponentsCustomizado();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        setTitle("¡Hola que deseas hacer hoy!");

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ---------- BARRA DE PROGRESO EN HILO SEPARADO ----------
        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                try {
                    // Espera 50ms entre cada incremento
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // Muestra error si el hilo fue interrumpido
                    e.printStackTrace();
                }
                // Actualiza la barra con el valor actual
                jProgressBar.setValue(i);
            }
            // Lanza el hilo
        }).start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        panelBienvenida = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCambiarColor = new javax.swing.JButton();
        jProgressBar = new javax.swing.JProgressBar();
        btnLogin = new javax.swing.JButton();
        btnRegistro = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("¡Hola que deseas hacer hoy!");

        panelBienvenida.setBackground(new java.awt.Color(250, 218, 221));
        panelBienvenida.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(139, 94, 60)), ".........................Restaurante Usugi-Dono.........................", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Gabriola", 0, 36), new java.awt.Color(0, 0, 0))); // NOI18N
        panelBienvenida.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Elephant", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("    ¿Que desea Hacer?");

        btnCambiarColor.setBackground(new java.awt.Color(232, 74, 95));
        btnCambiarColor.setForeground(new java.awt.Color(255, 255, 255));
        btnCambiarColor.setText("Cambiar a modo oscuro");
        btnCambiarColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarColorActionPerformed(evt);
            }
        });

        jProgressBar.setBackground(new java.awt.Color(168, 198, 134));
        jProgressBar.setForeground(new java.awt.Color(200, 200, 200));
        jProgressBar.setStringPainted(true);

        btnLogin.setBackground(new java.awt.Color(232, 74, 95));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Iniciar Sesion");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnRegistro.setBackground(new java.awt.Color(232, 74, 95));
        btnRegistro.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistro.setText("Registrarse");
        btnRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(232, 74, 95));
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBienvenidaLayout = new javax.swing.GroupLayout(panelBienvenida);
        panelBienvenida.setLayout(panelBienvenidaLayout);
        panelBienvenidaLayout.setHorizontalGroup(
            panelBienvenidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBienvenidaLayout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(btnLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegistro)
                .addGap(138, 138, 138))
            .addGroup(panelBienvenidaLayout.createSequentialGroup()
                .addGroup(panelBienvenidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBienvenidaLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBienvenidaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCambiarColor, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBienvenidaLayout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addComponent(btnRegresar))
                    .addGroup(panelBienvenidaLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        panelBienvenidaLayout.setVerticalGroup(
            panelBienvenidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBienvenidaLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addGap(52, 52, 52)
                .addGroup(panelBienvenidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnRegistro))
                .addGap(18, 18, 18)
                .addComponent(btnRegresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(btnCambiarColor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBienvenida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initComponentsCustomizado() {

    jMenu1 = new javax.swing.JMenu();
    jMenu2 = new javax.swing.JMenu();
    jMenuItem1 = new javax.swing.JMenuItem();
    jMenuItem2 = new javax.swing.JMenuItem();
    jMenuItem3 = new javax.swing.JMenuItem();
    panelBienvenida = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    btnCambiarColor = new javax.swing.JButton();
    jProgressBar = new javax.swing.JProgressBar();
    btnLogin = new javax.swing.JButton();
    btnRegistro = new javax.swing.JButton();
    btnRegresar = new javax.swing.JButton();

    jMenu1.setText("jMenu1");
    jMenu2.setText("jMenu2");
    jMenuItem1.setText("jMenuItem1");
    jMenuItem2.setText("jMenuItem2");
    jMenuItem3.setText("jMenuItem3");

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("¡Hola que deseas hacer hoy!");

  
    panelBienvenida.setBackground(new java.awt.Color(250, 218, 221));
    panelBienvenida.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(139, 94, 60)),
            ".........................Restaurante Usugi-Dono.........................",
            javax.swing.border.TitledBorder.CENTER,
            javax.swing.border.TitledBorder.ABOVE_TOP,
            new java.awt.Font("Gabriola", 0, 36),
            new java.awt.Color(0, 0, 0)
    ));
    panelBienvenida.setLayout(new java.awt.GridBagLayout());
    java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();

  
    jLabel1.setFont(new java.awt.Font("Elephant", 0, 18));
    jLabel1.setForeground(new java.awt.Color(0, 0, 0));
    jLabel1.setText("¿Qué desea Hacer?");

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.insets = new java.awt.Insets(30, 0, 30, 0);
    gbc.anchor = java.awt.GridBagConstraints.CENTER;
    panelBienvenida.add(jLabel1, gbc);

    
    btnLogin.setBackground(new java.awt.Color(232, 74, 95));
    btnLogin.setForeground(new java.awt.Color(255, 255, 255));
    btnLogin.setText("Iniciar Sesión");
    btnLogin.setPreferredSize(new java.awt.Dimension(150, 40));
    btnLogin.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnLoginActionPerformed(evt);
        }
    });

    btnRegistro.setBackground(new java.awt.Color(232, 74, 95));
    btnRegistro.setForeground(new java.awt.Color(255, 255, 255));
    btnRegistro.setText("Registrarse");
    btnRegistro.setPreferredSize(new java.awt.Dimension(150, 40));
    btnRegistro.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRegistroActionPerformed(evt);
        }
    });

    btnRegresar.setBackground(new java.awt.Color(232, 74, 95));
    btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
    btnRegresar.setText("Regresar");
    btnRegresar.setPreferredSize(new java.awt.Dimension(150, 40));
    btnRegresar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRegresarActionPerformed(evt);
        }
    });

    JPanel panelBotones = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 20));
    panelBotones.setOpaque(false);
    panelBotones.add(btnLogin);
    panelBotones.add(btnRegistro);
    panelBotones.add(btnRegresar);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.anchor = java.awt.GridBagConstraints.NORTH;
    panelBienvenida.add(panelBotones, gbc);

    
    btnCambiarColor.setBackground(new java.awt.Color(232, 74, 95));
    btnCambiarColor.setForeground(new java.awt.Color(255, 255, 255));
    btnCambiarColor.setText("Cambiar a modo oscuro");
    btnCambiarColor.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCambiarColorActionPerformed(evt);
        }
    });

    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 1.0;
    gbc.weighty = 0.0;
    gbc.anchor = java.awt.GridBagConstraints.SOUTHWEST;
    gbc.insets = new java.awt.Insets(10, 20, 5, 20);
    panelBienvenida.add(btnCambiarColor, gbc);

   
    jProgressBar.setBackground(new java.awt.Color(168, 198, 134));
    jProgressBar.setForeground(new java.awt.Color(200, 200, 200));
    jProgressBar.setStringPainted(true);

    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weightx = 1.0;
    gbc.weighty = 0.0;
    gbc.anchor = java.awt.GridBagConstraints.SOUTH;
    gbc.insets = new java.awt.Insets(0, 20, 20, 20);
    gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
    panelBienvenida.add(jProgressBar, gbc);

    
    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBienvenida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBienvenida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
    setExtendedState(JFrame.MAXIMIZED_BOTH); 
}

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
       Ventana_InicioSesionUser ventana= new Ventana_InicioSesionUser();
       ventana.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroActionPerformed
       vRegistro= new VentanaRegistro();
       vRegistro.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnRegistroActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
       VentanaPrincipal ventana= new VentanaPrincipal();
       ventana.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnCambiarColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarColorActionPerformed
        Color nuevoColor = JColorChooser.showDialog(this, "Selecciona un color", panelBienvenida.getBackground());
        if (nuevoColor != null) {
            panelBienvenida.setBackground(nuevoColor); // cambia color en ventana1
            if (vRegistro != null) {
                vRegistro.cambiarColorPanel(nuevoColor); // cambia color en ventana2
            }
        }
    }//GEN-LAST:event_btnCambiarColorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiarColor;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegistro;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JPanel panelBienvenida;
    // End of variables declaration//GEN-END:variables
}
