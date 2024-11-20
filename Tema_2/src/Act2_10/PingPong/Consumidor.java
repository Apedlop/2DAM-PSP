package Act2_10.PingPong;

public class Consumidor extends Thread {

    private Cola cola;

    public Consumidor(Cola cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        while (true) { // Consume indefinidamente
            String mensaje = cola.get();
            System.out.print(mensaje + " "); // Muestra el mensaje
            try {
                sleep(100); // Simula tiempo de procesamiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
