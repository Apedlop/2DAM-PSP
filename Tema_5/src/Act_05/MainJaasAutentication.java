package Act_05;

import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class MainJaasAutentication {

    public static void main(String[] args) {

        // Datos proporcionados desde la línea de comandos
        String user = System.getProperty("usuario");
        String pass = System.getProperty("clave");

        // Paso al CallbackHandler el nombre de usuario y la clave para que el LoginModule acceda a ellos
        CallbackHandler handler = new MyCallbackHandler(user, pass);

        try {
            LoginContext loginContext = new LoginContext("EjemploLogin", handler);

            // Llamada al método login para realizar la autenticación
            loginContext.login();
            System.out.println("Usuario autenticado...");
        } catch (LoginException e) {
            // Si la autenticación no tiene éxito
            System.err.println("ERRROR => No se puede autenticar el usuario");
            e.printStackTrace();
        }
    }
}
