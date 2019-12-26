//Encargado de codificar esta clase: Kevin Tumay
/*ListaSecuencial2.java*/
//Lista secuencial de tipo circular, con los operadores aprendidos en clase
package MiLibreria;

public class ListaSecuencial2 implements InterfazLista{
    protected Nodo last;
    protected int size;
    
    public ListaSecuencial2(){
        size = 0;
    }
    
    //implementacion de operadores
    
    public void add(int index, Object o){
        if(index<0 || index>size){
            throw new IllegalArgumentException("index<0 || index>size");
        }
        Nodo nuevo = new Nodo(o);
        if(isEmpty()){//está vacía
            last = nuevo;
            nuevo.setSig(nuevo);
        } else if(index==0){//insertar al inicio
            nuevo.setSig(last.getSig());
            last.setSig(nuevo);
        } else if(index==size){//insertar al final
            nuevo.setSig(last.getSig());
            last.setSig(nuevo);
            last = nuevo;
        } else{
            Nodo anterior = nodo(index-1);
            nuevo.setSig(anterior.getSig());
            anterior.setSig(nuevo);
        }
        size++;
    }
    
    public void remove(int index){
        if(index<0 || index>(size-1)){
            throw new IllegalArgumentException("index<0 || index>(size-1)");
        }
        if(index==0){//eliminar al comienzo
            if(size==1){//solo hay un elemento
                clear();
                return;
            }
            last.setSig(last.getSig().getSig());
        } else if(index==(size-1)){//eliminar al final
            Nodo nuevoLast = nodo(size-2);
            nuevoLast.setSig(last.getSig());
            last = nuevoLast;
        } else{//eliminar por el medio
            Nodo anterior = nodo(index-1);
            anterior.setSig(anterior.getSig().getSig());
        }
        size--;
    }
    
    public int indexOf(Object o){
        if(isEmpty()){
            return -1;
        }
        Nodo actual = last;
        for(int i=0; i<size; i++){
            actual = actual.getSig();
            if(actual.getE().toString().equals(o.toString())){
                return i;
            }
        }
        return -1;
    }
    
    public Object get(int index){
        return nodo(index).getE();
    }
    
    public void clear(){
        size = 0;
    }
    
    public boolean isEmpty(){
        return size==0;
    }
    
    public int length(){
        return size;
    }
    
    /*
    //toString() original de ListaSecuencial2 aprendido en clase:
    public String toString(){
        Nodo actual;
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if(!isEmpty()){
            actual = last;
            do{
                actual = actual.getSig();
                sb.append(actual.toString());
                if(actual!=last){
                    sb.append(" ");
                }
            } while(actual!=last);
        }
        sb.append("]");
        return sb.toString();
    }*/
    
    //toString() modificado para mejor visualización de los tdas utilizados:
    public String toString(){
        Nodo actual;
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        if(!isEmpty()){
            actual = last;
            do{
                actual = actual.getSig();
                sb.append("\n\t" + actual.toString());
                if(actual!=last){
                    sb.append(",");
                }
            } while(actual!=last);
        }
        sb.append("\n}");
        return sb.toString();
    }
    
    public Nodo nodo(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("index<0 || index>=size");
        }
        Nodo actual = last.getSig();
        for(int i=0; i<index; actual = actual.getSig(), i++){}
        return actual;
    }
}