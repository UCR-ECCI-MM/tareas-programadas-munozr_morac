
/**
 * Se encarga de considerar las reglas del juego y evaluar la partida. 
 *
 * @author Kyara Muñoz
 * @author Pablo Mora
 */
public class Arbitro
{
    //Atributos de la clase
    private final String TITULO_VENTANA = "Boggle";
    private final String NOMBRE_IMAGEN = "dado.png";
    private final String MENSAJE = "Seleccione la acción que desea realizar"; 
    private final String[] OPCIONES = {"Mostrar tablero","Revolver tablero", "Terminar partida"};
    private final String ERROR_MENU = "Opción inválida, vuelva a escoger.";
    private Tablero tablero;
    private Interfaz interfaz;

    /**
     * Contructor de la clase.
     */
    public Arbitro(Tablero tablero)
    {
        this.tablero = tablero;
        interfaz = new Interfaz(TITULO_VENTANA, NOMBRE_IMAGEN);
    }
    
    /**
     * Método que evalua la partida. 
     */
    public void jugar() {
        int opcionMenu = 0;
         
        while (opcionMenu != 2) {
            opcionMenu = interfaz.pedirOpcion(OPCIONES, MENSAJE);
            
            switch (opcionMenu) {
                case 0:
                    interfaz.decirMensaje("Escogió mostrar tablero");
                    break;
                case 1:
                    interfaz.decirMensaje("Escogió revolver tablero");
                    break;
                case 2:
                    interfaz.decirMensaje("Escogió terminar partida");
                    break;
                default:
                    interfaz.decirMensaje(ERROR_MENU);
            }
        
            //rovolver tablero -llamar metodo de tablero-. 
            //mostrar tablero.
            //repetir 
                //preguntar palabra.
                //validar palabra.
                //Decir si es valida. 
        }
    }
}
