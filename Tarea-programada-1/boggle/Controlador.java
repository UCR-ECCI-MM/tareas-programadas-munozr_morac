
/**
 * Write a description of class Controlador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.swing.JOptionPane;

public class Controlador
{
    // instance variables - replace the example below with your own
    private final String TITULO_VENTANA = "Bogle";
    private final String NOMBRE_IMAGEN = "mono.png";
    private final String MENSAJE = "Seleccione la acción que desea realizar"; 
    private final String[] OPCIONES = {"Jugar","Ver créditos", "Ayuda", "Salir"};
    private final String CREDITOS = "Juego Boggle\n\nProgramadores:\n-Kyara Muñoz\n-Jose Pablo Mora Cubillo\n\n20/09/2021";
    private final String AYUDA = "Aquí va la ayuda";
    private final String SALIR = "Gracias por utilizar nuestro juego";
    private final String ERROR_MENU = "Opción inválida, vuelva a escoger";
    private Interfaz interfaz;
    private Arbitro arbitro;
    
    /**
     * Constructor for objects of class Controlador
     */
    public Controlador()
    {
         
        interfaz = new Interfaz(TITULO_VENTANA, NOMBRE_IMAGEN);
        arbitro = new Arbitro();
    }
    
    public void opcionMenu()
    {
        int opcionMenu = 0;
        
        while (opcionMenu != 4) {
            switch (opcionMenu) {
                case 1:
                    arbitro.jugar();
                    break;
                case 2:
                    interfaz.decirMensaje(CREDITOS);
                    break;
                case 3:
                    interfaz.decirMensaje(AYUDA);
                    break;
                case 4:
                    interfaz.decirMensaje(SALIR);
                    break;
                default:
                    interfaz.decirMensaje(ERROR_MENU);
            }
        }
    }
    
    public static void main(String[] parametros)
    {
        Controlador controlador;
        controlador = new Controlador();
        controlador.iniciar();
    }
}
