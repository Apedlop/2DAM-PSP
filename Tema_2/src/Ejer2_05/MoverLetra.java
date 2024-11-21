package Ejer2_05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MoverLetra extends JFrame {

    private JButton botonCambioEstado; // Botón para detener/reanudar el hilo
    private MoverLetraHilo hilo; // Instancia del hilo que mueve la letra

    public MoverLetra() {
        setTitle("Letra en Movimiento");
        setSize(800, 400); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar ventana al salir
        setLocationRelativeTo(null); // Centrar la ventana

        // Crear un botón para detener/reanudar el hilo
        botonCambioEstado = new JButton("Iniciar Hilo");
        botonCambioEstado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        add(botonCambioEstado, BorderLayout.SOUTH); // Agrega el botón al borde sur de la ventana
    }

    @Override
    public void actionPerformed(MoverLetraHilo hilo) {
        if (hilo.isHiloActivo()) { // Si el hilo está activo, lo detenemos
            hilo.detener();
            botonCambioEstado.setText("Finalizar Hilo");
        } else { // Si el hilo no está activo, lo iniciamos
            hilo.iniciar();
            botonCambioEstado.setText("Reanudar Hilo");
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (hilo != null) {
            // Dibuja la letra en la posición (x, y)
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("A", hilo.getX(), hilo.getY());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MoverLetra ventana = new MoverLetra(); // Crea la ventana
                ventana.hilo = new MoverLetraHilo(ventana); // Crea el hilo
                ventana.setVisible(true); // Muestra la ventana
            }
        });
    }
}
