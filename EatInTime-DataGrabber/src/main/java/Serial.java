import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.util.Enumeration;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.*;

public class Serial implements SerialPortEventListener {

    SerialPort serialPort;

    private static final String PORT_NAMES[] = {
            "/dev/cu.usbmodem1421", // Mac OS X
            "/dev/cu.usbmodem1411", // Mac OS X
            "/dev/tty.usbserial-A9007UX1", // Mac OS X
            "/dev/ttyUSB0", // Linux
            "COM3", // Windows
    };

    private BufferedReader input;
    private static OutputStream output;
    private static final int TIME_OUT = 4000000;
    private static final int DATA_RATE = 9600; 


    private boolean isDebug = true;
    //private static MongoDB dbObjMongoDb = new MongoDB("mongodb://heroku_tw4s316k:knsfmmk94vnt3onv3a3n88b5hq@ds145188.mlab.com:45188/heroku_tw4s316k", "heroku_tw4s316k");

    // Redis Client
    // URL : redis://h:pb6830aa876d513594fcb451b8cc41bf84d37142effd38f78a1dae83ff6eac31e@ec2-54-204-17-168.compute-1.amazonaws.com:18709

    private static String userPhoneNumber;

    public Serial(String phoneNumber){
        userPhoneNumber = phoneNumber;
    }

    public void initialize() {
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();

            for (String portName : PORT_NAMES) {
                System.out.println(portName + "   " + currPortId.getName());
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    System.out.println("got the port name!");
                    break;
                }
            }
        }
        if (portId == null) {
            System.out.println("Could not find COM port.");
            return;
        }

        System.out.println("the port id = " + portId.getName());

        try {
            serialPort = (SerialPort) portId.open(this.getClass().getName(),TIME_OUT);

            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,    SerialPort.PARITY_NONE);

            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();

            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        }
        catch (Exception e) {

            System.err.println(e.toString());
            this.close();
        }
    }

    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                // get the input line
                String inputLine=input.readLine();
                System.out.println(inputLine);

                // make HTTP request to insert the data
                String url = "rocky-lake-67126.herokuapp.com/insertRawData";
                if (isDebug){
                    url = "http://localhost:8080/insertRawData";
                }
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost postRequest = new HttpPost(url);

                StringEntity inputLineStringEntity = new StringEntity(inputLine);

                inputLineStringEntity.setContentType("application/json");
                postRequest.setEntity(inputLineStringEntity);                

                HttpResponse response = httpClient.execute(postRequest);

                // if (response.getStatusLine().getStatusCode() != 201) {
                //     throw new RuntimeException("Failed : HTTP error code : "
                //         + response.getStatusLine().getStatusCode());
                // }

                BufferedReader br = new BufferedReader(
                                new InputStreamReader((response.getEntity().getContent())));

                String output;
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                }

                // // httpClient.getConnectionManager().shutdown();

                JSONObject obj = new JSONObject(inputLine);
                int ch4_level = obj.getInt("CH4");

                if (ch4_level > 1000){
                    TwilloAPI twillo = new TwilloAPI("ACfab4e5e91d80e6a7e5a75c46bae99557", "01b3906a745ee451d37b35bbc583d6b9");
                    System.out.println("SEND SMS to " + userPhoneNumber);
                    String msg = "Alert! Your food is in danger of getting spoiled!";
                    twillo.sendMessage(userPhoneNumber, msg);                    
                }else{
                    System.out.println("DON'T SEND SMS!");
                }

            } catch (Exception e) {
                System.err.println(e.toString());
                //dataStreamObj.closeDataStream();
                this.close();
            }
        }
    }

    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            //dataStreamObj.closeDataStream();
            serialPort.close();
        }
    }

    public synchronized void send(int b){
        try{
            output.write(b);
        }
        catch (Exception e) {
            System.err.println(e.toString());
            //dataStreamObj.closeDataStream();
            this.close();
        }
    }

    public synchronized int read(){
        int b = 0;

        try{
            b = (int)input.read();
        }
        catch (Exception e) {
            System.err.println(e.toString());
            //dataStreamObj.closeDataStream();
            this.close();
        }
        return b;
    }
}