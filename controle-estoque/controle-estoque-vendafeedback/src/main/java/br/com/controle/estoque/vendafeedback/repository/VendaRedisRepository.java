package br.com.controle.estoque.vendafeedback.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.controle.estoque.vendafeedback.dominio.VendaRedis;

public interface VendaRedisRepository extends CrudRepository<VendaRedis, String> {

}
