package testecomunicacaoserial;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComunicacaoSerial implements SerialPortEventListener{
    private String port;
    private static final int BAUD_RATE = 9600;
    private Scanner input;
    private OutputStream output;
    private CommPortIdentifier portId;
    private SerialPort serialPort;
    private String data;
    
    public ComunicacaoSerial(String ComPort){
        this.port = ComPort;
        
        try {
            this.portId = CommPortIdentifier.getPortIdentifier(this.port);
            serialPort = (SerialPort) portId.open(this.getClass().getName(), BAUD_RATE);
            
            serialPort.setSerialPortParams(BAUD_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
         
            input = new Scanner(serialPort.getInputStream());
            output = serialPort.getOutputStream();
            
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (NoSuchPortException ex) {
            System.out.println("Port not found");
        } catch (PortInUseException ex) {
            System.out.println("Port in use");
        } catch (IOException ex) {
            System.out.println("Input reader failed");
        } catch (UnsupportedCommOperationException ex) {
            System.out.println("Serial port params failed");
        } catch (TooManyListenersException ex) {
            System.out.println("Listener já adicionado");
        }
        
    }
    
    public String readData(){
        return data;
    }

    @Override
    public synchronized void serialEvent(SerialPortEvent event) {
        
        if(event.getEventType() == SerialPortEvent.DATA_AVAILABLE){
            try {
                data = input.nextLine();
            } catch (Exception ex) {
                System.out.println("Failed to read data");
            }
        }
    }
}
