# Java-REST-Web-Application

This is a Java web application made in IntelliJ IDE using the Spring Framework. 

This application functions as a local REST service on port 8080, and contains some simple hardware information in a database.

Some of the functionalities include:
- GET request for receiving information about all hardware in the database
- GET request for receiving information about a specific hardware based on a unique code that defines that specific hardware
- POST request for adding new hardware into the database, with functioning validation for specific hardware attributes such as name, price etc.
- PUT request for updating hardware info based on a provided code
- DELETE request for deleting selected hardware

This project was created on a Three-Tier Architecture Model, which means that all of the functionalites were seperated into three different parts of the program.

#### Controllers / Web Layer
Web Layer components take care of client input and server response towards the client. 
In this project, Controller classes define where and how the different requests get called.

#### Service Layer
Service layer contains all of the application and infrastructure services.
Services connect Web Layer with the Repository Layer and include additional functionalities.

#### Repository Layer
Repository layer is used for communication with the database. 
