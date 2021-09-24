import java.util.Random;
/**
 * Esta clase representa el tablero del juego con los dados 
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
    
    /**
     * Constructor para objetos de la clase Tablero
     */
    public Tablero() {  
        matrizDados = new Dado[CANTIDAD_FILAS][CANTIDAD_COLUMNAS];
        generador = new Random();
        for (int fila = 0; fila < matrizDados.length; fila++) {
            for (int columna = 0; columna < matrizDados[fila].length; columna++) {
                matrizDados[fila][columna] = new Dado();
            }
        }
    }
    
    public Dado[][] getMatrizDados() {
        return matrizDados;
    }
    
    public void setMatrizDados(Dado[][] matrizDados) {
        this.matrizDados = matrizDados;
    }
    
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
    
    public String toString() {
        String hilera = "";
        for (int fila = 0; fila < matrizDados.length; fila++) {
            for (int columna = 0; columna < matrizDados[fila].length; columna++) {
                hilera = hilera + matrizDados[fila][columna].getCaraVisible() + " ";
            }
            hilera = hilera + "\n";
        }
        return hilera;
    }
}
