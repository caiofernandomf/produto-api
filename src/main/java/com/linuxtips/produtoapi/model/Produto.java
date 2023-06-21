package com.linuxtips.produtoapi.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamoDBTable(tableName = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String  produtoId;

    @DynamoDBAttribute
    private String nome;

    @DynamoDBAttribute
    private Double preco;
}