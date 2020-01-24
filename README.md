# Spring Boot 2.3.0 Milestone 1 Cloud Native Buildpacks example

Example repository by my blog post:  

## Prerequisites

* [Docker](https://hub.docker.com/search?type=edition&offering=community) is installed
* [Pack (Cloud Native Buildpacks)](https://buildpacks.io/docs/install-pack/) is installed

Verify Docker and Pack are working:

```shell script
pack version
v0.6.0 (git sha: 109b629d388cec0ed3836b9fed6717727a9187c1)
```

```shell script
docker -v
Docker version 19.03.5, build 633a0ea838
```

## Show the 

```shell script
./mvnw spring-boot:build-image
```

## Build the Cloud native image for your Spring Boot application 

```shell script
./mvnw spring-boot:build-image
```

## Show the build Docker image

```shell script
docker images 
REPOSITORY                        TAG                 IMAGE ID            CREATED             SIZE
cloud-native-buildpacks-example   0.0.1-SNAPSHOT      48d2171e0777        14 seconds ago      226MB
cloudfoundry/cnb                  0.0.43-bionic       526111e562c1        10 days ago         731MB
cloudfoundry/run                  base-cnb            7cd3a43a4c98        2 months ago        71.2MB
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

## Step > Running detector is 'hanging'

Make sure pack is installed and added to your $PATH:

```shell script
pack version
v0.6.0 (git sha: 109b629d388cec0ed3836b9fed6717727a9187c1)
```