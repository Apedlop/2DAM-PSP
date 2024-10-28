package Act2_03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame implements ActionListener {
    HiloContador hilo1, hilo2;
    private JButton b1, b2; // Botones del JFrame

    public Principal() {
        // Configuración de la ventana principal
        setTitle("Contador de Hilos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Cambiar el color de fondo del contenido del JFrame
        getContentPane().setBackground(Color.YELLOW);

        // Añadir botones y configurar su acción
        b1 = new JButton("Finalizar Hilo 1");
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Finalizar Hilo 2");
        b2.addActionListener(this);
        add(b2);

        // Inicializar los hilos con contadores iniciales
        hilo1 = new HiloContador(8, this);
        hilo2 = new HiloContador(20, this);

        // Iniciar los hilos desde el principio
        hilo1.start();
        hilo2.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // Llama a la clase base para limpiar la ventana
        g.setFont(new Font("Verdana", Font.BOLD, 20)); // Configurar fuente
        g.drawString("Hilo 1: " + hilo1.getContador(), 80, 100); // Mostrar el contador1
        g.drawString("Hilo 2: " + hilo2.getContador(), 80, 150); // Mostrar el contador2
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) { // Controlar Hilo 1
            hilo1.detenerHilo(); // Detener hilo 1
            b1.setText("Finalizado Hilo 1"); // Cambiar texto del botón
        }

        if (e.getSource() == b2) { // Controlar Hilo 2
            hilo2.detenerHilo(); // Detener hilo 2
            b2.setText("Finalizado Hilo 2"); // Cambiar texto del botón
        }
        repaint(); // Vuelve a dibujar para reflejar los cambios
    }

    public static void main(String[] args) {
        Principal app = new Principal(); // Crea una instancia de la aplicación
        app.setVisible(true); // Muestra la ventana
    }
}
