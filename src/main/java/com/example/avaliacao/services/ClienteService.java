package com.example.avaliacao.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.avaliacao.exceptions.DadosInvalidosException;
import com.example.avaliacao.exceptions.EmailJaExisteException;
import com.example.avaliacao.exceptions.NaoEncontradoException;
import com.example.avaliacao.model.Cliente;
import com.example.avaliacao.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<Cliente> procurarTodos() {
        return repository.findAll();
    }

    public Cliente buscarClientePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("ID inválido! Cliente não encontrado."));
    }

    public String criarCliente(Cliente cliente) {
        // Validação: Campos obrigatórios
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new DadosInvalidosException("O nome do cliente é obrigatório!");
        }
        if (cliente.getEmail() == null || cliente.getEmail().isBlank()) {
            throw new DadosInvalidosException("O e-mail do cliente é obrigatório!");
        }
        if (cliente.getTelefone() == null || cliente.getTelefone().isBlank()) {
            throw new DadosInvalidosException("O telefone do cliente é obrigatório!");
        }

        if (repository.existsByEmail(cliente.getEmail())) {
            throw new EmailJaExisteException("Já existe cliente com o email: " + cliente.getEmail());
        }
        
        repository.save(cliente);
        return "Cliente cadastrado com sucesso!";
    }

    public String editarCliente(Long id, Cliente cliente) {
        Cliente response = buscarClientePorId(id);

        if (cliente.getNome() != null && !cliente.getNome().isBlank()) {
            response.setNome(cliente.getNome());
        }
        if (cliente.getTelefone() != null && !cliente.getTelefone().isBlank()) {
            response.setTelefone(cliente.getTelefone());
        }
        
        if (cliente.getEmail() != null && !cliente.getEmail().isBlank() && !cliente.getEmail().equals(response.getEmail())) {
            if (repository.existsByEmail(cliente.getEmail())) {
                throw new EmailJaExisteException("Já existe cliente com o email: " + cliente.getEmail());
            }
            response.setEmail(cliente.getEmail());
        }

        repository.save(response);
        return "Cliente editado com sucesso";
    }

    public String excluirCliente(Long id) {
        Cliente response = buscarClientePorId(id);
        repository.delete(response);
        return "Cliente excluído com sucesso!";
    }
}