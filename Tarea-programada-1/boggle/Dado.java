import java.util.ArrayList; 
import java.util.Random;
/**
 * Esta clase representa un dado. 
 * 
 * @author Kyara Muñoz
 * @author Pablo Mora
 */
public class Dado
{
    // 
    private char[] carasDado;
    private char caraVisible;
    private Random generador;

    /**
     * Constructor de la clase Dados. 
     */
    public Dado()
    {
        generador = new Random();
        carasDado = new char[6];
        for(int indice = 0; indice<6; indice++) {
            carasDado[indice] = (char)(65 + generador.nextInt(26));
        }
        caraVisible = carasDado[generador.nextInt(6)];
        //Atributos de la clase.
        //Contiene las 6 caras del dado. 
        //carasDado = new ArrayList<String>(); //Inicializar el arreglo. // Asignar aleatoriamente las caras //¿Cambiar a char?
        //caraVisible ->Forzosamente se le debe asignar un valor. La profe prefiere que sea una cara aleatoria por facilidad. 
        // ascii 65(A) a 90(Z)
    }
    
    /**
     * Método que permite elegir una cara del dado de forma aleatoria. 
     */
    
    public void tirarDado() {
       caraVisible = carasDado[generador.nextInt(6)];
     }
    
    //Getter 
    /**
     * 
     */
    public char getCaraVisible(){
        return caraVisible;
    }
    
    /**
     * 
     */
    public char getUnaCara(int numeroCara){
        return carasDado[numeroCara];
    }
    
    /**
     * 
     */
    public char[] getCarasDado() {
        return carasDado;
    }
    
    //Setter
    /**
     * 
     */
    public void setCaraVisible(int nuevaCaraVisible){
        caraVisible = carasDado[nuevaCaraVisible];
    }
    
    /**
     * 
     */
    public void setUnaCara(int numeroCara, char nuevaCara){
        carasDado[numeroCara] = nuevaCara;
    }
    
    /**
     * No está permitido por java. 
     *
    public void setCarasDado(char[] nuevasCaras){
        carasDado = nuevasCaras; 
    }
    */
}
