
**Aerospike DB Web Application**
—————————————————

This repository contains Spring based web application used to demonstrate Aerospike NOSQL DB API calls to insert and retrieve data. These APIs are exposed as a REST service . Landing page also has small panel to run performance/load test.

***Features:***

 - Create sets/records using API calls
 - User name/password authentication using UDF
 - Load data for Performance test . 
 -  During login( submit button click) user defined function "profile.lua" will be registered automatically and Secondary index will be created on set name "users".


***Tools and technologies used :***

 - Spring web MVC framework
 - JAX-RS Restful APIs
 - Tomcat 7 server to deploy web application
 

****Prerequisite:**** 

 - Aerospike Server - If you don't have it installed, click [here](http://www.aerospike.com/docs/operations/install/) to install it.
 - Create set called "users" with atleast 2 bins called "username" and "password" and Insert one user record as below.

>  aql> insert into test.users (PK,username,password) values  
                               ('1','anand','anand123')
  

 +----------+------------+--------+
| username | password   |  
+----------+------------+--------+
| "anand"  | "anand123" |  
+----------+------------+--------+

 
**Installing and Running This Application**
This is a Java based application that uses Aerospike Java Client , eclipse and Maven. The pom.xml file shows Maven how to build the package. Maven can easily be obtained from:
http://maven.apache.org/

***To run this tool:*** 
 **1.** The source code can be imported into your IDE and/or built using Maven
    

>  mvn clean install

 **2.** Download tomcat server from “http://tomcat.apache.org/download-70.cgi” 
 
 **3.** Copy the “AerospikeDBWebApp.war” from the AerospikeDBWebApp/target folder and drop it to tomcat server(webapps folder) at the below path of your tomcat folder

>   “apache-tomcat-7.0.55/webapps”

 **4.** Start the server, go to “apache-tomcat-7.0.55/bin” and run below command for   linux machines. 
 

>   anandprakash$ ./startup.sh

 **5.** Open the browser and run below url to access the Web application.    
          http://localhost:8080/AerospikeDBWebApp/credential.htm

***User Interface screenshot and details:***

 - To create/insert records in AerospikeDB, enter the details as described in the screen shot
 ![Insert records](https://github.com/anandintouch/AerospikeDBWebApp/blob/master/src/main/webapp/images/insert_records.png)
 - For performance test use below input panel to load data as needed into given Set name ,Bin name and Bin value.  
 ![Load test panel](https://github.com/anandintouch/AerospikeDBWebApp/blob/master/src/main/webapp/images/loadtest.png)