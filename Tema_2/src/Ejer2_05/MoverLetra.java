package Ejer2_05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MoverLetra extends JFrame implements ActionListener {

    private JButton boton; // Botón para detener/reanudar el hilo
    private MoverLetraHilo hilo; // Instancia del hilo que mueve la letra

    // Constructor de la clase MoverLetra, que configura la ventana
    public MoverLetra() {
        setTitle("Letra en Movimiento");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Crear un botón para iniciar o detener el hilo
        boton = new JButton("Iniciar Hilo");
        boton.setBounds(125, 20, 150, 30);
        boton.addActionListener(this);
        add(boton);

        // Crear el hilo, pero no lo iniciamos hasta que el botón sea presionado
        hilo = new MoverLetraHilo(this); // Se crea la instancia del hilo pasándole la ventana
    }

    // Método que maneja los eventos del botón (acción de clic)
    @Override
    public void actionPerformed(ActionEvent e) {
        if (hilo.isHiloActivo()) { // Si el hilo está activo, lo detenemos
            hilo.detener(); // Llama al método detener() de la clase MoverLetraHilo
            boton.setText("Iniciar Hilo");
        } else { // Si el hilo no está activo, lo iniciamos
            hilo.iniciar(); // Llama al método iniciar() de la clase MoverLetraHilo
            boton.setText("Detener Hilo");
        }
    }

    // Método para pintar los elementos gráficos en la ventana
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Llama al método paint() de la clase JFrame para limpiar el área de dibujo
        if (hilo != null) {
            // Dibuja la letra "o" en la posición (x, y) proporcionada por el hilo
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("o", hilo.getX(), hilo.getY());
        }
    }

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        MoverLetra app = new MoverLetra(); // Crea una instancia de la ventana
        app.setVisible(true); // Hace visible la ventana
    }
}
