package Act2_07.runnable;

public class HiloB implements Runnable {

    private Contador cont;
    private int iteracionesTotales;

    public HiloB(String nombre, Contador cont, int iteracionesTotales) {
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
