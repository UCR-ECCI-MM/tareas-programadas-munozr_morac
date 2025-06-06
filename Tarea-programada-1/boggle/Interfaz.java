import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
/**
 * Se encarga de mostrar mensajes, pedir datos e interactuar con el usuario.
 * 
 * @author Kyara Muñoz
 * @author Pablo Mora
 */
public class Interfaz extends JOptionPane
{
    // Atributos de la clase. 
    private final String TITULO;
    private final ImageIcon IMAGEN;

    /**
     * Constructor de la clase Interfaz. 
     */
    public Interfaz(String elTitulo, String elNombreArchivoImagen)
    {
        TITULO = elTitulo;
        IMAGEN = new ImageIcon(this.getClass().getResource(elNombreArchivoImagen));
    }

    /**
     * Muestra en pantalla una ventana con un mensaje.  
     * 
     * @param mensaje el mensaje que se le desea mostrar al usuario.  
     */
    public void decirMensaje(String mensaje)
    {
        this.showMessageDialog(null, mensaje, TITULO, PLAIN_MESSAGE, IMAGEN);
    }
    
    /**  
     * Le pide al usuario una hilera y la retorna.
     * 
     * @param  mensaje el mensaje que se le desea mostrar al usuario.
     * @return la hilera introducida por el usuario. 
     */
    public String pedirHilera(String mensaje)
    {
        String hilera; 
        hilera = this.showInputDialog(null, mensaje, TITULO, PLAIN_MESSAGE);
        return hilera;
    }
    
    /**
     * Le permite al usuario escoger entre varias opciones y retorna la opción escogida.  
     *  
     * @param opciones arreglo que contiene las opciones que se le va a mostrar al usuario.     
     * @param mensaje el mensaje que se le desea mostrar al usuario.
     * @return la opción escogida por el usuario.
     */
    public int pedirOpcion(String[] opciones, String mensaje)
    {
        int opcionSelecionada;
        opcionSelecionada = this.showOptionDialog(null, mensaje, TITULO, DEFAULT_OPTION, PLAIN_MESSAGE, IMAGEN, opciones, null);
        return opcionSelecionada;
    }
}
