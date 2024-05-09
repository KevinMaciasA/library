# Library Project

This Library Project is a Java Spring application that uses JPA and PostgreSQL for data persistence. It interacts with the Gutendex API to provide various functionalities related to books and authors.

## Key Features

1. **Find Book by Title:** Search for a book by its title.

2. **Show all Books:** Display a list of all available books.

3. **List all Authors:** View a list of all authors.

4. **Show Authors Alive at Specific Year:** Find authors who were alive during a specific year.

5. **Show Books by Language:** Filter books based on their language.

**Easy to Use:** Simple and intuitive CLI experience.

## Getting Started

Before proceeding, ensure you have Java Development Kit (JDK), Maven, and PostgreSQL installed on your system.

1. Clone the repository to your local machine:

   ```sh
   git clone https://github.com/KevinMaciasA/library.git
   ```

## Configuration

Before using the Library Project, configure the database connection in the application.template.properties file:

```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

Ensure two things:

1. To change the filename from:

```sh
application.template.properties -> application.properties
```

2. That PostgreSQL is running and replace "your_username" and "your_password" with your actual PostgreSQL credentials.

## Running the Project

To run the project, first compile it and then start the application:

1. Compile the project:

```sh
mvn clean install
```

2. Run the application:

```sh
mvn spring-boot:run
```

## CLI

### Main menu

### Find Book by Title

### List all Books

### List all Authors

### Authors Alive at Specific Year

### Books by Language

## Acknowledgments

Special thanks to Gutendex for providing the book data necessary for this project.
