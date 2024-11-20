package Act2_07.runnable;

public class HiloA implements Runnable {

    private Contador cont;
    private int iteracionesTotales;

    public HiloA(String nombre, Contador cont, int iteracionesTotales) {
        this.cont = cont;
        this.iteracionesTotales = iteracionesTotales;
    }

    @Override
    public void run() {
        while (cont.incrementarActual()) { // Cada hilo verifica si puede incrementar
            cont.incrementar();
        }
    }
}
