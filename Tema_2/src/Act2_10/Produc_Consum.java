package Act2_10;

public class Produc_Consum {

    public static void main(String[] args) {

        Cola cola = new Cola();
        Productor p = new Productor(cola, 1);
        Consumidor c1 = new Consumidor(cola, 1);
        Consumidor c2 = new Consumidor(cola, 2);

        // Iniciar los hilos
        p.start();
        c1.start();
        c2.start();

    }

}
