package Act2_06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejer_6 extends JFrame implements ActionListener {

    Ejer_6_HiloContador hilo1, hilo2, hilo3;
    private JButton comenzarCarrera;
    private JLabel h1, h2, h3, prioridad1, prioridad2, prioridad3, ganador;
    private JProgressBar progress1, progress2, progress3;
    JSlider slider1, slider2, slider3;

    public Ejer_6() {
        setTitle("USANDO PRIORIDADES. CARRERA DE HILOS");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Utilizar layout nulo para posicionar manualmente los elementos

        // Botón de "Comenzar Carrera"
        comenzarCarrera = new JButton("Comenzar Carrera");
        comenzarCarrera.setBounds(175, 20, 150, 30);
        comenzarCarrera.addActionListener(this);
        add(comenzarCarrera);

        // HILO 1
        h1 = new JLabel("Hilo 1", JLabel.LEFT);
        h1.setFont(new Font("Arial", Font.BOLD, 20));
        h1.setBounds(25, 100, 75, 30);
        add(h1);

        slider1 = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
        slider1.setBounds(150, 105, 200, 45);
        // Configuración de las marcas del slider
        slider1.setMajorTickSpacing(5); // Espaciado entre marcas principales
        slider1.setMinorTickSpacing(1); // ESpacios entre las marcas secundarias
        slider1.setPaintTicks(true); // Habilita el dibujo de las marcas
        slider1.setPaintLabels(true); // Habilita el dibujo de las etiquetas
        add(slider1);

        prioridad1 = new JLabel("Prioridad", JLabel.RIGHT);
        prioridad1.setFont(new Font("Arial", Font.PLAIN, 15));
        prioridad1.setBounds(375, 100, 100, 30);
        add(prioridad1);

        progress1 = new JProgressBar();
        progress1.setBounds(25, 150, 450, 20);
        progress1.setStringPainted(true);
        add(progress1);

        // HILO 2
        h2 = new JLabel("Hilo 2", JLabel.LEFT);
        h2.setFont(new Font("Arial", Font.BOLD, 20));
        h2.setBounds(25, 200, 75, 30);
        add(h2);

        slider2 = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
        slider2.setBounds(150, 205, 200, 45);
        // Configuración de las marcas del slider
        slider2.setMajorTickSpacing(5); // Espaciado entre marcas principales
        slider2.setMinorTickSpacing(1); // ESpacios entre las marcas secundarias
        slider2.setPaintTicks(true); // Habilita el dibujo de las marcas
        slider2.setPaintLabels(true); // Habilita el dibujo de las etiquetas
        add(slider2);

        prioridad2 = new JLabel("Prioridad", JLabel.RIGHT);
        prioridad2.setFont(new Font("Arial", Font.PLAIN, 15));
        prioridad2.setBounds(375, 200, 100, 30);
        add(prioridad2);

        progress2 = new JProgressBar();
        progress2.setBounds(25, 250, 450, 20);
        progress2.setStringPainted(true);
        add(progress2);

        // HILO 3
        h3 = new JLabel("Hilo 3", JLabel.LEFT);
        h3.setFont(new Font("Arial", Font.BOLD, 20));
        h3.setBounds(25, 300, 75, 30);
        add(h3);

        slider3 = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
        slider3.setBounds(150, 305, 200, 45);
        // Configuración de las marcas del slider
        slider3.setMajorTickSpacing(5); // Espaciado entre marcas principales
        slider3.setMinorTickSpacing(1); // ESpacios entre las marcas secundarias
        slider3.setPaintTicks(true); // Habilita el dibujo de las marcas
        slider3.setPaintLabels(true); // Habilita el dibujo de las etiquetas
        add(slider3);

        prioridad3 = new JLabel("Prioridad", JLabel.RIGHT);
        prioridad3.setFont(new Font("Arial", Font.PLAIN, 15));
        prioridad3.setBounds(375, 300, 100, 30);
        add(prioridad3);

        progress3 = new JProgressBar();
        progress3.setBounds(25, 350, 450, 20);
        progress3.setStringPainted(true);
        add(progress3);

        // GANADOR
        ganador = new JLabel("", JLabel.CENTER);
        ganador.setFont(new Font("Arial", Font.BOLD, 25));
        ganador.setBounds(100, 420, 300, 30);
        add(ganador);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comenzarCarrera) {
            hilo1 = new Ejer_6_HiloContador(this);
            hilo2 = new Ejer_6_HiloContador(this);
            hilo3 = new Ejer_6_HiloContador(this);

            // Asignamos las prioridades según los valores de los sliders
            asignarPrioridades();

            hilo1.start();
            hilo2.start();
            hilo3.start();
        }
    }

    // Asigna la prioridad de los hilos según los sliders
    private void asignarPrioridades() {
        if (slider1.getValue() > slider2.getValue() && slider1.getValue() > slider3.getValue()) {
            hilo1.setPriority(Thread.MAX_PRIORITY);
            // Asignar prioridades normales y mínimas a los otros dos hilos
            if (slider2.getValue() > slider3.getValue()) {
                hilo2.setPriority(Thread.NORM_PRIORITY);
                hilo3.setPriority(Thread.MIN_PRIORITY);
            } else {
                hilo2.setPriority(Thread.MIN_PRIORITY);
                hilo3.setPriority(Thread.NORM_PRIORITY);
            }
        } else if (slider2.getValue() > slider1.getValue() && slider2.getValue() > slider3.getValue()) {
            hilo2.setPriority(Thread.MAX_PRIORITY);
            // Asignar prioridades normales y mínimas a los otros dos hilos
            if (slider1.getValue() >= slider3.getValue()) {
                hilo1.setPriority(Thread.NORM_PRIORITY);
                hilo3.setPriority(Thread.MIN_PRIORITY);
            } else {
                hilo1.setPriority(Thread.MIN_PRIORITY);
                hilo3.setPriority(Thread.NORM_PRIORITY);
            }
        } else if (slider3.getValue() > slider1.getValue() && slider3.getValue() > slider2.getValue()) {
            hilo3.setPriority(Thread.MAX_PRIORITY);
            // Asignar prioridades normales y mínimas a los otros dos hilos
            if (slider1.getValue() > slider2.getValue()) {
                hilo1.setPriority(Thread.NORM_PRIORITY);
                hilo2.setPriority(Thread.MIN_PRIORITY);
            } else {
                hilo1.setPriority(Thread.MIN_PRIORITY);
                hilo2.setPriority(Thread.NORM_PRIORITY);
            }
        } else {
            hilo1.setPriority(Thread.NORM_PRIORITY);
            hilo2.setPriority(Thread.NORM_PRIORITY);
            hilo3.setPriority(Thread.NORM_PRIORITY);
        }
    }

    // Actualiza las barras de progreso según el contador de cada hilo
    public void actualizarProgressBar(Ejer_6_HiloContador hilo) {
        if (hilo == hilo1) {
            progress1.setValue(hilo.getContador());
        } else if (hilo == hilo2) {
            progress2.setValue(hilo.getContador());
        } else if (hilo == hilo3) {
            progress3.setValue(hilo.getContador());
        }
    }

    // Muestra el ganador
    public void mostrarGanador(Ejer_6_HiloContador hilo) {
        if (hilo == hilo1) {
            ganador.setText("¡Hilo 1 GANÓ!");
        } else if (hilo == hilo2) {
            ganador.setText("¡Hilo 2 GANÓ!");
        } else if (hilo == hilo3) {
            ganador.setText("¡Hilo 3 GANÓ!");
        }
    }


    public static void main(String[] args) {
        Ejer_6 app = new Ejer_6();
        app.setVisible(true);
    }

}
