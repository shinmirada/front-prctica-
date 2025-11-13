/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class VentanaPlatos extends javax.swing.JFrame {

    private final Color ROSA_SAKURA = new Color(250, 218, 221);
    private final Color ROJO_TORII = new Color(232, 74, 95);
    private final Color BLANCO_CREMOSO = new Color(255, 248, 240);

    public VentanaPlatos(String nombre, String descripcion, String precio) {
        // ✅ PRIMERO: initComponents
        initComponents();

        // ✅ SEGUNDO: Configurar ventana
        setTitle("Detalle del Plato");
        setSize(1000, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ✅ TERCERO: Crear componentes personalizados
        JLabel lblTitulo = new JLabel(nombre, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(ROSA_SAKURA);
        lblTitulo.setForeground(ROJO_TORII);

        JTextArea txtDescripcion = new JTextArea("Descripción: " + descripcion + "\n\nPrecio: " + precio);
        txtDescripcion.setEditable(false);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setFont(new Font("Arial", Font.PLAIN, 14));
        txtDescripcion.setBackground(BLANCO_CREMOSO);

        JScrollPane scroll = new JScrollPane(txtDescripcion);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(ROJO_TORII);
        btnCerrar.setForeground(BLANCO_CREMOSO);
        btnCerrar.setFont(new Font("Dialog", Font.BOLD, 12));
        btnCerrar.addActionListener(e -> dispose());

        getContentPane().setBackground(ROSA_SAKURA);
        add(lblTitulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(btnCerrar, BorderLayout.SOUTH);
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
