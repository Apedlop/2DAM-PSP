package Act2_03;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends Applet implements ActionListener {

    HiloContador hilo1, hilo2;
    private Font fuente;
    private Button b1, b2; // Botones del Applet

    public void init() {
        setBackground(Color.yellow); // Color de fondo
        add(b1 = new Button("Finalizar Hilo 1"));
        b1.addActionListener(this);
        add(b2 = new Button("Finalizar Hilo 2"));
        b2.addActionListener(this);
        fuente = new Font("Verdana", Font.BOLD, 26);

        // Inicializar los hilos con contadores iniciales y pasar la referencia de la applet
        hilo1 = new HiloContador(8, this);
        hilo2 = new HiloContador(20, this);

        // Iniciar los hilos desde el principio
        hilo1.start();
        hilo2.start();
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400);
        g.setFont(fuente); // Fuente
        g.drawString("Hilo 1: " + Long.toString(hilo1.getContador()), 80, 100); // Mostrar el contador1
        g.drawString("Hilo 2: " + Long.toString(hilo2.getContador()), 80, 150); // Mostrar el contador2
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) { // Controlar Hilo 1
            if (hilo1 != null && hilo1.isAlive()) {
                hilo1.detenerHilo(); // Detener hilo 1 si está vivo
                b1.setLabel("Finalizado Hilo 1"); // Cambiar texto del botón a "Iniciar Hilo 1"
            }
        }

        if (e.getSource() == b2) { // Controlar Hilo 2
            if (hilo2 != null && hilo2.isAlive()) {
                hilo2.detenerHilo(); // Detener hilo 2 si está vivo
                b2.setLabel("Finalizado Hilo 2"); // Cambiar texto del botón a "Iniciar Hilo 2"
            }
        }
    }

    public void stop() {
        if (hilo1 != null && hilo1.isAlive()) {
            hilo1.detenerHilo(); // Detener el hilo 1 cuando el applet se detiene
        }
        if (hilo2 != null && hilo2.isAlive()) {
            hilo2.detenerHilo(); // Detener el hilo 2 cuando el applet se detiene
        }
    }
}
