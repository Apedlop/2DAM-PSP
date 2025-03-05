package Act_06;

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
    private String usuario;
    private String clave;
    private EjemploPrincipal usuarioPrincipal;

    @Override
    public void initialize(Subject subject, CallbackHandler handler, Map<String, ?> state, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = handler;
    }

    @Override
    public boolean login() throws LoginException {
        if (callbackHandler == null) {
            throw new LoginException("Se necesita CallbackHandler");
        }

        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("Nombre de usuario: ");
        callbacks[1] = new PasswordCallback("Clave: ", false);

        try {
            callbackHandler.handle(callbacks);
            usuario = ((NameCallback) callbacks[0]).getName();
            char[] passw = ((PasswordCallback) callbacks[1]).getPassword();
            clave = new String(passw);

            return "pedro".equalsIgnoreCase(usuario) && "abcd".equals(clave);
        } catch (Exception e) {
            throw new LoginException("Error durante la autenticación");
        }
    }

    @Override
    public boolean commit() throws LoginException {
        if (usuario == null) return false;

        usuarioPrincipal = new EjemploPrincipal(usuario);
        if (!subject.getPrincipals().contains(usuarioPrincipal)) {
            subject.getPrincipals().add(usuarioPrincipal);
        }

        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        usuarioPrincipal = null;
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(usuarioPrincipal);
        usuarioPrincipal = null;
        usuario = null;
        clave = null;
        return true;
    }
}
