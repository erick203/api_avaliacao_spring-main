package com.example.avaliacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.avaliacao.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
}