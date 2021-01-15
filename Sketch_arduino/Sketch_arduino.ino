#include <Wire.h>
#include <SparkFunMLX90614.h> // Biblioteca para comunicação com sensor de temperatura
//#include <SoftwareSerial.h> Opcional caso a comunicação seja através dos pinos do arduino

// Importação de bibliotecas 

#define sanitationCheck 7
#define tempCheck 8
#define buzzer 3

// Declaração de objetos 

IRTherm therm;

void setup() {
  pinMode(buzzer, OUTPUT);           // Seta buzzer como OUTPUT
  Serial.begin(9600);                // Inicia comunicação Comunicação Serial
  therm.begin();                     // Inicia o sensor de temperatura  
  therm.setUnit(TEMP_C);             //Seta a unidade de temperatura desejada
}

void loop() {
  if(digitalRead(tempCheck)){                                       // Lê sensor de obstáculo
    delay(2000);
    if(therm.read()){                                               // Lê a temperatura do usuário                                                 
      Serial.println(String(therm.object(), 2));                    // Converte a temperatura em formato String, e envia para a interface 
      tone(buzzer, 2800);                                           // Toca o buzzer para alertar o usuário que a medição foi realizada
      delay(100);
      noTone(buzzer);
      delay(100);
      tone(buzzer, 2800);
      delay(100);
      noTone(buzzer);
      delay(500);
    }

    while(true){                                                   
      if(digitalRead(sanitationCheck)){                             // Leitura em loop do estado do Switch responsável por detectar a realização da higienização
        Serial.println("Higienização feita");                       // Se a higienização foi feita, envia para a interface
      }
    }
    
  }
  delay(1000);
}
