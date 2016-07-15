# User Registration

This project aims to be the cleanest and most up to date way to build Web UI on a Java-based server.

My goal is to fully comment the code and the Git repository to explain how the different technologies are integrated.

## Chosen technologies

The major technologies are [JPA 2][], [Spring][Spring Framework] 4.0.6 and Angular JS but this is the complete list of architecture choices:

### Java frameworks
- [Hibernate] 4.0 _as JPA 2 implementation_ 
- [JPA 2] _as persistence API_
- [Spring Data JPA] 1.1 _as DAO layer implementation_
- [Spring Framework] 4.0.6 _as main IoC container_
- [Spring MVC] 4.0.6 _as web framework_
- [Spring Data Rest] 4.0.6 _as REST exporter_


### JavaScript frameworks
- [Angular JS] 2.0 _as JavaScript file and module loader_
- [jQuery] 1.8 _as main JavaScript framework_


### Design patterns and guide lines
- [Single-page application] 
- [RESTful] [JSON] API

## Key integrations

This sections contains a quick description of the key integration points between all technologies with a link to the file involved.

### No XML Spring configuration

Servlet 3.0 and Spring 4.0 adds support of full Java based configuration replacing web.xml and applicationContext.xml. It allows smaller and compiled configurations for building XML free applications.

This application demonstrate this new way of configuration.

### Spring Data JPA configuration

Repository layer of the application is fully implemented by [Spring Data JPA][]. The configuration is in the file [JPAConfiguration.java](blob/master/src/main/java/com/comcast/registration/configuration/JPAConfiguration.java).
As you can see, only the root package for repository interfaces is needed.

The Spring Data JPA implement standard JpaRepository interface methods and the specifics defined in the interface. 

### Spring MVC, Spring Data Rest

Spring Data Rest is very good new idea of Spring Source team and build a bridge from Spring Data to Spring MVC. It allows us to automatically published a Spring Data models.

