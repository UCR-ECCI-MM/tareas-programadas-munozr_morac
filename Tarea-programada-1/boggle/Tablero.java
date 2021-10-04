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
        puntosLetras[1] = 3; //valor letra b
        puntosLetras[2] = 3; //valor letra c
        puntosLetras[3] = 2; //valor letra d
        puntosLetras[4] = 1; //valor letra e
        puntosLetras[5] = 4; //valor letra f
        puntosLetras[6] = 2; //valor letra g
        puntosLetras[7] = 4; //valor letra h
        puntosLetras[8] = 1; //valor letra i
        puntosLetras[9] = 8; //valor letra j
        puntosLetras[10] = 5; //valor letra k
        puntosLetras[11] = 1; //valor letra l
        puntosLetras[12] = 3; //valor letra m
        puntosLetras[13] = 1; //valor letra n
        puntosLetras[14] = 1; //valor letra o
        puntosLetras[15] = 3; //valor letra p
        puntosLetras[16] = 5; //valor letra q
        puntosLetras[17] = 1; //valor letra r
        puntosLetras[18] = 1; //valor letra s
        puntosLetras[19] = 1; //valor letra t
        puntosLetras[20] = 1; //valor letra u
        puntosLetras[21] = 4; //valor letra v
        puntosLetras[22] = 10; //valor letra w
        puntosLetras[23] = 8; //valor letra x
        puntosLetras[24] = 4; //valor letra y
        puntosLetras[25] = 8; //valor letra z
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
     * Muestra en pantalla una hilera con el tablero.  
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
     * Retorna el atributo matrizDados() del tablero. 
     * @return matriz que almacena los dados. 
     */
    public Dado[][] getMatrizDados() {
        return matrizDados;
    }

    /**
     * Sobreescribe la matriz de dados. 
     */
    public void setMatrizDados(Dado[][] matrizDados) {
        this.matrizDados = matrizDados;
    }

    public int actualizarPuntaje(int[] vectorPosiciones) {
        return 0;
    }
    
    public int getPuntajeTotalJuego() {
        return puntajeTotalJuego;
    }
}


