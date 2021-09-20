import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
/**
 * Write a description of class Interfaz here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Interfaz extends JOptionPane
{
    // instance variables - replace the example below with your own
    private final String TITULO;
    private final ImageIcon IMAGEN;

    /**
     * Constructor for objects of class Interfaz
     */
    public Interfaz(String elTitulo, String elNombreArchivoImagen)
    {
        // initialise instance variables
        TITULO = elTitulo;
        IMAGEN = new ImageIcon(this.getClass().getResource(elNombreArchivoImagen));
        
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void decirMensaje(String mensaje)
    {
        // put your code here
        this.showMessageDialog(null, mensaje, TITULO, PLAIN_MESSAGE, IMAGEN);
    }
    
    
}
