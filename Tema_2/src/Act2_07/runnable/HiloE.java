package Act2_07.runnable;

public class HiloE implements Runnable {


    private Contador cont;
    private int iteracionesTotales;

    public HiloE(String nombre, Contador cont, int iteracionesTotales) {
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

