package Act2_04;

public class Ejer_8_MyHilo extends Thread {
    private SolicitaSuspender suspender = new SolicitaSuspender(); // Petición de SUSPENDER HILO
    private long contador = 0;
    private boolean enEjecucion = true; // Variable de control para finalizar el bucle
    private Ejer_8 app; // Referencia a la ventana principal

    public Ejer_8_MyHilo(Ejer_8 app) {
        this.app = app; // Inicializa la referencia a la ventana principal
    }

    public void suspende() {
        suspender.set(true);
    }

    public void reanuda() {
        suspender.set(false);
    }

    public long getContador() {
        return contador;
    }

    @Override
    public void run() {
        try {
            while (enEjecucion) {
                suspender.esperandoParaReanudar(); // Comprobar si debe suspenderse
                contador++; // Incrementa el contador
                System.out.println("Contador: " + contador); // Muestra el valor del contador

                // Actualiza el contador en la interfaz gráfica
                app.actualizarContador(this);

                Thread.sleep(1000); // Pausa para ver el incremento
            }
            System.out.println("Hilo finalizado."); // Mensaje al finalizar el bucle
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void finalizar() {
        enEjecucion = false; // Finaliza el bucle
    }
}