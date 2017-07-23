# EatInTime
EatInTime lets you measure the freshness of food and helps users take care of their food

## Problem Statement

## Solution --> EatInTime!

## System Design
![alt text](https://github.com/chanhyeoni/EatInTime/blob/master/Screen%20Shot%202017-07-23%20at%204.00.09%20PM.png)


## Running the project
### Set up the Hardware
Preapre the following: <br />
&nbsp;Arduino 101<br />
&nbsp;Base Shield V2<br />
&nbsp;The Grove Multichannel Gas Sensor<br />
&nbsp;The USB to Type B calbe<br />
&nbsp;The Grove 4 pin connector<br />
Connect the USB to Type B cable to the Arduino board and to your computer. Then. mount the Base Shield on top of the microcontroller board. Lastly, connect the gas sensor to the I2C port of the Base Shield.
![alt text](https://github.com/chanhyeoni/EatInTime/blob/master/Screen%20Shot%202017-07-23%20at%202.50.01%20PM.png)

The wire diagram is as follows:


### Run the code
Arduino<br />
Go to the folder IntelHackArduino and open the sketch file. Then, upload the sketch.

Other software<br />
Open the terminal or any window that you can type the command with. Run the following command:
'''
	./dataGrabber.sh
'''

You will see the input prompt message to type your phone number to receive the alert message. Type your ten-digit phone number without any dash line.
'''
	Enter your phone number (no '-') :    
'''

You may use any food container you like or have. Make sure the sensor connected to the board is inside the food container. And test it with both fresh food and the food that you would like to throw into garbage
