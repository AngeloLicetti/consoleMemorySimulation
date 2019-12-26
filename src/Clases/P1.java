//Encargado de codificar esta clase: Rodrigo Fernandez
/*P1.java*/
//Clase principal pide el valor del dato de entrada n y que presenta el menú con las opciones
package Clases;

import MiLibreria.ES;
import java.util.logging.Level;
import java.util.logging.Logger;

public class P1 {
    static LC lc;
    static MemoriaDinamica md;
    //-------------------------------
    public static void mostrarMenu(){
        String opcion = "";
        do
            {
                ES.escribe("\n\nELIJA UNA OPCION");
                ES.escribe("\n==========================");
                ES.escribe("\n1. Cargar archivo ");
                ES.escribe("\n2. Eliminar archivo");
                ES.escribe("\n3. Salir");
                ES.escribe("\nSeleccione operacion: ");
                opcion = ES.leeString();
                switch (opcion)
                {
                    case "1":
                        //Cargar archivo:
                        cargarArchivo();
                        ES.escribe("\n============================================");
                        ES.escribe("\nPulse enter para continuar");
                        ES.leeString();
                        break;
                    case "2":
                        //Eliminar archivo:
                        eliminarArchivo();
                        ES.escribe("\n============================================");
                        ES.escribe("\nPulse enter para continuar");
                        ES.leeString();
                        break;
                    case "3":
                        //Fin de la aplicación:
                        System.exit(0);
                        break;
                    default:
                        ES.escribe("\n\nElija operación correcta...");
                        ES.escribe("\n");
                        break;
                }
            } while (opcion != "3");
    }
    
    public static void cargarArchivo(){
        ES.escribe("\n\n===Cargar archivo===");
        String ruta;
        ES.escribe("\nNombre del archivo: ");
        ruta = ES.leeString();
        if(!md.existeArchivo(ruta)){
            ES.escribe("\nEl archivo '" + ruta + "' no existe.\n");
            return;
        }
        try{
            if(md.cargarArchivo(ruta)){
                ES.escribe("\nArchivo '" + ruta + "'cargado exitosamente.\n");
                ES.escribe(md.estructurasToString());
            } else{
                ES.escribe("\nHa ocurrido un error al leer el archivo. No ha sido cargado.\n");
            }
            
        } catch(Exception e){
            ES.escribe(e.getMessage());
            ES.escribe(md.estructurasToString());
        }
    }
    
    public static void eliminarArchivo(){
        ES.escribe("\n\n===Eliminar archivo===");
        String ruta;
        ES.escribe("\nNombre del archivo: ");
        ruta = ES.leeString();
        try{
            md.eliminarArchivo(ruta);
        } catch(Exception e){
            ES.escribe(e.getMessage());
        }
        ES.escribe(md.estructurasToString());
    }
    
    public static void probar(){
        try {
            md.cargarArchivo("45c.txt");
            md.cargarArchivo("20c.txt");
            md.cargarArchivo("15c.txt");
            md.cargarArchivo("45c2.txt");
            md.cargarArchivo("10c.txt");
            md.cargarArchivo("20c2.txt");
            md.cargarArchivo("15c2.txt");
            md.eliminarArchivo("20c.txt");
            ES.escribe(md.estructurasToString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        int n;
        ED_1 ed;
        LCD lcd;
        //----------------------------------
        //Se ingresa el único dato de entrada: n
        ES.escribe("n: ");
        n = ES.leeInt();
        //Se crea LC con n celdas de tamaño n:
        lc = new LC(n);
        //Se crean ed y lcd:
        ed = new ED_1();
        lcd = new LCD(n);
        //Se crea la memoria dinamica con sus respectivos atributos:
        md = new MemoriaDinamica(ed, lc, lcd);
        //probar();
        //Se muestra el menu de operaciones disponibles:
        mostrarMenu();
    }
}
