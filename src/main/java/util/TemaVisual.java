/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MI PC
 */
public class TemaVisual {
     // Color de fondo global (por defecto)
    private static Color colorFondo = new Color(250, 218, 221);

    // Lista de ventanas que deben actualizarse al cambiar el color
    private static final List<ActualizaTemaVentanas> ventanas = new ArrayList<>();

    // Registrar una ventana en la lista
    public static void registrarVentana(ActualizaTemaVentanas ventana) {
        if (!ventanas.contains(ventana)) {
            ventanas.add(ventana);
        }
    }

    // Cambiar color global y notificar a todas las ventanas
    public static void cambiarColor(Color nuevoColor) {
        colorFondo = nuevoColor;
        for (ActualizaTemaVentanas v : ventanas) {
            v.aplicarColor(nuevoColor);
        }
    }

    public static Color getColorFondo() {
        return colorFondo;
    }
}
