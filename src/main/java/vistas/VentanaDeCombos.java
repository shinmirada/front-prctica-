/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;


import apiService.PlatoApiService;
import enums.Rol;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List; 
import javax.swing.border.TitledBorder;
import modelo.Plato;
import retrofit2.Response;
import retrofit2.Retrofit;
import util.RetrofitClient;

/**
 *
 * @author Dilan
 */
public class VentanaDeCombos extends javax.swing.JFrame {

    private PlatoApiService apiService;
    private List<Plato> listaPlatos;
    private JButton[][] botones;
    private JPanel panelBotones;
    private JButton btnSiguiente, btnAtras;
    private String ClienteDoc;
    private Rol rolUsuario;
    private final Color ROSA_SAKURA = new Color(250, 218, 221);
    private final Color ROJO_TORII = new Color(232, 74, 95);
    private final Color BLANCO_CREMOSO = new Color(255, 248, 240);
    private final Color MARRON_MADERA = new Color(139, 94, 60);
    

    public VentanaDeCombos(Rol rolUsuario,String ClienteDoc) {
        this.ClienteDoc=ClienteDoc;
        this.rolUsuario=rolUsuario;
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Ventana Combos");
        initCustomComponents();
        setLocationRelativeTo(null);

        
        Retrofit retrofit = RetrofitClient.getClient();
        apiService = retrofit.create(PlatoApiService.class);

     
        cargarPlatosDesdeServidor();
    }

    private void initCustomComponents() {
        panelBotones = new JPanel();

        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(MARRON_MADERA),
                ".............. Menú de Platos ..............",
                TitledBorder.CENTER,
                TitledBorder.ABOVE_TOP,
                new Font("Gabriola", Font.PLAIN, 36),
                Color.BLACK
        );
        panelBotones.setBorder(border);
        panelBotones.setBackground(ROSA_SAKURA);

        btnAtras = new JButton("Atrás");
        btnSiguiente = new JButton("Siguiente");

        JButton[] navButtons = {btnAtras, btnSiguiente};
        for (JButton b : navButtons) {
            b.setBackground(ROJO_TORII);
            b.setForeground(BLANCO_CREMOSO);
            b.setFont(new Font("Dialog", Font.BOLD, 12));
        }

        JPanel panelNavegacion = new JPanel();
        panelNavegacion.setBackground(ROSA_SAKURA);
        panelNavegacion.add(btnAtras);
        panelNavegacion.add(btnSiguiente);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelBotones, BorderLayout.CENTER);
        getContentPane().add(panelNavegacion, BorderLayout.SOUTH);

        btnAtras.addActionListener(e -> {
            Ventana_InicioSesionUser ventana = new Ventana_InicioSesionUser();
            ventana.setVisible(true);
            this.setVisible(false);
        });

        btnSiguiente.addActionListener(e -> {
             VentanaDeOrden ventana = new VentanaDeOrden(rolUsuario, ClienteDoc);
            ventana.setVisible(true);
            this.setVisible(false);
        });
    }

  
    private void cargarPlatosDesdeServidor() {
        try {
            Response<List<Plato>> response = apiService.getAllPlatos().execute();

            if (response.isSuccessful() && response.body() != null) {
                listaPlatos = response.body();
                dibujarBotonesDinamicos(listaPlatos);
            } else {
                JOptionPane.showMessageDialog(this, "Error al obtener platos: " + response.code());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error de conexión con el servidor");
            e.printStackTrace();
        }
    }

 
    private void dibujarBotonesDinamicos(List<Plato> platos) {
        panelBotones.removeAll();

        int total = platos.size();
        int filas = (int) Math.ceil(total / 3.0); 
        botones = new JButton[filas][3];
        panelBotones.setLayout(new GridLayout(filas, 3, 10, 10));

        int index = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < 3; j++) {
                if (index >= platos.size()) break;

                Plato plato = platos.get(index);
                JButton btnPlato = new JButton(plato.getNombre());
                btnPlato.setBackground(BLANCO_CREMOSO);
                btnPlato.setFont(new Font("Dialog", Font.PLAIN, 12));

                btnPlato.addActionListener(evt -> {
                    new VentanaPlatos(
                            plato.getNombre(),
                            plato.getDescripcion(),
                            "$" + plato.getPrecio()
                    ).setVisible(true);
                });

                panelBotones.add(btnPlato);
                botones[i][j] = btnPlato;
                index++;
            }
        }

        panelBotones.revalidate();
        panelBotones.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana Combos\n");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 432, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
