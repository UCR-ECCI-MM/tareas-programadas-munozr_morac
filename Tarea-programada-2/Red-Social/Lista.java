
/**
 * Write a description of class Lista here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Lista
{
    private Nodo primero;
    private Nodo ultimo;
    
    public Lista() {
        primero = null;
        ultimo = null;
    }
    
    public Nodo getPrimero() {
        return primero;
    }
    
    public void setPrimero(Nodo primero) {
        this.primero = primero;
    }
    
    public Nodo getUltimo() {
        return ultimo;
    }
    
    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }
    
    public boolean verificarExistencia(String nombre) {
        Nodo nodoAuxiliar = primero;
        boolean existe = false;
        
        while (nodoAuxiliar != null) {
            if (nombre.compareTo(nodoAuxiliar.getPersona().getNombre()) == 0) {
                existe = true;
                nodoAuxiliar = null;
            }
        }
        
        return existe;
    }
    
    
    
    public boolean agregarPersona(Persona nuevaPersona) {
        boolean agregado = false;
        
        if (verificarExistencia(nuevaPersona.getNombre()) == false) {
            agregado = true;
            Nodo nodoNuevo = new Nodo(nuevaPersona);
        
            if (primero == null) {
                primero = nodoNuevo;
                ultimo = nodoNuevo;
            } else {
                if (nodoNuevo.getPersona().getNombre().compareTo(primero.getPersona().getNombre()) < 0) {
                    primero.setAnterior(nodoNuevo);
                    nodoNuevo.setSiguiente(primero);
                    primero = nodoNuevo;
                } else {
                    if (nodoNuevo.getPersona().getNombre().compareTo(ultimo.getPersona().getNombre()) > 0) {
                        ultimo.setSiguiente(nodoNuevo);
                        nodoNuevo.setAnterior(ultimo);
                        ultimo = nodoNuevo;
                    } else {
                        Nodo nodoAuxiliar = primero;
                
                        while (nodoAuxiliar != ultimo) {
                            if (nodoAuxiliar.getPersona().getNombre().compareTo(nodoNuevo.getPersona().getNombre()) < 0 
                            && nodoNuevo.getPersona().getNombre().compareTo(nodoAuxiliar.getSiguiente().getPersona().getNombre()) < 0) {
                                nodoNuevo.setAnterior(nodoAuxiliar);
                                nodoNuevo.setSiguiente(nodoAuxiliar.getSiguiente());
                                nodoAuxiliar.setSiguiente(nodoNuevo);
                                nodoAuxiliar.getSiguiente().setAnterior(nodoNuevo);
                                nodoAuxiliar = ultimo;
                            } else {
                                nodoAuxiliar = nodoAuxiliar.getSiguiente();
                            }
                        }
                    }
                }
            }
        }
        
        return agregado;
    }
    
    public String toString() {
        Nodo nodoAuxiliar = primero;
        String hilera = "";
        
        while (nodoAuxiliar != null) {
            hilera += nodoAuxiliar.getPersona().toString() + "\n";
            nodoAuxiliar = nodoAuxiliar.getSiguiente();
        }
        
        return hilera;
    }
}
