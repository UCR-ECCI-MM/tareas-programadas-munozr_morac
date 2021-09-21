
/**
 * Write a description of class Controlador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Controlador
{
    // instance variables - replace the example below with your own
    private final String TITULO_VENTANA = "Bogle";
    private final String NOMBRE_IMAGEN = "mono.png";
    private final String MENSAJE = "Seleccione la acción que desea realizar";
    private final String[] OPCIONES = {"Jugar","Ver créditos", "Ayuda", "Salir"};
    private Interfaz interfaz;
    //private Arbitro arbitro;
    
    /**
     * Constructor for objects of class Controlador
     */
    public Controlador()
    {
        interfaz = new Interfaz(TITULO_VENTANA, NOMBRE_IMAGEN);
        //arbitro = new Arbitro();
        
        
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void sampleMethod()
    {
        // put your code here
        
    }
}
