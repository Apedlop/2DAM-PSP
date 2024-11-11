package Act2_07;

public class Contador {

    private int contador = 0;

    Contador(int contador) {
        this.contador = contador;
    }

    public void incrementar() {
        contador++;
    }

    public int valor() {
        return contador;
    }

}
