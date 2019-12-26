//Encargado de codificar esta clase: Kevin Tumay
/*SecuenciaDeCeldasDisponibles.java*/
//Tipo de dato al que pertenecen los elementos de las celdas de LCD
package Clases;
public class SecuenciaDeCeldasDisponibles {
    //Numero de celda inicial:
    private int nci;
    //Numero de caracteres a partir de celda inicial:
    private int x;
    
    public SecuenciaDeCeldasDisponibles(int nci, int x){
        this.nci=nci;
        this.x=x;
    }
    
    public int getNci(){
        return nci;
    }
    
    public void setNci(int nci){
        this.nci=nci;
    }
    
    public int getX(){
        return x;
    }
    
    public void disminuirEspacio(int c){
        this.x = this.x-c;
    }
    
    public void aumentarEspacio(int c){
        this.x = this.x+c;
    }
    
    public String toString(){
        return "[Número de celda inicial: " + nci
                + "; cantidad de caracteres disponibles: " + x + "]";
    }
}
