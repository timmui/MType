

/**
* MType
*/

#include <SPI.h>
#include <WiFi.h>
#include <Wire.h>
#include <Seeed_QTouch.h>
#include <HashMap.h>

String yourdata = "mnhs";
char ssid[] = "mars-open-internet"; //  your network SSID (name) 
int i = -1;
int status = WL_IDLE_STATUS;
WiFiClient client;
char buff[200] = "0";

const byte HASH_SIZE = 26;
HashType<char*,char*> hashRawArray[HASH_SIZE];
HashMap<char*,char*> hashMap = HashMap<char*,char*>(hashRawArray , HASH_SIZE );



void setup()
{
  hashMap[0](".-","a");
  hashMap[1]("-...","b");
  hashMap[2]("-.-.","c");
  hashMap[3]("-..","d");
  hashMap[4](".","e");
  hashMap[5]("..-.","f");
  hashMap[6]("--.","g");
  hashMap[7]("....","h");
  hashMap[8]("..","i");
  hashMap[9](".---","j");
  hashMap[10]("-.-","k");
  hashMap[11](".-..","l");
  hashMap[12]("--","m");
  hashMap[13]("-.","n");
  hashMap[14]("---","o");
  hashMap[15](".--.","p");
  hashMap[16]("--.-","q");
  hashMap[17](".-.","r");
  hashMap[18]("...","s");
  hashMap[19]("-","t");
  hashMap[20]("..-","u");
  hashMap[21]("...-","v");
  hashMap[22](".--","w");
  hashMap[23]("-..-","x");
  hashMap[24]("-.--","y");
  hashMap[25]("--..","z");
  
  Serial.begin(9600);
  Serial.println("Connected to wifi");
  
//  Serial.println(hashMap.getValueOf("name"));
  Serial.println(ssid);
    // Connect to WPA/WPA2 network. Change this line if using open or WEP network:    
    status = WiFi.begin(ssid);
    // wait 10 seconds for connection:
    delay(10000);
  //printWifiStatus();
}

//struct nlist { /* table entry: */
//   struct nlist *next; /* next entry in chain */
//   char *name; /* defined name */
//   char *defn; /* replacement text */
//};
//{".-": "a", 
//"-...":"b",
//"-.-.":"c",
//"-..":"d",
//".":"e",
//"..-.":"f",
//"--.":"g",
//"....":"h",
//"..":"i",
//".---":"j",
//"-.-":"k",
//".-..":"l",
//"--":"m",
//"-.":"n",
//"---":"o",
//".--.":"p",
//"--.-":"q",
//".-.":"r",
//"...":"s",
//"-":"t",
//"..-":"u",
//"...-":"v",
//".--":"w",
//"-..-":"x",
//"-.--":"y",
//"--..":"z"};


// Message
String message = "";
String morseMessage = "";

int selectCount = 0;

void post(String message){
  while(1)
  String yourdata = message;
  char server1[] = "ecetitanic-mtype.azure-mobile.net/api/mtype?text=text&sender=you&receiver=microsoft";
  if (client.connect(server1, 80)) {
    Serial.println("connected to server");
    // Make a HTTP request:
    client.println("POST /post.php HTTP/1.1");
    client.println("Host: azure-mobile.net");
    client.println("User-Agent: Arduino/1.0");
    client.println("Content-Type: application/x-www-form-urlencoded; charset=UTF-8");
    client.println("Connection: close");
    client.print("Content-Length: ");
    client.println(yourdata.length());


    client.println();
    client.println(yourdata);

  }
  delay (2000);
  //Serial.println("****************");
  while (client.available()) {
    char c = client.read();
    Serial.write(c);
  }

  //Serial.println(buff);

}

void loop()
{
 int tn = QTouch.touchNum();
   
 if(tn == 0){
   // Dot
   selectCount = 0;
   morseMessage += '.';
   Serial.println(".");
 }
 else if(tn == 2){
   // Dash
   selectCount = 0;
   morseMessage += '-';
   Serial.println("-");
 }
 else if(tn == 1 && selectCount == 1){
   //Space
   selectCount = 0;
   message += " ";
   morseMessage = "";
   Serial.println(" ");
 }
 else if(tn == 1 && selectCount == 0){
   //Done Letter
   selectCount = 1;
//   message += (hash[morseMessage] != undefined)? hash[morseMessage] : "";
   morseMessage = "";
 }
 else if (QTouch.isTouch(1) || QTouch.isTouch(2)){
   //Send Message
   selectCount = 0;
 }
 Serial.println(message);
 post(message);
 delay(10);
}
