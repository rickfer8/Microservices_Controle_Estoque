package br.com.curso.controle_estoque.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import br.com.curso.controle_estoque.dominio.Produto;

public interface ProdutoRepository extends Repository<Produto, Long> {
	
	@Query("from Produto obj where obj.descricao = ?1")
	Produto findProduto(String descricao);

}
