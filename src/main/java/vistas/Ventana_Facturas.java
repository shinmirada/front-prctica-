package vistas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import apiService.FacturaApiService;
import enums.Rol;
import modelo.Factura;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import util.RetrofitClient;

public class Ventana_Facturas extends javax.swing.JFrame implements util.ActualizaTemaVentanas {

    private Rol rolUsuario;
    private String clienteDoc;
    private FacturaApiService apiService;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtPedidoId;
    private javax.swing.JButton btnAccion;

    public Ventana_Facturas(Rol rolUsuario, String clienteDoc) {
        this.rolUsuario = rolUsuario;
        this.clienteDoc = clienteDoc;

        initComponents();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        Retrofit retrofit = RetrofitClient.getClient();
        apiService = retrofit.create(FacturaApiService.class);

        configurarVentanaPorRol();
        // Registrar esta ventana en el manejador de temas
        util.TemaVisual.registrarVentana(this);

        // Aplicar color actual al iniciar
        aplicarColor(util.TemaVisual.getColorFondo());
    }

    @Override
    public void aplicarColor(Color nuevoColor) {
        jPanelRosa.setBackground(nuevoColor);
        jPanelVerde.setBackground(nuevoColor);
        jPanel3.setBackground(nuevoColor);
        jTabbedPane1.setBackground(nuevoColor);
        btnRegresar.setBackground(nuevoColor.darker());
        jButton1.setBackground(nuevoColor.darker());
        btnAccion.setBackground(nuevoColor.darker());

    }

    private void configurarVentanaPorRol() {

        jPanelVerde.removeAll();
        jPanelVerde.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(8, 8, 8, 8);
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;

        javax.swing.JLabel lblDoc = new javax.swing.JLabel("Documento del Cliente:");
        lblDoc.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        jPanelVerde.add(lblDoc, gbc);

        txtDocumento = new javax.swing.JTextField(15);
        txtDocumento.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        jPanelVerde.add(txtDocumento, gbc);

        javax.swing.JLabel lblPedido = new javax.swing.JLabel("ID Pedido:");
        lblPedido.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        jPanelVerde.add(lblPedido, gbc);

        txtPedidoId = new javax.swing.JTextField(15);
        txtPedidoId.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        jPanelVerde.add(txtPedidoId, gbc);

        btnAccion = new javax.swing.JButton();
        btnAccion.setBackground(new java.awt.Color(232, 74, 95));
        btnAccion.setForeground(java.awt.Color.WHITE);
        btnAccion.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        jPanelVerde.add(btnAccion, gbc);

        if (rolUsuario == Rol.CLIENTE) {
            btnAccion.setText("Consultar Mis Facturas");
            txtDocumento.setText(clienteDoc);
            txtDocumento.setEditable(false);
            txtPedidoId.setEditable(false);
            btnAccion.addActionListener(e -> consultarFacturasCliente());

            if (jTabbedPane1.getTabCount() > 0) {
                jTabbedPane1.setEnabledAt(0, false);
                jTabbedPane1.setSelectedIndex(1);
            }

            consultarFacturasCliente();
        } else {
            btnAccion.setText("Registrar Factura");
            txtDocumento.setText("");
            txtDocumento.setEditable(true);
            txtPedidoId.setEditable(true);
            btnAccion.addActionListener(e -> generarFactura());

            if (jTabbedPane1.getTabCount() > 0) {
                jTabbedPane1.setEnabledAt(0, true);
            }
            cargarTodasFacturas();
        }

        jPanelVerde.revalidate();
        jPanelVerde.repaint();
    }

    private void consultarFacturasCliente() {
        final String doc = this.clienteDoc;

        javax.swing.SwingWorker<java.util.List<Factura>, Void> worker = new javax.swing.SwingWorker<>() {
            @Override
            protected java.util.List<Factura> doInBackground() throws Exception {
                retrofit2.Response<java.util.List<Factura>> response = apiService.getFacturasByUsuario(doc).execute();
                if (response.isSuccessful()) {
                    return response.body();
                } else {
                    throw new java.io.IOException("Error HTTP: " + response.code());
                }
            }

            @Override
            protected void done() {
                try {
                    java.util.List<Factura> facturas = get();
                    if (facturas == null || facturas.isEmpty()) {
                        mostrarFacturasEnTabla(java.util.Collections.emptyList());
                        javax.swing.JOptionPane.showMessageDialog(Ventana_Facturas.this, "No se encontraron facturas para este cliente.");
                    } else {
                        mostrarFacturasEnTabla(facturas);
                        jTabbedPane1.setSelectedIndex(1);
                    }
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(Ventana_Facturas.this, "Error al consultar facturas: " + ex.getMessage());
                }
            }
        };
        worker.execute();
    }

    private void cargarTodasFacturas() {
        javax.swing.SwingWorker<java.util.List<Factura>, Void> worker = new javax.swing.SwingWorker<>() {
            @Override
            protected java.util.List<Factura> doInBackground() throws Exception {
                retrofit2.Response<java.util.List<Factura>> response = apiService.getAllFacturas().execute();
                if (response.isSuccessful()) {
                    return response.body();
                } else {
                    throw new java.io.IOException("Error HTTP: " + response.code());
                }
            }

            @Override
            protected void done() {
                try {
                    java.util.List<Factura> facturas = get();
                    mostrarFacturasEnTabla(facturas);
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(Ventana_Facturas.this, "Error al cargar facturas: " + ex.getMessage());
                }
            }
        };
        worker.execute();
    }

