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
        h1 = new JLabel("Hilo 1", JLabel.CENTER);
        h1.setFont(new Font("Arial", Font.BOLD, 20));
        h1.setBounds(25, 100, 75, 30);
        add(h1);

        slider1 = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
        slider1.setBounds(150, 105, 200, 50);
        // Configuración de las marcas del slider
        slider1.setMajorTickSpacing(5); // Espaciado entre marcas principales
        slider1.setMinorTickSpacing(1); // ESpacios entre las marcas secundarias
        slider1.setPaintTicks(true); // Habilita el dibujo de las marcas
        slider1.setPaintLabels(true); // Habilita el dibujo de las etiquetas
        add(slider1);

        pro

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        Ejer_6 app = new Ejer_6();
        app.setVisible(true);
    }

}
