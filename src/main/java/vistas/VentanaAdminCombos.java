package vistas;

import apiService.PlatoApiService;
import enums.Estado;
import enums.Rol;
import modelo.Plato;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import util.RetrofitClient;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class VentanaAdminCombos extends javax.swing.JFrame implements util.ActualizaTemaVentanas {

    private JPanel panelMatriz;
    private PlatoApiService apiService;
    
    // Agregamos aquí los botones como atributos de clase:
    private JButton btnAtras;
    private JButton btnActualizar;
    private JButton btnAgregar;
    private JButton btnEditar;
    private JButton btnEliminar;

    private static final Color ROSA_PASTEL = new Color(0xF9, 0xC5, 0xD5);
    private static final Color BLANCO_CREMOSO = new Color(0xFF, 0xF8, 0xF0);
    private static final Color ROJO_TORII = new Color(0xE8, 0x4A, 0x5F);
    private static final Color MARRON_MADERA = new Color(139, 94, 60);
    private static final Color VERDE_MECCHA = new Color(168, 198, 134);
    private static final Color GRIS_CLARO = new Color(187, 187, 187);

    public VentanaAdminCombos(Rol rolUsuario, String clienteDoc) {
        setTitle("Gestión de Platos (Administrador)");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        // Registrar esta ventana en el manejador de temas
        util.TemaVisual.registrarVentana(this);

        // Aplicar color actual al iniciar
        aplicarColor(util.TemaVisual.getColorFondo());

        Retrofit retrofit = RetrofitClient.getClient();
        apiService = retrofit.create(PlatoApiService.class);

        panelMatriz = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JScrollPane scroll = new JScrollPane(panelMatriz);

        JPanel panelContenido = new JPanel(new BorderLayout());
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(MARRON_MADERA, 2),
                ".............. Gestión de Platos ..............",
                TitledBorder.CENTER,
                TitledBorder.ABOVE_TOP,
                new Font("Gabriola", Font.PLAIN, 36),
                Color.BLACK
        );
        panelContenido.setBorder(border);
        panelContenido.setBackground(ROSA_PASTEL);
        panelContenido.add(scroll, BorderLayout.CENTER);

        // Botones CRUD
        JButton btnAtras = new JButton("Atrás");
        JButton btnActualizar = new JButton("Actualizar Lista");
        JButton btnAgregar = new JButton("Agregar Plato");
        JButton btnEditar = new JButton("Editar Plato");
        JButton btnEliminar = new JButton("Eliminar Plato");

        JButton[] botones = {btnAtras, btnActualizar, btnAgregar, btnEditar, btnEliminar};
        for (JButton b : botones) {
            b.setBackground(ROJO_TORII);
            b.setForeground(BLANCO_CREMOSO);
            b.setFont(new Font("Dialog", Font.BOLD, 14));
        }

        JPanel panelNavegacion = new JPanel();
        panelNavegacion.setBackground(ROSA_PASTEL);
        panelNavegacion.add(btnAtras);
        panelNavegacion.add(btnActualizar);
        panelNavegacion.add(btnAgregar);
        panelNavegacion.add(btnEditar);
        panelNavegacion.add(btnEliminar);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelContenido, BorderLayout.CENTER);
        getContentPane().add(panelNavegacion, BorderLayout.SOUTH);

        btnAtras.addActionListener(e -> {
            new Ventana_InicioSesionUser().setVisible(true);
            this.setVisible(false);
        });

        btnActualizar.addActionListener(e -> cargarPlatos());

        btnAgregar.addActionListener(e -> agregarPlato());

        btnEditar.addActionListener(e -> editarPlato());

        btnEliminar.addActionListener(e -> eliminarPlato());

        cargarPlatos();
    }

    @Override
    public void aplicarColor(Color nuevoColor) {
        panelMatriz.setBackground(nuevoColor);
        btnAtras.setBackground(nuevoColor.darker());
        btnActualizar.setBackground(nuevoColor.darker());
        btnAgregar.setBackground(nuevoColor.darker());
        btnEditar.setBackground(nuevoColor.darker());
        btnEliminar.setBackground(nuevoColor.darker());
    }
    
    private void cargarPlatos() {
        panelMatriz.removeAll();
        try {
            Response<List<Plato>> response = apiService.getAllPlatos().execute();
            if (response.isSuccessful() && response.body() != null) {
                mostrarPlatos(response.body());
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron platos.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener platos: " + e.getMessage());
        }

        panelMatriz.revalidate();
        panelMatriz.repaint();
    }

    private void mostrarPlatos(List<Plato> platos) {
        for (Plato plato : platos) {
            JButton boton = new JButton("ID " + plato.getId() + ": " + plato.getNombre());
            boton.setFont(new Font("Dialog", Font.BOLD, 13));
            boton.setBackground(BLANCO_CREMOSO);
            boton.setForeground(Color.BLACK);

            boton.addActionListener(e -> mostrarDetallesPlato(plato));
            panelMatriz.add(boton);
        }
    }

    private void mostrarDetallesPlato(Plato plato) {
        JOptionPane.showMessageDialog(this,
                "ID: " + plato.getId()
                + "\nNombre: " + plato.getNombre()
                + "\nDescripción: " + plato.getDescripcion()
                + "\nPrecio: $" + plato.getPrecio(),
                "Detalles del Plato",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void agregarPlato() {
        JTextField nombre = new JTextField();
        JTextField descripcion = new JTextField();
        JTextField precio = new JTextField();

        Object[] campos = {
            "Nombre:", nombre,
            "Descripción:", descripcion,
            "Precio:", precio
        };

        int opcion = JOptionPane.showConfirmDialog(this, campos, "Agregar Plato", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            try {
                Plato nuevo = new Plato();
                nuevo.setNombre(nombre.getText());
                nuevo.setDescripcion(descripcion.getText());
                nuevo.setPrecio(Double.parseDouble(precio.getText()));

                Call<Plato> call = apiService.createPlato(nuevo);
                Response<Plato> response = call.execute();

                if (response.isSuccessful()) {
                    JOptionPane.showMessageDialog(this, "Plato agregado correctamente.");
                    cargarPlatos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar el plato (" + response.code() + ")");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void editarPlato() {
        String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID del plato a editar:");
        if (idStr == null || idStr.isEmpty()) {
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            Response<Plato> response = apiService.getPlatoById(id).execute();

            if (response.isSuccessful() && response.body() != null) {
                Plato plato = response.body();

                JTextField nombre = new JTextField(plato.getNombre());
                JTextField descripcion = new JTextField(plato.getDescripcion());
                JTextField precio = new JTextField(String.valueOf(plato.getPrecio()));

                Object[] campos = {
                    "Nombre:", nombre,
                    "Descripción:", descripcion,
                    "Precio:", precio
                };

                int opcion = JOptionPane.showConfirmDialog(this, campos, "Editar Plato", JOptionPane.OK_CANCEL_OPTION);
                if (opcion == JOptionPane.OK_OPTION) {
                    plato.setNombre(nombre.getText());
                    plato.setDescripcion(descripcion.getText());
                    plato.setPrecio(Double.parseDouble(precio.getText()));

                    Response<Plato> updateResponse = apiService.cambiarDatos(id, plato).execute();
                    if (updateResponse.isSuccessful()) {
                        JOptionPane.showMessageDialog(this, "Plato actualizado correctamente.");
                        cargarPlatos();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al actualizar (" + updateResponse.code() + ")");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el plato con ID " + id);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void eliminarPlato() {
        String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID del plato a eliminar:");
        if (idStr == null || idStr.isEmpty()) {
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            Response<Void> response = apiService.deletePlato(id).execute();

            if (response.isSuccessful()) {
                JOptionPane.showMessageDialog(this, "Plato eliminado correctamente.");
                cargarPlatos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el plato (" + response.code() + ")");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
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
