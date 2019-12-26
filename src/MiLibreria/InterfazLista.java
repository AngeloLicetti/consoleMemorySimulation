//Encargado de codificar esta clase: Kevin Tumay
/*InterfazLista.java*/
//Interfaz lista aprendida en clase, que será implementada por los TDAs listas (ED_1 y LCD)
//utilizados en el programa
package MiLibreria;

public interface InterfazLista {
    public void add(int index, Object o);
    public void remove(int index);
    public int indexOf(Object o);
    public Object get(int index);
    public void clear();
    public boolean isEmpty();
    public int length();
}
