# Hello world

## Description

* Step 1:

Make a service which is reachable over HTTP.
This service returns "Hello, world!" when receiving a GET request to the "/greeting" endpoint.

* Step 2:

Extend the service in such a way that it accepts a request parameter named "name".
The greeting should be adapted to return "Hello, {name}!".

* Step 3:

Add "business logic" which ensures that the name (if present) is automatically properly capitalized (first letter is made uppercase, others lowercase).
Extract this logic into a separate component.
Write a unit test to cover that component.
Use the DI framework to inject an instance of the new component into the HTTP service.
Refactor the HTTP service, so it delegates to the new component.

* Step 3.5

Add a logging filter/interceptor to log all requests and responses.
Use it to log the requested URI on the way in and the response status code on the way out.
Do not use `System.out.println()`, use a logging framework.
The easiest to use is `java.util.logging` (JUL), since it is part of the JDK and thus does not add any extra dependencies.
(On the other hand, it's not the nicest logging framework to work with...)

* Step 4:

Add a new endpoint: "/email".
When doing an HTTP POST to this endpoint, an email should be sent.
The recipient should be passed as parameter, the body of the email can be taken from the HTTP request body.
(Bonus: verify that the recipient is a valid email address, using bean validation).

Use MailHog as a fake SMTP server; see next section for more information.
Ensure that the email sending logic is executed asynchronously.
(Bonus: write a test with a mock for the mail sender to verify that calling the endpoint triggers sending an email.)

Add a security rule to protect this endpoint from anonymous access.
Try calling the endpoint and verify that it is not accessible.
Create a user using Spring security and try to send an email now (either with the autoconfigured InMemoryUserDetailsService, your own definition of an InMemoryUserDetailsService, or even a custom UserDetailsService bean altogether).
(Bonus: also secure the service bean with global method security.)

## Mock SMTP (MailHog)

SMTP implementation for integration testing (does not really send emails).

### Docker

    docker run --name mailhog -p 1025:1025 -p 8025:8025 mailhog/mailhog

* 1025 is used as SMTP port
* 8025 is used for the web UI

## Maven plugin

https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/#run

### Externalized configuration

See [examples](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/#run.examples).

#### Example with system properties

Add to `pom.xml`:

```xml

<properties>
    <email.user>none</email.user>
    <!-- ... -->
</properties>
```

Then in properties files, you can use `@...@` syntax to refer to maven properties:

```
spring.mail.username=@email.user@
```

Then pass values through the CLI (double quotes necessary on some shells):

    mvn spring-boot:run "-Demail.user=robin.demol@realdolmen.com" ...

## Documentation

Spring boot documentation root: https://docs.spring.io/spring-boot/docs/current/reference/html/

Used modules:

- [Web](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.developing-web-applications.spring-mvc)
- [Config](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config)
- [Security](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.security)
- [Email](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.email)
- [Async](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.task-execution-and-scheduling)

## cURL cheat sheet

    curl [flags] url

- `-s` to suppress request progress
- `-i` to print return headers
- `-u name:pass` to specify basic authentication
- `-X <METHOD>` to specify the HTTP method
- `-d '...'` to specify the request body (for POST, PUT, DELETE...)
