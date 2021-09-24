import javax.swing.JOptionPane;
/**
 * Se encarga de manejar la funcionalidad general del programa. 
 * 
 * @author Kyara Muñoz
 * @author Pablo Mora
 */


public class Controlador
{
    // Atributos de la clase. 
    private final String TITULO_VENTANA = "Boggle";
    private final String NOMBRE_IMAGEN = "dado.png";
    private final String MENSAJE = "Seleccione la acción que desea realizar"; 
    private final String[] OPCIONES = {"Jugar","Ver créditos", "Ayuda", "Salir"};
    private final String CREDITOS = "Juego Boggle\n\nProgramadores:\n- Kyara Muñoz\n- Jose Pablo Mora Cubillo\n\n20/09/2021";
    private final String AYUDA = "Aquí va la ayuda";
    private final String SALIR = "Gracias por utilizar nuestro juego.";
    private final String ERROR_MENU = "Opción inválida, vuelva a escoger.";
    private Interfaz interfaz;
    private Arbitro arbitro;
    private Tablero tablero;
    
    /**
     * Constructor de la clase. 
     */
    public Controlador()
    {
        interfaz = new Interfaz(TITULO_VENTANA, NOMBRE_IMAGEN);
        tablero = new Tablero();
        arbitro = new Arbitro(tablero);
    }
    
    public void iniciar()
    {
        int opcionMenu = 0;
        
        while (opcionMenu != 3) {
            opcionMenu = interfaz.pedirOpcion(OPCIONES, MENSAJE);
            
            switch (opcionMenu) {
                case 0:
                    arbitro.jugar();
                    break;
                case 1:
                    interfaz.decirMensaje(CREDITOS);
                    break;
                case 2:
                    interfaz.decirMensaje(AYUDA);
                    break;
                case 3:
                    interfaz.decirMensaje(SALIR);
                    break;
                default:
                    interfaz.decirMensaje(ERROR_MENU);
            }
        }
    }
    
    
    public static void main(String[] parametros)
    {
        Controlador controlador;
        controlador = new Controlador();
        controlador.iniciar();
    }
}
