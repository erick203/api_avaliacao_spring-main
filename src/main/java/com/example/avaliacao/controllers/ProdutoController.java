package com.example.avaliacao.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.avaliacao.model.Produto;
import com.example.avaliacao.services.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<Produto>> procurarTodos() {
        List<Produto> lista = service.procurarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> procurarPorId(@PathVariable Long id) {
        Produto produto = service.buscarProdutoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @PostMapping
    public ResponseEntity<String> criarProduto(@RequestBody Produto produto) {
        String response = service.criarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        String response = service.editarProduto(id, produto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirProduto(@PathVariable Long id) {
        String response = service.excluirProduto(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}