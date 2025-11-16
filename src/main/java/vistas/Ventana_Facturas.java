package vistas;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import apiService.FacturaApiService;
import enums.Rol;
import modelo.Factura;
import retrofit2.Response;
import retrofit2.Retrofit;
import util.RetrofitClient;

public class Ventana_Facturas extends javax.swing.JFrame {

    private String clienteDoc;
    private FacturaApiService apiService;
    private javax.swing.JTextField txtPedidoId;
    private Rol rolUsuario;

    public Ventana_Facturas( Rol rolUsuario, String clienteDoc) {
        this.clienteDoc = clienteDoc;
        this.rolUsuario= rolUsuario;

        initComponents();  
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        Retrofit retrofit = RetrofitClient.getClient();
        apiService = retrofit.create(FacturaApiService.class);

        configurarVentana();
    }

    private void configurarVentana() {
        jPanelVerde.removeAll();
        jPanelVerde.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;

        // Título
        javax.swing.JLabel lblTitulo = new javax.swing.JLabel("Mis Facturas");
        lblTitulo.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        jPanelVerde.add(lblTitulo, gbc);

        // Campo de búsqueda
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.anchor = java.awt.GridBagConstraints.WEST;

        javax.swing.JLabel lblBuscar = new javax.swing.JLabel("Buscar por ID Pedido:");
        lblBuscar.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
        gbc.gridx = 0;
        jPanelVerde.add(lblBuscar, gbc);

        txtPedidoId = new javax.swing.JTextField(15);
        txtPedidoId.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
        gbc.gridx = 1;
        jPanelVerde.add(txtPedidoId, gbc);

        // Botón Buscar
        javax.swing.JButton btnBuscar = new javax.swing.JButton("Buscar");
        btnBuscar.setBackground(new java.awt.Color(232, 74, 95));
        btnBuscar.setForeground(java.awt.Color.WHITE);
        btnBuscar.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
        btnBuscar.addActionListener(e -> filtrarPorPedido());
        gbc.gridx = 2;
        jPanelVerde.add(btnBuscar, gbc);

        // Botón Mostrar Todas
        javax.swing.JButton btnMostrarTodas = new javax.swing.JButton("Mostrar Todas");
        btnMostrarTodas.setBackground(new java.awt.Color(168, 198, 134));
        btnMostrarTodas.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
        btnMostrarTodas.addActionListener(e -> cargarMisFacturas());
        gbc.gridx = 3;
        jPanelVerde.add(btnMostrarTodas, gbc);

        jPanelVerde.revalidate();
        jPanelVerde.repaint();

        // Cargar facturas del cliente al abrir la ventana
        cargarMisFacturas();
    }

    private void cargarMisFacturas() {
        javax.swing.SwingWorker<List<Factura>, Void> worker = new javax.swing.SwingWorker<>() {
            @Override
            protected List<Factura> doInBackground() throws Exception {
                Response<List<Factura>> response = apiService.getFacturasByUsuario(clienteDoc).execute();
                if (response.isSuccessful()) {
                    return response.body();
                } else {
                    throw new IOException("Error HTTP: " + response.code());
                }
            }

            @Override
            protected void done() {
                try {
                    List<Factura> facturas = get();
                    if (facturas == null || facturas.isEmpty()) {
                        mostrarFacturasEnTabla(java.util.Collections.emptyList());
                        JOptionPane.showMessageDialog(Ventana_Facturas.this, 
                            "No tienes facturas registradas aún.");
                    } else {
                        mostrarFacturasEnTabla(facturas);
                    }
                    txtPedidoId.setText(""); // Limpiar campo de búsqueda
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Ventana_Facturas.this, 
                        "Error al cargar tus facturas: " + ex.getMessage());
                }
            }
        };
        worker.execute();
    }
    private void filtrarPorPedido() {
        String pedidoIdStr = txtPedidoId.getText().trim();
        
        if (pedidoIdStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un ID de pedido");
            return;
        }

        try {
            int pedidoId = Integer.parseInt(pedidoIdStr);
            
            javax.swing.SwingWorker<Factura, Void> worker = new javax.swing.SwingWorker<>() {
                @Override
                protected Factura doInBackground() throws Exception {
                    // ✅ CORREGIDO: Usar el método correcto
                    Response<Factura> response = apiService.getFacturaByPedidoYUsuario(pedidoId, clienteDoc).execute();
                    if (response.isSuccessful()) {
                        return response.body();
                    } else if (response.code() == 404) {
                        return null;
                    } else {
                        throw new IOException("Error HTTP: " + response.code());
                    }
                }

                @Override
                protected void done() {
                    try {
                        Factura factura = get();
                        if (factura == null) {
                            mostrarFacturasEnTabla(java.util.Collections.emptyList());
                            JOptionPane.showMessageDialog(Ventana_Facturas.this, 
                                "No se encontró factura para el pedido #" + pedidoId);
                        } else {
                            mostrarFacturasEnTabla(List.of(factura));
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(Ventana_Facturas.this, 
                            "Error al buscar factura: " + ex.getMessage());
                    }
                }
            };
            worker.execute();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID del pedido debe ser numérico");
        }
    }

    private void mostrarFacturasEnTabla(List<Factura> facturas) {
        String[] cols = {"ID", "Fecha", "Pedido ID", "Total"};
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (facturas != null) {
            for (Factura f : facturas) {
                model.addRow(new Object[]{
                    f.getFacturaid(),           // ✅ Ahora es Long id
                    f.getFecha(),        // ✅ LocalDateTime fecha
                    f.getPedidoId(),  // ✅ Obtener ID del pedido
                    "$" + String.format("%.2f", f.getTotal())  // ✅ BigDecimal total
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
    }

    
    
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {
    	
    this.setVisible(false);
        VentanaMisPedidos ventana = new VentanaMisPedidos(rolUsuario, clienteDoc);
        ventana.setVisible(true);
   
    }


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
