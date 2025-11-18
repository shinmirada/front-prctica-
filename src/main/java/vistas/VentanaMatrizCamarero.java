package vistas;

import apiService.FacturaApiService;
import apiService.PedidoApiService;
import enums.Estado;
import enums.Rol;
import modelo.EstadoUpdateDTO;
import modelo.Factura;
import modelo.ItemPedido;
import modelo.Pedido;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import util.RetrofitClient;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class VentanaMatrizCamarero extends javax.swing.JFrame {

    private JPanel panelMatriz;
    private PedidoApiService apiService;
    private FacturaApiService facturaApiService;
    private Rol rolUsuario;
    private String clienteDoc;

    private static final Color ROSA_PASTEL = new Color(0xF9, 0xC5, 0xD5);
    private static final Color BLANCO_CREMOSO = new Color(0xFF, 0xF8, 0xF0);
    private static final Color ROJO_TORII = new Color(0xE8, 0x4A, 0x5F);
    private static final Color MARRON_MADERA = new Color(139, 94, 60);
    private static final Color VERDE_MECCHA = new Color(168, 198, 134);
    private static final Color GRIS_CLARO = new Color(187, 187, 187);

    public VentanaMatrizCamarero(Rol rolUsuario, String clienteDoc) {
        this.rolUsuario = rolUsuario;
        this.clienteDoc = clienteDoc;

        setTitle("Pedidos Existentes");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Retrofit retrofit = RetrofitClient.getClient();
        apiService = retrofit.create(PedidoApiService.class);
        facturaApiService = retrofit.create(FacturaApiService.class);

        panelMatriz = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JScrollPane scroll = new JScrollPane(panelMatriz);

        JPanel panelContenido = new JPanel(new BorderLayout());
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(MARRON_MADERA, 2),
                ".............. Pedidos Para Despachar ..............",
                TitledBorder.CENTER,
                TitledBorder.ABOVE_TOP,
                new Font("Gabriola", Font.PLAIN, 36),
                Color.BLACK
        );
        panelContenido.setBorder(border);
        panelContenido.setBackground(ROSA_PASTEL);
        panelContenido.add(scroll, BorderLayout.CENTER);

        JButton btnAtras = new JButton("Atrás");
        JButton btnActualizar = new JButton("Actualizar Lista");
        JButton btnSiguiente = new JButton("Ver Facturas ➜");

        JButton[] navButtons = {btnAtras, btnActualizar, btnSiguiente};
        for (JButton b : navButtons) {
            b.setBackground(ROJO_TORII);
            b.setForeground(BLANCO_CREMOSO);
            b.setFont(new Font("Dialog", Font.BOLD, 14));
        }

        JPanel panelNavegacion = new JPanel();
        panelNavegacion.setBackground(ROSA_PASTEL);
        panelNavegacion.add(btnAtras);
        panelNavegacion.add(btnActualizar);
        panelNavegacion.add(btnSiguiente);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelContenido, BorderLayout.CENTER);
        getContentPane().add(panelNavegacion, BorderLayout.SOUTH);

        btnAtras.addActionListener(e -> {
            new Ventana_InicioSesionUser().setVisible(true);
            this.setVisible(false);
        });

        btnActualizar.addActionListener(e -> cargarPedidos());

        btnSiguiente.addActionListener(e -> {
            VentanaFacturasMesero ventana = new VentanaFacturasMesero(rolUsuario, clienteDoc);
            ventana.setVisible(true);
            this.setVisible(false);
        });

        cargarPedidos();
    }

    private void cargarPedidos() {
        panelMatriz.removeAll();

        try {
            Response<List<Pedido>> response = apiService.getAllPedidos().execute();
            if (response.isSuccessful() && response.body() != null) {
                mostrarPedidos(response.body());
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron pedidos.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener pedidos: " + e.getMessage());
        }

        panelMatriz.revalidate();
        panelMatriz.repaint();
    }

    private void mostrarPedidos(List<Pedido> pedidos) {
        for (Pedido pedido : pedidos) {
            String nombrePlato = obtenerNombrePlato(pedido);
            
            JButton boton = new JButton("Pedido #" + pedido.getId() + " - " + nombrePlato);
            boton.setFont(new Font("Dialog", Font.BOLD, 13));
            boton.setForeground(Color.BLACK);

            Estado estado = pedido.getEstado();
            switch (estado) {
                case PENDIENTE ->
                    boton.setBackground(pedido.isEsDomicilio() ? ROJO_TORII : MARRON_MADERA);
                case PREPARACION ->
                    boton.setBackground(VERDE_MECCHA);
                case FINALIZADO ->
                    boton.setBackground(GRIS_CLARO);
                default ->
                    boton.setBackground(BLANCO_CREMOSO);
            }

            boton.addActionListener(e -> mostrarDetallesPedido(pedido));
            panelMatriz.add(boton);
        }
    }

    private void mostrarDetallesPedido(Pedido pedido) {
        // ✅ Panel con mejor estructura y fuente más grande
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Título del pedido
        JLabel lblTitulo = new JLabel("=== PEDIDO #" + pedido.getId() + " ===");
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(15));
        
        // Platos
        JLabel lblPlatos = new JLabel("🍽️ Platos:");
        lblPlatos.setFont(new Font("Dialog", Font.BOLD, 15));
        panel.add(lblPlatos);
        panel.add(Box.createVerticalStrut(5));
        
        if (pedido.getItems() != null && !pedido.getItems().isEmpty()) {
            for (ItemPedido item : pedido.getItems()) {
                JLabel lblItem = new JLabel("  • " + item.getPlato().getNombre() 
                        + " x" + item.getCantidad() 
                        + " ($" + String.format("%.0f", item.getPrecioUnitario()) + ")");
                lblItem.setFont(new Font("Dialog", Font.PLAIN, 14));
                panel.add(lblItem);
            }
        } else {
            JLabel lblSinItems = new JLabel("  (Sin items)");
            lblSinItems.setFont(new Font("Dialog", Font.ITALIC, 14));
            panel.add(lblSinItems);
        }
        
        panel.add(Box.createVerticalStrut(15));
        
        // Modalidad
        String modalidadTexto = pedido.isEsDomicilio() ? "🏠 Domicilio" : "🍽️ Restaurante";
        JLabel lblModalidad = new JLabel("Modalidad: " + modalidadTexto);
        lblModalidad.setFont(new Font("Dialog", Font.BOLD, 14));
        panel.add(lblModalidad);
        panel.add(Box.createVerticalStrut(10));
        
        // Cliente
        if (pedido.getCliente() != null) {
            JLabel lblCliente = new JLabel("👤 Cliente: " + pedido.getCliente().getNombre());
            lblCliente.setFont(new Font("Dialog", Font.PLAIN, 14));
            panel.add(lblCliente);
            
            JLabel lblDoc = new JLabel("📋 Documento: " + pedido.getCliente().getDocumento());
            lblDoc.setFont(new Font("Dialog", Font.PLAIN, 14));
            panel.add(lblDoc);
        } else {
            JLabel lblSinCliente = new JLabel("Cliente: (Sin información)");
            lblSinCliente.setFont(new Font("Dialog", Font.ITALIC, 14));
            panel.add(lblSinCliente);
        }
        
        panel.add(Box.createVerticalStrut(10));
        
        // Estado
        JLabel lblEstado = new JLabel("📊 Estado actual: " + pedido.getEstado());
        lblEstado.setFont(new Font("Dialog", Font.BOLD, 15));
        panel.add(lblEstado);
        
        panel.add(Box.createVerticalStrut(20));

        // Botón Actualizar Estado
        JButton btnActualizar = new JButton("🔄 Actualizar Estado");
        btnActualizar.setBackground(VERDE_MECCHA);
        btnActualizar.setForeground(Color.BLACK);
        btnActualizar.setFont(new Font("Dialog", Font.BOLD, 14));
        btnActualizar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnActualizar);
        
        panel.add(Box.createVerticalStrut(10));

        // ✅ Botón Generar Factura (solo si está FINALIZADO)
        if (pedido.getEstado() == Estado.FINALIZADO) {
            JButton btnGenerarFactura = new JButton("💰 Generar Factura");
            btnGenerarFactura.setBackground(new Color(255, 193, 7));
            btnGenerarFactura.setForeground(Color.BLACK);
            btnGenerarFactura.setFont(new Font("Dialog", Font.BOLD, 14));
            btnGenerarFactura.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnGenerarFactura.addActionListener(e -> generarFactura(pedido));
            panel.add(btnGenerarFactura);
        }

        // ✅ VENTANA MÁS GRANDE CON SCROLL (500x500)
        JDialog dialog = new JDialog(this, "Detalles del Pedido", true);
        dialog.setLayout(new BorderLayout(10, 10));
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(500, 500));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.pack();
        dialog.setLocationRelativeTo(this);

        btnActualizar.addActionListener(e -> {
            Estado nuevoEstado = seleccionarNuevoEstado(pedido.getEstado());
            if (nuevoEstado != null) {
                actualizarEstadoPedido(pedido.getId(), nuevoEstado);
                dialog.dispose();
                cargarPedidos();
            }
        });

        dialog.setVisible(true);
    }

    private void generarFactura(Pedido pedido) {
        // Verificar si ya tiene factura
        try {
            String clienteDoc = pedido.getCliente().getDocumento();
            Response<Factura> responseCheck = facturaApiService.getFacturaByPedidoYUsuario(
                    pedido.getId(), clienteDoc).execute();
            
            if (responseCheck.isSuccessful() && responseCheck.body() != null) {
                JOptionPane.showMessageDialog(this, 
                    "⚠️ Este pedido ya tiene una factura generada\n" +
                    "ID Factura: " + responseCheck.body().getFacturaid(),
                    "Factura Existente",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (IOException e) {
            // Continuar si no se encuentra factura (es lo esperado)
        }

        // ✅ CORRECCIÓN: Crear factura usando Map con los datos correctos
        try {
            java.util.Map<String, Object> facturaData = new java.util.HashMap<>();
            facturaData.put("usuarioDoc", pedido.getCliente().getDocumento());
            facturaData.put("pedidoId", pedido.getId());

            Response<Factura> response = facturaApiService.createFactura(facturaData).execute();

            if (response.isSuccessful() && response.body() != null) {
                Factura factura = response.body();
                JOptionPane.showMessageDialog(this, 
                    "✅ Factura generada exitosamente\n\n" +
                    "ID Factura: " + factura.getFacturaid() + "\n" +
                    "Total: $" + String.format("%.2f", factura.getTotal()) + "\n" +
                    "Cliente: " + factura.getUsuario().getNombre(),
                    "Factura Generada",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                String errorBody = "";
                try {
                    errorBody = response.errorBody() != null ? response.errorBody().string() : "";
                } catch (Exception ex) {}
                
                JOptionPane.showMessageDialog(this, 
                    "❌ Error al generar factura\n" +
                    "Código: " + response.code() + "\n" +
                    "Detalle: " + errorBody,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "❌ Error de conexión al generar factura:\n" + e.getMessage(),
                "Error de Conexión",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private Estado seleccionarNuevoEstado(Estado estadoActual) {
        Estado[] opciones = switch (estadoActual) {
            case PENDIENTE ->
                new Estado[]{Estado.PREPARACION};
            case PREPARACION ->
                new Estado[]{Estado.FINALIZADO};
            default ->
                new Estado[]{};
        };

        return (Estado) JOptionPane.showInputDialog(
                this,
                "Selecciona el nuevo estado:",
                "Actualizar Estado",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                estadoActual
        );
    }

    private void actualizarEstadoPedido(int id, Estado nuevoEstado) {
        try {
            EstadoUpdateDTO dto = new EstadoUpdateDTO(nuevoEstado);
            Call<Pedido> call = apiService.updateEstado(id, dto);
            Response<Pedido> response = call.execute();

            if (response.isSuccessful()) {
                JOptionPane.showMessageDialog(this, 
                    "✅ Estado actualizado correctamente a " + nuevoEstado,
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "⚠️ Error al actualizar el estado (código: " + response.code() + ")",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "❌ Error al conectar con el servidor: " + e.getMessage(),
                "Error de Conexión",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String obtenerNombrePlato(Pedido pedido) {
        if (pedido.getItems() == null || pedido.getItems().isEmpty()) {
            return "Sin platos";
        }
        
        if (pedido.getItems().size() == 1) {
            return pedido.getItems().get(0).getPlato().getNombre();
        } else {
            return pedido.getItems().get(0).getPlato().getNombre() 
                    + " (+" + (pedido.getItems().size() - 1) + " más)";
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
