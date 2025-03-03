package Act_05;

import javax.security.auth.Subject;
import java.security.Principal;

public class Main implements Principal, java.io.Serializable {

    private String name;

    public Main(String name) {
        if (name == null) {
            throw new NullPointerException("Entrasa nula");
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    // Compara el objeto especificado con el Principal para ver si son iguales
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Main)) return false;
        Main that = (Main) o;
        if (this.getName().equals(that.getName())) return true;
        return false;
    }

    public int hashCode() {return name.hashCode();}

    public String toString() {return (name);}
}
