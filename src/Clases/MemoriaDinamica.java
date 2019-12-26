//Encargado de codificar esta clase: Angelo Licetti
//Esta clase contiene variables que almacenan las estructuras de datos usadas en el programa
//así como los métodos (operaciones) que se utilizan durante la corrida del programa y que
//manipulan de diversas maneras las estructuras de datos utilizadas
package Clases;
import MiLibreria.ES;
import MiLibreria.Nodo;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemoriaDinamica {
    private ED_1 ed;
    private LC lc;
    private LCD lcd;
    
    public MemoriaDinamica(ED_1 ed, LC lc, LCD lcd){
        this.ed=ed;
        this.lc=lc;
        this.lcd=lcd;
    }
    
    public boolean existeArchivo(String ruta){
        //verifica si existe la ruta y si es un archivo válido:
        File archivo=new File(ruta);
        return archivo.exists();
    }
    
    public boolean cargarArchivo(String ruta) throws Exception{//retorna true si llega a cargar el archivo
        //crea un FileReader, que nos permitira leer el archivo:
        FileReader lector;
        try{
            lector = new FileReader(ruta);
        }catch(Exception e){//no es un archivo válido para leer; error: 1
            return false;
        }
        //crea un BufferedReader, que almacena el texto del FileReader lector, y
        //nos servirá para leer los caracteres de manera flexible
        BufferedReader buffer = new BufferedReader(lector);
        //crea un arreglo con los caracteres del buffer:
        char[] caracteres = caracteresDe(buffer);
        //calcula la canitdad de caracteres:
        int tamaño = caracteres.length;
        //verifica si hay espacio usando lcd:
        if(!lcd.hayEspacioConsecutivoPara(tamaño)){ //si no hay espacio, reacomodar segun instrucciones del pdf del proyecto:
            lcd.reComprimirrLc(ed, lc);
            if(!lcd.hayEspacioConsecutivoPara(tamaño)){//si no hay espacio inclusive despues de la reacomodacion, lanza una excepción:
                throw new Exception("\nExcepción: Ya no hay espacio suficiente para cargar el archivo: [" + ruta + "]");
            }
            //si después de reacomodar sí hay espacio lo carga:
            cargarArchivoAux(ruta, tamaño, caracteres);
        }
        //si hay espacio lo carga
        cargarArchivoAux(ruta, tamaño, caracteres);
        return true;
    }
    
    public char[] caracteresDe(BufferedReader br){//retorna un arreglo con los caracteres del br
        //crea un arreglo de caracteres ret; este será el arreglo que se retornará:
        char[] ret = null;
        //crea una variable de tipo String que nos permitirá almacenar los caracteres:
        String caracteres = "";
        //Se encierra esto en un bloque try-catch por si ocurre alguna excepcion
        //durante la lectura de los caracteres:
        try {
            //lee la linea siguiente del br:
            String linea = br.readLine();
            //mientras que la linea siguiente tenga caracteres, los añadirá al String caracteres
            while(linea != null){
                caracteres += linea;
                linea = br.readLine();
                if(linea != null){
                    caracteres += "\n";
                }
            }
            //convierte el String caracteres a un arreglo de caracteres:
            ret = caracteres.toCharArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //retorna el arreglo de caracteres obtenido:
        return ret;
    }
    
    private void cargarArchivoAux(String ruta, int tamaño, char[] caracteres){//carga el archivo almacenado en el buffer después de verificarse que si hay espacio
        //inserta los caracteres del archivo en lc y modifica lcd convenientemente
        //y retorna la primera celda de lc usada:
        Nodo pc = lcd.insertarCaracteres(lc, caracteres, tamaño);
        //crea el objecto de la clase Archivo con sus atributos correspondientes:
        Archivo archivo = new Archivo(ruta, tamaño, pc);
        //almacena el archivo en ed:
        ed.add(ed.length(), archivo);
    }
    
    public void eliminarArchivo(String ruta) throws Exception{
        //verifica el archivo ha sido cargado:
        int indiceDelEliminado = ed.indexOf(ruta);
        if(indiceDelEliminado<0){
            throw new Exception("\nExcepción: El archivo especificado no ha sido cargado.");
        }
        //copia los datos del archivo que serán útiles antes de eliminarlo:
        Archivo temp = (Archivo)ed.get(indiceDelEliminado);
        int tamaño = temp.getCc();
        int pc = lc.indexOf(temp.getPc());
        //elimina el archivo de ed:
        ed.remove(indiceDelEliminado);
        //modifica convenientemente lcd:
        lcd.removeArchivo(pc, tamaño);
    }
    
    public String estructurasToString(){
        //valor a retornar:
        String ret = "\n=====Estructuras de datos y tipos de datos abstractos=====";
        //añade ed a ret:
        ret += "\n===ED===";
        ret += ed.toString();
        //añade lc a ret:
        ret += "\n===LC===";
        ret += lc.toString();
        //añade lcd a ret:
        ret += "\n===LCD===";
        ret += lcd.toString();
        return ret;
    }
}
