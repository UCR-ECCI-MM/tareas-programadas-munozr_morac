
/**
 * Write a description of class Usuario here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Usuario
{
    private String nombre;
    private int edad;
    private String amigos;
    
    public Usuario(String nombre, int edad, String amigos) {
        this.nombre = nombre;
        this.edad = edad;
        this.amigos = amigos;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public String getAmigos() {
        return amigos;
    }
    
    public void setAmigos(String amigos) {
        this.amigos = amigos;
    }
    
    public String toString() {
        return nombre + " tiene " + edad + " años";
    }
}
