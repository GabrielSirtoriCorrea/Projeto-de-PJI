/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testecomunicacaoserial;

public class PocComunicacaoSerial {

    public static void main(String[] args) throws InterruptedException {
        String response;
        ComunicacaoSerial serial = new ComunicacaoSerial("COM8");
        
        while(true){
           response = serial.readData();
            System.out.println(response);
         
            if("Sensor acionado".equals(response)){
                System.out.println("ACIONADO");
                break;
            }
            
         }
        
         while(true){
           response = serial.readData();
            System.out.println(response);
         
            if("Botao pressionado".equals(response)){
                System.out.println("PRESSIONADO");
                break;
            }
            
         }
        
        
       }
            
            
  }    
        
       
   
