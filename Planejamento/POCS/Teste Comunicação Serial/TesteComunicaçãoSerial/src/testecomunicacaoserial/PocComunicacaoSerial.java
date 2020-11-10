/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testecomunicacaoserial;

public class PocComunicacaoSerial {

    public static void main(String[] args) {
        
        ComunicacaoSerial serial = new ComunicacaoSerial("COM8");
        
        while(true){
            System.out.println(serial.readData());
        }    
       
    }
}