    private void generarFactura() {
        String documentoCliente = txtDocumento.getText().trim();
        String pedidoIdStr = txtPedidoId.getText().trim();
        if (documentoCliente.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor ingrese el documento del cliente.");
            return;
        }
        if (pedidoIdStr.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor ingrese el ID del pedido.");
            return;
        }
        int pedidoId;
        try {
            pedidoId = Integer.parseInt(pedidoIdStr);
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "El ID del pedido debe ser numérico.");
            return;
        }

        Factura factura = new Factura();
        factura.setUsuarioDoc(documentoCliente);
        factura.setPedidoId(pedidoId);
        factura.setHora(java.time.LocalDateTime.now().toString());

        javax.swing.SwingWorker<Factura, Void> worker = new javax.swing.SwingWorker<>() {
            @Override
            protected Factura doInBackground() throws Exception {
                retrofit2.Call<Factura> call = apiService.createFactura(factura);
                retrofit2.Response<Factura> response = call.execute();
                if (response.isSuccessful()) {
                    return response.body();
                } else {
                    throw new java.io.IOException("Error HTTP: " + response.code());
                }
            }

            @Override
            protected void done() {
                try {
                    Factura f = get();
                    javax.swing.JOptionPane.showMessageDialog(Ventana_Facturas.this, "Factura generada correctamente para el pedido #" + pedidoId);
                    cargarTodasFacturas();
                    jTabbedPane1.setSelectedIndex(1);
                    txtPedidoId.setText("");
                    txtDocumento.setText("");
                } catch (Exception ex) {
                    String msg = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
                    if (msg != null && msg.contains("Error HTTP: 409")) {
                        javax.swing.JOptionPane.showMessageDialog(Ventana_Facturas.this, "Ya existe una factura para este pedido.");
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(Ventana_Facturas.this, "Error al generar factura: " + msg);
                    }
                }
            }
        };
        worker.execute();
    }

    private void mostrarFacturasEnTabla(java.util.List<Factura> facturas) {
        String[] cols = {"Código", "Fecha", "Cliente", "PedidoID", "Valor"};
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (facturas != null) {
            for (Factura f : facturas) {
                model.addRow(new Object[]{
                    f.getCodigo(),
                    f.getHora(),
                    f.getUsuarioDoc(),
                    f.getPedidoId(),
                    f.getValor()
                });
            }
        }
        tlbFactura.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelRosa = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelVerde = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tlbFactura = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana Factura");

        jPanelRosa.setBackground(new java.awt.Color(250, 218, 221));
        jPanelRosa.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(139, 94, 60)), "                                                                                     ...........Ventana Facturacion............", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Gabriola", 0, 36))); // NOI18N
        jPanelRosa.setToolTipText("");

        jTabbedPane1.setBackground(new java.awt.Color(168, 198, 134));

        jPanelVerde.setBackground(new java.awt.Color(168, 198, 134));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setText("Codigo :");

        jButton1.setBackground(new java.awt.Color(232, 74, 95));
        jButton1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton1.setText("Imprimir Factura");

        javax.swing.GroupLayout jPanelVerdeLayout = new javax.swing.GroupLayout(jPanelVerde);
        jPanelVerde.setLayout(jPanelVerdeLayout);
        jPanelVerdeLayout.setHorizontalGroup(
            jPanelVerdeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVerdeLayout.createSequentialGroup()
                .addGroup(jPanelVerdeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVerdeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelVerdeLayout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(868, Short.MAX_VALUE))
        );
        jPanelVerdeLayout.setVerticalGroup(
            jPanelVerdeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVerdeLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanelVerdeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(96, 96, 96)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(301, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Facturas", jPanelVerde);

        jPanel3.setBackground(new java.awt.Color(168, 198, 134));

        tlbFactura.setBackground(new java.awt.Color(168, 198, 134));
        tlbFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Fecha", "Cliente", "Valor"
            }
        ));
        jScrollPane1.setViewportView(tlbFactura);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lista Facturas", jPanel3);

        btnRegresar.setBackground(new java.awt.Color(255, 153, 153));
        btnRegresar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnRegresar.setText("Regresar ");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelRosaLayout = new javax.swing.GroupLayout(jPanelRosa);
        jPanelRosa.setLayout(jPanelRosaLayout);
        jPanelRosaLayout.setHorizontalGroup(
            jPanelRosaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRosaLayout.createSequentialGroup()
                .addGroup(jPanelRosaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRosaLayout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelRosaLayout.createSequentialGroup()
                        .addGap(456, 456, 456)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanelRosaLayout.setVerticalGroup(
            jPanelRosaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRosaLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelRosa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelRosa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed

        this.setVisible(false);

        if (rolUsuario == Rol.MESERO) {
            VentanaMatrizCamarero ventana = new VentanaMatrizCamarero(rolUsuario, clienteDoc);
            ventana.setVisible(true);
        } else if (rolUsuario == Rol.CLIENTE) {
            VentanaMisPedidos ventana = new VentanaMisPedidos(rolUsuario, clienteDoc);
            ventana.setVisible(true);
        } else {

            JOptionPane.showMessageDialog(this, "Rol no reconocido");
            this.setVisible(true);
        }
    }//GEN-LAST:event_btnRegresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelRosa;
    private javax.swing.JPanel jPanelVerde;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tlbFactura;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
