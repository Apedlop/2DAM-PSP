package Act2_09;

public class Produc_Consum {

    public static void main(String[] args) {

        Cola cola = new Cola();
        Productor p = new Productor(cola, 1);
        Consumidor c = new Consumidor(cola, 1);

        p.start();
        c.start();


        /* ¿Se optiene la salida deseada? No, no se optiene la salida deseada, ya que si no usamos un mecanismo de sincronización adecuado los hilos interactúan desincronizada, ya que si solo cambiamos el sleep() en lo único que ayuda es a simular la velocidad de producción/consumo, sin garantizar que los hilos se coordinen entre sí.
        *  Entonces, para poder obtener la salida deseada solo debemos sincornizar en la Clase Cola para que solo un hilo pueda acceder a ella a la vez. Para ello usamos el synchronized en los métodos put() y get(), para asegurar de que el productor y consumidor no entren en competencia directa al acceder a la cola. */

    }

}
