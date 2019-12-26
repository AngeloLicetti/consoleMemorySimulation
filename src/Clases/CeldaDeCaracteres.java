//Encargado de codificar esta clase: Rodrigo Fernandez
/*CeldaDeCaracteres.java*/
//Tipo de dato al que pertenecen los elementos de las celdas de LC
package Clases;
public class CeldaDeCaracteres {
    char[] caracteres;
    public CeldaDeCaracteres(char[] caracteres){
        this.caracteres=caracteres;
    }
    
   public String toString(){
       String ret="[";
        for(int i=0; i<caracteres.length; i++){
            ret+= unmaskEscapeSequence(String.valueOf(caracteres[i]));
            if(i!=caracteres.length-1){
                ret+=", ";
            } else{
                ret+="]";
            }
        }
        return ret;
   }
   
   private String unmaskEscapeSequence(String s){
       //este método sirve para que al imprimir las estructuras de datos, específicamente LC
       //no se impriman caracteres como saltos de línea o tabulaciones, pues esto complicaría la visualización
       //de los elementos de LC; por ello, si algún caracter es especial como saltos de línea o tabulaciones,
       //estos son reemplazados por sus respectivos símbolos que los representan
       //Por ejemplo, el espacio en blanco es impreso como (" ") para que al visualizar LC se entienda que no
       //es una celda vacía, sino un espacio en blanco
       if(s.equals(" ")){
           return "\" \"";
       }
       //Otro ejemplo: si un caracter es un salto de línea (\n), en vez de imprimir el salto de línea
       //(el cual haría que todo se vea más desordenado) se imprime el símbolo (\n) en su lugar
       //paraq que al visualizar LC se entienda que ese caracter contiene un salto de línea
       if(s.equals("\n")){
           return "\\n";
       }
       if(s.equals("\r")){
           return "\\r";
       }
       //Otro ejemplo: sucede lo mismo para las tabulaciones (\t)
       if(s.equals("\t")){
           return "\\t";
       }
       if(s.equals("\f")){
           return "\\f";
       }
       return s;
   }
}
