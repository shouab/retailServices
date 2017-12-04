# retailServices
Built in Spring BOOT against MyRetail Case Study for Target.com

Published: Sunday, December 3, 2017

This document includes the following:

Instructions for testing, running and interacting with the retailServices System

retailServices System is developed using the following Technology Stack and Environment

Java 1.8
Spring Boot 1.5.9.RELEASE
Pivotal tc Server developer edition v 3.2/Apache Tomcat 8.5  
IDE: STS Version 3.9.1.Release
MONGODB version 3.4.10 for Windows 64 bit
Gradle for Dependency Management
POSTMAN for Rest Services Testing
Operating System: Windows 10

Instructions to deploy and Test:

1. Download and install MONGODB from https://www.mongodb.com/download-center#community its straight forward installation once installed execute the mongod.exe available under 	MONGODBinstallationFolder/bin after successfull run make sure its listening on localhost port 27017.   
2. Download the Apache Tomcat 8.5 core for Windows 64 bit from following link
 	https://tomcat.apache.org/download-80.cgi
3. Copy it on Some suitable location and unzip it. Do set your Java_Home and classpath and path variables
	run the catalina.bat file located in \apache-tomcat-8.5.24-windows-x64\apache-tomcat-8.5.24\conf\bin. Server will start at http://localhost:8080/
4. Add the following lines in the file tomcat-users.xml located at \apache-tomcat-8.5.24-windows-x64\apache-tomcat-8.5.24\conf
	<role rolename="manager-gui"/>
	<user username="admin" password="admin" roles="manager-gui"/>
	Note: this is because Tomcat will come by Default with No Users we need to add a user with role manager-gui to deploy our retailServices.war file.
5. click on the link managerapp available on  http://localhost:8080/ it will prompt for user name and password Enter admin as username and admin as password.
6. It will divert to Manager Application page now click on browse button available under WAR file to deploy Header now select the retailServices.war which is available at 	following location as part of my package.
7. Click the deploy package it will deploy the retailServices.war.
8. Now the application is accessible on http://localhost:8080/
9. Use Chrome Browser and add POSTMAN extension from https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en
10. Now we are ready to test the API. First we need to post some data in MONGODB datasource so we can read it later.
11. Use Postman to hit a 'Post' Request with URL http://localhost:8080/retailServices/api/product/price/save  
	sample JSON
	{
    	"productID": 16752456,
    	"price": 5000.50,
    	"currencyCode": "RUPEE"
	}
12. Following Product ID's have configured for Product Name source product IDs: 15117729, 16483589, 16696652, 16752456, 15643793 in the system.
13. We can fetch the Product Information at following URL http://localhost:8080/retailServices/api/product/{productID} with a GET request.
14. Price Update service is available at http://localhost:8080/retailServices/api/product/price/modify/{productID} with a PUT request. Sample JSON is as under
	sample JSON
 	{
    "price": 150.30,
    "currencyCode": "GBP"
 	}
 15. I have added the Unit Test Cases for Required API.



