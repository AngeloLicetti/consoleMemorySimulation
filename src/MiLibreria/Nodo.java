//Encargado de codificar esta clase: Kevin Tumay
/*Nodo.java*/
//El nodo es un tipo de dato utilizado para representar y almacenar un elemento de las estructuras de datos
//utilizadas en el programa, tanto en las implementaciones del TDA Lista (ED_1, LCD) como en la secuencia de
//nodos enlazados (LC)
package MiLibreria;
public class Nodo {
    private Object e;
    private Nodo sig;
    
    public Nodo(Object e){
        setE(e);
    }
    
    public Object getE(){
        return e;
    }
    
    public void setE(Object e){
        this.e = e;
    }
    
    public Nodo getSig(){
        return sig;
    }
    
    public void setSig(Nodo sig){
        this.sig = sig;
    }
    
    public String toString(){
        return e.toString();
    }
}