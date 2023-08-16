package com.linuxtips.produtoapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linuxtips.produtoapi.model.Produto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Deve buscar retornar produto pelo Id com sucesso")
    public void deveRetornarUmProdutoPeloId() throws Exception{

        this.mockMvc.perform(get("/produtos/{produtoId}",123)
        ).andDo(print()).andExpect(status().isOk());

    }

    @Test
    @DisplayName("Deve retornar uma lista de produtos com sucesso")
    public void deveRetornarListaDeProdutos() throws Exception{

        this.mockMvc.perform(get("/produtos")
        ).andDo(print()).andExpect(status().isOk());

    }

    @Test
    @DisplayName("Deve criar um produto com sucesso")
    public String deveCriarUmProduto() throws Exception{
        String uuid = UUID.randomUUID().toString();
        this.mockMvc.perform(
                post("/produtos")
                        .content(asJsonString(new Produto(
                               uuid ,"Java",799.0
                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated());
        return uuid;
    }

    @Test
    @DisplayName("Deve atualizar um produto com sucesso")
    public void deveAtualizarUmProduto() throws Exception{
        String uuid = deveCriarUmProduto();

        this.mockMvc.perform(
                        put("/produtos/{produtoId}",uuid)
                                .content(asJsonString(new Produto(
                                        uuid,"Go",1099.9
                                )))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve excluir um produto com sucesso")
    public void deveExcluirUmProduto() throws Exception{
        String uuid = deveCriarUmProduto();

        this.mockMvc.perform(
                        delete("/produtos/{produtoId}",uuid))
                .andDo(print()).andExpect(status().isNoContent());
    }
}