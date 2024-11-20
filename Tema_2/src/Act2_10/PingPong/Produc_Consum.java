package Act2_10.PingPong;

public class Produc_Consum {

    public static void main(String[] args) {

        Cola cola = new Cola();
        Productor p = new Productor(cola);
        Consumidor c = new Consumidor(cola);

        // Iniciar los hilos
        p.start();
        c.start();

    }

}
