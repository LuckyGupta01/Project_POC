# Project_POC

#Pre-Requisites
1. Java 1.8
2. Maven 3.0 or above
3. Postman for API testing

#Follow below mentioned steps to clone from Repository
1. Fork this repository and clone the code in your local machine
2. Clone using `git clone - https://github.com/Suraj-Git-Cloud/Project_POC.git`
3. Navigate to Project_POC folder
    `cd Project_POC`
4. run maven command 'mvn clean spring-boot : run' or run mvn clean install and copy the jar name from target folder and run command 'java -jar {jarname} 'folder in comand prompt.
5. open any Api testing tool (Postman)
  hit the url 'http://localhost:8096/api/v1/robobank/inputfile/{fileName}'
  Note- The code is designed as per requirement to read only csv or xml file. 
5. Verify the responses
{
    "records": [
        "112806  Clothes from Peter de Vries",
        "112806  Tickets for Erik Dekker"
    ]
}

6. You will get the report of  records (reference that are not unique and the end balance is incorrect in the file)

### Further Instructions on Release

- Right click on the Assignment, select Run As -> JUnit Test to run your Assignment.
