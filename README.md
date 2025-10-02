# Track My Habits

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Features

* Create, Read, Update, Delete (CRUD) of habits
* Data validation with Hibernate Validator.

## Technologies Used

* **Quarkus:** A supersonic subatomic Java framework used for building fast and efficient applications.
* **PostgreSQL:** Relational database for storing habit data.
* **Hibernate ORM with Panache:** Simplifies database interaction and entity management.
* **Flyway:** For database migration management.
* **Lombok:** Reduces boilerplate code.
* **MapStruct:** For mapping between DTOs and entities.
* **Hexagonal Architecture:** Used to achieve a clean separation between the business logic and the infrastructure.

## Endpoints

```
POST    /login
POST    /users

GET     /habits
GET     /habits/{id}
POST    /habits
PUT     /habits/{id}
DELETE  /habits/{id}

```

#### Public Endpoints (No Authentication Required)

The following endpoints are accessible without a JWT token:
```
POST /login – User authentication (returns the JWT).

POST /users – Register a new user.

GET /habits – Get all habits.

GET /habits/{id} – Get a habit by ID.
```
#### Authentication Required (JWT)

The following endpoints require a valid JWT with the user role:
```
POST /habits – Create a new habit.

PUT /habits/{id} – Update an existing habit.
```
#### Admin Role Required

This endpoint requires the JWT to include the admin role:
```
DELETE /habits/{id} – Delete a habit.
```
## JWT Key Setup

To enable JWT authentication, create the following key files in your project:
```
src/main/resources/privateKey.pem
src/main/resources/publicKey.pem
```
> **_NOTE:_**
Make sure these files contain your RSA private and public keys, respectively, to properly sign and verify JWT tokens.

## Generating RSA Keys

For generating your .pem files, you can use OpenSSL or online tools for academic purposes, such as [CryptoTools RSA Key Generator](https://cryptotools.net/rsagen)

## Docker Compose Setup

To run the project along with the database, you'll need to configure environment variables. For security reasons, the configuration files are not included in the repository.

### Step 1: Create the Environment Variables File for Docker

Create a file named .env at the root of your project, in the same directory where the docker-compose.yml file is located. This file tells Docker how to set up the database service.

Contents of the .env file:
```
DB_USER=user
DB_PASS=password
DB_NAME=db
```

Important: The DB_PASS variable is required for the PostgreSQL container. Please change it to a secure password.

### Step 2: Application Configuration File

The Quarkus application needs its own configuration to connect to the database.

Create a file named application.env.properties at the root of your project.

Contents of the application.env.properties file:

```
DB_HOST=postgres
DB_PORT=5432
DB_NAME=db
DB_USER=user
DB_PASS=pass
```

> **_NOTE:_** The application.env.properties file is copied into the container during the Docker image build process (as specified in the Dockerfile). **Don't worry about it** — it will be handled automatically when you build and run the Docker container.

### Step 3: Starting the Services

Once you have created and configured the two files, you can start the Docker services.

Make sure Docker is installed and running.

Open a terminal at the root of your project.

Run the following command to start the services:
```
docker-compose up --build
```

This will build your application's Docker image and bring up the database containers.

## Running Locally

If you want to run the application locally (without Docker), you only need the application.env.properties file. The **.env** file is only used for Docker configuration and is not needed when running the application directly on your local machine.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/track-my-habits-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it
