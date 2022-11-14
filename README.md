# data_transformer
This program takes JSON records from AWS SQS Queue, masks device_id and ip fields, and writes user login data into PostgreSQL database.


1. In order to create and fill the AWS SQS Queue, stand up PostgreSQL database
please follow steps from ***README_input_output.md*** file
to execute ***docker-compose.yml*** .

2. Reading data from AWS SQS Queue, 
transforming and writing it into PostgreSQL database could be accomplished by creating 
and running ***data_transformer-1.0-SNAPSHOT.jar***:
    1. Install JDK (version 17 or later): https://docs.oracle.com/en/java/javase/18/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A
   
    2. Install maven (3.8.4 or later): https://maven.apache.org/install.html
   
    3. In terminal, switch to the data_transformer directory:
        ```
       cd PATH_TO_DIRECTORY/data_manipulation/data_transformer
       ```
       
    4. Create *.jar* file using maven (*target directory* would appear in *data_transformer*):
       ```
       mvn package
       ```
       
    5. Execute ***data_transformer-1.0-SNAPSHOT.jar*** (located in PATH_TO_DIRECTORY/data_manipulation/data_transformer/target)
   
       ```
       java -jar target/data_transformer-1.0-SNAPSHOT.jar
       ```
       The terminal output should be like this:
   
       ```
       Database opened successfully.
       Data transferred successfully.
       ```
       

Here is the list of improvements that should be implemented in the future:
1) Rewrite the logic of the program in a way that it can process a generalized message with an arbitrary JSON message body.   
1) Add an event listener that would retrieve messages from an AWS Queue, if new messages were added after the initial scan of the Queue.   
2) Implement DTO (Data Transfer Object) pattern.
3) Adding additional precautions for securing a PostgreSQL database. I added parameterized statements to prevent SQL injection and masked required fields. However, I think that the user_id field should be also masked. 
