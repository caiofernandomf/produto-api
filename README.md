# produto-api
Projeto do curso Descomplicando Java e Spring da LinuxTips

# RUN:
    docker-compose up -d
    aws dynamodb create-table --table-name produto --attribute-definitions AttributeName=produtoId,AttributeType=S --key-schema AttributeName=produtoId,KeyType=HASH --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 --endpoint-url http://localhost:4566
    ./mvnw spring-boot:run