# EatInTime
EatInTime lets you measure the freshness of food and helps users take care of their food

## Problem Statement

## Solution --> EatInTime!

## System Design
![alt text](https://github.com/chanhyeoni/EatInTime/blob/master/Screen%20Shot%202017-07-22%20at%203.43.12%20PM.png)


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

### Run the code
Arduino<br />
Go to the folder IntelHackArduino and open the sketch file. Then, upload the sketch.

Other software<br />
Open the terminal or any window that you can type the command with. Run all the three shell scripts in a separate window. If for some reason a permission error pops up, type the following.

```
	chmod +x 'the name of the script file'
	...
	chmod +x apiEndPoint.sh
```

run the script files as follows

```
	./apiEndPoint.sh
	./dataGrabber.sh #must run this in a separate terminal
	./backgroundTask.sh #must run this in a separate terminal
```



