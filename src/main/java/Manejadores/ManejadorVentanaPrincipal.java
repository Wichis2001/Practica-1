/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manejadores;
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
        String id="";
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
                if(Character.isLetterOrDigit(caracter)||(Character.compare(caracter, '.')==0)){
                    id=id+caracter; 
                } 
            } else if(Character.isWhitespace(caracter)==true&&id!=("")){
                char analizar = id.charAt(0); 
                if(Character.isLetter(analizar)){
                    Token token = new Token(Token.Tipo.IDENTIFICADOR,id);
                    tokens.add(token);
                    id="";
                } else{
                    int contador=0;
                    for(int x=0;x<id.length();x++){
                        char prueba = id.charAt(x); 
                        if(Character.isDigit(prueba)){
                            contador=contador+1;
                        }
                        if (Character.compare(prueba, '.')==0){
                            contador=contador+1;
                        }
                    }
                    if (contador==id.length()){           
                        int contador2=0;
                        for(int h=0;h<id.length();h++){
                            char corroborar= id.charAt(h);
                            if(Character.compare(corroborar, '.')==0){
                                contador2=1;
                                if(h==id.length()-1){
                                    contador2=2;
                                }
                            }
                        }    
                        if (contador2==0&&id!=""){
                            Token token = new Token(Token.Tipo.ENTERO,id);
                            tokens.add(token);
                            id="";
                            contador2=0;
                        } else if (contador2==1&&id!=""){
                            Token token = new Token(Token.Tipo.DECIMAL,id);
                            tokens.add(token);
                            id="";
                            contador2=0;
                        } else if(contador2==2&&id!=""){
                            Token token = new Token(Token.Tipo.ERROR,id);
                            tokens.add(token);
                            id="";
                            contador2=0;
                        }
   
                    } else {
                       Token token = new Token(Token.Tipo.ERROR,id);
                        tokens.add(token);
                        id="";
                    }
                    
                }
               
 
            } 
            
        }
        
        ventana.getResultado().setText("");
        this.llenarI(id);
        for(Token token:tokens){
            ventana.getResultado().append(token.redaccionTipo() + ": "+ token.getValor()+"\n");
        }   
    }
    
    public void llenarI(String id){
        if (!"".equals(id)){
            char analizar = id.charAt(0); 
            if(Character.isLetter(analizar)){
                Token token = new Token(Token.Tipo.IDENTIFICADOR,id);
                tokens.add(token);
                id="";
            } else{
                int contador=0;
                for(int x=0;x<id.length();x++){
                    char prueba = id.charAt(x); 
                    if(Character.isDigit(prueba)||(Character.compare(prueba, '.')==0)){
                        contador++;
                    }
                }
                if (contador==id.length()){
                    int contador2=0;
                    for(int h=0;h<id.length();h++){
                        char corroborar= id.charAt(h);
                        if(Character.compare(corroborar, '.')==0){
                            contador2=1;
                            if(h==id.length()-1){
                                contador2=2;
                            }
                        }
                    }    
                    if (contador2==0&&id!=""){
                        Token token = new Token(Token.Tipo.ENTERO,id);
                        tokens.add(token);
                        id="";
                        contador2=0;
                    } else if (contador2==1&&id!=""){
                        Token token = new Token(Token.Tipo.DECIMAL,id);
                        tokens.add(token);
                        id="";
                        contador2=0;
                    } else if(contador2==2&&id!=""){
                        Token token = new Token(Token.Tipo.ERROR,id);
                        tokens.add(token);
                        id="";
                        contador2=0;
                    }
                } else {
                   Token token = new Token(Token.Tipo.ERROR,id);
                    tokens.add(token);
                    id="";
                }

            }
        }
    }
}
