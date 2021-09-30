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

    /**
     * Evalúa si las posiciones ingresadas por el usuario están continuas vertical, horizontal o diagonalmente.
     * @param vectorPosiciones vector de enteros con las posiciones ingresadas por el usuario.
     * @return hilera con la palabra formada por las posiciones ingresadas por el usuario si las posiciones están continuas.
     * En caso contrario genera un mensaje de error.
     */
    public boolean evaluarPosiciones(int[] vectorPosiciones) { 
        boolean vectorValido = true;
        int indice = 0;
        int filaMayor = CANTIDAD_FILAS - 1;
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
                        if(!(filaSiguiente >= fila+1 && columnaSiguiente <= columna +1)){
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


