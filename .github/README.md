Spring Cloud Karmahostage
===

# 1. Why do you need Spring Cloud Karmahostage

Spring Cloud Karmahostage provide Spring Cloud common interface immplementations that consume Karmhostage services. 
The main objective of this project is to facilitate the integration of Karmahostage functionality in Spring Cloud and Spring Boot applications.


# 2. Starters

Starters are convenient dependency descriptors you can include in your application. Include a starter to get the dependencies
and Spring Boot auto-configuration for a feature set.

| Starter  | Features  | 
|---|---|
|  ```<dependency>``` <br /> ```  <groupId>org.springframework.cloud</groupId>```<br />```  <artifactId>spring-cloud-starter-karmahostage</artifactId>``` <br /> ```</dependency>``` | All Spring Cloud Features |
|  ```<dependency>``` <br /> ```  <groupId>org.springframework.cloud</groupId>```<br />```  <artifactId>spring-cloud-starter-secrets</artifactId>``` <br /> ```</dependency>``` | Load Secrets from Karmahostage |

3. Karmahostage PropertySource Implementation

The most common approach to configuring your Spring Boot application is to 
create an *application.properties* or *application.yaml* or an *application-profile.properties* or *application-profile.yaml*
file that contains key-value pairs that provide customization values to your application or Spring Boot starters. 
You can override these properties by specifying system properties or environment variables.

Karmahostage has the notion of Secrets for storing sensitive data such as passwords, OAuth tokens, and so on. 
This project provides integration with Secrets to make secrets accessible by Spring Boot applications. 
You can explicitly enable or disable This feature by setting the *spring.cloud.karmahostage.secrets.enabled* property.

When enabled, the SecretsPropertySource looks up Karmahostage for Secrets from the following sources:

- Named after the application (as defined by spring.application.name)
- Looking at the existing configuration defined under *spring.cloud.karmahostage.secrets*

Note: 
As for every integration with karmahostage, you'll need an API key. Visit [Karmahostage](https://dashboard.karmahostage.com) to register for an API key.

You'll need to set the API key using the *spring.cloud.karmahostage.api-key* property.

## 3.1 Application Name

By default, if no paths have been defined (See 3.2), the application will look at the application name. It will try to search for
a secret with the name of your application as the key. 

## 3.2

You can override the default behaviour by providing *spring.cloud.karmahostage.secrets.paths*.

Example:

```properties
spring.cloud.karmahostage.secrets.paths=database-username,database-password
```




 