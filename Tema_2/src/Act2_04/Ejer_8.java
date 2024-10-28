// Ejer_8.java
package Act2_04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Ejer_8 extends JFrame implements ActionListener {
    Ejer_8_MyHilo hilo1, hilo2;
    private JButton comenzar, reanudar1, reanudar2, suspender1, suspender2, finalizar;
    private JLabel contadorLabel1, contadorLabel2, hiloLabel1, hiloLabel2; // Etiquetas para mostrar el contador y los nombres de los hilos

    public Ejer_8() {
        setTitle("Ejer 8");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.YELLOW);

        // Panel para los botones
        comenzar = new JButton("Comenzar Proceso");
        comenzar.addActionListener(this);
        add(comenzar);

        reanudar1 = new JButton("Reanudar 1");
        reanudar1.addActionListener(this);
        add(reanudar1);

        reanudar2 = new JButton("Reanudar 2");
        reanudar2.addActionListener(this);
        add(reanudar2);

        suspender1 = new JButton("Suspender 1");
        suspender1.addActionListener(this);
        add(suspender1);

        suspender2 = new JButton("Suspender 2");
        suspender2.addActionListener(this);
        add(suspender2);

        finalizar = new JButton("Finalizar");
        finalizar.addActionListener(this);
        add(finalizar);

        // Etiquetas para los números de los hilos
        contadorLabel1 = new JLabel("0", JLabel.CENTER);
        contadorLabel1.setFont(new Font("Arial", Font.BOLD, 20));
        add(contadorLabel1);

        contadorLabel2 = new JLabel("0", JLabel.CENTER);
        contadorLabel2.setFont(new Font("Arial", Font.BOLD, 20));
        add(contadorLabel2);

        // Etiquetas para "HILO 1" y "HILO 2"
        hiloLabel1 = new JLabel("HILO 1", JLabel.CENTER);
        hiloLabel1.setFont(new Font("Arial", Font.PLAIN, 16));
        add(hiloLabel1);

        hiloLabel2 = new JLabel("HILO 2", JLabel.CENTER);
        hiloLabel2.setFont(new Font("Arial", Font.PLAIN, 16));
        add(hiloLabel2);
    }

    public void actualizarContador(Ejer_8_MyHilo hilo) {
        if (hilo == hilo1) {
            contadorLabel1.setText("HILO 1: " + hilo.getContador());
        } else if (hilo == hilo2) {
            contadorLabel2.setText("HILO 2: " + hilo.getContador());
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comenzar) {
            hilo1 = new Ejer_8_MyHilo(this); // Inicializar hilo1
            hilo2 = new Ejer_8_MyHilo(this); // Inicializar hilo2
            hilo1.start();
            hilo2.start();
            comenzar.setText("Comenzado");
        } else if (e.getSource() == reanudar1) {
            hilo1.reanuda();
        } else if (e.getSource() == reanudar2) {
            hilo2.reanuda();
        } else if (e.getSource() == suspender1) {
            hilo1.suspende();
        } else if (e.getSource() == suspender2) {
            hilo2.suspende();
        } else if (e.getSource() == finalizar) {
            hilo1.finalizar();
            hilo2.finalizar();
            finalizar.setText("Finalizado");
        }
        repaint();
    }

    public static void main(String[] args) {
        Ejer_8 app = new Ejer_8();
        app.setVisible(true);
    }
}
