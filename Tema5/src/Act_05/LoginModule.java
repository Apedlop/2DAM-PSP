package Act_05;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import java.util.Map;

public class LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;

    public boolean commit() throws LoginException {return true;}
    public boolean logout() throws LoginException {return true;}
    public boolean abort() throws LoginException {return true;}

    public void initialize(Subject subject, CallbackHandler handler, Map state, Map options) {
        this.subject = subject;
        this.callbackHandler = handler;
    }

    // Método logoin - se realiza la autenticación
    public boolean login() throws LoginException {
        boolean autenticado = true;
        if (callbackHandler == null) {
            throw new LoginException("Se necesita CallbackHandler");
        }

        // Se crea el array de Callbacks
        Callback[] callbacks = new Callback[2];

        // Constructor de NameCallback y PasswordCallback con prompt
        callbacks[0] = new NameCallback("Nombre de usuario: ");
        callbacks[1] = new PasswordCallback("Clave: ", false);

        try {
            // Se solicita el usuario y la contraseña mediante el método handle
            String usuario = ((NameCallback)callbacks[0]).getName();
            char[] passw = ((PasswordCallback)callbacks[1]).getPassword();
            String clave = new String(passw);

            // Utilización del nombre de usuario
            autenticado = ("maria".equalsIgnoreCase(usuario) && "1234".equals(clave));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return autenticado;
    }
}
