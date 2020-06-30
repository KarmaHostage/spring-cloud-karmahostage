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
|  ```<dependency>``` <br /> ```  <groupId>org.springframework.cloud</groupId>```<br />```  <artifactId>spring-cloud-starter-kubernetes</artifactId>``` <br /> ```</dependency>``` | All Spring Cloud Features |
|  ```<dependency>``` <br /> ```  <groupId>org.springframework.cloud</groupId>```<br />```  <artifactId>spring-cloud-starter-secrets</artifactId>``` <br /> ```</dependency>``` | Load Secrets from Karmahostage |
