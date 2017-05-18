# weather

A Weather Client Using XML

Application overview:
This application will connect to the National Weather Service web site using XML and SOAP to display current weather conditions. The user inputs Latitude and Longitude values and gets the Probability of precipitation, Dew Point, Wave Direction and Wind Speed as the output for the given location. In the background, the application makes a SOAP request to WSDL and connects to the resources of the National Weather Service web site. Now, we passed NDFDgen function to get the required parameters based on latitude and longitude. 

Tools used: Eclipse IDE (Version: Neon.2 Release (4.6.2)), Java Version 8 Update 121, Tomcat Server (Version 7.0)

Project file: weather.java

Features:
•	Client uses SOAP and/or XML to retrieve the
•	Client displays correct weather information (Shows 4 variables)
•	Session disconnects after fetching data
•	Client reconnects on command for a refresh

Steps to run the program:
1.	Once you configure the workspace run the code by clicking the Run icon and select the Java Application to run 
2.	Input the latitude and longitude values to get the output
 
Test Scenarios and implementation:
1.	Enter latitude and longitude as inputs and get the variables as output 
2.	Application requires the user to input ‘R’ for Restart or ‘E’ for Exit
Refresh: The operation restarts
Exit: Exits the program if E is typed, else any other letter or value also leads to exit 
 
Bugs and limitations: 
•	It shows the following warning whenever we run the code but still code works 
•	Did not handle exceptions all exceptions
•	Can execute only in console
•	Did not consider invalid inputs for Latitude and Longitude

Assumptions: 
•	Sockets implementation is not required
•	Tested for only few latitude and longitude values

References:
•	http://www.eclipse.org/webtools/community/education/web/t320/Implementing_a_Simple_Web_Service.pdf
•	https://www.youtube.com/watch?v=KFlDdb65w3U
•	https://graphical.weather.gov/xml/
•	http://stackoverflow.com/questions/27580655/how-to-set-a-date-as-input-in-java
•	http://stackoverflow.com/questions/2502340/noaa-web-service-for-current-weather	
•	http://www.journaldev.com/1194/java-xpath-example-tutorial
