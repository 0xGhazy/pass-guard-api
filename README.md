# PassGuard CRUD API
PassGuard is a Spring Boot CRUD API that manage your passwords by storing them encrypted and give you an easy way to retrieve them anytime.

## Concepts and Features

### Swagger UI
![Screenshot 2023-11-04 231339](https://github.com/0xGhazy/hackerrank-sql-solutions/assets/60070427/d134923b-5133-4b54-9758-69f26c2e6931)

### AES256 Encryption
Encryption handled by `EncryptionHandler.java` file.
GET `localhost:8000/api/v1/accounts/3` result
```json
{
  "data": {
    "id": 3,
    "username": "0xGhazy",
    "password": "A6C41XXKu5dS8uxzorsUnyBq5sdK8tAzCXwlszMzcLI=",
    "platform": {
      "id": 3,
      "name": "LOL",
      "iconUri": "https://styles.redditmedia.com/t5_2rfxx/styles/communityIcon_9yj66cjf8oq61.png"
    },
    "vault": {
      "id": 1,
      "name": "Work Accounts"
    }
  },
  "message": "[+] Account retrieved successfully.",
  "status": "FOUND"
}
```
GET `localhost:8000/api/v1/accounts/3/plain` result
```json
{
    "data": {
        "id": 3,
        "username": "0xGhazy",
        "password": "15948875236",
        "platform": {
            "id": 3,
            "name": "LOL",
            "iconUri": "https://styles.redditmedia.com/t5_2rfxx/styles/communityIcon_9yj66cjf8oq61.png"
        },
        "vault": {
            "id": 1,
            "name": "Work Accounts"
        }
    },
    "message": "[+] Account retrieved successfully.",
    "status": "FOUND"
}
```

### Descriptive Response Messages
```json
{
    "data": {
        "id": 3,
        "username": "0xGhazy",
        "password": "15948875236",
        "platform": {
            "id": 3,
            "name": "LOL",
            "iconUri": "https://styles.redditmedia.com/t5_2rfxx/styles/communityIcon_9yj66cjf8oq61.png"
        },
        "vault": {
            "id": 1,
            "name": "Work Accounts"
        }
    },
    "message": "[+] Account retrieved successfully.",
    "status": "FOUND"
}
```
- Builder Design Pattern


## Prerequisite
- Java 17
- Spring Boot
- Maven
- MySql

## Built with

<div align="center">

![Static Badge](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot) ![Static Badge](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white) ![Static Badge](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white) ![Static Badge](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white) ![Static Badge](https://img.shields.io/badge/Markdown-000000?style=for-the-badge&logo=markdown&logoColor=white) ![Static Badge](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white) ![Static Badge](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white) ![Static Badge](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white)

</div>

## Installation and Usage
1. Clone this repository into your local machine.
2. Install dependencies using the `pom.xml` file.
3. Edit the `application.properties` file and add your configurations.

```properties
# Server Settings
server.port=8000

# database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/pass_guard
spring.datasource.username=root
spring.datasource.password=0000

# hibernate properties
# makes hibernate generate better sql for the chosen database.
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update

springdoc.swagger-ui.disable-swagger-default-url=true
# change the Swagger UI
springdoc.swagger-ui.path=/doc

# security configurations
security.salt=test_salt
security.key=test_secret_key
```

