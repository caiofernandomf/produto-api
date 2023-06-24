# produto-api
Projeto do curso Descomplicando Java e Spring da LinuxTips

# RUN(Linux):
    docker-compose up -d
    aws dynamodb create-table --table-name produto --attribute-definitions AttributeName=produtoId,AttributeType=S --key-schema AttributeName=produtoId,KeyType=HASH --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 --endpoint-url http://localhost:4566
    ./mvnw spring-boot:run

## ⚙️ Project Dependencies
    - Lombok
    - aws-java-sdk-dynamodb

## 💻 Environment dependencies
    - Java 19 
    - AWS CLI
    - Docker
    - docker-compose

## ROUTES

<br>

Rest/Restful architecture - HTTP - POST, GET, PUT, DELETE - CRUD.

<br>

### POST

<div align = "center">

| Method |                route                |     Description      |
|:------:|:-----------------------------------:|:--------------------:|
| `POST` | http://localhost:8080/produtos | Create a new product |

<br>
</div>

### GET

<div align = "center">

| Method |                   route                    |     Description      |
|:------:|:------------------------------------------:|:--------------------:|
| `GET`  |       http://localhost:8080/produtos       | List all products   |
| `GET`  | http://localhost:8080/produtos/{produtoId} | List a product by Id |

<br>
</div>

### PUT

<div align = "center">

| Method |                   route                    |   Description   |
|:------:|:------------------------------------------:|:---------------:|
| `PUT`  | http://localhost:8080/produtos/{produtoId} | Upate a product |

<br>
</div>

### DELETE

<div align = "center">

|  Method  |                   route                    |   Description    |
|:--------:|:------------------------------------------:|:----------------:|
| `DELETE` | http://localhost:8080/produtos/{produtoId} | Delete a product |

<br>
</div>