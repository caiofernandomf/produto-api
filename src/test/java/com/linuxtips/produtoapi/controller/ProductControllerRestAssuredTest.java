package com.linuxtips.produtoapi.controller;

import com.linuxtips.produtoapi.model.Produto;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class ProductControllerRestAssuredTest {

    static {
        RestAssured.baseURI="HTTP://localhost:8080/";
    }

    public Response criaProduto(Produto produto)throws Exception{
        RequestSpecification requestSpecification =
                given()
                        .contentType("application/json")
                        .body(produto);
        return requestSpecification.post("/produtos");
    }

    private ResponseSpecification responseSpecification(int responseStatus){
        return new ResponseSpecBuilder()
                .expectStatusCode(responseStatus)
                .build();
    }

    @Test
    @DisplayName("Deve cadastrar um produto com sucesso")
    public void deveCriarProdutoComSucesso(){
        try{
            criaProduto(new Produto("123","Java",799.0))
                    .then()
                    .assertThat().spec(responseSpecification(201))
                    .and()
                    .assertThat()
                    .body("nome",equalTo("Java"));
        }catch (Exception e){
            fail("Não foi possível cadastrar um produto",e);
        }
    }

    @Test
    @DisplayName("Deve retornar um produto pelo id com sucesso")
    public void deveRetornarProdutoComSucesso(){
        given().
                when()
                .basePath("/produtos")
                .get("/123")
                .then()
                .assertThat()
                .spec(responseSpecification(200))
                .assertThat()
                .body("produtoId",equalTo("123"))
                .body("nome",equalTo("Java"))
                .body("preco",equalTo(799.0f));
    }

    @Test
    @DisplayName("Deve excluir um produto com sucesso")
    public void deveExcluirProdutoComSucesso(){
        try {

            criaProduto(new Produto("456", "Go", 699.0d));

            given()
                    .when()
                    .basePath("/produtos")
                    .delete("456")
                    .then()
                    .assertThat()
                    .spec(responseSpecification(204));
        }catch (Exception e){
            fail("Não foi possível excluir um produto");
        }
    }

    @Test
    @DisplayName("Deve atualizar um produto com sucesso")
    public void deveAtualizarUmProdutoComSucesso()throws Exception{
        Produto produto =
                criaProduto(new Produto("163","Rust",1000.0d))
                    .getBody()
                    .as(Produto.class);

        produto.setNome("Rust(~)");
        produto.setPreco(799.9d);

        Produto produtoAtualizado =given()
                .contentType("application/json")
                .body(produto)
                .when()
                .basePath("/produtos")
                .put("/163")
                .then()
                .assertThat()
                .spec(responseSpecification(200))
                .and()
                .extract()
                .as(Produto.class);

        assertEquals(produto,produtoAtualizado);

    }

    @Test
    @DisplayName("Deve retornar uma lista de produtos sucesso")
    public void deveRetornarListaDeProdutosComSucesso(){
       List listaProdutos =  given().
                when()
                .basePath("/produtos")
                .get("")
                .then()
                .assertThat()
                .spec(responseSpecification(200))
               .and().extract().as(List.class);

       assertTrue(listaProdutos.size()>0);
    }
}
