#define sensorIR 7
#define button 8

boolean sensorValue = false;
boolean buttonValue = false;

int cod;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(sensorIR, INPUT);
  pinMode(button, INPUT);
}

void loop() {
  sensorValue = digitalRead(sensorIR);
  buttonValue = digitalRead(button);

  if(buttonValue){
    Serial.println("Botao pressionado");
  }
  if(!sensorValue){
    Serial.println("Sensor acionado");
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
