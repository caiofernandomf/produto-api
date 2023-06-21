package com.linuxtips.produtoapi.controller;

import com.linuxtips.produtoapi.model.Produto;
import com.linuxtips.produtoapi.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping("/produtos")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto){
        return produtoService.criarProduto(produto);
    }

    @GetMapping("/produtos/{produtoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Produto> retornarProduto(@PathVariable String produtoId){
        return produtoService.buscarProduto(produtoId);
    }

    @GetMapping("/produtos")
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> listarProdutos(){
        return produtoService.listarProdutos();
    }

    @DeleteMapping("/produtos/{produtoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> excluirProduto(@PathVariable String produtoId){
        return produtoService.exlcuirProduto(produtoId);
    }

    @PutMapping("/produtos/{produtoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Produto> atualizarProduto(@PathVariable String produtoId,@RequestBody Produto produto){
        return produtoService.atualizarProduto(produtoId,produto);
    }


}
