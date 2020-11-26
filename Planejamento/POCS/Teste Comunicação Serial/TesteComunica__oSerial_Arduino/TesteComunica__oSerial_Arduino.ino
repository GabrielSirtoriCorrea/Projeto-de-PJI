#define sensorIR 7
#define button 8
#define buzzer 3

boolean sensorValue = false;
boolean buttonValue = false;

int cod;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(sensorIR, INPUT);
  pinMode(button, INPUT);
  pinMode(buzzer, OUTPUT);
}

void loop() {
  sensorValue = digitalRead(sensorIR);
  buttonValue = digitalRead(button);

  if(buttonValue){
    Serial.println("Botao pressionado");
  }
  
  if(!sensorValue){
    delay(2000);
    //LER TEMPERATURA
    Serial.println(36);
    tone(buzzer, 2800);
    delay(100);
    noTone(buzzer);
    delay(100);
    tone(buzzer, 2800);
    delay(100);
    noTone(buzzer);
    delay(500);
  }

  /*while(Serial.available()){
    cod = Serial.read();
    switch(cod){
      case 49:
        Serial.write(!sensorValue);
        break;
      case 2:
        Serial.println("teste");
        break;
    }

    
  }*/

  
}
