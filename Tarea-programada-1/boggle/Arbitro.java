
/**
 * Se encarga de considerar las reglas del juego y evaluar las posiciones que digita el usuario. 
 *
 * @author Kyara Muñoz
 * @author Pablo Mora
 */
public class Arbitro
{
    //Atributos de la clase //FUNCIONA
    private final String TITULO_VENTANA = "Boggle";
    private final String NOMBRE_IMAGEN = "dado.png";
    private final String MENSAJE = "Escoja una opción:"; 
    private final String[] OPCIONES = {"Realizar turno","Revolver tablero", "Terminar partida"};
    private final String ERROR_MENU = "Opción inválida, vuelva a escoger.";
    private Tablero tablero;
    private Interfaz interfaz;
    private int[] posicionesUsadas;
    private int contadorPosicionesUsadas;

    /**
     * Contructor de la clase Arbitro.
     * @param tablero El objeto de la clase tablero.  
     */
    public Arbitro(Tablero tablero)
    {
        this.tablero = tablero;
        interfaz = new Interfaz(TITULO_VENTANA, NOMBRE_IMAGEN);
    }

    /**
     * Muestra el menú del juego, captura las posiciones digitadas por el usuario y llama a evaluar
     * los posibles errores de las posiciones.
     */
    public void jugar() {
        //Inicialización de variables para reiniciar la partida
        tablero.agitarTablero(); 
        posicionesUsadas = new int[32]; // 16 posiciones máximas *2 números cada posición
        contadorPosicionesUsadas = 0;
        
        int opcionMenu = 0;
        String registroPalabras = "";
        String registroPalabrasCompleto = "";
        int puntajeTotalJuego = 0;

        while (opcionMenu != 2) {
            opcionMenu = interfaz.pedirOpcion(OPCIONES, MENSAJE);
            String hileraPosicionesLimpia;
            switch (opcionMenu) {
                case 0: //Realizar turno
                    String hileraPosiciones = interfaz.pedirHilera(tablero.toString() + 
                            "\n" + registroPalabrasCompleto +
                            "\n\nDigite las posiciones de las letras que desea evaluar:");

                    if (esHileraNula(hileraPosiciones) == true) {
                        interfaz.decirMensaje("No digitó ninguna posición, presionó la X o Cancelar");
                    } else {
                        if (esHileraVacia(hileraPosiciones) == true) {
                            interfaz.decirMensaje("No digitó ninguna posición");
                        } else {
                            hileraPosicionesLimpia = limpiarHilera(hileraPosiciones);
                            if (hileraPosicionesLimpia == null) {
                                interfaz.decirMensaje("Digitó una posición inválida. Debe digitar posiciones entre 0 y 3");
                            } else {
                                if (esLargoInvalido(hileraPosicionesLimpia) == true) {
                                    interfaz.decirMensaje("La cantidad de posiciones es inválida. Debe digitar una cantidad de valores par, entre 4 y 32");
                                } else {
                                    int[] vectorPosiciones = convertirHileraAVector(hileraPosicionesLimpia);
                                    if(revisarPosicionesEnPalabra(vectorPosiciones) == false) {
                                        interfaz.decirMensaje("Su palabra presenta posiciones repetidas ");
                                    } else {
                                        if (revisarPosicionesUsadas(vectorPosiciones) == false) {
                                            interfaz.decirMensaje("Una de las posiciones digitadas ya ha sido utilizada en otra palabra");
                                        } else {
                                            if (evaluarPosicionesContinuas(vectorPosiciones) == false) {
                                                interfaz.decirMensaje("Las posiciones digitadas no están continuas");
                                            } else {
                                                agregarPosicionesUsadas(vectorPosiciones);
                                                
                                                String palabra = tablero.obtenerPalabra(vectorPosiciones);
                                                int puntajePalabra = tablero.obtenerPuntajePalabra(palabra);
                                                puntajeTotalJuego = puntajeTotalJuego + puntajePalabra;

                                                registroPalabras = registroPalabras + palabra + ":" + " " + 
                                                puntajePalabra + " pts" + "\n";

                                                registroPalabrasCompleto = "\nPALABRAS: \n" + registroPalabras + 
                                                "\nPuntaje total: " + puntajeTotalJuego;

                                                interfaz.decirMensaje(tablero.toString() + "\nLa palabra " +
                                                    palabra + " es correcta.\n" + registroPalabrasCompleto); 

                                            } 
                                        } 
                                    } 
                                } 
                            }                            
                        }
                    }
                    break;
                case 1: //Revolver tablero
                    tablero.agitarTablero();
                    posicionesUsadas = new int[32];
                    contadorPosicionesUsadas = 0;
                    registroPalabras += "\n";
                    interfaz.decirMensaje(tablero.toString() + "\n\n\nEl tablero se revolvió correctamente.");
                    break;
                case 2: //Terminar partida
                    interfaz.decirMensaje("La partida ha finalizado. ");
                    break;
                default:
                    interfaz.decirMensaje(ERROR_MENU);
            }
        }
    }

