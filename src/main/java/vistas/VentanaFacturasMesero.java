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

public class VentanaFacturasMesero extends javax.swing.JFrame {

    private FacturaApiService apiService;
    private JTextField txtClienteDoc;
    private JTextField txtPedidoId;
    private JTable tblFacturas;
    private Rol rolUsuario;
    private String meseroDoc;

    private static final Color ROSA_PASTEL = new Color(0xF9, 0xC5, 0xD5);
    private static final Color BLANCO_CREMOSO = new Color(0xFF, 0xF8, 0xF0);
    private static final Color ROJO_TORII = new Color(0xE8, 0x4A, 0x5F);
    private static final Color MARRON_MADERA = new Color(139, 94, 60);
    private static final Color VERDE_MECCHA = new Color(168, 198, 134);

    public VentanaFacturasMesero(Rol rolUsuario, String meseroDoc) {
        this.rolUsuario = rolUsuario;
        this.meseroDoc = meseroDoc;

        setTitle("Consulta de Facturas - Mesero");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Retrofit retrofit = RetrofitClient.getClient();
        apiService = retrofit.create(FacturaApiService.class);

        initCustomComponents();
    }

    private void initCustomComponents() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(ROSA_PASTEL);
        panelPrincipal.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(MARRON_MADERA, 2),
                ".............. Consulta de Facturas ..............",
                javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.ABOVE_TOP,
                new Font("Gabriola", Font.PLAIN, 36),
                Color.BLACK
        ));

        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel(new GridBagLayout());
        panelBusqueda.setBackground(ROSA_PASTEL);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Buscar por Cliente
        JLabel lblCliente = new JLabel("Doc. Cliente:");
        lblCliente.setFont(new Font("Dialog", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelBusqueda.add(lblCliente, gbc);

        txtClienteDoc = new JTextField(15);
        gbc.gridx = 1;
        panelBusqueda.add(txtClienteDoc, gbc);

        JButton btnBuscarCliente = new JButton("Buscar por Cliente");
        btnBuscarCliente.setBackground(ROJO_TORII);
        btnBuscarCliente.setForeground(BLANCO_CREMOSO);
        btnBuscarCliente.setFont(new Font("Dialog", Font.BOLD, 12));
        btnBuscarCliente.addActionListener(e -> buscarPorCliente());
        gbc.gridx = 2;
        panelBusqueda.add(btnBuscarCliente, gbc);

        // Buscar por Pedido
        JLabel lblPedido = new JLabel("ID Pedido:");
        lblPedido.setFont(new Font("Dialog", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelBusqueda.add(lblPedido, gbc);

        txtPedidoId = new JTextField(15);
        gbc.gridx = 1;
        panelBusqueda.add(txtPedidoId, gbc);

        JButton btnBuscarPedido = new JButton("Buscar por Pedido");
        btnBuscarPedido.setBackground(VERDE_MECCHA);
        btnBuscarPedido.setForeground(Color.BLACK);
        btnBuscarPedido.setFont(new Font("Dialog", Font.BOLD, 12));
        btnBuscarPedido.addActionListener(e -> buscarPorPedido());
        gbc.gridx = 2;
        panelBusqueda.add(btnBuscarPedido, gbc);

        // Botón Todas
        JButton btnTodas = new JButton("Mostrar Todas");
        btnTodas.setBackground(MARRON_MADERA);
        btnTodas.setForeground(BLANCO_CREMOSO);
        btnTodas.setFont(new Font("Dialog", Font.BOLD, 12));
        btnTodas.addActionListener(e -> cargarTodasFacturas());
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelBusqueda.add(btnTodas, gbc);

        panelPrincipal.add(panelBusqueda, BorderLayout.NORTH);

        // Tabla de facturas
        String[] columnas = {"ID Factura", "Fecha", "Cliente", "Pedido ID", "Total"};
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblFacturas = new JTable(model);
        tblFacturas.setFont(new Font("Dialog", Font.PLAIN, 13));
        tblFacturas.setRowHeight(25);
        JScrollPane scroll = new JScrollPane(tblFacturas);
        panelPrincipal.add(scroll, BorderLayout.CENTER);

        // Panel inferior de navegación
        JPanel panelNavegacion = new JPanel();
        panelNavegacion.setBackground(ROSA_PASTEL);

        JButton btnRegresar = new JButton("← Regresar");
        btnRegresar.setBackground(ROJO_TORII);
        btnRegresar.setForeground(BLANCO_CREMOSO);
        btnRegresar.setFont(new Font("Dialog", Font.BOLD, 14));
        btnRegresar.addActionListener(e -> {
            new VentanaMatrizCamarero(rolUsuario, meseroDoc).setVisible(true);
            this.dispose();
        });
        panelNavegacion.add(btnRegresar);

        panelPrincipal.add(panelNavegacion, BorderLayout.SOUTH);

        getContentPane().add(panelPrincipal);
    }

    private void buscarPorCliente() {
        String clienteDoc = txtClienteDoc.getText().trim();
        if (clienteDoc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el documento del cliente");
            return;
        }

        try {
            Response<List<Factura>> response = apiService.getFacturasByUsuario(clienteDoc).execute();
            if (response.isSuccessful() && response.body() != null) {
                mostrarFacturasEnTabla(response.body());
            } else {
                // ✅ Mostrar tabla vacía sin mensaje de error
                mostrarFacturasEnTabla(java.util.Collections.emptyList());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage());
        }
    }

    private void buscarPorPedido() {
        String pedidoIdStr = txtPedidoId.getText().trim();
        String clienteDoc = txtClienteDoc.getText().trim();

        if (pedidoIdStr.isEmpty() || clienteDoc.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Debe ingresar tanto el ID del pedido como el documento del cliente");
            return;
        }

        try {
            int pedidoId = Integer.parseInt(pedidoIdStr);
            Response<Factura> response = apiService.getFacturaByPedidoYUsuario(pedidoId, clienteDoc).execute();
            
            if (response.isSuccessful() && response.body() != null) {
                mostrarFacturasEnTabla(List.of(response.body()));
            } else {
                mostrarFacturasEnTabla(java.util.Collections.emptyList());
                JOptionPane.showMessageDialog(this, "No se encontró factura para ese pedido");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID del pedido debe ser numérico");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage());
        }
    }

    private void cargarTodasFacturas() {
        try {
            Response<List<Factura>> response = apiService.getAllFacturas().execute();
            if (response.isSuccessful() && response.body() != null) {
                mostrarFacturasEnTabla(response.body());
            } else {
                JOptionPane.showMessageDialog(this, "Error al cargar facturas");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage());
        }
    }

    private void mostrarFacturasEnTabla(List<Factura> facturas) {
        javax.swing.table.DefaultTableModel model = 
            (javax.swing.table.DefaultTableModel) tblFacturas.getModel();
        model.setRowCount(0);

        if (facturas != null && !facturas.isEmpty()) {
            for (Factura f : facturas) {
                String fecha = f.getFecha() != null 
                    ? f.getFecha().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                    : "N/A";
                
                String cliente = f.getUsuario() != null ? f.getUsuario().getNombre() : "N/A";
                String pedidoId = f.getPedido() != null ? String.valueOf(f.getPedido().getId()) : "N/A";

                model.addRow(new Object[]{
                    f.getFacturaid(),
                    fecha,
                    cliente,
                    pedidoId,
                    "$" + String.format("%.2f", f.getTotal())
                });
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
