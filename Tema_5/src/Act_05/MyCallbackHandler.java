package Act_05;

import java.io.*;
import javax.security.auth.callback.*;

public class MyCallbackHandler implements CallbackHandler {
    private String usuario;
    private String clave;

    // Constructor recibe parámetros usuario y clave
    public MyCallbackHandler(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    // Método handle será invocado por el LoginModule
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            Callback callback = callbacks[i];

            if (callback instanceof NameCallback) {
                NameCallback nameBC = (NameCallback) callback;

                // Se asigna al NameCallback el nombre de usuario
                nameBC.setName(usuario);
            } else if (callback instanceof PasswordCallback) {
                PasswordCallback passwordCB = (PasswordCallback) callback;

                // Se asigna al PasswordCallback la clave
                passwordCB.setPassword(clave.toCharArray());
            }
        }
    }
}
