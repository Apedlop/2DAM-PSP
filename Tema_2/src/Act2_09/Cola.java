package Act2_09;

public class Cola {

//    private int numero;
//    private boolean disponible = false;
//
//    public int get() {
//        if (disponible) {       // Hay número en la cola
//            disponible = false; // Se pone la cola vacía
//            return numero;      // Se devuelve
//        }
//        return -1; // No hay número disponible, cola vacía
//    }
//
//    public void put(int valor) {
//        numero = valor;
//        disponible = true;
//    }

    private int numero;
    private boolean disponible = false;

    // Método sincronizado para obtener el número de la cola
    public synchronized int get() {
        while (!disponible) {
            try {
                wait(); // Espera hasta que haya un valor disponible en la cola
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        disponible = false; // Se pone la cola vacía
        notify(); // Notifica al productor que puede producir de nuevo
        return numero; // Se devuelve el valor
    }

    // Método sincronizado para poner un valor en la cola
    public synchronized void put(int valor) {
        while (disponible) {
            try {
                wait(); // Espera hasta que el consumidor consuma un valor
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        numero = valor;
        disponible = true;
        notify(); // Notifica al consumidor que hay un nuevo valor disponible
    }


}
