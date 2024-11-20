package Act2_07;

public class Contador {

    private int contador = 0;
    private int actual = 0;

    Contador(int contadorInicial) {
        this.contador = contadorInicial;
    }

    public void incrementar() {
        contador++;
    }

    public int valor() {
        return contador;
    }

    public boolean incrementarActual() {
        if (actual < 5000) { // Máximo de iteraciones compartidas
            actual++;
            return true;
        }
        return false;
    }
}
