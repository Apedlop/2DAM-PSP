package Act2_04;

public class MyHilo extends Thread {

    private SolicitaSuspender suspender = new SolicitaSuspender(); // Petición de SUSPENDER HILO
    private long contador = 0;
    private boolean enEjecucion = true; // Variable de control para finalizar el bucle

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
