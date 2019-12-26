//Encargado de codificar esta clase: Angelo Licetti
//LCD es una implementación del TDA lista
//Como se usan 2 implementaciones del TDA lista usadas en el programa (ambas del tipo secuencial circular)
//y ambas usan los mismos operadores de este TDA, se usa una clase padre llamada
//ListaSecuencial2, la cual es la clase padre que heredan ED_1 y LCD
package Clases;

import MiLibreria.ES;
import MiLibreria.InterfazLista;
import MiLibreria.Nodo;
import MiLibreria.ListaSecuencial2;

public class LCD extends ListaSecuencial2{
    private int n;
    
    public LCD(int n){
        //invoca al constructor de ListaSecuencial2
        super();
        //asigna n:
        this.n=n;
        //crea una SecuenciaDeCeldasDisponibles que inicia en la celda 0 de lcd y tiene n*n espacios disponubles
        SecuenciaDeCeldasDisponibles scd = new SecuenciaDeCeldasDisponibles(0, n*n);
        //agrega la SecuenciaDeCelsaDisponibles creada anteriormente a lcd:
        add(0, scd);
    }
    
    public boolean hayEspacioConsecutivoPara(int tamaño){
        //retorna verdadero solo si hay alguna SecuenciaDeCaracteresDisponibles en lcd con
        //capacidad suficiente para almacenar "tamaño" caracteres
        //recorre cada elemento de lcd para buscar alguna SecuenciaDeCaracteresDisponibles con tamaño
        //igual o mayor que el "tamaño"
        Nodo actual = last.getSig();
        for(int i=0; i<size; i++){
            if(((SecuenciaDeCeldasDisponibles)actual.getE()).getX()>=tamaño){
                //en cuanto encuentra una SecuenciaDeCaracteresDisponibles con igual o mayor
                //tamaño que "tamaño" retorna verdadero
                return true;
            }
            actual = actual.getSig();
        }
        //si no encuentra ninguna, retorna falso:
        return false;
    }
    
    public Nodo insertarCaracteres(LC lc, char[] caracteres, int tamaño){
        //este método inserta los caracteres del archivo en lc, modifica lcd convenientemente
        //y retorna la primera celda de lc usada:
        Nodo pc = null;
        //elige el espacio de lcd más pequeño en donde se pueden almacenar los caracteres
        int celdasNecesarias = tamaño%n==0? (int)tamaño/n : ((int)tamaño/n)+1;
        SecuenciaDeCeldasDisponibles secuenciaIdeal = secuenciaMasPequeniaCon(celdasNecesarias*n);
        //celdas sobrantes = celdas de la secuencia disponible -(menos) celdas necesarias
        int celdasSobrantes = ((secuenciaIdeal.getX())/n)-celdasNecesarias;
        //asigna a pc ubicacion de la primera celda que se usará para guardar los caracteres
        //el índice de esta celda será el número de celda inicial de la secuencia más pequeña encontrada
        //anteriormente, más las celdas sobrantes; en otras palabras,
        //el indice pc será el indice de la celda más a la derecha posible, tal como lo indican
        //las instrucciones del proyecto en el pdf:
        pc = lc.getCelda(secuenciaIdeal.getNci() + celdasSobrantes);
        //guarda los caracteres en lc, a partir de la celda pc:
        cargarCaracteres(caracteres, tamaño, pc);
        //acomoda los elementos de lcd convenientemente:
        secuenciaIdeal.disminuirEspacio(celdasNecesarias*n);
        //IMPORTANTE: si luego de reducir el espacio de la secuencia este espacio es ahora igual a cero
        //entonces la secuencia debe ser eliminada de lcd
        if(secuenciaIdeal.getX()==0){
            remove(indexOf(secuenciaIdeal));
        }
        return pc;
    }
    
