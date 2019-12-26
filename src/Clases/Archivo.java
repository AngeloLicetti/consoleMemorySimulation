//Encargado de codificar esta clase: Rodrigo Fernandez
/*Archivo.java*/
//Tipo de dato al que pertenecen los elementos de las celdas de ED_1
package Clases;

import MiLibreria.Nodo;

public class Archivo {
    //Nombre del archivo:
    private String na;
    //Cantidad de caracteres:
    private int cc;
    //Primera celda:
    private Nodo pc;
    
    public Archivo(String na, int cc, Nodo pc){
        this.na=na;
        this.cc=cc;
        this.pc=pc;
    }
    
    public String toString(){
        return "[Nombre del archivo: " + na
                + "; cantidad de caracteres: " + cc
                + "; primera celda: " + pc + "]";
    }
    
    public String getNa(){
        return na;
    }
    
    public int getCc(){
        return cc;
    }
    
    public Nodo getPc(){
        return pc;
    }
    
    public void setPc(Nodo pc){
        this.pc=pc;
    }
}
