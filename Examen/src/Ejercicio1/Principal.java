package Ejercicio1;

/*
* El saldo no debe ser mayor a 700, es decir, que en el incremento no se pueda superar esa cantidad.
* Crear una clase cuenta, donde se pedirá el saldo inicial, además ahí se calculará el retiro y el ingreso del dinero, lo cual será in random.
* Crear una clase persona, donde se extenderá de Thread, para hacer el sleep.
* Iniciar dos hilos personas, en la clase Principal, para que se haga el ingreso y el retido del dinero.
* Hacer join para que se espere a que los hilos terminen.
* Hacer syncronized para sincornizarlos.
* */

public class Principal {

    public static void main(String[] args) throws InterruptedException {

        Cuenta cuenta = new Cuenta(40);

        Persona persona1 = new Persona(1, cuenta);
        Persona persona2 = new Persona(2, cuenta);

        // Iniciar hilos
        persona1.start();
        persona2.start();

        // Esperar a que todos los hilos terminen
        persona1.join();
        persona2.join();
    }

}