    /**
     * Evalúa si el usuario presionó X o Cancelar, en cuyo caso la hilera sería null.
     * 
     * @param hileraPosiciones la hilera que digitó el usuario.
     * @return true si el usuario presiona X o Cancelar o false si el usuario presiona Aceptar.
     */
    public boolean esHileraNula(String hileraPosiciones) {
        if (hileraPosiciones == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Evalúa si el usuario digita algún caracter. 
     * 
     * @param hileraPosiciones la hilera que digitó el usuario.
     * @return true si el usuario no digita ningún caracter o false si el usuario digita alguno.
     */
    public boolean esHileraVacia(String hileraPosiciones) {
        if (hileraPosiciones.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Evalúa si cada posición de la hilera contiene caracteres válidos. Si encuentra alguno de separación (' ' ';' ',' '(' ')'), lo ignora y pasa a evaluar el 
     * siguiente caracter de la hilera. Si el caracter no corresponde a los de separación ni 1, 2, 3 o 4, lo toma como inválido
     * 
     * @param hileraPosiciones la hilera que digitó el usuario.
     * @return la hilera completa con los caracteres válidos a evalua o null si se encontró algún caracter inválido.
     */
    public String limpiarHilera(String hileraPosiciones) {
        boolean valido = true;
        String hileraLimpia = "";
        int[] arregloPosiciones;
        char caracterActual;
        int indice = 0;

        while (indice < hileraPosiciones.length() && valido == true) {
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

    /**
     * Evalúa si el largo de la hilera es válido.
     * Un largo menor a 4 es inválido porque una palabra debe tener al menos dos posiciones.
     * Un largo mayor a 32 es inválido porque el tablero tiene un máximo de 16 posiciones que equivale a 32 caracteres.
     * Un largo de cantidad de caracteres impar es inválido porque cada posición implica un par de caracteres.
     * 
     * @param hileraPosiciones la hilera que digitó el usuario.
     * @return true si la cantidad de caracteres en la hilera es menor a 4 o mayor a 32 o de largo impar, o false en caso contrario.
     */
    public boolean esLargoInvalido(String hileraPosiciones) {
        if (hileraPosiciones.length() < 4 || hileraPosiciones.length() > 32 || hileraPosiciones.length() % 2 != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Convierte los caracteres de la hilera en enteros y los almacena en un vector.
     * 
     * @param hileraPosiciones la hilera que digitó el usuario.
     * @return el vector de enteros con las posiciones de la hilera.
     */
    public int[] convertirHileraAVector(String hileraPosiciones) {
        char caracterActual;
        int[] vectorPosiciones = new int[hileraPosiciones.length()];

        for(int indice = 0; indice < hileraPosiciones.length(); indice++){
            caracterActual = hileraPosiciones.charAt(indice);
            vectorPosiciones[indice] = caracterActual - 48; //pasa el caracter al valor entero, por ejemplo: el valor ascii del caracter 1 es 49, menos 48 queda el número 1
        }

        return vectorPosiciones;
    }

    /**
     * Evalúa si las posiciones digitadas por el usuario no son repetidas en un mismo turno.
     * 
     * @param vectorPosiciones el vector de enteros con las posiciones digitadas por el usuario.
     * @return false si alguna posición se repite o true en el caso contrario.
     */
    public boolean revisarPosicionesEnPalabra(int[] vectorPosiciones) {
        boolean valido = true;

        for (int indice = 0; (indice < vectorPosiciones.length -2) && valido == true; indice += 2) { // Avanza cada dos números porque una posición tiene dos números
            for (int indice2 = indice + 2; indice2 < vectorPosiciones.length && valido == true; indice2 += 2) {
                if (vectorPosiciones[indice] == vectorPosiciones[indice2] && vectorPosiciones[indice + 1] == vectorPosiciones[indice2 + 1]) {
                    valido = false; 
                }
            }
        }

        return valido;
    }

    /**
     * Método que revisa si las posiciones digitadas de las letras en una palabra ya han sido usadas en turnos anteriores.
     * 
     * @param vectorPosiciones el vector de enteros con las posiciones digitadas por el usuario.
     * @return false si las posiciones digitadas son repetidas con respecto a las de los turnos anteriores o false en caso contrario.
     */
    public boolean revisarPosicionesUsadas(int[] vectorPosiciones) {
        boolean valido = true;

        for (int indice = 0; indice < vectorPosiciones.length && valido == true; indice += 2) { // Avanza cada dos números porque una posición tiene dos números
            for (int indice2 = 0; indice2 < contadorPosicionesUsadas && valido == true; indice2 += 2) {
                if (vectorPosiciones[indice] == posicionesUsadas[indice2] && vectorPosiciones[indice + 1] == posicionesUsadas[indice2 + 1]) {
                    valido = false; 
                }
            }
        }
        return valido;
    }

    /**
     * Método agrega a un vector las posiciones digitadas por el usuario.
     * 
     * @param vectorPosiciones el vector de enteros con las posiciones digitadas por el usuario.
     */
    public void agregarPosicionesUsadas (int[] vectorPosiciones) {
        for (int indice = 0; indice < vectorPosiciones.length; indice++) {
            posicionesUsadas[contadorPosicionesUsadas] = vectorPosiciones[indice];
            contadorPosicionesUsadas ++; 
        }
        
    }

    /**
     * Evalúa si las posiciones ingresadas por el usuario están continuas vertical, horizontal o diagonalmente.
     * 
     * @param vectorPosiciones vector de enteros con las posiciones ingresadas por el usuario.
     * @return true si las posiciones ingresadas son continuas en el tablero o false en caso contrario.
     */
    public boolean evaluarPosicionesContinuas(int[] vectorPosiciones) { 
        boolean vectorValido = true;
        int indice = 0;
        int filaMayor = 3;
        while (indice < (vectorPosiciones.length-2) && vectorValido == true) {
            int fila = vectorPosiciones[indice];
            int columna = vectorPosiciones[indice + 1];
            int filaSiguiente = vectorPosiciones[indice + 2];
            int columnaSiguiente = vectorPosiciones[indice + 3];

            if (fila == 0) {
                if (columna == 0){
                    if(!(filaSiguiente <= fila +1 && columnaSiguiente <= columna +1) ){  
                        vectorValido = false; 
                        // Funciona porque previamente se verifico que los valores estuvieran entre 0 y la longitud de la matriz.
                        // Por lo estamos seguros de que no va a haber valores negativos. 
                    } 
                } else {
                    if (columna == filaMayor) {
                        if (!(filaSiguiente <= fila+1 && columnaSiguiente >= columna-1)){
                            vectorValido = false; 
                        }
                    } else  {
                        if(!((columnaSiguiente >= columna-1 && columnaSiguiente <= columna + 1) && filaSiguiente <= fila +1)) {
                            vectorValido = false;
                        }
                    }
                }
            } else { 
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
                            if(!((columnaSiguiente >= columna-1 && columnaSiguiente <= columna+1) && filaSiguiente >= fila -1)) {
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
