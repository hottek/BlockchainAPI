# Blockchain API
This is the implementation of a simple blockchain which is controlled through a REST-API.  
The API is written using the [Jersey](https://jersey.github.io/) framework. [Maven](https://maven.apache.org/) is used as a build tool.  
The Java program is hosted as a servlet using [Apache Tomcat 8](https://tomcat.apache.org/download-80.cgi).
### API
- GET `/api/v1/mine` Mine one block  
Returns the index of the block  

- POST `/api/v1/create_transaction` Create a transaction for the next Block   
Content-type: `application/json`  
Body: `{ "sender" : "", "recipient" : "", "amount" : ""}`  
Returns the string "Success"