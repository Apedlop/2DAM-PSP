// Clase usada en el fichero jaas.config.

package Act_05;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.util.Map;

public class EjemploLoginModule implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;

    // Métodos de la interfaz LoginModule
    public boolean commit() throws LoginException { return true; }
    public boolean logout() throws LoginException { return true; }
    public boolean abort() throws LoginException { return true; }

    // Inicialización del LoginModule
    public void initialize(Subject subject, CallbackHandler handler, Map<String, ?> state, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = handler;
    }

    // Método login - se realiza la autenticación
    public boolean login() throws LoginException {
        boolean autenticado = false;
        if (callbackHandler == null) {
            throw new LoginException("Se necesita CallbackHandler");
        }

        // Se crea el array de Callbacks
        Callback[] callbacks = new Callback[2];

        // Constructor de NameCallback y PasswordCallback con prompt
        callbacks[0] = new NameCallback("Nombre de usuario: ");
        callbacks[1] = new PasswordCallback("Clave: ", false);

        try {
            // Se invoca al método handle del CallbackHandler para solicitar el usuario y la contraseña
            callbackHandler.handle(callbacks);
            String usuario = ((NameCallback)callbacks[0]).getName();
            char[] passw = ((PasswordCallback)callbacks[1]).getPassword();
            String clave = new String(passw);

            // La autenticación se realiza aquí con nombre de usuario: pedro, su clave: abcd
            autenticado = ("pedro".equalsIgnoreCase(usuario) && "abcd".equals(clave));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return autenticado; // Devuelve true o false
    }
}
