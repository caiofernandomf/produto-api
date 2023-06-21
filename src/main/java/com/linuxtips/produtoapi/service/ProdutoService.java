package com.linuxtips.produtoapi.service;

import com.linuxtips.produtoapi.model.Produto;
import com.linuxtips.produtoapi.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ResponseEntity<Produto> criarProduto(Produto produto){
        return
                ResponseEntity.ok().body(
                    produtoRepository.salvarProduto(produto)
                );
    }

    public ResponseEntity<Produto> buscarProduto(String produtoId){
        return

                produtoRepository.retornaProduto(produtoId)
                        .map(produto -> ResponseEntity.ok().body(produto))
                        .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Produto> atualizarProduto(String produtoId, Produto produto){
        return
                produtoRepository.retornaProduto(produtoId)
                .map(produto1 ->{
                        produto1.setNome(produto.getNome());
                        produto1.setPreco(produto.getPreco());
                        return ResponseEntity.ok().body(
                                produtoRepository.atualizar(produtoId,produto1));
                }
                ).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<String> exlcuirProduto(String produtoId){
        return
                produtoRepository.retornaProduto(produtoId)
                        .map(produto -> ResponseEntity
                                .ok()
                                .body(produtoRepository.excluir(produto) )
                ).orElse(ResponseEntity.notFound().build());
    }

    public List<Produto> listarProdutos(){
        return produtoRepository.listarProdutosComScan();
    }
}
