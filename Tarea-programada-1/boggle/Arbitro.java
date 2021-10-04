
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
                    // Falta controlar la entrada, qué pasa si el usuario no introduce valores o le da en la equis.
                    String hileraPosiciones = interfaz.pedirHilera(tablero.toString() + "\n\n\nDigite las posiciones de las letras que desea evaluar:");
                    String hileraPosicionesLimpia = this.limpiarHilera(hileraPosiciones);
                    int tamanoHileraLimpia = hileraPosicionesLimpia.length();
                    
                    if(hileraPosicionesLimpia != null && tamanoHileraLimpia%2 == 0 && tamanoHileraLimpia >= 4) {
                        int[] posiciones = this.hileraToVector(hileraPosicionesLimpia);
                        boolean palabraValida = evaluarPosiciones(posiciones);
                        if (palabraValida == true) {
                            interfaz.decirMensaje("Valido"); //Falta mejorar mensajes
                        } else {
                            interfaz.decirMensaje("Invalido");
                        }
                        /**
                         * Falta métodos que verifican que la posición no está repetida dentro de la palabra ni 
                         * entre palabras. Falta método encargado de la calificación.
                         
                         */
                    } else {
                        interfaz.decirMensaje("La información introducida no es válida.  ");
                    }
                    
                    //int[] vectorPosiciones = new int[2];
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

    public boolean esHileraNula(String hileraPosiciones) {
        if (hileraPosiciones == null) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean esHileraVacia(String hileraPosiciones) {
        if (hileraPosiciones.equals("")) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Falta comentario -------------------------------------------------
     * -----------------------------------------------------------
     */
    public String limpiarHilera(String hileraPosiciones) {
        boolean valido = true;
        String hileraLimpia = "";
        int[] arregloPosiciones;
        char caracterActual;
        
        for (int indice = 0; indice < hileraPosiciones.length() && valido == true; indice++) {
            caracterActual = hileraPosiciones.charAt(indice);            
            
            if (caracterActual == ' ' || caracterActual == ';' || caracterActual == ',' || caracterActual == '(' || caracterActual == ')') {
                indice++;
            } else {
                if(caracterActual == '0' || caracterActual == '1' || caracterActual == '2' || caracterActual == '3') {
                    hileraLimpia = hileraLimpia + caracterActual;
                    indice++;
                } else {
                    valido = false; 
                }
            }
        }
        
        if (valido == false){
            hileraLimpia = null;
        }
        return hileraLimpia;
        
    }
    
    public boolean esLargoInvalido(String hileraPosiciones) {
        if (hileraPosiciones.length() > 16 || hileraPosiciones.length() % 2 != 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public int[] hileraToVector (String hileraPosiciones) {
        char caracterActual;
        int[] vectorPosiciones = new int[hileraPosiciones.length()];
        
        for(int indice = 0; indice < hileraPosiciones.length(); indice++){
            caracterActual = hileraPosiciones.charAt(indice);
            vectorPosiciones[indice] = caracterActual - 30; //pasa el caracter al valor entero, por ejemplo: el valor ascii del caracter 1 es 31, menos 30 queda el número 1
        }
        
        return vectorPosiciones;
    }
    
       /**
     * Evalúa si las posiciones ingresadas por el usuario están continuas vertical, horizontal o diagonalmente.
     * @param vectorPosiciones vector de enteros con las posiciones ingresadas por el usuario.
     * @return hilera con la palabra formada por las posiciones ingresadas por el usuario si las posiciones están continuas.
     * En caso contrario genera un mensaje de error.
     */
    public boolean evaluarPosiciones(int[] vectorPosiciones) { 
        boolean vectorValido = true;
        int indice = 0;
        int filaMayor = 3;
        while (indice < (vectorPosiciones.length-2) && vectorValido == true) {
            int fila = vectorPosiciones[indice];
            int columna = vectorPosiciones[indice +1];
            int filaSiguiente = vectorPosiciones[indice +2];
            int columnaSiguiente = vectorPosiciones[indice +3];

            if (fila == 0) {
                if (columna==0){
                    if(!(filaSiguiente <= fila +1 && columnaSiguiente <= columna +1) ){  
                        vectorValido = false; 
                        // Funciona porque previamente se verifico que los valores estuvieran entre 0 y la longitud de la matriz.
                        // Por lo estamos seguros de que no va a haber valores negativos. 
                    } else {
                        if (columna == filaMayor) {
                            if (!(filaSiguiente <= fila+1 && columnaSiguiente >= columna-1)){
                                vectorValido = false; 
                            }
                        } else  {
                            if(!((filaSiguiente >= fila-1 && filaSiguiente <= fila+1) && columnaSiguiente <= columna +1)) {
                                vectorValido = false;
                            }
                        }
                    }
                } 
            }else { 
                if (fila == filaMayor) {
                    if(columna==0) {
                        if(!(filaSiguiente >= fila-1 && columnaSiguiente <= columna +1)){
                            vectorValido = false;
                        }
                    } else {
                        if (columna == filaMayor){
                            if(!(filaSiguiente >= fila-1 && columnaSiguiente >= columna-1)){
                                vectorValido = false; 
                            }
                        } else {
                            if(!((filaSiguiente >= fila-1 && filaSiguiente <= fila+1) && columnaSiguiente >= columna -1)) {
                                vectorValido = false;
                            } 
                        }
                    }
                } else {
                    if (columna == 0) {
                        if(!((filaSiguiente >= fila -1 && filaSiguiente <= fila +1) && columnaSiguiente <= columna +1)){
                            vectorValido = false;
                        }
                    } else {
                        if (columna == filaMayor) {
                            if(!((filaSiguiente >= fila -1 && filaSiguiente <= fila +1) && columnaSiguiente >= columna -1)){
                                vectorValido = false;
                            }
                        } else {
                            if(!((filaSiguiente >= fila -1 && filaSiguiente <= fila +1) && (columnaSiguiente >= columna -1 &&  columnaSiguiente <= columna +1))) {
                                vectorValido = false;
                            }
                        }
                    }
                }
            }
            indice= indice + 2;
        }
        
        return vectorValido;
    }
}
