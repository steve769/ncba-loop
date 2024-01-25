# LOOP DPS - Coding assessment
## How to run my application
Please follow the steps outlined below to run my application in your environment.

STEP 1: Navigate to the ZIP file containing the Project Assessment, It is named StephenOtienoLOOP.


STEP 2: Navigate to the root folder containing the project using the command below

```sh
cd StephenOtienoLOOP/loop
```
STEP 3: Run the application using the command shown below - docker compose runs the SPing Boot Application in a container
and spins up any other containers that the application may "depend-on"

```sh
docker compose up
```

STEP 4: The Application is now Up and Running. on the Link Below, Click on the link to Access the Swagger Documentation
[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

STEP 5: In case the application fails to start-up please check the ports - another process may be running on either port 8080 or 5432. You can track down the process id using the first command and kill it using the second command.
```sh
lsof -i :8080
kill -9 PID
```
## Development Approach
### Exception Handling🛑
Exception handling is a critical aspect of Java application development to ensure robustness, 
maintainability, and proper error reporting. Here are some best practices for exception handling
in Java applications:

For this reason, I have wrapped all methods that may throw exceptions in try...catch blocks,
catching the Exception and printing the cause of the Exception using the 
```java
  }catch(Exception ex){
            return APIResponse.genericResponse("failure", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
```
### Logging & Monitoring📈
Keeping in mind that the Assessement Required that we think of the Program as a program that runs of 
production, it is imperative to implement logging, for visibility and audit.
- I have used Sl4j, logging at critical points of the application so as to help with easy code maintainability as well as debugging.

As a side note a logging strategy, in production should encampus in-app logging, file logging and database logging for purposes of durability and redundancy for disaster recovery.
### Security⚠️
In the spirit of keeping things simple, I have used a map to hold two users and their credentials inside the code - 
This represented my simulation of "only a client can retrive, their cards". 

The AccountCardController is the only endpoint that is "Locked" - you need a clientId & clientSecret to access it.
And these are saved directly in the code.

```java
 private final Map<String , String> credentials = new HashMap<>();
    public AccountCardServiceImpl(AccountRepository accountRepository) {
        //sample credentials
        credentials.put("kevin", "password1");
        credentials.put("steve", "password2");
        this.accountRepository = accountRepository;
    }

```
This approach is by no means the best way of handling security in a Spring Boot application. SPRING SECURITY provides 
in my opinion the best way to secure APIs, since it locks everything down and the Developer has to go "Opening up", the required endpoints. I could have also used, JWT tokens with short expiry timelines & MFA.
### Containerization & Networking📦
In order to containerize my Spring Boot application, I have written a Dockerfile that pulls Java 21 image, copies the jar file into the image then defines the entrypoint command.

Also, I have used a Docker compose file to run to help avert the complexity of having to manually configure a docker network and run two separate containers, the Java container and the Database container.
### Testing🧪
I have wrote Unit tests for most of my methods, and also mocked some of the Objects that require Mocking. It is also a good 
strategy, or best practise to not only test passing tests, but also to test the failing cases inorder to make the tests as robust as possible.

When Testing the Repository layer - Some people prefer to use an In-Memory database, which is a good strategy, but I 
prefer using TestContainers running the Database engine so as to Mirror the Development and Higher environments as much as possible.
## Libraries Used
 - org,projectlombok -> for generating boilerplate code. - constructor methods, getters, setters, equals,hashCode etc
 - org.springdoc -> For Swagger Integration as some parts of org.springfox library are deprecated
 - org.assertj -> For more intuitive tests, readable test cases
 - org.testcontainers -> For testing the repository layer of code
