package com.example.avaliacao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.avaliacao.exceptions.NaoEncontradoException;
import com.example.avaliacao.model.Produto;
import com.example.avaliacao.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    // GET - Lista todos os produtos
    public List<Produto> procurarTodos() {
        return repository.findAll();
    }

    // GET by ID - Busca um produto pelo seu Id
    public Produto buscarProdutoPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("ID inválido! Produto não encontrado"));
    }

    // POST - Cria um novo produto
    public String criarProduto(Produto produto) {
        repository.save(produto);
        return "Produto cadastrado com sucesso!";
    }

    // PUT - Edita um produto existente
    public String editarProduto(Long id, Produto produto) {
        Produto response = buscarProdutoPorId(id); // Já reaproveita a validação de ID

        // Só atualiza os campos que não vierem nulos no JSON
        if (produto.getNome() != null) {
            response.setNome(produto.getNome());
        }
        if (produto.getPreco() != null) {
            response.setPreco(produto.getPreco());
        }
        if (produto.getEstoque() != null) {
            response.setEstoque(produto.getEstoque());
        }
        if (produto.getCliente() != null) {
            response.setCliente(produto.getCliente());
        }

        repository.save(response);
        return "Produto editado com sucesso!";
    }

    // DELETE - Exclui um produto existente
    public String excluirProduto(Long id) {
        Produto response = buscarProdutoPorId(id); // Valida se existe antes de excluir
        repository.delete(response);
        return "Produto excluído com sucesso!";
    }
}