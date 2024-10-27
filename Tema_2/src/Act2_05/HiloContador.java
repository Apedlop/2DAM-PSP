package Act2_05;

import java.applet.Applet;

public class HiloContador extends Thread {
    private long contador;
    private boolean parar; // Bandera para detener el hilo
    private Applet applet; // Referencia a la applet

    public HiloContador(long contadorInicial, Applet applet) {
        this.contador = contadorInicial;
        this.parar = false;
        this.applet = applet; // Guardar la referencia
    }

    public void detenerHilo() {
        parar = true; // Cambiar bandera para detener el hilo
        interrupt(); // Interrumpir el hilo si está en espera
    }

    @Override
    public void run() {
        try {
            while (!parar) { // Bucle controlado por la bandera
                System.out.println("Contador: " + contador);
                contador++;
                applet.repaint(); // Actualizar el Applet para mostrar el contador
                Thread.sleep(1000); // Pausar para ver el incremento
            }
        } catch (InterruptedException e) {
            System.out.println("Hilo interrumpido.");
        }
        System.out.println("FIN HILO");
    }

    public long getContador() {
        return contador; // Obtener el valor actual del contador
    }
}
