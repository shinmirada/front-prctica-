package vistas;

import apiService.PlatoApiService;
import enums.Rol;
import modelo.Plato;
import retrofit2.Response;
import retrofit2.Retrofit;
import util.RetrofitClient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class VentanaAdminPlatos extends javax.swing.JFrame {

    private PlatoApiService apiService;
    private JTable tblPlatos;
    private JTextField txtNombre, txtDescripcion, txtPrecio;
    private JButton btnAgregar, btnActualizar, btnLimpiar, btnRefrescar;
    private Rol rolUsuario;
    private String adminDoc;
    private Integer platoSeleccionadoId = null;

    private static final Color ROSA_PASTEL = new Color(0xF9, 0xC5, 0xD5);
    private static final Color BLANCO_CREMOSO = new Color(0xFF, 0xF8, 0xF0);
    private static final Color ROJO_TORII = new Color(0xE8, 0x4A, 0x5F);
    private static final Color MARRON_MADERA = new Color(139, 94, 60);
    private static final Color VERDE_MECCHA = new Color(168, 198, 134);

    public VentanaAdminPlatos(Rol rolUsuario, String adminDoc) {
        this.rolUsuario = rolUsuario;
        this.adminDoc = adminDoc;

        setTitle("Panel de Administración - Platos");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Retrofit retrofit = RetrofitClient.getClient();
        apiService = retrofit.create(PlatoApiService.class);

        initCustomComponents();
        cargarPlatos();
    }

    private void initCustomComponents() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(ROSA_PASTEL);
        panelPrincipal.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(MARRON_MADERA, 2),
                ".............. Administración de Platos ..............",
                javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.ABOVE_TOP,
                new Font("Gabriola", Font.PLAIN, 36),
                Color.BLACK
        ));

        // ===== PANEL FORMULARIO (IZQUIERDA) =====
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(ROSA_PASTEL);
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Plato"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Dialog", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(lblNombre, gbc);

        txtNombre = new JTextField(20);
        gbc.gridx = 1;
        panelFormulario.add(txtNombre, gbc);

        // Descripción
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("Dialog", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(lblDescripcion, gbc);

        txtDescripcion = new JTextField(20);
        gbc.gridx = 1;
        panelFormulario.add(txtDescripcion, gbc);

        // Precio
        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setFont(new Font("Dialog", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulario.add(lblPrecio, gbc);

        txtPrecio = new JTextField(20);
        gbc.gridx = 1;
        panelFormulario.add(txtPrecio, gbc);

        // Botones CRUD (solo 3 botones ahora)
        JPanel panelBotonesCRUD = new JPanel(new GridLayout(1, 3, 10, 10));
        panelBotonesCRUD.setOpaque(false);

        btnAgregar = new JButton("➕ Agregar");
        btnAgregar.setBackground(VERDE_MECCHA);
        btnAgregar.setFont(new Font("Dialog", Font.BOLD, 13));
        btnAgregar.addActionListener(e -> agregarPlato());

        btnActualizar = new JButton("✏️ Actualizar");
        btnActualizar.setBackground(new Color(255, 193, 7));
        btnActualizar.setFont(new Font("Dialog", Font.BOLD, 13));
        btnActualizar.addActionListener(e -> actualizarPlato());
        btnActualizar.setEnabled(false);

        btnLimpiar = new JButton("🔄 Limpiar");
        btnLimpiar.setBackground(MARRON_MADERA);
        btnLimpiar.setForeground(BLANCO_CREMOSO);
        btnLimpiar.setFont(new Font("Dialog", Font.BOLD, 13));
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        panelBotonesCRUD.add(btnAgregar);
        panelBotonesCRUD.add(btnActualizar);
        panelBotonesCRUD.add(btnLimpiar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panelFormulario.add(panelBotonesCRUD, gbc);

        panelPrincipal.add(panelFormulario, BorderLayout.WEST);

        // ===== PANEL TABLA (CENTRO) =====
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBackground(ROSA_PASTEL);

        String[] columnas = {"ID", "Nombre", "Descripción", "Precio"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblPlatos = new JTable(model);
        tblPlatos.setFont(new Font("Dialog", Font.PLAIN, 13));
        tblPlatos.setRowHeight(25);
        tblPlatos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarPlatoSeleccionado();
            }
        });

        JScrollPane scroll = new JScrollPane(tblPlatos);
        panelTabla.add(scroll, BorderLayout.CENTER);

        btnRefrescar = new JButton("🔄 Refrescar Lista");
        btnRefrescar.setBackground(VERDE_MECCHA);
        btnRefrescar.setFont(new Font("Dialog", Font.BOLD, 14));
        btnRefrescar.addActionListener(e -> cargarPlatos());
        panelTabla.add(btnRefrescar, BorderLayout.SOUTH);

        panelPrincipal.add(panelTabla, BorderLayout.CENTER);

        // ===== PANEL NAVEGACIÓN (ABAJO) =====
        JPanel panelNavegacion = new JPanel();
        panelNavegacion.setBackground(ROSA_PASTEL);

        JButton btnRegresar = new JButton("← Cerrar Sesión");
        btnRegresar.setBackground(ROJO_TORII);
        btnRegresar.setForeground(BLANCO_CREMOSO);
        btnRegresar.setFont(new Font("Dialog", Font.BOLD, 14));
        btnRegresar.addActionListener(e -> {
            new Ventana_InicioSesionUser().setVisible(true);
            this.dispose();
        });
        panelNavegacion.add(btnRegresar);

        panelPrincipal.add(panelNavegacion, BorderLayout.SOUTH);

        getContentPane().add(panelPrincipal);
    }

    private void cargarPlatos() {
        try {
            Response<List<Plato>> response = apiService.getAllPlatos().execute();
            if (response.isSuccessful() && response.body() != null) {
                mostrarPlatosEnTabla(response.body());
            } else {
                JOptionPane.showMessageDialog(this, "Error al cargar platos");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage());
        }
    }

    private void mostrarPlatosEnTabla(List<Plato> platos) {
        DefaultTableModel model = (DefaultTableModel) tblPlatos.getModel();
        model.setRowCount(0);

        for (Plato p : platos) {
            model.addRow(new Object[]{
                p.getId(),
                p.getNombre(),
                p.getDescripcion(),
                "$" + String.format("%.0f", p.getPrecio())
            });
        }
    }

    private void cargarPlatoSeleccionado() {
        int fila = tblPlatos.getSelectedRow();
        if (fila == -1) return;

        platoSeleccionadoId = (Integer) tblPlatos.getValueAt(fila, 0);
        txtNombre.setText((String) tblPlatos.getValueAt(fila, 1));
        txtDescripcion.setText((String) tblPlatos.getValueAt(fila, 2));
        
        String precioStr = ((String) tblPlatos.getValueAt(fila, 3)).replace("$", "");
        txtPrecio.setText(precioStr);

        btnAgregar.setEnabled(false);
        btnActualizar.setEnabled(true);
    }

    private void agregarPlato() {
        if (!validarCampos()) return;

        // ✅ Crear Plato sin establecer ID (quedará como null)
        Plato plato = new Plato();
        plato.setNombre(txtNombre.getText().trim());
        plato.setDescripcion(txtDescripcion.getText().trim());
        plato.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
        
        // ✅ CRÍTICO: Asegurarse que el ID sea null
        plato.setId(null);

        try {
            Response<Plato> response = apiService.createPlato(plato).execute();
            if (response.isSuccessful()) {
                JOptionPane.showMessageDialog(this, "✅ Plato agregado correctamente");
                limpiarFormulario();
                cargarPlatos();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al agregar plato: " + response.code());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage());
        }
    }

    private void actualizarPlato() {
        if (platoSeleccionadoId == null || !validarCampos()) return;

        Plato plato = new Plato();
        plato.setDescripcion(txtDescripcion.getText().trim());
        plato.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));

        try {
            Response<Plato> response = apiService.cambiarDatos(platoSeleccionadoId, plato).execute();
            if (response.isSuccessful()) {
                JOptionPane.showMessageDialog(this, "✅ Plato actualizado correctamente");
                limpiarFormulario();
                cargarPlatos();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al actualizar plato");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage());
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty() ||
            txtDescripcion.getText().trim().isEmpty() ||
            txtPrecio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return false;
        }

        try {
            Double.parseDouble(txtPrecio.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio debe ser numérico");
            return false;
        }

        return true;
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        platoSeleccionadoId = null;
        tblPlatos.clearSelection();

        btnAgregar.setEnabled(true);
        btnActualizar.setEnabled(false);
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
