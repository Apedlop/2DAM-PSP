package Act2_07;

public class HiloC extends Thread {


    private Contador cont;
    private int iteracionesTotales;

    public HiloC(String nombre, Contador cont, int iteracionesTotales) {
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
