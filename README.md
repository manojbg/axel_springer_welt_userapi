# Axel Springer-WELT User API Service
## Introduction :
This Spring Boot service provides REST APIs for retrieving user details along with their respective post information
## Contents :
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)

## General info :
Axel Springer - WELT offers two external APIs:

1. Retrieve user data using the endpoint:
    https://jsonplaceholder.typicode.com/users/{userId}

2. Retrieve user posts using the endpoint:
    https://jsonplaceholder.typicode.com/posts?userId={userId}
   
This Spring Boot service provides a REST API that accepts a user ID and returns the corresponding user information and posts.

## Technologies :

* Java 17
* SpringBoot 3.x
* Docker
* Maven
* Swagger

## Setup :

Prerequisites:

* Java 17 or a later version.
* Maven (mvn)

1. Clone the "axel_springer_welt_userapi" project:

    git clone https://github.com/manojbg/axel_springer_welt_userapi.git

2.  Import the project into IntelliJ or Eclipse (Any IDE) and build the project:

    mvn clean install

3.  There are two ways to run the application:

    Docker:
     Use the "docker build -t {image_name}" command to build Docker images. Then, run the images as a container with "docker run -d {image_name}".

    As a Spring application:
     Run the project as a Spring application using your IDE, or execute "java -jar {build_jar_file}" in the command prompt to run the application.

API details can be found at: http://localhost:8080/swagger-ui/index.html#

## Features :
1. Rest API which accepts a user ID and returns user information and user posts:

Request URL:
    http://localhost:8080/api/v1/user/{userId}

Response :

    {
        "id": 1,
        "name": "Leanne Graham",
        "email": "Sincere@april.biz",
        "username": "Bret",
        "phone": "1-770-736-8031 x56442",
        "website": "hildegard.org",
        "address": {
            "street": "Kulas Light",
            "city": "Gwenborough",
            "zipcode": "92998-3874",
            "suite": "Apt. 556",
            "geo": {
                "lat": "-37.3159",
                "lng": "81.1496"
            }
        },
        "company": {
            "name": "Romaguera-Crona",
            "catchPhrase": "Multi-layered client-server neural-net",
            "bs": "harness real-time e-markets"
        },
        "posts": [
        {
            "userId": 1,
            "id": 1,
            "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
        }
        ]
    }

