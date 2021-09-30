
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
    private final String MENSAJE = "Escoja una opción:"; 
    private final String[] OPCIONES = {"Realizar turno","Revolver tablero", "Terminar partida"};
    private final String ERROR_MENU = "Opción inválida, vuelva a escoger.";
    private Tablero tablero;
    private Interfaz interfaz;

    /**
     * Contructor de la clase Arbitro.
     *@param tablero El objeto de la clase tablero.  
     */
    public Arbitro(Tablero tablero)
    {
        this.tablero = tablero;
        interfaz = new Interfaz(TITULO_VENTANA, NOMBRE_IMAGEN);
    }

    /**
     * Método que muestra el menú del juego. 
     */
    public void jugar() {
        int opcionMenu = 0;

        while (opcionMenu != 2) {
            opcionMenu = interfaz.pedirOpcion(OPCIONES, MENSAJE);

            switch (opcionMenu) {
                case 0:
                    String hileraPosiciones = interfaz.pedirHilera(tablero.toString() + "\n\n\nDigite las posiciones de las letras que desea evaluar:");
                    // Aquí evaluar errores de ingreso de datos
                    int[] vectorPosiciones = new int[2];
                    //String resultadoTurno = tablero.evaluarPosiciones(vectorPosiciones);
                    //interfaz.decirMensaje(tablero.toString() + "\n\n\n" + resultadoTurno);
                    break;
                case 1:
                    tablero.agitarTablero();
                    interfaz.decirMensaje(tablero.toString() + "\n\n\nEl tablero se revolvió correctamente.");
                    break;
                case 2:
                    interfaz.decirMensaje("La partida ha finalizado. ");
                    break;
                default:
                    interfaz.decirMensaje(ERROR_MENU);
            }
        }
    }

    
}
