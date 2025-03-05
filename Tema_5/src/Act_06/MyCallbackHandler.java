package Act_06;

import javax.security.auth.callback.*;
import java.io.IOException;

public class MyCallbackHandler implements CallbackHandler {
    private String usuario;
    private String clave;

    public MyCallbackHandler(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                ((NameCallback) callback).setName(usuario);
            } else if (callback instanceof PasswordCallback) {
                ((PasswordCallback) callback).setPassword(clave.toCharArray());
            } else {
                throw new UnsupportedCallbackException(callback, "Tipo de callback no soportado");
            }
        }
    }
}
