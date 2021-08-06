/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enums;
import Manejadores.*;
import Enums.*;
import Ventanas.*;
import java.util.ArrayList;
/**
 *
 * @author luis
 */
public class ManejadorVentanaPrincipal {
    
    private final VentanaPrincipal ventana; 
    public ArrayList<Token> tokens = new ArrayList<>();
    
    public ManejadorVentanaPrincipal(VentanaPrincipal ventana) {
        this.ventana = ventana;
    }
    
    
    public void examinadorTexto(){
        String error="";
        String id="";
        String decimal="";
        String cadena=ventana.getTexto().getText();
        for (int i=0;i <cadena.length();i++ ){
            char caracter = cadena.charAt(i); 
            if(Character.compare(caracter, '}')==0){
                Token token = new Token(Token.Tipo.LLAVE_DERECHA,"}");
                tokens.add(token);
            } else if(Character.compare(caracter, '{')==0){
                Token token = new Token(Token.Tipo.LLAVE_IZQUIERDO,"{");
                tokens.add(token);
            } else if(Character.compare(caracter, '[')==0){
                Token token = new Token(Token.Tipo.CORCHETE_IZQUIERDO,"[");
                tokens.add(token);
            } else if(Character.compare(caracter, ']')==0){
                Token token = new Token(Token.Tipo.CORCHETE_DERECHO,"]");
                tokens.add(token);
            } else if(Character.compare(caracter, ',')==0){
                Token token = new Token(Token.Tipo.COMA,",");
                tokens.add(token);
            } else if(Character.compare(caracter, ';')==0){
                Token token = new Token(Token.Tipo.PUNTOYCOMA,";");
                tokens.add(token);
            } else if(Character.isWhitespace(caracter)==false){
                if(Character.isDigit(caracter)){
                    decimal=decimal+caracter;
                    error=error+caracter;
                } else {
                    if(Character.compare(caracter, '.')==0){
                        decimal=decimal+caracter;
                        error=error+caracter;
                    }
                }
            } else if(Character.isWhitespace(caracter)==true&&decimal!=("")&&error!=("")){
                int contador=0;
                for(int h=0;h<decimal.length();h++){
                    char corroborar= decimal.charAt(h);
                    if(Character.compare(corroborar, '.')==0){
                        contador=1;
                        if(h==decimal.length()-1){
                            contador=2;
                        }
                     }
                }    
                if (contador==0&&decimal!=""){
                    Token token = new Token(Token.Tipo.ENTERO,decimal);
                    tokens.add(token);
                    decimal="";
                    error="";
                } else if (contador==1&&decimal!=""){
                    Token token = new Token(Token.Tipo.DECIMAL,decimal);
                    tokens.add(token);
                    decimal="";
                    error="";
                } else if(contador==2&&decimal!=""){
                    Token token = new Token(Token.Tipo.ERROR,error);
                    tokens.add(token);
                    decimal="";
                    error="";
                }
          
            }
            
            if(Character.isWhitespace(caracter)==false){
                if(Character.isLetter(caracter)){
                    id=id+caracter;
                    error=error+caracter;
                    
                } 
            } else if(Character.isWhitespace(caracter)==true&&id!=("")){
                char analizar = id.charAt(0); 
                if(Character.isLetter(analizar)){
                    Token token = new Token(Token.Tipo.IDENTIFICADOR,id);
                    tokens.add(token);
                    id="";
                    error="";
                } else{
                    Token token = new Token(Token.Tipo.ERROR,error);
                    tokens.add(token);
                    id="";
                    error="";
                }
               
                
            } 
            
        }
        
        ventana.getResultado().setText("");
        this.llenarI(id,error);
        this.llenarD(decimal, error);
        for(Token token:tokens){
            ventana.getResultado().append(token.redaccionTipo() + ": "+ token.getValor()+"\n");
        }   
    }
    
    public void llenarI(String id, String error){
        if (!"".equals(id)){
            char analizar = id.charAt(0); 
            if(Character.isLetter(analizar)){
                Token token = new Token(Token.Tipo.IDENTIFICADOR,id);
                tokens.add(token);
                id="";
                error="";
            } else{
                Token token = new Token(Token.Tipo.ERROR,error);
                tokens.add(token);
                id="";
                error="";
            }
        }
    }
    
    public void llenarD(String decimal, String error){
        int contador=0;
        for(int h=0;h<decimal.length();h++){
            char corroborar= decimal.charAt(h);
            if(Character.compare(corroborar, '.')==0){
                contador=1;
                if(h==decimal.length()-1){
                    contador=2;
                }
             }
        }    
        if (contador==0&&decimal!=""){
            Token token = new Token(Token.Tipo.ENTERO,decimal);
            tokens.add(token);
            decimal="";
            error="";
        } else if (contador==1&&decimal!=""){
            Token token = new Token(Token.Tipo.DECIMAL,decimal);
            tokens.add(token);
            decimal="";
            error="";
        } else if(contador==2&&decimal!=""){
            Token token = new Token(Token.Tipo.ERROR,error);
            tokens.add(token);
            decimal="";
            error="";
        }
    }    
}
