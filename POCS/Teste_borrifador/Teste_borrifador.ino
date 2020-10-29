#include <Servo.h>
#define servo1 2
#define servo2 4

Servo s1;
Servo s2;

void setup() {
  // put your setup code here, to run once:
  s1.attach(servo1);
  s2.attach(servo2);
  s1.write(180);
  s2.write(0);
  delay(5000);
  s1.write(0);
  s2.write(180);

}

void loop() {
  // put your main code here, to run repeatedly:
}
