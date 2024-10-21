package Act2_01;

public class Principal_Ejer_1 {

    public static void main(String[] args) {

        /* Al haber más hilos (5) la planificación de la CPU los ejecuta de manera concurrente y en orden impredecible,
        ya que los hilos se ejecutan independientemente unos de otros.*/
        Ejer_1 holaMundo = new Ejer_1("Hola Mundo");
        holaMundo.start();

        Ejer_1 h1 = new Ejer_1("H1");
        Ejer_1 h2 = new Ejer_1("H2");
        Ejer_1 h3 = new Ejer_1("H3");
        Ejer_1 h4 = new Ejer_1("H4");
        Ejer_1 h5 = new Ejer_1("H5");

        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();

    }

}
