# mvc-springboot-thymeleaf
A simple project to show the Spring Boot with Thymeleaf in action.

Creating MVC architecture using Spring boot and Thymeleaf.

**Thymeleaf** is a Java template engine for processing and creating HTML5, XML, JavaScript, CSS, and text. It is able to apply a set of transformations to template files in order to display data and/or text produced by your applications.

The library is extremely extensible and its natural templating capability ensures templates can be prototyped without a back-end – which makes development very fast when compared with other popular template engines such as JSP.

Thymeleaf has a good integration with Spring MVC framework making it first choice library for frontend development in Java and Spring technologies.

## Technologies used
- Spring Boot
- Spring Boot Thymeleaf Starter Pack

## Running the application locally
There are several ways to run a Spring Boot application on your local machine.

### Using Main method
Clone the repository to your local drive.
```shell
git clone https://github.com/prasbhat/mvc-springboot-thymeleaf.git
```
Import the project as "Maven Project" into your favourite IDE and execute the `main` method in the `MVCSpringbootThymeleafApplication` class from your IDE.

`Right Click on the file and Run as Java Application`

### Running the application with Maven
Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:
```shell
git clone https://github.com/prasbhat/mvc-springboot-thymeleaf.git
cd mvc-springboot-thymeleaf
mvn spring-boot:run
```

### Running the application with Executable JAR
The code can also be built into a jar and then executed/run. Once the jar is built, run the jar by double clicking on it or by using the command:
```shell
git clone https://github.com/prasbhat/mvc-springboot-thymeleaf.git
cd mvc-springboot-thymeleaf
mvn package -DskipTests
java -jar target/mvc-springboot-thymeleaf-0.0.1-SNAPSHOT.jar
```

For more information on this project, refer the article [here](https://myzonesoft.com/post/mvc-springboot-thymeleaf/)