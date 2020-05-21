package br.com.curso.controle_estoque.service.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curso.controle_estoque.dominio.Produto;
import br.com.curso.controle_estoque.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto getProduto(String descricao) {
		return produtoRepository.findProduto(descricao);
	}
}
