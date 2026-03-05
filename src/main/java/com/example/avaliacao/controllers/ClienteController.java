package com.example.avaliacao.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.avaliacao.model.Cliente;
import com.example.avaliacao.services.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Gerenciamento de Clientes", description = "Endpoints para o CRUD de clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;
    
    @Operation(summary = "Lista todos os clientes", description = "Retorna uma lista contendo todos os clientes cadastrados no banco de dados.")
    @GetMapping
    public ResponseEntity<List<Cliente>> procurarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.procurarTodos());
    }
    
    @Operation(summary = "Busca cliente por ID", description = "Retorna um único cliente filtrado pelo seu ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> procurarPorId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarClientePorId(id));
    }
    
    @Operation(summary = "Cadastra um cliente", description = "Insere um novo cliente no banco de dados. Valida se o e-mail já existe.")
    @PostMapping
    public ResponseEntity<String> criarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarCliente(cliente));
    }
    
    @Operation(summary = "Edita um cliente", description = "Atualiza os dados de um cliente existente. Apenas os campos enviados serão alterados.")
    @PutMapping("/{id}")
    public ResponseEntity<String> editarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.OK).body(service.editarCliente(id, cliente));
    }
    
    @Operation(summary = "Exclui um cliente", description = "Remove um cliente do banco de dados pelo seu ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirCliente(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.excluirCliente(id));
    }
}