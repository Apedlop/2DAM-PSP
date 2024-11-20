package Act2_10.PingPong;


public class Cola {

    private String mensaje;
    private boolean disponible = false;

    // Método sincronizado para obtener un mensaje de la cola
    public synchronized String get() {
        while (!disponible) {
            try {
                wait(); // Espera hasta que haya un mensaje disponible
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        disponible = false; // Marca la cola como vacía
        notifyAll(); // Notifica al productor que puede producir más
        return mensaje; // Devuelve el mensaje
    }

    // Método sincronizado para colocar un mensaje en la cola
    public synchronized void put(String mensaje) {
        while (disponible) {
            try {
                wait(); // Espera hasta que el consumidor procese el mensaje
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.mensaje = mensaje;
        disponible = true; // Marca la cola como llena
        notify(); // Notifica al consumidor que puede consumir
    }
}