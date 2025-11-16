package vistas;


import apiService.PedidoApiService;
import enums.Rol;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.ItemPedidoDTO;
import modelo.Pedido;
import modelo.PedidoRequestDTO;
import retrofit2.Response;
import retrofit2.Retrofit;
import util.RetrofitClient;

public class VentanaDeOrden extends javax.swing.JFrame {
    
    private PedidoApiService apiService;
    private static final Color GRIS_SUAVE = new Color(200, 200, 200);
    private static final Color ROJO_TORII = new Color(232, 74, 95);
    private String ClienteDoc;
     private Rol rolUsuario;
  

    public VentanaDeOrden(Rol rolUsuario, String ClienteDoc) {
        this.ClienteDoc=ClienteDoc;
        this.rolUsuario=rolUsuario;
        initComponents2();            // inicializa UI
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        llenarComboBox();            // llena el combo
        // estilizar botones (se hace después de initComponents)
        estilizarBoton(btnDomicilio);
        estilizarBoton(btnRestaurante);
        estilizarBoton(btnRegresar);
        Retrofit retrofit = RetrofitClient.getClient();
        apiService = retrofit.create(PedidoApiService.class);
    }

    private void llenarComboBox() {

   String[] elementos = {
            "1 - Sushi clasico", "2 - Ramen especial", "3 - Bento Teriyaki", "4 - Tempura mixto",
            "5 - Udon tradicional", "6 - Yakisoba"
        };
        comboPlatos.setModel(new DefaultComboBoxModel<>(elementos));
    
    }
    

@SuppressWarnings("unchecked")
private void initComponents2() {

    panelOrdenPedido = new javax.swing.JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            int w = getWidth(), h = getHeight();
            Color top = new Color(255, 230, 230);
            Color bottom = new Color(240, 200, 200);
            g2.setPaint(new GradientPaint(0, 0, top, 0, h, bottom));
            g2.fillRect(0, 0, w, h);
            g2.dispose();
        }
    };

    jLabelSubtitulo = new javax.swing.JLabel();
    comboPlatos = new javax.swing.JComboBox<>();
    btnDomicilio = new javax.swing.JButton();
    btnRestaurante = new javax.swing.JButton();
    btnRegresar = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Ventana orden cliente");

    // ---- Config panel principal ----
    panelOrdenPedido.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(139, 94, 60)),
            ".........................Orden de pedido.........................",
            javax.swing.border.TitledBorder.CENTER,
            javax.swing.border.TitledBorder.ABOVE_TOP,
            new java.awt.Font("Gabriola", 0, 36),
            new java.awt.Color(0, 0, 0)));
    panelOrdenPedido.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(15, 15, 15, 15);
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.CENTER;

    // ---- Subtítulo ----
    jLabelSubtitulo.setFont(new java.awt.Font("Elephant", 0, 20));
    jLabelSubtitulo.setForeground(Color.BLACK);
    jLabelSubtitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabelSubtitulo.setText("Porfavor Seleccione Su Plato y Modalidad De Servicio");

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    panelOrdenPedido.add(jLabelSubtitulo, gbc);

    // ---- Combo ----
    comboPlatos.setFont(new java.awt.Font("Segoe UI", 0, 16));
    comboPlatos.setPreferredSize(new java.awt.Dimension(280, 35));

    gbc.gridy = 1;
    panelOrdenPedido.add(comboPlatos, gbc);

    // ---- Botón Domicilio ----
    btnDomicilio.setText("Pedir A Domicilio");
    btnDomicilio.setBackground(new java.awt.Color(232, 74, 95));
    btnDomicilio.setForeground(Color.WHITE);
    btnDomicilio.setPreferredSize(new java.awt.Dimension(200, 40));
    btnDomicilio.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDomicilioActionPerformed(evt);
        }
    });

    gbc.gridy = 2;
    gbc.gridx = 0;
    gbc.gridwidth = 1;
    panelOrdenPedido.add(btnDomicilio, gbc);

    // ---- Botón Restaurante ----
    btnRestaurante.setText("Comer En El Restaurante");
    btnRestaurante.setBackground(new java.awt.Color(232, 74, 95));
    btnRestaurante.setForeground(Color.WHITE);
    btnRestaurante.setPreferredSize(new java.awt.Dimension(200, 40));
    btnRestaurante.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRestauranteActionPerformed(evt);
        }
    });

    gbc.gridx = 1;
    panelOrdenPedido.add(btnRestaurante, gbc);

    // ---- Botón Regresar ----
    btnRegresar.setText("Regresar");
    btnRegresar.setBackground(new java.awt.Color(232, 74, 95));
    btnRegresar.setForeground(Color.WHITE);
    btnRegresar.setPreferredSize(new java.awt.Dimension(150, 35));
    btnRegresar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRegresarActionPerformed(evt);
        }
    });

    gbc.gridy = 3;
    gbc.gridx = 0;
    gbc.gridwidth = 2;
    panelOrdenPedido.add(btnRegresar, gbc);

    // ---- Añadir panel principal ----
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(panelOrdenPedido, BorderLayout.CENTER);

    pack();
}
private void estilizarBoton(javax.swing.JButton boton) {
    boton.setFocusPainted(false);
    boton.setBorderPainted(false);
    boton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));

    boton.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {
            boton.setBackground(new java.awt.Color(200, 50, 70));
        }
        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {
            boton.setBackground(new java.awt.Color(232, 74, 95));
        }
    });
}
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelOrdenPedido = new javax.swing.JPanel();
        jLabelSubtitulo = new javax.swing.JLabel();
        comboPlatos = new javax.swing.JComboBox<>();
        btnDomicilio = new javax.swing.JButton();
        btnRestaurante = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana orden cliente");

        panelOrdenPedido.setBackground(new java.awt.Color(250, 218, 221));
        panelOrdenPedido.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(139, 94, 60)), ".........................Orden de pedido.........................", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Gabriola", 0, 36))); // NOI18N
        panelOrdenPedido.setToolTipText("");

        jLabelSubtitulo.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSubtitulo.setFont(new java.awt.Font("Elephant", 0, 18)); // NOI18N
        jLabelSubtitulo.setText("Por favor Selecione Su Plato y Modalidad De Servicio");

        comboPlatos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnDomicilio.setBackground(new java.awt.Color(232, 74, 95));
        btnDomicilio.setForeground(new java.awt.Color(255, 255, 255));
        btnDomicilio.setText("Pedir A Domicilio");
        btnDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDomicilioActionPerformed(evt);
            }
        });

        btnRestaurante.setBackground(new java.awt.Color(232, 74, 95));
        btnRestaurante.setForeground(new java.awt.Color(255, 255, 255));
        btnRestaurante.setText("Comer En El Restaurante");
        btnRestaurante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestauranteActionPerformed(evt);
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

        javax.swing.GroupLayout panelOrdenPedidoLayout = new javax.swing.GroupLayout(panelOrdenPedido);
        panelOrdenPedido.setLayout(panelOrdenPedidoLayout);
        panelOrdenPedidoLayout.setHorizontalGroup(
            panelOrdenPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOrdenPedidoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(panelOrdenPedidoLayout.createSequentialGroup()
                .addGroup(panelOrdenPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelOrdenPedidoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelSubtitulo))
                    .addGroup(panelOrdenPedidoLayout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelOrdenPedidoLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(comboPlatos, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        panelOrdenPedidoLayout.setVerticalGroup(
            panelOrdenPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOrdenPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSubtitulo)
                .addGap(35, 35, 35)
                .addComponent(comboPlatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(panelOrdenPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRestaurante)
                    .addComponent(btnDomicilio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(btnRegresar)
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelOrdenPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelOrdenPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRestauranteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestauranteActionPerformed
        // TODO add your handling code here:

    	String seleccionado = comboPlatos.getSelectedItem().toString();
        int idPlato = Integer.parseInt(seleccionado.split(" - ")[0].trim());

        // ✅ Crear el DTO correctamente
        PedidoRequestDTO pedidoRequest = new PedidoRequestDTO();
        pedidoRequest.setClienteDoc(ClienteDoc);
        pedidoRequest.setEsDomicilio(false);
        
        // ✅ Crear lista con UN solo item
        List<ItemPedidoDTO> items = new ArrayList<>();
        items.add(new ItemPedidoDTO(idPlato, 1)); // 1 plato, cantidad 1
        pedidoRequest.setItems(items);

        try {
            Response<Pedido> response = apiService.createPedido(pedidoRequest).execute();

            if (response.isSuccessful() && response.body() != null) {
                Pedido creado = response.body();
                JOptionPane.showMessageDialog(this,
                        "✅ Pedido creado correctamente\n"
                        + "ID Pedido: " + creado.getId() + "\n"
                        + "Plato: " + seleccionado + "\n"
                        + "Modalidad: Restaurante");

                VentanaMisPedidos ventana = new VentanaMisPedidos(rolUsuario, ClienteDoc);
                ventana.setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this,
                        "⚠️ Error al crear el pedido. Código HTTP: " + response.code());
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "❌ Error de conexión con el servidor");
            e.printStackTrace();
        }
    }

    private void btnDomicilioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDomicilioActionPerformed
        // TODO add your handling code here:
    	String seleccionado = comboPlatos.getSelectedItem().toString();
        int idPlato = Integer.parseInt(seleccionado.split(" - ")[0].trim());

        // ✅ Crear el DTO correctamente
        PedidoRequestDTO pedidoRequest = new PedidoRequestDTO();
        pedidoRequest.setClienteDoc(ClienteDoc);
        pedidoRequest.setEsDomicilio(true);
        
        // ✅ Crear lista con UN solo item
        List<ItemPedidoDTO> items = new ArrayList<>();
        items.add(new ItemPedidoDTO(idPlato, 1)); // 1 plato, cantidad 1
        pedidoRequest.setItems(items);

        try {
            Response<Pedido> response = apiService.createPedido(pedidoRequest).execute();

            if (response.isSuccessful() && response.body() != null) {
                Pedido creado = response.body();
                JOptionPane.showMessageDialog(this,
                        "✅ Pedido creado correctamente\n"
                        + "ID Pedido: " + creado.getId() + "\n"
                        + "Plato: " + seleccionado + "\n"
                        + "Modalidad: Domicilio");

                VentanaMisPedidos ventana = new VentanaMisPedidos(rolUsuario, ClienteDoc);
                ventana.setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this,
                        "⚠️ Error al crear el pedido. Código HTTP: " + response.code());
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "❌ Error de conexión con el servidor");
            e.printStackTrace();
        }
    }
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
       VentanaDeCombos ventana=new VentanaDeCombos(rolUsuario, ClienteDoc);
       ventana.setVisible(true);
        this.setVisible(false);


    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDomicilio;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnRestaurante;
    private javax.swing.JComboBox<String> comboPlatos;
    private javax.swing.JLabel jLabelSubtitulo;
    private javax.swing.JPanel panelOrdenPedido;
    // End of variables declaration//GEN-END:variables

 
}
