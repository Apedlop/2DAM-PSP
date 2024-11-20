package Act2_07.sincronizado;

public class Contador {

    private int contador = 0;
    private int actual = 0; // Iteraciones realizadas

    Contador(int contadorInicial) {
        this.contador = contadorInicial;
    }

    // Método sincronizado para incrementar el contador
    public synchronized void incrementar() {
        contador++;
    }

    // Método sincronizado para verificar y actualizar el número de iteraciones
    public synchronized boolean incrementarActual() {
        if (actual < 5000) { // Máximo de iteraciones compartidas
            actual++;
            return true;
        }
        return false;
    }

    // Método para obtener el valor del contador
    public synchronized int valor() {
        return contador;
    }
}
