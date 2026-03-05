package com.example.avaliacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.avaliacao.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	boolean existsByEmail(String email);
	//findAll()
	//findById(id)
	//save()
	//delete()
	//findByNome()
	//findByEmail()
}
