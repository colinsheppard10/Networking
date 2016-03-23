
#include <ESP8266WiFi.h>

const char* ssid     = "HOME-1682";
const char* password = "33F9FDA473994D43";

//const char* host = "seniordesign.ddns.net";
const char* host = "10.0.0.6";

void setup() {
  Serial.begin(115200);
  delay(100);

  // We start by connecting to a WiFi network

  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  
  WiFi.begin(ssid, password);
  
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");  
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
}

int value = 0;

void loop() {
 // delay(5000);
  ++value;

  Serial.print("connecting to ");
  Serial.println(host);
  
  // Use WiFiClient class to create TCP connections
  WiFiClient client;
  const int Port = 3490;
  if (!client.connect(host, Port)) {
    Serial.println("connection failed");
    return;
  }
  
  // This will send the request to the server
  client.print("0000000000");
  delay(500);
  
  // Read all the lines of the reply from server and print them to Serial
  while(client.available()){
    String line = client.readStringUntil('\r');
    char buffer[5];
    Serial.print(line);

    if ((line[1]=='D') && (line[2] == 'a') && (line[3] == 't') && (line[4] == 'e')){
      Serial.print("we have found the date");
      }
  }
  
  Serial.println();
  Serial.println("closing connection");
}