    public void cargarCaracteres(char[] caracteres, int tamaño, Nodo pc){
        //j es un contador que permite avanzar en los espacios del arreglo
        //perteneciente a cada elemento de LC:
        int j=0;
        //asigna iterativamente los caracteres uno por uno a las celdas de LC a partir de pc:
        for(int i=0; i<tamaño; i++){
            //si i no es 0 e i es divisible por n, es dceir,
            //si está en la última posición del arreglo de una celda de PC
            //entonces pasa a la siguiente celda y reinicia el valor de j a 0:
            if(i!=0 && i%n==0){
                j=0;
                pc=pc.getSig();
            }
            //asigna un caracter al arreglo de la celda actual en la posición j del arreglo:
            ((CeldaDeCaracteres)pc.getE()).caracteres[j]=caracteres[i];
            //aumenta j en 1 para seguir recorriendo el arreglo:
            j++;
            //limpia los espacios sobrantes de la ultima celda usada para insertar este archivo
            //por si habian caracteres de algún archivo eliminado en el pasado que ya no queremos
            //que se visualizen al imprimir esta celda con caracteres del nuevo archivo que se 
            //acaba de cargar; asignar el caracter '\0' equivale a asignar el valor de null:
            if(i==(tamaño-1)){
                while(j<n){
                    ((CeldaDeCaracteres)pc.getE()).caracteres[j]= '\0';
                    j++;
                }
            }
        }
    }
    
    public SecuenciaDeCeldasDisponibles secuenciaMasPequeniaCon(int caracteresNecesarios){
        //retorna la secuencia mas pequeña capaz de almacenar los caracteresNecesarios
        SecuenciaDeCeldasDisponibles ret=null;
        Nodo actual=last.getSig();
        int cantidadActual;
        for(int i=0; i<size; i++, actual=actual.getSig()){
            cantidadActual=((SecuenciaDeCeldasDisponibles)actual.getE()).getX();
            if(cantidadActual>=caracteresNecesarios){
                if(ret==null||ret.equals(null)){
                    ret = (SecuenciaDeCeldasDisponibles)actual.getE();
                } else if(cantidadActual<=ret.getX()){
                    ret = (SecuenciaDeCeldasDisponibles)actual.getE();
                }
            }
        }
        return ret;
    }
    
    public void reComprimirrLc(ED_1 ed, LC lc){//reacomoda lc según lo especificado en el pdf del proyecto
        //si lcd está vacía; es decir, si no hay secuencias de celdas disponibles, entonces
        //entonces no hay necesidad de ejecutar todo el código de abajo, pues significa que lc está
        //lleno y, por lo tanto, no se puede comprimir más:
        if(isEmpty()){
            return;
        }
        //si lcd no está vacío entonces todos los espacios libres deben quedar como últimas celdas de LC
        //se mueven los datos de lc (todo se junta a la izquierda) y se modifica ed convenientemente
        //esto se hace a través del método recomprimirLc de ed, que a su vez retorna el indice de la primera
        //celda disponible en lc luego de comprimir todo al lado izquierdo:
        int primeraCeldaDisponible = ed.recomprimirLc(lc);
        //las celdas disponibles ahor están todas a la izquierda y son igual a n menos el indice
        //de la primera celda disponible en lc luego de comprimir todo al lado izquierdo:
        int celdasDisponibles = n-primeraCeldaDisponible;
        //modifica convenientemente lcd:
        //convierte lcd en un lcd nuevo:
        clear();
        //nuevoEspacioDisponible = numero de caracteres disponibles total de las celdas vacías
        //que ya han sido reacomodadas todas al final:
        int nuevoEspacioDisponible = n*celdasDisponibles;
        //se crea UNA secuencia de celdas disponibles comenzando en la primera celda disponible
        //y con una cantidad de caracterres igual al nuevoEspacioDisponible
        SecuenciaDeCeldasDisponibles scd = new SecuenciaDeCeldasDisponibles(primeraCeldaDisponible, nuevoEspacioDisponible);
        //añade la secuencia creada a lcd
        add(0, scd);
    }
    
