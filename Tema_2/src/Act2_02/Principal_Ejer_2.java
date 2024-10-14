package Act2_02;

public class Principal_Ejer_2 {

    public static void main(String[] args) {

        Ejer_2 h1 = new Ejer_2("h1");
        Ejer_2 h2 = new Ejer_2("h2");
        Ejer_2 h3 = new Ejer_2("h3");
        Ejer_2 h4 = new Ejer_2("h4");
        Ejer_2 h5 = new Ejer_2("h5");

        new Thread(h1).start();
        new Thread(h2).start();
        new Thread(h3).start();
        new Thread(h4).start();
        new Thread(h5).start();

    }

}
