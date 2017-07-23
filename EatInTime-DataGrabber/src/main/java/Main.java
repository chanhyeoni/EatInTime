import java.util.concurrent.*;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) {

		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter your phone number (no '-') : ");
		int phoneNumber = reader.nextInt(); // Scans the next token of the input as an int.
		final String phoneNumberStr = "+1" + Integer.toString(phoneNumber);

		// Collect data from Arduino
		Runnable collectData = new Runnable(){

			@Override
			public void run(){
			    String threadName = Thread.currentThread().getName();
			    System.out.println("Hello " + threadName);
				Serial serialObj = new Serial(phoneNumberStr);
				serialObj.initialize();

			}
		};

		
		Thread thread1 = new Thread(collectData);
		thread1.start();
	}	
}