import java.util.Random;
/**
 * Esta clase representa el tablero del juego con los dados. 
 * 
 * @author Kyara Muñoz
 * @author Pablo Mora
 */

public class Tablero {       
    //Atributos de la clase
    private Dado[][] matrizDados;
    private final int CANTIDAD_FILAS = 4;
    private final int CANTIDAD_COLUMNAS = 4;
    private Random generador;
    private int[] puntosLetras;
    private int puntajeTotalJuego;

    /**
     * Constructor para objetos de la clase Tablero.
     */
    public Tablero() {  
        matrizDados = new Dado[CANTIDAD_FILAS][CANTIDAD_COLUMNAS];
        generador = new Random();
        for (int fila = 0; fila < matrizDados.length; fila++) {
            for (int columna = 0; columna < matrizDados[fila].length; columna++) {
                matrizDados[fila][columna] = new Dado();
            }
        }
        puntosLetras = new int[26]; // letras del abecedario
        puntosLetras[0] = 1; //valor letra a
        puntosLetras[1] = 2; //valor letra b
        puntosLetras[2] = 2; //valor letra c
        puntosLetras[3] = 2; //valor letra d
        puntosLetras[4] = 1; //valor letra e
        puntosLetras[5] = 2; //valor letra f
        puntosLetras[6] = 2; //valor letra g
        puntosLetras[7] = 2; //valor letra h
        puntosLetras[8] = 1; //valor letra i
        puntosLetras[9] = 3; //valor letra j
        puntosLetras[10] = 3; //valor letra k
        puntosLetras[11] = 1; //valor letra l
        puntosLetras[12] = 2; //valor letra m
        puntosLetras[13] = 1; //valor letra n
        puntosLetras[14] = 1; //valor letra o
        puntosLetras[15] = 2; //valor letra p
        puntosLetras[16] = 3; //valor letra q
        puntosLetras[17] = 1; //valor letra r
        puntosLetras[18] = 1; //valor letra s
        puntosLetras[19] = 1; //valor letra t
        puntosLetras[20] = 1; //valor letra u
        puntosLetras[21] = 3; //valor letra v
        puntosLetras[22] = 3; //valor letra w
        puntosLetras[23] = 3; //valor letra x
        puntosLetras[24] = 3; //valor letra y
        puntosLetras[25] = 3; //valor letra z
    }

    /**
     * Cambia de forma aleatoria la posición de los dados y la cara visible.  
     */
    public void agitarTablero() {
        Dado temporal;
        int filaAleatoria;
        int columnaAleatoria;
        for (int fila = 0; fila < matrizDados.length; fila++) {
            for (int columna = 0; columna < matrizDados[fila].length; columna++) {
                temporal = matrizDados[fila][columna];
                filaAleatoria = generador.nextInt(CANTIDAD_FILAS);
                columnaAleatoria = generador.nextInt(CANTIDAD_COLUMNAS);
                matrizDados[fila][columna] = matrizDados[filaAleatoria][columnaAleatoria];
                matrizDados[filaAleatoria][columnaAleatoria] = temporal;
                matrizDados[fila][columna].tirarDado();
            }
        }
    }

    /**
     * Muestra en pantalla una hilera con el tablero.
     *  
     * @return hilera con la representación del tablero.
     */
    public String toString() {
        String hilera = "           ";

        for (int columna = 0; columna < matrizDados[0].length; columna++) {
            hilera = hilera + columna + "      ";
        }
        hilera = hilera + "\n\n";

        for (int fila = 0; fila < matrizDados.length; fila++) {
            hilera = hilera + fila + "          ";
            for (int columna = 0; columna < matrizDados[fila].length; columna++) {
                hilera = hilera + matrizDados[fila][columna].getCaraVisible() + "     ";
            }
            hilera = hilera + "\n";
        }
        return hilera;
    }

    /**
     * Regresa una palabra a partir de un vector que contiene las coordenadas de las letras en el tablero.  
     * 
     * @param vectorPosiciones vector con las posiciones de las letras en el tablero. 
     * @return String con la palabra generada a partir del vector. 
     */
    public String obtenerPalabra(int[] vectorPosiciones){
        String palabra = "";
        int fila;
        int columna;
        Dado dadoConsultado;     
        
        for (int indice = 0; indice < (vectorPosiciones.length - 1); indice += 2) {
            fila = vectorPosiciones[indice];
            columna = vectorPosiciones[indice + 1];
            dadoConsultado = getDado(fila, columna);
            palabra = palabra + dadoConsultado.getCaraVisible();
        }
        return palabra;
    }
    
    /**
     * Calcula el puntaje de una palabra.
     * 
     * @param palabra Palabra que se desea evaluar. 
     * @return int con puntaje de la palabra evaluada. 
     */
    public int obtenerPuntajePalabra (String palabra) {
        int puntaje = 0;
        char letra;
        int valorLetra;
        
        for(int indice = 0; indice < palabra.length(); indice++){
            letra = palabra.charAt(indice);
            valorLetra = letra - 65; // Al valor ascii del carácter le restamos el valores correspondiente a la A (65).   
            puntaje = puntaje + puntosLetras[valorLetra];
        }
        
        return puntaje;
    }
    
    /***
     * Calcula el puntaje total del juego.
     * 
     * @param palabra Palabra correspondiente al turno actual.   
     * @return int con el puntaje total del juego. 
     */
    public int getPuntajeTotalJuego(int puntajePalabra) {
        int puntajeTotalJuego = 0;
        puntajeTotalJuego = puntajeTotalJuego + puntajePalabra;
        return puntajeTotalJuego;
    }
    
    /**
     * Retorna el atributo matrizDados() del tablero. 
     * 
     * @return matriz que almacena los dados. 
     */
    public Dado[][] getMatrizDados() {
        return matrizDados;
    }

    
    /**
     * Retorna un dado según una posición dada. 
     * 
     * @param fila Fila del dado.
     * @param columna Columna del dado.
     * @return Dado consultado. 
     */
    public Dado getDado(int fila, int columna) {
        return matrizDados[fila][columna];
    }
    
    /**
     * Sobreescribe la matriz de dados.
     * 
     * @param matrizDados matriz de juego.
     */
    public void setMatrizDados(Dado[][] matrizDados) {
        matrizDados = matrizDados;
    }
}