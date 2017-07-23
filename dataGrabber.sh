#!/bin/bash
cd EatInTime-DataGrabber
mvn clean package && mvn exec:java -Dexec.mainClass="Main" 