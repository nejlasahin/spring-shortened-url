# Shortened Url Project
This project was developed with Spring Boot and React.

Project Status : ![100%](https://progress-bar.dev/100)

![output](./docs/output.gif)

## Used Technologies

- Java 11
- Spring Boot
- Spring Data JPA
- Spring Security
- Swagger documentation
- Lombok
- MySQL database
- H2 database (for **test**)
- ReactJS for frontend
- Maven Frontend Plugin

## Install & Run With Maven

*$PORT: 8080*

```ssh

git clone https://github.com/nejlasahin/spring-shortened-url.git

$ cd spring-shortened-url
$ mvn clean install
$ java -jar target/*.jar

```

## Pull & Run With Docker

*$PORT: 8080*

```ssh

git clone https://github.com/nejlasahin/spring-shortened-url.git

$ cd spring-shortened-url
$ docker-compose up

```

## Swagger UI will be run on this url

`http://localhost:${PORT}/swagger-ui.html`

## API Endpoints

![endpoints](./docs/endpoints.png)

## MIT License

Copyright (c) 2021 Nejla Şahin

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
