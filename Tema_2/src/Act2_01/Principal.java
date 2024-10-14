package Act2_01;

public class Principal {

    public static void main(String[] args) {

        /* Sale ordenado debido a la planificación de la CPU, que puede hacer que un hilo
        se ejecute completamente antes de que comience el otro, pero NO está garantizado. */
        Hilo hilo1 = new Hilo("TIC");
        Hilo hilo2 = new Hilo("TAC");

        hilo1.start();
        hilo2.start();

    }

}