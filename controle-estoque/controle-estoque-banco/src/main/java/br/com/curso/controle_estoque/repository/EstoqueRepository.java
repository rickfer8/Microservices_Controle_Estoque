package br.com.curso.controle_estoque.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import br.com.curso.controle_estoque.dominio.Estoque;

public interface EstoqueRepository extends Repository<Estoque, Long> {
	
	@Query("select count(obj.id) from Estoque obj where obj.produto.id = ?1 and obj.quantidade >= ?2")
	Integer isEstoqueSuficiente(Long idProduto, Integer quantidade);
	
	@Modifying
	@Query("update Estoque set quantidade = (quantidade - ?2) where produto.id = ?1 ")
	void atualizarEstoque(Long idProduto, Integer quantidade);

}
