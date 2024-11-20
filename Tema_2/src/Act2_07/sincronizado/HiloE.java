package Act2_07.sincronizado;

public class HiloE extends Thread {


    private Contador cont;
    private int iteracionesTotales;

    public HiloE(String nombre, Contador cont, int iteracionesTotales) {
        super(nombre);
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

