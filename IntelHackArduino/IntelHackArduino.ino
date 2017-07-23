

/**
 * This arduino code snippet reads the gas from the Grove Multichannel gas sensor and outputs the data in Json format in Serial.
 * delay of 1 second and calls the loop() function repeatedly
 */

//#include <Wire.h>
#include "MutichannelGasSensor.h"
//#include "HDC1000.h"
#include <SPI.h>
#include <WiFi.h>

//char ssid[] = "Supreme"; // your network SSID (name) 
//char pass[] = "AndyWarhol"; // your network password
//int status = WL_IDLE_STATUS; // the Wifi radio's status
//int isDebug = false; // a flag variable that lets you use either the development-mode or production-mode API
//WiFiClient client; // WifiClient object that enables the wifi connection
//char apiURL[] = "rocky-lake-67126.herokuapp.com"; // the url of the microservice app

int deviceID = 1; // for the purpose of test

float nh3; float co; float no2; float c3h8; float c4h10; float ch4; float h2; float c2h5oh;
// the variables for each gas element detected by the gas sensor

//double temperature; double humidity;
//// the variable for the temperature and humidity

void setup()
{
    /*
     * setup() sets up the baud rate, the gas sensor (connected to the board via I2C pin, and 
     * the wifi connection
     */

    //Bridge.begin();
    Serial.begin(115200);  // start serial for output (set up the baud rate at 115200 bpm)
    
    // configure the gas sensor
    gas.begin(0x04);//the default I2C address of the slave is 0x04
    gas.powerOn();

//    // configure the temperature & humidity sensor
//    HDC1000 tempHumid(0x41,2);
//    tempHumid.begin(HDC1000_BOTH_TEMP_HUMI, HDC1000_TEMP_HUMI_14BIT, HDC1000_HEAT_ON);

//    // configure the wifi
//    Serial.println("Attempting to connect to WPA network...");
//    status = WiFi.begin(ssid, pass);
//
//    // if you're not connected, stop here:
//    if ( status != WL_CONNECTED) { 
//      Serial.println("Couldn't get a wifi connection");
//      while(true);
//    } 
//    // if you are connected, print out info about the connection:
//    else {
//      Serial.println("Connected to network");    
//      uint16_t port = 80;    
//      if (client.connect(apiURL, port)){
//        Serial.println("connected to " + String(apiURL)); 
//      }else{
//        Serial.println("not calling " + String(apiURL));
//      }      
//    }

}

void loop()
{   
    /**
     * loop() retrieves data read from the gas sensor using the instance gas from MutichannelGasSensor.h,
     * parses them such that it creates the json string, and make a API call (insertRawData)
     * to the microservice app (the Sptring Boot project) to add the data to the data repository (NoSQL)
     */
    // call the functions that measures the gases read by the gas sensor
    nh3 = gas.measure_NH3();
    co = gas.measure_CO();
    no2 = gas.measure_NO2();
    c3h8 = gas.measure_C3H8();
    c4h10 = gas.measure_C4H10();
    ch4 = gas.measure_CH4();
    h2 = gas.measure_H2();
    c2h5oh = gas.measure_C2H5OH();

//    // call the functions that measures the temperature and humidity by the HDC1000 sensor
//    temperature = tempHumid.getTemp();
//    humidity = tempHumid.getHumi();

    // make a json string
    String data = "{\"device_key\": " + String(deviceID) + 
//      ", \"temperature\": " + String(int(temperature))+ "."+String(getDecimal(temperature)) + 
//      ", \"humidity\": " + String(int(humidity))+ "."+String(getDecimal(humidity)) +  
      ", \"NH3\": " + String(int(nh3))+ "."+String(getDecimal(nh3)) + 
      ", \"CO\": " + String(int(co))+ "."+String(getDecimal(co)) + 
      ", \"NO2\": " + String(int(no2))+ "."+String(getDecimal(no2)) + 
      ", \"C3H8\": " + String(int(c3h8))+ "."+String(getDecimal(c3h8)) + 
      ", \"C4H10\": " + String(int(c4h10))+ "."+String(getDecimal(c4h10)) +  
      ", \"CH4\": " + String(int(ch4))+ "."+String(getDecimal(ch4)) +  
      ", \"H2\": " + String(int(h2))+ "."+String(getDecimal(h2)) + 
      ", \"C2H5OH\": " + String(int(c2h5oh))+ "."+String(getDecimal(c2h5oh)) +  
      "}";

    Serial.println(data);

//    // make a API call using the WifiClient library (make a POST request of insertNewData)
//    client.println("POST /insertRawData HTTP/1.1");
//    client.println("HOST : " + String(apiURL));
//    client.println("Content-Type: application/json");
//    client.print("Content-Length: ");
//    client.println(data.length());
//    client.println("");
//    client.println(data);
//
//    unsigned long timeout = millis();
//
//    // Read all the lines of the reply from server and print them to Serial
//    if(client.available()){
//      Serial.println("the request complete!");
//      char str=client.read();
//      Serial.println(str);
//    }else{
//      while (client.available() == 0) {
//        if (millis() - timeout > 5000) {
//          Serial.println(">>> Client Timeout !");
//          client.stop();
//          break;
//        }
//      }
//    
//    }    

    delay(1000); // to do --> is the 1-second interval the right solution?
}

long getDecimal(float val)
{
  /**
   * getDecimal() takes a float value and retrusn its decimal part
   */
   int intPart = int(val);
   long decPart = 1000*(val-intPart); 
   if(decPart>0)return(decPart);           
   else if(decPart<0)return((-1)*decPart); 
   else if(decPart=0)return(00);         
}
