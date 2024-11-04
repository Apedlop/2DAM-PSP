package Act2_05;

public class Ejer_9_HiloContador extends Thread {
    private long contador;
    private int ms;
    private boolean enEjecución = true;
    private Ejer_9 app; // Referencia a la ventana principal

    public Ejer_9_HiloContador(int ms, Ejer_9 app) {
        this.ms = ms;
        this.app = app; // Guardar la referencia
    }

    public void detenerHilo() {
        interrupt(); // Interrumpir el hilo si está en espera
    }

    public void run() {
        while (enEjecución) { // Bucle infinito
            try {
                Thread.sleep(ms); // Pausa de 300ms entre incrementos
                contador++; // Incrementar el contador
                app.actualizarContador(this);
            } catch (InterruptedException e) {
                // Si se interrumpe, salimos del bucle y finalizamos el hilo
                return; // Finaliza el hilo
            }
            app.repaint(); // Solicitar un redibujo desde el hilo
        }
    }

    public long getContador() {
        return contador; // Obtener el valor actual del contador
    }

    public void finalizar() {
        enEjecución = false;
    }
}