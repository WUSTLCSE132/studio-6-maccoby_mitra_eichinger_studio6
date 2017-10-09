const int buttonPin = 2;
int p = 0;
unsigned long debounceDelay = 200;
unsigned long lastDebounceTime = 0;

void buttonPressed() {
  if((millis()-lastDebounceTime) > debounceDelay){
     p++;
     lastDebounceTime = millis();
  }
}

void setup() {
  pinMode(buttonPin, INPUT_PULLUP);

  // Interrupts can happen on "edges" of signals.  
  // Three edge types are supported: CHANGE, RISING, and FALLING 
  attachInterrupt(digitalPinToInterrupt(buttonPin), buttonPressed, FALLING);
  Serial.begin(9600);
}

void loop() {
    Serial.println(p);
    delay(1000);
}