    public void removeArchivo(int pc, int tamaño){
        //nuevasCeldasDisponibles = las celdas que ocupaban el archivo que ha sido eliminado y
        //que ahora están disponibles
        int nuevasCeldasDisponibles = tamaño%n==0? (int)tamaño/n : ((int)tamaño/n)+1;
        int nuevoEspacioDisponible = nuevasCeldasDisponibles*n;
        int izq = indiceDeCeldaInmediataIzquierda(pc);
        int der = indiceDeCeldaInmediataDerecha(pc, nuevasCeldasDisponibles);
        if(izq>=0){
            if(der>=0){
                //si tiene secuencias de espacios disponibles inmediatamente a la izquierda y a la derecha:
                //obtiene cuanto espacio hay a la derecha:
                int espacioDerecha = ((SecuenciaDeCeldasDisponibles)(get(der))).getX();
                //aumenta a la secuencia de celdas disponibles de la izquierda
                //el nuevo espacio disponible del archivo eliminado
                //más (+) el espacio de la secuencia de celdas disponibles
                //que estaba a la derecha del archivo eliminado
                //en otras palabras, junta las tres secuencias dsiponibles en una sola
                //porque tras eliminar el archivo estas tres scuencias se vuelven consecutivas
                ((SecuenciaDeCeldasDisponibles)(get(izq))).aumentarEspacio(nuevoEspacioDisponible + espacioDerecha);
                //elimina el de la derecha, pues este espacio ya ha sido agregado al elemento de la izquierda:
                remove(der);
                return;
            }
            //si tiene secuencias de espacios disponibles SOLO inmediatamente a la izquierda:
            //aumenta el nuevo espacio disponible del archivo elimniado
            //a la secuencia que se encontró inmediatamente a la izquierda de este espacio
            //en otras palabras, junta ambos espacios en uno solo:
            ((SecuenciaDeCeldasDisponibles)(get(izq))).aumentarEspacio(nuevoEspacioDisponible);
            return;
        }
        if(der>=0){
            //si tiene secuencias de espacios disponibles SOLO inmediatamente a la derecha:
            //asigna como nci (numero de celda inicial) de la secuencia de celdas disponibles de la izquierda
            //el valor de la primera celda del archivo eliminado y
            //aumenta el nuevo espacio disponible del archivo elimniado
            //a la secuencia que se encontró inmediatamente a la derecha de este espacio
            //en otras palabras, junta ambos espacios en uno solo:
            ((SecuenciaDeCeldasDisponibles)(get(der))).setNci(pc);
            ((SecuenciaDeCeldasDisponibles)(get(der))).aumentarEspacio(nuevoEspacioDisponible);
            return;
        }
        //si no tiene secuencias de espacios disponibles inmediatos ni a la izquierda ni a la derecha:
        SecuenciaDeCeldasDisponibles nuevaSecuencia = new SecuenciaDeCeldasDisponibles(pc, nuevoEspacioDisponible);
        add(nuevoIndicePara(pc, tamaño), nuevaSecuencia);
    }
    
    public int nuevoIndicePara(int pc, int tamaño){
        //retorna el indice apropiado para insertar en el la nueva secuencia de caracteres disponibles
        //de modo que esta estructura mantenga un orden adecuado para la facilitar su visualizacion al imprimirla
        //luvia de ideas: contar con un for hasta que el nci del e del nodo actual sea mayor a pc;
        //es solo provisional; si el profe lo encuentra ineficiente e innecesario se descarta:
        Nodo actual = last.getSig();
        for(int i=0; i<size; actual = actual.getSig(), i++){
            if(((SecuenciaDeCeldasDisponibles)(actual.getE())).getNci()>pc){
                return i;
            }
        }
        return size;
    }
    
    private int indiceDeCeldaInmediataIzquierda(int pc){
        //busca si hay una secuencia de celdas disponibles justo inmeidatamente a la izquierda de
        //el archivo eliminado; si es que encuentra una, retorna su indice en lcd
        Nodo actual = last.getSig();
        for(int i=0; i<size; actual = actual.getSig(), i++){
            SecuenciaDeCeldasDisponibles tempSec =(SecuenciaDeCeldasDisponibles)actual.getE();
            if(tempSec.getNci() + (tempSec.getX()/n) == pc){
                return i;
            }
        }
        //si no encuentra ninguna, es decir, si no había ninguna secuencia de celdas disponibles
        //inmediatamente a la izquierda del espacio donde estaba el archivo eliminado, entonces
        //retorna un número negativo:
        return -1;
    }
    
    private int indiceDeCeldaInmediataDerecha(int pc, int nuevasCeldas){
        //busca si hay una secuencia de celdas disponibles justo inmeidatamente a la derecha de
        //el archivo eliminado; si es que encuentra una, retorna su indice en lcd
        Nodo actual = last.getSig();
        for(int i=0; i<size; actual = actual.getSig(), i++){
            SecuenciaDeCeldasDisponibles tempSec =(SecuenciaDeCeldasDisponibles)actual.getE();
            if(pc + nuevasCeldas == tempSec.getNci()){
                return i;
            }
        }
        //si no encuentra ninguna, es decir, si no había ninguna secuencia de celdas disponibles
        //inmediatamente a la derecha del espacio donde estaba el archivo eliminado, entonces
        //retorna un número negativo:
        return -1;
    }
}
