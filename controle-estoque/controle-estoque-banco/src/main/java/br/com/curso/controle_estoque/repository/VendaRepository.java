package br.com.curso.controle_estoque.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.curso.controle_estoque.dominio.Venda;

public interface VendaRepository extends CrudRepository<Venda, Long	> {

}
