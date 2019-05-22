package br.com.locadora.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.locadora.model.Carro;

public interface CarroRepository extends CrudRepository<Carro, String>{

}
