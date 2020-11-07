#define sensorIR 7
#define button 8

boolean sensorValue;
boolean buttonValue;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(sensorIR, INPUT);
  pinMode(button, INPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  //sensorValue = digitalRead(sensorIR);

  //if(!sensorValue){
    //Serial.println("TEMPERATURA: 38");
    //delay(3000);
  //}
  buttonValue = digitalRead(button);

  if(buttonValue){
    Serial.println("HIGIENIZAÇÃO FEITA");
    delay(2000);
  }
}
