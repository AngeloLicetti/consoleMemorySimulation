//Encargado de codificar esta clase: Rodrigo Fernandez
//LC es una estructura de datos, específicamente, secuencia de nodos enlazados
//utilizada para almacenar los caracteres, es decir, como una memoria
//Nunca cambia su estructura en toda la corrida del programa
//Su estructura es creada desde que se crea un objeto de esta clase (en el constructor)
package Clases;

import MiLibreria.Nodo;

public class LC {
    //n es la cantidad de celdas que tiene LC y también la cantidad de caracteres de cada celda:
    private int n;
    private Nodo first;
    
    public LC(int n){
        //asigna n
        this.n = n;
        //llama al método para crear sus celdas (su estructura)
        //y esta estructura nunca cambia a partir de este momento
        crearCeldas();
    }
    
    public int length(){
        return n;
    }
    
    public Nodo getFirst(){
        return first;
    }
    
    public void setFirst(){
        this.first = first;
    }
    
    public Nodo getCelda(int index){
        return nodo(index);
    }
    
    //Autor de este método: Angelo Licetti
    //*este método solo se usa al momento de reComprimir lc
    public Nodo moverCadenaDeCeldasAlInicio( Nodo nuevoNodo, int topCadena){
        //NUEVA LÓGICA:
        //Este método utiliza una secuencia de celdas consecutivas de lc que contiene un archivo
        //y cambia de posición este grupo de celdas; las mueve al inicio de lc, sin alterar el
        //número de elementos de lc, a través de una lógica mucho más eficaz que antes
        //pues ahora se utilizan las referencias a los nodos de manera mucho más eficaz,
        //aprovechando de mejor manera los recursos y evitando iteraciones inecesarias que
        //existían en la lógica utilizada en la primera presentación del proyecto
        int indiceDelPrimerNodoDeLaCadena = indexOf(nuevoNodo);
        if(indiceDelPrimerNodoDeLaCadena==0){
            return first;
        }
        int indiceDelNodoAnteriorALaCadena = indiceDelPrimerNodoDeLaCadena-1;
        Nodo nodoAnteriorALaCadena = nodo(indiceDelNodoAnteriorALaCadena);
        int indiceDelNodoPosteriorALaCadena = indiceDelPrimerNodoDeLaCadena + topCadena;
        Nodo nodoSiguienteALaCadena;
        if(indiceDelNodoPosteriorALaCadena>=n){
            nodoSiguienteALaCadena=null;
        } else{
            nodoSiguienteALaCadena=nodoAnteriorALaCadena.getSig();
            for(int i=0; i<topCadena; i++){
                nodoSiguienteALaCadena=nodoSiguienteALaCadena.getSig();
            }
        }
        //obtiene el ultimoNodoEnLaCadena de nodos a insertar:
        Nodo ultimoEnLaCadena=nuevoNodo;
        for(int i=0; i<(topCadena-1); i++){
            ultimoEnLaCadena=ultimoEnLaCadena.getSig();
        }
        //Todo el código usado arriba se necesita solamente para referenciar 
        //las variables que se van a usar a partir de la línea 74:
        //lo que hace este método se puede entender facilmente leyendo
        //e interpretando las líneas de código que se presentan a continuación:
        nodoAnteriorALaCadena.setSig(nodoSiguienteALaCadena);
        ultimoEnLaCadena.setSig(first);
        first=nuevoNodo;
        return first;
    }
    
    private void crearCeldas(){
        //CeldaDeCaracteres es el tipo de dato que contiene cada nodo de Lc
        CeldaDeCaracteres celda;
        //Crea n celdas usando un for:
        for(int i=0; i<n; i++){
            //NuevoFirst = nodo que será añadido al comienzo de la secuencia de nodos
            Nodo nuevoFirst;
            //celda = nueva CeldaDeCaracteres que recibe un nuevo arreglo de caracteres de tamaño n
            celda = new CeldaDeCaracteres(new char[n]);
            //nuevoFirst = nuevo nodo que recibe el elemento celda (CeldaDeCaracteres con arreglo vacío de tamaño n):
            nuevoFirst = new Nodo(celda);
            //como lo inserta al inicio, si es el primer elemento que se va a insertar
            //simplemente se lo asigna a first
            if(i==0){
                first=nuevoFirst;
                continue;
            }
            //en caso contrario, se le asigna al nuevo nodo como siguiente nodo a first
            //y luego se asigna el nuevo nodo a first
            //en otras palabras, inserta el nuevo nodo al inicio:
            nuevoFirst.setSig(first);
            first = nuevoFirst;
        }
    }
    
    public String toString(){
        String ret="{";
        Nodo actual = first;
        for(int i=0; i<n; i++){
            ret+="\n\t" + actual.toString();
            if(i!=n-1){
                ret+=",";
            } else{
                ret+="\n}";
            }
            actual=actual.getSig();
        }
        return ret;
    }
    
    public Nodo nodo(int index){
        if(index<0 || index>=n){
            throw new IllegalArgumentException("index<0 || index>=size; index = " + index);
        }
        Nodo actual = first;
        for(int i=0; i<index; actual = actual.getSig(), i++){}
        return actual;
    }
    
    public int indexOf(Nodo nod){
        //retorna el índice de la primera ocurrencia del nodo nod
        Nodo actual = first;
        for(int i=0; i<n; i++){
            if(actual.equals(nod)){
                return i;
            }
            actual = actual.getSig();
        }
        return -1;
    }
    
    //ACTUALIZACIÓN: ESTE MÉTODO YA NO ES USADO
    /*
    public void reemplazarPor(LC nuevoLc){
        //cambia todos los datos de lc por los datos de un nuevo lc
        //este método es invocado desde la clase ED_1 en el método recomprimirLc
        //basicamente reemplaza los datos de lc por los del nuevo lc, el cual contiene
        //los mismos datos de lc pero comprimidos todos a la derecha
        //el nodo nuevoFirst es el first del nuevoLc
        Nodo nuevoFirst = nuevoLc.getCelda(0);
        //como lc es una secuencia de nodos enlazados para reemplazar sus datos por los del nuevo lc
        //simplemente asigna el nuevoFirst a first:
        first=nuevoFirst;
    }*/
}
