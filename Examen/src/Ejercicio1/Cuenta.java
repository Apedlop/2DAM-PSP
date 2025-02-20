package Ejercicio1;

public class Cuenta {

    private double saldo;

    public Cuenta(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    // Método que devuelve el saldo actual
    public double getSaldo() {
        return saldo;
    }

    // Método para incrementar el saldo si ha habido un increso
    public synchronized void deposito(double deposito, int id) {
        double ingreso = saldo + deposito;
        if (saldo < 700 || ingreso < 700) {
            System.out.println("Saldo antes: " + saldo + ", saldo despues del ingreso: " + ingreso);
        } else {
            saldo -= deposito;
            System.out.println("\tNo se puede superar el máximo de 700 de saldo");
        }

    }

    // Método para decrementar el saldo si ha habido una retirada (reintegro)
    public synchronized void retiro(double retiro, int id) {
        double reintegro = saldo - retiro;
        if (reintegro > 0) {
            System.out.println("Saldo antes: " + saldo + ", saldo despues de retiro: " + retiro);
        } else {
            saldo += retiro;
            System.out.println("\tNo se puede sacar más del saldo permitido");
        }

    }
}
