# Airtel Code Challenge (Currency Converter)


## About this Project

This project is a JAVA Spring MVC application written as a code challenge for Airtel's Software developer role, Basicallly it helps you perform simple conversions from US Dollar to [5 currencies](helpdesk@ehealthafrica.com) while leveraging on the openexchange REST API.

Two basic features 
 - View your previous conversions
 - Perform a new Conversion

 ### Requirements Checklist
  [x] Integrstion with openexchange
  [x] Registration Page
  [x] Login Page
  [x] Main screen
  [x] Query History
  [x] Main currencies (EUR, USD, GBP, NZD, AUD, JPY, HUF) and Naira
  [x] Spring MVC
  [x] JPA
  [x] Fields - Firstname, Lastname, email, password, date of birth and address
  [x] Integration tests (1, covers the conversion method)
  [x] REST API - `/conversion` OpenAPI doc available
  [] Caching 
  [] JMX setup

## Developer? 
### Technologies  used
* Entirely based on Spring MVC and Thymeleaf, little or no javascript is required to run application


### System Requirements
  * Java(JDK) 8 or higher [install java](https://www.oracle.com/java/technologies/javase-downloads.html)
  * Maven 3 or higher [install maven](https://maven.apache.org/install.html)
  * Database used is H2, No setup required, will create a local file named `testdb.mv.db` when application starts, Ensure you have a write access to current directory

### Building the Server and App
  * Clone this repository ` git clone https://github.com/JohnTheBeloved/.git`
  * cd into project folder `cd airtel-code-challenge`
  * compile  : `mvn compile`
  * Run tests: `mvn test`
  * Build and Install the application: `mvn install`
  * The exeutable jar file would  be located in `target/airtel-currencyconverter-0.0.1-SNAPSHOT.jar`

### Running the App
  * Move the built jar to a location of your choice
  * Execute the executable jar file:  `java -jar airtel-currencyconverter-0.0.1-SNAPSHOT.jar`

### Accessing the app
  * The application is now running on http://localhost:8080
  * Open your browser and access the http://localhost:8080
## Users
###  How to use the app
 - Create the Jar application archive (see developer above)
 - Run the Java application (see developer section above)
 - Navigate to http://localhost:8080/register
 - Put in your details to register and click on the registration button
 - After successful registration, you are taken to the login page
 - The home page is then displayed where you can see your previous conversions and also perform conversion
 - To logout of the application, click on the logout link at the top left corner
 - Yes, that's it. That all :) Thanks. Or if we have [more time](https://www.youtube.com/watch?v=sy0fIyongdI), we can add more features


