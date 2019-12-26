//Encargado de codificar esta clase: Kevin Tumay
//ED_1 es una implementación del TDA lista
//Como se usan 2 implementaciones del TDA lista usadas en el programa (ambas del tipo secuencial circular)
//y ambas usan los mismos operadores de este TDA, se usa una clase padre llamada
//ListaSecuencial2, la cual es la clase padre que heredan ED_1 y LCD
package Clases;

import MiLibreria.InterfazLista;
import MiLibreria.ListaSecuencial2;
import MiLibreria.Nodo;

public class ED_1 extends ListaSecuencial2{
    
    public int indexOf(String nombre){
        //retorna el indice de la primera ocurrencia de un archivo con na igual a(==) nombre
        //si no existen elementos, retorna un valor negativo:
        if(isEmpty()){
            return -1;
        }
        //recorre iterativamente los elementos de ED_1 para encontrar el indice del elemento buscado:
        Nodo actual = last;
        for(int i=0; i<size; i++){
            actual = actual.getSig();
            //si el nombre del elemento actual es igual al nombre buscado, retorna el indice del elemento actual:
            if(((Archivo)actual.getE()).getNa().equals(nombre)){
                return i;
            }
        }
        return -1;
    }
    
    //Autor de este método: Angelo Licetti
    //*este método solo se usa al momento de reComprimir lc
    public int recomprimirLc(LC antiguoLc){
        //j es un contador que sirve para indicar el inidice de la primera celda
        //que quedará vacía luego de recomprimir lc
        int j=0;
        //recupera el valor de n:
        int n=antiguoLc.length();
        //recorre cada archivo de ed, para utilizar convenientemente sus atributos, específicamente, su tamaño
        //y su puntero al primer nodo del antiguo lc que contenía los caracteres de este, para así usar esos
        //datos estratégicamente y además, asignar al puntero el valor del primer nodo de lc
        //que contendrá sus caracteres; es decir, se para dejar consistenete ed_1:
        Nodo actual = last;
        for(int i=0; i<size; i++){
            actual = actual.getSig();
            Nodo antiguoPc = ((Archivo)actual.getE()).getPc();
            int tamaño = ((Archivo)actual.getE()).getCc();
            int numeroDeCeldasUsadas = tamaño%n==0? (int)tamaño/n : ((int)tamaño/n)+1;
            //en esta parte, para el archivo actual, cambia de lugar sus caracteres lo más al
            //inicio posible de lc a través del método moverCadenaDeCeldasAlInicio de lc;
            //además, este método retorna el nodo que será asignado al puntero del archivo actual
            //para dejarlo consistente
            Nodo nuevoPc = antiguoLc.moverCadenaDeCeldasAlInicio(/*j, */antiguoPc, numeroDeCeldasUsadas);
            j+=numeroDeCeldasUsadas;
            ((Archivo)actual.getE()).setPc(nuevoPc);
        }
        //ACTUALIZACIÓN: LA LÍNEA 60 YA NO ES USADA, PUES AHORA TODO EL PROCESO DE RECOMPRIMIR SE
        //HACE EN EL MISMO LC; YA NO SE USA UNO EXTRA PARA NO UTILIZAR ESPACIO INECESARIAMENTE
        //antiguoLc.reemplazarPor(nuevoLc);
        return j;
    }
}
