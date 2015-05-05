# Aerospike DB Web Application
—————————————————————————---------

This repository contains Spring based web application used to demonstrate Aerospike NOSQL DB API calls to insert and retrieve data. 
These APIs are exposed as a REST service .

Features:
-  Create sets/records using API calls
-  User name/password authentication using UDF  

Tools and technologies used :
- Spring web MVC  framework
- JAX-RS  Restful APIs  
- Tomcat 7 server to deploy web application

To run this tool:
1. The source code can be imported into your IDE and/or built using Maven
	mvn clean install
2. Download tomcat server from “http://tomcat.apache.org/download-70.cgi”
3. Copy the “AerospikeDBWebApp.war”  from the AerospikeDBWebApp/target  folder and drop it to tomcat server(webapps folder) at the below path of your tomcat folder
   “apache-tomcat-7.0.55/webapps”
4. Start the server, go to “apache-tomcat-7.0.55/bin” and run below command for linux machines
   startup.sh  
5. Open the browser and run below url to access the web application
   http://localhost:8080/AerospikeDBWebApp/credential.htm 
