package Act_06;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.PrivilegedAction;

public class MainJaasAutentication {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) {

        // Obtener usuario y clave desde argumentos del sistema
        String user = System.getProperty("usuario", "pedro");
        String pass = System.getProperty("clave", "abcd");

        CallbackHandler handler = new MyCallbackHandler(user, pass);
        LoginContext loginContext = null;

        try {
            loginContext = new LoginContext("EjemploLogin", handler);
            loginContext.login();
            System.out.println("Usuario autenticado...");
        } catch (LoginException e) {
            System.err.println("ERROR => No se puede autenticar el usuario.");
            System.exit(-1);
        }

        // Obtener el Subject autenticado
        Subject subject = loginContext.getSubject();

        // Crear una acción privilegiada
        PrivilegedAction action = new EjemploAccion();

        try {
            // Ejecutar la acción con los permisos del usuario autenticado
            Subject.doAsPrivileged(subject, action, null);
        } catch (SecurityException se) {
            System.out.println("Acceso denegado => " + se.getMessage());
        }

        try {
            // Cerrar sesión del usuario
            loginContext.logout();
        } catch (LoginException e) {
            System.out.println("Logout: " + e.getMessage());
            System.exit(-1);
        }
    }
}
