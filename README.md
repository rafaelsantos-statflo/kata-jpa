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

Secondly, we should be able to connect to our local postgree running from docker.
Open any database client (i.e: https://dbeaver.io/) and connect to the database with the following details:

```
host: localhost
port: 5432
database: kata
username: admin
password: pass
```

Alternatively, you may use the provided pgAdmin, navigate to the pgAdmin page http://localhost:5050/ and sign-in with the following credentials:
```
email: admin@admin.com
password: pass
```

Still on the pgAdmin page, create a new server with the following properties and leave the other values as default.
You should be able to connect and see an empty database named `kata`. 
```
host: kata-jpa-postgres
username: admin
password: pass
```

Finally, run all tests:
```bash
/mvnw
```


Entity Diagram
--------------

```
                               ┌─|PRODUCT|─────┐
                               │id             │
                               │name           │
                               └─────────────┬─┘
                                             │{OneToMany}
                                             │
                                             │
┌─|CATEGORY|────┐              ┌─|VARIANT|───┴─┐
│id?            │ {ManyToMany} │id             │{ ? }       ┌─|IVENTORY|────┐
│name           ├──────────────┤name           ├────────────┤id?            │
└───────────────┘              └─────────────┬─┘            │quantity       │
                                             │{OneToMany}   └─────────────┬─┘
                                             │                            │{ ? }
                                             │                            │
Where to add a SKU?            ┌─|ATTRIBUTE|─┴─┐                          │
                               │key            │            ┌─|LOCATION|──┴─┐
                               │value          │            │id             │
                               └───────────────┘            │name           │
                                                            └─────────────┬─┘
                                                                          │{OneToOne}
                                                                          │
                                                                          │
                                                            ┌─|ADDRESS|───┴─┐
                                                            │id             │
                                                            │lineOne        │
                                                            │lineTwo        │
                                                            │postalCode     │
                                                            │country        │
                                                            │city           │
                                                            └───────────────┘
```

Resources
---------
* [Jakarta Persistence Specification](https://jakarta.ee/specifications/persistence/3.1/jakarta-persistence-spec-3.1.html)
* [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) 
* [Spring Boot Data JPA Reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
