package Act2_06;

import javax.swing.*;
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

        // 

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
