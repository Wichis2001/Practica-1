/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enums;

/**
 *
 * @author luis
 */
public class Token {
   
    private String valor;
    private Tipo tipo;

    public Token(Tipo tipo, String valor) {
        this.valor = valor;
        this.tipo = tipo;
    }

    public enum Tipo{
        CORCHETE_IZQUIERDO, CORCHETE_DERECHO, LLAVE_IZQUIERDO, LLAVE_DERECHA, IDENTIFICADOR, PUNTOYCOMA, COMA, ENTERO, DECIMAL, ERROR
    }

    public String getValor() {
        return valor;
    }

    public Tipo getTipo() {
        return tipo;
    }    
    
    
    public String redaccionTipo(){
        Token.Tipo actual = this.tipo;
        switch(actual) {
            case CORCHETE_DERECHO:
                return "Corchete Derecho";
            case CORCHETE_IZQUIERDO:
                return "Corchete Izquierdo";
            case  LLAVE_DERECHA:
                return "Llave Derecha";
            case LLAVE_IZQUIERDO:
                return "Llave Izquierda";
            case IDENTIFICADOR:
                return "Identificador";
            case PUNTOYCOMA:
                return "Punto y Coma";
            case COMA:
                return "Coma";
            case ENTERO:
                return "Entero";
            case DECIMAL:
                return "Decimal";
            case ERROR:
                return "Error";
        } 
        return null;
    }
}
