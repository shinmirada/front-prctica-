package vistas;

import apiService.PedidoApiService;
import enums.Rol;
import modelo.Pedido;
import retrofit2.Response;
import retrofit2.Retrofit;
import util.RetrofitClient;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import modelo.ItemPedido;

public class VentanaMisPedidos extends javax.swing.JFrame {

    private JPanel panelMatriz;
    private PedidoApiService apiService;
    private String clienteDoc;
     private Rol rolUsuario;
     

    private static final Color ROSA_PASTEL = new Color(0xF9, 0xC5, 0xD5);
    private static final Color BLANCO_CREMOSO = new Color(0xFF, 0xF8, 0xF0);
    private static final Color ROJO_TORII = new Color(0xE8, 0x4A, 0x5F);
    private final Color MARRON_MADERA = new Color(139, 94, 60);


    public VentanaMisPedidos(Rol rolUsuario,String clienteDoc) {
        this.clienteDoc = clienteDoc;
        this.rolUsuario=rolUsuario;

        setTitle("Mis Pedidos");
        setSize(600, 500);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        panelMatriz = new JPanel();
        panelMatriz.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JScrollPane scroll = new JScrollPane(panelMatriz);

        JPanel panelContenido = new JPanel(new BorderLayout());
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(MARRON_MADERA, 2),
                ".............. Historial de Pedidos ..............",
                TitledBorder.CENTER,
                TitledBorder.ABOVE_TOP,
                new Font("Gabriola", Font.PLAIN, 36),
                Color.BLACK
        );
        panelContenido.setBorder(border);
        panelContenido.setBackground(ROSA_PASTEL);
        panelContenido.add(scroll, BorderLayout.CENTER);

        JButton btnAtras = new JButton("Atrás");
        JButton btnSiguiente = new JButton("Siguiente");

        JButton[] navButtons = {btnAtras, btnSiguiente};
        for (JButton b : navButtons) {
            b.setBackground(ROJO_TORII);
            b.setForeground(BLANCO_CREMOSO);
            b.setFont(new Font("Dialog", Font.BOLD, 14));
        }

        JPanel panelNavegacion = new JPanel();
        panelNavegacion.setBackground(ROSA_PASTEL);
        panelNavegacion.add(btnAtras);
        panelNavegacion.add(btnSiguiente);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelContenido, BorderLayout.CENTER);
        getContentPane().add(panelNavegacion, BorderLayout.SOUTH);

       
        Retrofit retrofit = RetrofitClient.getClient();
        apiService = retrofit.create(PedidoApiService.class);

       
        cargarPedidosCliente();

   
        btnAtras.addActionListener(e -> {
            VentanaDeOrden ventana = new VentanaDeOrden(rolUsuario, clienteDoc);
            ventana.setVisible(true);
            this.setVisible(false);
                    });
        
        btnSiguiente.addActionListener(e -> {
          Ventana_Facturas ventana= new Ventana_Facturas(rolUsuario, clienteDoc);
          ventana.setVisible(true);
            this.setVisible(false);
        });
    }

    private void cargarPedidosCliente() {
        try {
            Response<List<Pedido>> response = apiService.getPedidosByCliente(clienteDoc).execute();
            if (response.isSuccessful() && response.body() != null) {
                List<Pedido> pedidos = response.body();
                mostrarPedidos(pedidos);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron pedidos para este cliente.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error de conexión con el servidor: " + e.getMessage());
        }
    }
    
    
    private void mostrarPedidos(List<Pedido> pedidos) {
        panelMatriz.removeAll(); 

        for (Pedido pedido : pedidos) {
            // ✅ Obtener nombre del plato desde items
            String nombrePlato = obtenerNombrePlato(pedido);
            
            JButton boton = new JButton("Pedido #" + pedido.getId() + " - " + nombrePlato);
            boton.setBackground(BLANCO_CREMOSO);
            boton.setForeground(Color.BLACK);
            boton.setFont(new Font("Dialog", Font.PLAIN, 14));

            if (pedido.isEsDomicilio()) {
                boton.setBackground(ROJO_TORII); 
            } else {
                boton.setBackground(MARRON_MADERA); 
            }
             
            boton.addActionListener(e -> {
                StringBuilder detalles = new StringBuilder();
                detalles.append("=== PEDIDO #").append(pedido.getId()).append(" ===\n\n");
                
                if (pedido.getItems() != null && !pedido.getItems().isEmpty()) {
                    detalles.append("PLATOS:\n");
                    for (ItemPedido item : pedido.getItems()) {
                        detalles.append("• ").append(item.getPlato().getNombre())
                                .append(" x").append(item.getCantidad())
                                .append(" ($").append(item.getPrecioUnitario()).append(")\n");
                    }
                    detalles.append("\n");
                }
                
                detalles.append("Estado: ").append(pedido.getEstado()).append("\n");
                detalles.append("Modalidad: ").append(pedido.isEsDomicilio() ? "🏠 Domicilio" : "🍽️ Restaurante").append("\n");
                
                // ✅ CORRECTO: Acceder al cliente completo
                if (pedido.getCliente() != null) {
                    detalles.append("Cliente: ").append(pedido.getCliente().getNombre()).append("\n");
                    detalles.append("Documento: ").append(pedido.getCliente().getDocumento());
                }
                
                JOptionPane.showMessageDialog(this, detalles.toString());
            });

            panelMatriz.add(boton);
        }

        panelMatriz.revalidate();
        panelMatriz.repaint();
    }
    
 // ✅ Método auxiliar para obtener nombre del plato
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
        setTitle("Ventana Pedidos Cliente");

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
