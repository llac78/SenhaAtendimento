package com.llac.senha.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.llac.senha.model.SenhaGerada;

@Repository
public interface SenhaGeradaRepository extends CrudRepository<SenhaGerada, Long> {
	
	@Query(value="SELECT MAX(id) FROM senhas", nativeQuery=true)
    public Long pesquisarMaximoId();
}
