# Airtel Code Challenge (Currency Converter)


## About this Project

This project is a JAVA Spring MVC application written as a code challenge for Airtel Software developer, Basicallly it helps you perform simple conversions between 5 currencies to the US Dollar while leveraging on the openexchange REST API.

Two basic features 
 - View your previous conversions
 - Perform a new Conversion

## User? How to use the app
 - Create the Jar application archive see bellow
 - Run the Java application see section below
 - Navigate to http://localhost:8080/register
 - Enter your details to register and click on the registration button
 - After successful registration, you are taken to the login page to login
 - The home page is then displayed where you can see your previous conversions and also perform a new conversion
 - To logout of the application, click on the logout link at the top left corner
 - Thanks



## Technologies used
* Entirely based on Spring MVC and Thymeleaf, little to no javascript is required to run application


### System Requirements
  * Java 8 or higher,
  * Node 8 or higher

### Building the Frontend Application
  * Clone this repository: git clone https://github.com/JohnTheBeloved/embi-person-mngmt.git 
  * Build the reactjs source :  `cd src_reactjs_frontend && npm run build`
  * Move the front end build into server  : `mv build ../src/main/resources/public`

### Building the Server and App
  * Clone `cd ../`
  * Install all dependencies and build  : `./mvnw clean && ./mvnw compile && ./mvnw install`
  * The exeutable jar file would  be located in `target/embi-core-0.0.1-SNAPSHOT.jar`

### Running the App
  * Move the built jar to a location of your choice
  * Execute the executable jar file:  `java -jar embi-core-0.0.1-SNAPSHOT.jar`

### Running the Ap
  * `mvn test`

### Accessing the app
  * The application is now running on http://localhost:8080
  * Open your browser and access the http://localhost:8080
