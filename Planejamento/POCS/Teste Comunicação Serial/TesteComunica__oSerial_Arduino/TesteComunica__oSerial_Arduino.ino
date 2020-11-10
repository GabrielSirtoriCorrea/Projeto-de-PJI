#define sensorIR 7
#define button 8

boolean sensorValue;
boolean buttonValue;

char cod;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(sensorIR, INPUT);
  pinMode(button, INPUT);
}

void loop() {
  sensorValue = digitalRead(sensorIR);
  buttonValue = digitalRead(button);

  while(Serial.available()){
    cod = Serial.read();

    switch(cod){
      case '1':
        Serial.write(!sensorValue);
        break;
      case '2':
        Serial.write('a');
        break;
    }

    
  }
}
