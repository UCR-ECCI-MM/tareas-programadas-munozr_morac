import java.util.Random;
/**
 * Esta clase representa un dado del juego 
 * 
 * @author Kyara Muñoz
 * @author Pablo Mora
 */
public class Dado
{
    // Atributos de la clase
    private char[] carasDado; //Contiene las 6 caras del dado
    private char caraVisible;
    private Random generador;
    private final int CANTIDAD_CARAS = 6;
  
    /**
     * Constructor para objetos de la clase Dado
     */
    public Dado()
    {
        generador = new Random();
        carasDado = new char[CANTIDAD_CARAS];
        for(int indice = 0; indice < CANTIDAD_CARAS; indice++) {
            carasDado[indice] = (char)(65 + generador.nextInt(26)); // Asigna aleatoriamente las caras, ascii 65(A) a 90(Z)
        }
        caraVisible = carasDado[generador.nextInt(6)]; // Se asigna una de las 6 caras aleatoriamente
    }
    
    //Getters
    /**
     * Retorna el atributo caraVisible del dado
     * @return cara visible del dado
     */
    public char getCaraVisible(){
        return caraVisible;
    }
    
    /**
     * Retorna el atributo carasDado del dado
     * @return vector que almacena las caras del dado
     */
    public char[] getCarasDado() {
        return carasDado;
    }
    
    /**
     * Retorna la letra de la cara recibida
     * @param numeroCara número de cara a consultar
     * @return letra de una de las caras del dado
     */
    public char getUnaCara(int numeroCara){
        return carasDado[numeroCara];
    }
    
    //Setters
    /**
     * Sobreescribe la cara visible del dado
     * @param caraVisible la nueva cara visible del dado
     */
    public void setCaraVisible(char caraVisible){
        this.caraVisible = caraVisible;
    }
    
    /**
     * Sobreescribe el vector de caras del dado
     * @param carasDado el nuevo vector de caras del dado
     */
    public void setCarasDado(char[] carasDado){
        this.carasDado = carasDado; 
    }
    
    /**
     * Sobreescribe la letra de una de las caras del dado
     * @param numeroCara posición del vector de una cara del dado
     * @param nuevaCara nuevo caracter a sobreescribir en la posición numeroCara recibido
     */
    public void setUnaCara(int numeroCara, char nuevaCara){
        carasDado[numeroCara] = nuevaCara;
    }
    
    
    /**
     * Sobreescribe la cara visible del dado con la letra de una cara aleatoria del dado
     */
    public void tirarDado() {
       caraVisible = carasDado[generador.nextInt(6)];
     }
}
