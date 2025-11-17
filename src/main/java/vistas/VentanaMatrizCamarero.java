package vistas;

import apiService.PedidoApiService;
import enums.Estado;
import enums.Rol;
import modelo.EstadoUpdateDTO;
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
        setLocationRelativeTo(null);

        Retrofit retrofit = RetrofitClient.getClient();
        apiService = retrofit.create(PedidoApiService.class);

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
        JButton btnSiguiente = new JButton("Siguiente ➜");

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
            Ventana_Facturas ventana = new Ventana_Facturas(rolUsuario, clienteDoc);
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
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        
        panel.add(new JLabel("=== PEDIDO #" + pedido.getId() + " ==="));
        panel.add(new JLabel(" "));
        panel.add(new JLabel("Platos:"));
        
        if (pedido.getItems() != null && !pedido.getItems().isEmpty()) {
            for (ItemPedido item : pedido.getItems()) {
                panel.add(new JLabel("  • " + item.getPlato().getNombre() 
                        + " x" + item.getCantidad() 
                        + " ($" + item.getPrecioUnitario() + ")"));
            }
        } else {
            panel.add(new JLabel("  (Sin items)"));
        }
        
        panel.add(new JLabel(" "));
        panel.add(new JLabel("Modalidad: " + (pedido.isEsDomicilio() ? "🏠 Domicilio" : "🍽️ Restaurante")));
        
        if (pedido.getCliente() != null) {
            panel.add(new JLabel("Cliente: " + pedido.getCliente().getNombre()));
            panel.add(new JLabel("Documento: " + pedido.getCliente().getDocumento()));
        } else {
            panel.add(new JLabel("Cliente: (Sin información)"));
        }
        
        panel.add(new JLabel("Estado actual: " + pedido.getEstado()));

        JButton btnActualizar = new JButton("Actualizar Estado");
        btnActualizar.setBackground(VERDE_MECCHA);
        btnActualizar.setForeground(Color.BLACK);

        panel.add(btnActualizar);

        JDialog dialog = new JDialog(this, "Detalles del Pedido", true);
        dialog.add(panel);
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
            // ✅ Crear DTO type-safe
            EstadoUpdateDTO dto = new EstadoUpdateDTO(nuevoEstado);
            
            Call<Pedido> call = apiService.updateEstado(id, dto);
            Response<Pedido> response = call.execute();

            if (response.isSuccessful()) {
                JOptionPane.showMessageDialog(this, 
                    "✅ Estado actualizado correctamente a " + nuevoEstado);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "⚠️ Error al actualizar el estado (código: " + response.code() + ")");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "❌ Error al conectar con el servidor: " + e.getMessage());
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
