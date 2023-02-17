Kata JPA
--------
A coding kata to learn JPA with Spring Boot


Running the Tests
-----------------
First we need to run a postgresql database.
Our docker compose is going to start a postgres server and a pgAdmin

```bash
docker compose up
```

Navigate to the pgAdmin page http://localhost:5050/ and sign-in with the following credentials:
```
email: admin@admin.com
password: pass
```

Still on the pgAdmin page, create a new server with the following properties and leave the other values as default.
You should be able to connect and see an empty database named `kata`. 
```
name: Localhost
host: kata-jpa-postgres
username: admin
password: pass
```

Finally, run all tests:
```bash
/mvnw
```

Resources
---------
* [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) 