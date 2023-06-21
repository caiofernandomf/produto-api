package com.linuxtips.produtoapi.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.linuxtips.produtoapi.model.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProdutoRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public Produto salvarProduto(Produto produto){
        dynamoDBMapper.save(produto);
        return produto;
    }

    public Optional<Produto> retornaProduto(String produtoId){
        return  Optional.ofNullable(dynamoDBMapper.load(Produto.class,produtoId));
    }

    public List<Produto> listarProdutos(){
        return dynamoDBMapper.query(
                Produto.class,
                new DynamoDBQueryExpression<Produto>()
        );
    }

    public List<Produto> listarProdutosComScan(){
        return dynamoDBMapper.scan(
                Produto.class
                ,new DynamoDBScanExpression().withConsistentRead(false));
    }

    public List<Produto> listarProdutosComQueryTable(){
        return dynamoDBMapper.queryPage(
                Produto.class,
                new DynamoDBQueryExpression<Produto>().withConsistentRead(false)
                        .withProjectionExpression("produtoId,nome,preco")
        ).getResults();
    }

    public String excluir(Produto produto){
        //Produto produto = dynamoDBMapper.load(Produto.class,produtoId);
        dynamoDBMapper.delete(produto);
        return "produto exluído com sucesso !";
    }

    public Produto atualizar(String produtoId,Produto produto){
        dynamoDBMapper.save(
                produto,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("produtoId"
                                ,new ExpectedAttributeValue(
                                        new AttributeValue().withS(produtoId))));
        return produto;
    }

}
