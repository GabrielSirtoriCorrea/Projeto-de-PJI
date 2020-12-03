/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 *
 * @author Gazebo
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label interactiveLabel;
    
    private ComunicacaoSerial serial;
    private String serialResponse = null;
    private String lastResponse=null;
            
    private boolean temperatureCheck = true;
    private boolean sanitationCheck = false;
    private int temperature;
    
    private EventHandler handler;
    private KeyFrame frame;
    private Timeline timelineLoop;
    private Timeline delay;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        serial = new ComunicacaoSerial("COM8");
        
        delay = new Timeline(new KeyFrame(Duration.seconds(5), (ActionEvent event) -> {
                    interactiveLabel.setText("Olá! Antes de entrar, vamos fazer algumas verificações em decorrência do Covid-19. "
                    + "Por favor, aproxime sua cabeça do sensor acima, e passe a mão sobre o outro sensor para fazer uma checagem de temperatura.");
               
                }));
        
        handler = (EventHandler) (Event event) -> {
            SerialLoop();
        };

        frame = new KeyFrame(Duration.millis(1000), handler);
        timelineLoop = new Timeline();
        timelineLoop.getKeyFrames().add(frame);
        timelineLoop.setCycleCount(Timeline.INDEFINITE);
        timelineLoop.play();
        
    }

    private void SerialLoop(){
        
        serialResponse = serial.readData();
        System.out.println(serialResponse);
        System.out.println(lastResponse);
        
        if( lastResponse != serialResponse){
        
            if(null != serialResponse && temperatureCheck && !"Botao pressionado".equals(serialResponse)){
                    System.out.println("Sensor Acionado"); // pegar temperatura

                    temperature = Integer.parseInt(serialResponse);

                    System.out.println(temperature);


                   if(temperature >= 38){
                       interactiveLabel.setText("Cuidado!!! Sua temperatura é de " + temperature +" graus e está muito elevada, "
                               + "por favor, retire-se do local e procure um médico! ");
                        temperatureCheck= true;
                        sanitationCheck = false;

                        delay.play();

                   }else{
                    interactiveLabel.setText("Muito bem! Sua tempeatura é de " + temperature +" graus. Agora vamos fazer uma rápida higienização. Pressione com o pé o "
                           + "pedal logo abaixo, e estenda sua mão para receber o álcool.");
                    temperatureCheck= false;
                    sanitationCheck = true;

                   }

                }    

                if("Botao pressionado".equals(serialResponse) && sanitationCheck){
                        System.out.println("PRESSIONADO");
                         temperatureCheck= true;
                         sanitationCheck = false;

                         interactiveLabel.setText("Pronto! Agora você já pode entrar no local. Só não se esqueça de colocar a "
                                      + "MÁSCARA! Boas compras!");
                          delay.play();

                }
            }
        lastResponse = serialResponse;
    }
}
    
