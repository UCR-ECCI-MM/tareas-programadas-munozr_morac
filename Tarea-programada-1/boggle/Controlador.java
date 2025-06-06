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
    private final String MENSAJE = "Seleccione la acción que desea\nrealizar: "; 
    private final String[] OPCIONES = {"Jugar","Ver créditos", "Ayuda", "Salir"};
    private final String CREDITOS = "Juego Boggle\n\nProgramadores:\n- Kyara Muñoz Ramírez\n- Jose Pablo Mora Cubillo\n\n05/10/2021";
    
    private final String AYUDA = "Juego Boggle\n\nEl juego consta de un tablero de 16 dados, donde cada dado tiene 6 caras, cada una " + 
    "etiquetada con una letra. El usuario debe conseguir el mayor puntaje posible\nencontrando palabras en una disposición particular de" + 
    "los dados, donde cada letra del abecedario tiene asociado un puntaje predeterminado entre 1 y 3.\nCada posición está conformada por " + 
    "una fila y una columna con valores entre 0 y 3. Por ejemplo, si las letras que conforman una palabra están en las posiciones:\n01, 02 " + 
    "y 03, se debe ingresar dichas posiciones sin ningún caracter de separación de la siguiente manera:\n\n010203\n\nSe deben ingresar " + 
    "mínimo 2 posiciones y máximo 16. Una posición solo puede usarse una única vez para una sola palabra, tanto en el mismo turno como en\n" + 
    "los siguientes. Si la palabra es válida, aparecerá su respectivo puntaje junto con el de los demás turnos que se han realizado, y la " + 
    "suma del puntaje total de\ntodas las palabras.\nPara formar una palabra deben utilizarse las posiciones de letras que estén continuas " + 
    "en líneas rectas, ya sea horizontal, vertical o diagonalmente. Luego de terminar el turno, existe la posibilidad de revolver el tablero " + 
    "cuantas veces desee, el cual coloca los dados en las 16 posiciones diferentes que poseen  aleatoriamente.";
    
    private final String SALIR = "Gracias por utilizar nuestro juego.";
    private final String ERROR_MENU = "Opción inválida, vuelva a escoger.";
    private Interfaz interfaz;
    private Arbitro arbitro;
    private Tablero tablero;
    
    /**
     * Constructor de la clase Controlador. 
     */
    public Controlador()
    {
        interfaz = new Interfaz(TITULO_VENTANA, NOMBRE_IMAGEN);
        tablero = new Tablero();
        arbitro = new Arbitro(tablero);
    }
    
    /**
     * Inicia el juego y despliega un menú con las acciones que puede realizar el usuario.  
     */
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
    
    /**
     * Método principal que llama a inciar el juego.
     * 
     * @param parametros vector de Strings con valores de entrada del main.
     */
    public static void main(String[] parametros)
    {
        Controlador controlador;
        controlador = new Controlador();
        controlador.iniciar();
    }
}
