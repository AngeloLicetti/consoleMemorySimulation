//Encargado de codificar esta clase: Kevin Tumay
/*ES.java*/
//Autor: profesor Augusto Vega
//Utilizada principalmente para leer e imprimir entradas y salidas  del programa respectivamente
package MiLibreria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ES {
    
    private static BufferedReader in;
    static{
        in = new BufferedReader(
                    new InputStreamReader(System.in));  
    }
    public static void escribe(String s){
        System.out.print(s);
    }
    public static String leeString(){
        String buffer = null;
        try {
            buffer = in.readLine();
        }catch(IOException e){
            System.err.println("ES.leeString(): " + e);
        }
        return buffer;
    }
    
    public static int leeInt(){
        return Integer.parseInt(leeString());
    }
}
