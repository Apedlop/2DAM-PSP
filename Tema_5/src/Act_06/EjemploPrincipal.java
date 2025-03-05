package Act_06;

import java.security.Principal;

public class EjemploPrincipal implements Principal, java.io.Serializable {
    private String name;

    public EjemploPrincipal(String name) {
        if (name == null) throw new NullPointerException("Entrada nula");
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EjemploPrincipal that = (EjemploPrincipal) o;
        return this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
