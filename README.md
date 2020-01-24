# Spring Boot 2.3.0 Milestone 1 Cloud Native Buildpacks example

Example repository by my blog post: [First look at Cloud Native Buildpacks support in Spring Boot 2.3 Milestone 1](https://medium.com/@TimvanBaarsen/first-look-at-cloud-native-buildpacks-support-in-spring-boot-2-3-milestone-1-ece8e72ed93f)  

## Prerequisites

* [Docker](https://hub.docker.com/search?type=edition&offering=community) is installed

Verify Docker and Docker Compose are working:

```shell script
docker -v
docker-compose version 1.25.3, build d4d1b42b
```

```shell script
docker-compose -v
````


## Build the Cloud native image for your Spring Boot application 

```shell script
./mvnw spring-boot:build-image
```

## Show the build Docker image

```shell script
docker images | grep cloud-native
cloud-native-buildpacks-example   0.0.1-SNAPSHOT      2c7f20cd9123        4 seconds ago       226MB 
```
## Run the Cloud Native Container (in the background)

```shell script
docker run -d -p 8080:8080 --name springbootcontainer cloud-native-buildpacks-example:0.0.1-SNAPSHOT
b39e09fe68ce7a72dbc2d41d6d59d980b7412399872f377f64bac9a4984b75d8
```

## View the running container

```shell script
docker ps
CONTAINER ID        IMAGE                                            COMMAND                  CREATED             STATUS              PORTS                    NAMES
b39e09fe68ce        cloud-native-buildpacks-example:0.0.1-SNAPSHOT   "/cnb/lifecycle/laun…"   2 seconds ago       Up 1 second         0.0.0.0:8080->8080/tcp   springbootcontainer
```

## Check the running Spring Boot application

[http://localhost:8080/](http://localhost:8080/)

[Spring Boot Info Actuator](http://localhost:8080/actuator/info)

```json
{
  "java": {
    "vm": {
      "vendor": "AdoptOpenJDK"
    },
    "version": "11.0.5"
  },
  "application": {
    "encoding": "UTF-8",
    "name": "cloud-native-buildpack-example"
  },
  "spring": {
    "boot": {
      "version": "2.3.0.M1"
    }
  }
}
```

## Stop the container

```shell script
docker stop springbootcontainer
```

## Using Docker compose

See `docker-compose.yml` in the root of the project.

Start the container (in the background):

```shell script
docker-compose up -d
```

Show the running container:

```shell script
docker-compose ps
```

Stop the container:

```shell script
docker-compose down -v
```

## Troube shooting

### Error: file name is too long ( > 100 bytes)

When you encounter one of the following errors make sure you package name and or project name is not too long.

```
[ERROR] Failed to execute goal org.springframework.boot:spring-boot-maven-plugin:2.3.0.M1:build-image (default-cli) on project cloud-native-buildpacks-example: Execution default-cli of goal org.springframework.boot:spring-boot-maven-plugin:2.3.0.M1:build-image failed: file name 'BOOT-INF/classes/nl/jtim/spring/boot/cloudnative/buildpacks/SpringBootCloudNativeBuildpacksApplication.class' is too long ( > 100 bytes) -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/PluginExecutionException
```

```
[ERROR] Failed to execute goal org.springframework.boot:spring-boot-maven-plugin:2.3.0.M1:build-image (default-cli) on project spring-boot-cloud-native-buildpacks-example: Execution default-cli of goal org.springframework.boot:spring-boot-maven-plugin:2.3.0.M1:build-image failed: file name 'META-INF/maven/cloud.native.buildpacks.example/spring-boot-cloud-native-buildpacks-example/pom.properties' is too long ( > 100 bytes) -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/PluginExecutionException
```