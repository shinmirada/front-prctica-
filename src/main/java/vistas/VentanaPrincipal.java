package vistas;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class VentanaPrincipal extends javax.swing.JFrame implements util.ActualizaTemaVentanas{

    public VentanaPrincipal() {
        initComponentsmodificadoPersonal();

        setTitle("Ventana Principal");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
     // Registrar esta ventana en el manejador de temas
        util.TemaVisual.registrarVentana(this);

        // Aplicar color actual al iniciar
        aplicarColor(util.TemaVisual.getColorFondo());
    }

    @Override
    public void aplicarColor(Color nuevoColor) {
        // Cambiar el fondo principal
        jPanel1.setBackground(nuevoColor);

        // Cambiar botones
        btnContinuar.setBackground(nuevoColor.darker());

        // Menu y barra de menu
        jMenuBar1.setBackground(nuevoColor.darker());
        jMenu5.setBackground(nuevoColor);
        jMenuItem4.setBackground(nuevoColor.darker());
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnContinuar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana Principal\n");
        setPreferredSize(new java.awt.Dimension(1400, 700));

        jPanel1.setBackground(new java.awt.Color(250, 218, 221));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(139, 94, 60)), "......................Restaurante Usugi-Dono.........................", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Gabriola", 0, 36))); // NOI18N

        btnContinuar.setBackground(new java.awt.Color(232, 74, 95));
        btnContinuar.setForeground(new java.awt.Color(255, 255, 255));
        btnContinuar.setText("CONTINUAR");
        btnContinuar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Elephant", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("¡Bienvenido Querido Usuario!");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ImagenVistaPrincipal.png"))); // NOI18N
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(42, 42, 42)
                .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar1.setBackground(new java.awt.Color(139, 94, 60));

        jMenu5.setOpaque(true);
        jMenu5.setBackground(new java.awt.Color(250, 218, 221));
        jMenu5.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 248, 240)));
        jMenu5.setText("Salir");

        jMenuItem4.setBackground(new java.awt.Color(204, 0, 51));
        jMenuItem4.setText("Cerrar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

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
    }// </editor-fold>//GEN-END:initComponents

    private void initComponentsmodificadoPersonal() {

        jPanel1 = new javax.swing.JPanel();
        btnContinuar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana Principal\n");

        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);

        jPanel1.setBackground(new java.awt.Color(250, 218, 221));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(139, 94, 60)),
                "......................Restaurante Usugi-Dono.........................",
                javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.ABOVE_TOP,
                new java.awt.Font("Gabriola", 0, 36),
                new java.awt.Color(0, 0, 0)
        ));

        btnContinuar.setBackground(new java.awt.Color(232, 74, 95));
        btnContinuar.setForeground(new java.awt.Color(255, 255, 255));
        btnContinuar.setText("CONTINUAR");
        btnContinuar.setPreferredSize(new java.awt.Dimension(160, 50)); 
        btnContinuar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Elephant", 0, 22));
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("¡Bienvenido Querido Usuario!");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ImagenVistaPrincipal.png")));
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = java.awt.GridBagConstraints.RELATIVE;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(20, 80, 20, 20); 

        jPanel1.add(jLabel1, gbc);
        jPanel1.add(btnContinuar, gbc);
        jPanel1.add(jLabel3, gbc);

        jMenuBar1.setBackground(new java.awt.Color(139, 94, 60));

        jMenu5.setOpaque(true);
        jMenu5.setBackground(new java.awt.Color(250, 218, 221));
        jMenu5.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 248, 240)));
        jMenu5.setText("Salir");

        jMenuItem4.setBackground(new java.awt.Color(204, 0, 51));
        jMenuItem4.setText("Cerrar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenuBar1.add(jMenu5);
        setJMenuBar(jMenuBar1);

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


    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
//         TODO add your handling code here:
      VentanaInicioUsuarios  ventana= new VentanaInicioUsuarios();
        ventana.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Deseas salir de la aplicación?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
        new VentanaPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinuar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
