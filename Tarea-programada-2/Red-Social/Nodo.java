
/**
 * Write a description of class Nodo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Nodo
{
    private Persona persona;
    private Nodo siguiente;
    
    public Nodo(Persona persona) {
        this.persona = persona;
    }
    
    public Persona getpersona() {
        return persona;
    }
    
    public void setpersona(Persona persona) {
        this.persona = persona;
    }
    
    public Nodo getSiguiente() {
        return siguiente;
    }
    
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
    public String toString() {
        return persona.toString();
    }
}
