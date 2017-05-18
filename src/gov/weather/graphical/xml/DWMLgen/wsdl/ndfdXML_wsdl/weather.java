/** Name: Saipriya Thirvakadu, Student ID: 1001373543
	References: http://www.eclipse.org/webtools/community/education/web/t320/Implementing_a_Simple_Web_Service.pdf
				https://www.youtube.com/watch?v=KFlDdb65w3U
	 			https://graphical.weather.gov/xml/
	 			http://stackoverflow.com/questions/27580655/how-to-set-a-date-as-input-in-java
	 			http://stackoverflow.com/questions/2502340/noaa-web-service-for-current-weather	
	 			http://www.journaldev.com/1194/java-xpath-example-tutorial**/

package gov.weather.graphical.xml.DWMLgen.wsdl.ndfdXML_wsdl;		//Imported Package to use the web service
// Import statements for this project 
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class weather {				

 	public static void main(String[] args) throws Exception {
 		
 		startprogram();													// Function call to start the program
 	}
 		public static void startprogram() throws SAXException, IOException, ParserConfigurationException		// Start the program 
, InterruptedException
 		{
    	NdfdXMLPortTypeProxy ndfd = new NdfdXMLPortTypeProxy();			// This creates an object for NdfdXMLPortTypeProxy.java
    	@SuppressWarnings("resource")							
		Scanner scan = new Scanner(System.in);     						// This is used to read the latitude and longitude values
    	System.out.print("Enter the value of Latitude(Example: 38.99): ");				
    	BigDecimal latitude=(BigDecimal)scan.nextBigDecimal();			// Read the latitude value
    	System.out.print("Enter the value of Longitude(Example: -77.01): ");
    	BigDecimal longitude=(BigDecimal)scan.nextBigDecimal();			// Read the longitude value
    	//BigDecimal latitude=new BigDecimal(32.65829);   	
    	//BigDecimal longitude=new BigDecimal(-97.09509);
    	System.out.println("Latitude:"+latitude+" Longitude:"+longitude);		// Display the latitude and longitude values
    	WeatherParametersType weatherParameters = new WeatherParametersType();		// Create an object for Weather Parameters
		
		weatherParameters.setPop12(true);								// Set the Pop12 value to true 
		weatherParameters.setDew(true);									// Set the dew value to true
		weatherParameters.setWdir(true);								// Set the Wind direction value to true
		//weatherParameters.setTemp(true);								
		weatherParameters.setMint(true);								// Set the minimum temperature to true
		weatherParameters.setWspd(true);								// Set the Wind Speed to true
		weatherParameters.setMaxt(true); 								// Set the Max temperature to true
		
		
		Calendar  time = new GregorianCalendar(2017,04,31);				// Pass this as a GregorianCalendar for the Calendar to understand
		time.setTime(new Date());										// Set the time as new date
		String s = ndfd.NDFDgen(latitude, longitude, "time-series",time, time, "e" , weatherParameters);			// Call the NDFDgen function from NDFDgenproxy
    	System.out.println(s);											// Show the output
    	
    	Document doc = stringToDom(s);									// Load the XML for further parsing			
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();	// This defines a factory API that enables applications to obtain a parser that produce DOM object trees from XML documents	
        factory.setNamespaceAware(true);								// Setting the namespace aware to true
        DocumentBuilder builder;										// Create an object for Document Builder 
       
        try {
            builder = factory.newDocumentBuilder();							// Set the value of builder
           // doc = builder.parse(doc);
            // doc = builder.parse("C:/Users/Saipriya/workspace/First/weather_test.xml");
            XPathFactory xpathFactory = XPathFactory.newInstance();			//	Initiate Xpath Factory object
            																// Create XPath object
            XPath xpath = xpathFactory.newXPath();
            System.out.println("\nParameters for display:");				// Show the parameters for displaying the values

            String pop = getpop(doc, xpath);								// Get the value of Probability of precepitation
            System.out.println("Probability of precipitation=: " + pop);
            String dew = getdewpoint(doc, xpath);							// Get the value of Dew point
            System.out.println("Dew Point=: " + dew);
            //String waveh = getwaveheight(doc, xpath);
            //System.out.println("Wave Height=: " + waveh);
            String waved = getwavedirection(doc, xpath);					// Get the value of wave direction
            System.out.println("Wave Direction=: " + waved);
            // String max = getmaxtemp(doc, xpath);							// Get the value of maximum temperature
            //	System.out.println("Maximum temperature=: " + max);
            String speed = getwindspeed(doc, xpath);						// Get the value of Wind speed
            System.out.println("Wind Speed=: " + speed);
         
            System.out.print("\nType R to refresh and E to Exit:");			//Type R to refresh and E to Exit
            char c = scan.next().charAt(0);
            if(c=='R')														// If user input is R to perform refresh
            {	
            	//Refresh
            	System.out.println("Refreshing the program!");				
            	startprogram();												// Start the program again
            }else if(c=='E') 												// If user input is E to perform exit
            {
            	System.out.println("Exiting the program!");					
            	System.exit(0);												// Exit the system
            }
            else															// If the user typed wrong input then exit
            {	
            	System.out.println("Invalid input! We are exiting! ");
                System.exit(0);
            }
            
        } catch (ParserConfigurationException e) {							// Provide exception handler
            e.printStackTrace();
        }
 	}
	
   
    private static String getpop(Document doc, XPath xpath) {				// Function definition to get the value of POP12
        String name = null;
        try {
            XPathExpression pop = xpath.compile("/dwml/data/parameters/probability-of-precipitation/value/text()");
            name = (String) pop.evaluate(doc, XPathConstants.STRING);
        } catch (XPathExpressionException e) {								// Handle exception
            e.printStackTrace();
        }

        return name;
    }
    
//    private static String getmaxtemp(Document doc, XPath xpath) {			// Function definition to get the value of Max temperature
//        String name = null;
//        try {
//            XPathExpression maxtemp = xpath.compile("/dwml/data/parameters/temperature[@type='maximum']/value/text()");
//            name = (String) maxtemp.evaluate(doc, XPathConstants.STRING);
//        } catch (XPathExpressionException e) {
//            e.printStackTrace();
//        }
//
//        return name;
//    }
    
    private static String getdewpoint(Document doc, XPath xpath) {			// Function definition to get the value of Dew Point
        String name = null;
        try {
            XPathExpression dew = xpath.compile("/dwml/data/parameters/temperature[@type='dew point']/value/text()");
            name = (String) dew.evaluate(doc, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return name;															// Return this value to function
    }
    
    private static String getwindspeed(Document doc, XPath xpath) {				// Function definition to get the value of Wind Speed
        String name = null;
        try {
            XPathExpression wspeed = xpath.compile("/dwml/data/parameters/direction/value/text()");
            name = (String) wspeed.evaluate(doc, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return name;																				// Return this value to function
    }
    
    private static String getwavedirection(Document doc, XPath xpath) {							// Function definition to get the value of Wind Direction
        String name = null;
        try {
            XPathExpression wavedir = xpath.compile("/dwml/data/parameters/direction/value/text()");
            name = (String) wavedir.evaluate(doc, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return name;																			// Return this value to function
    }
 
    public static Document stringToDom(String s) throws SAXException, IOException, ParserConfigurationException // Load the string to XML document 
	{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(new StringReader(s)));						
    }
 	
}

