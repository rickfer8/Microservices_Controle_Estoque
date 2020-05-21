package br.com.curso.controle_estoque.service.estoque;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curso.controle_estoque.repository.EstoqueRepository;

@Service
public class EstoqueService {
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	public boolean isEstoqueSuficiente(Long idProduto, Integer quantidade) {
		return estoqueRepository.isEstoqueSuficiente(idProduto, quantidade) > 0;
	}
	
	@Transactional
	public void atualizarEstoque(Long idProduto, Integer quantidade) {
		estoqueRepository.atualizarEstoque(idProduto, quantidade);
	}

}
