package br.com.curso.controle_estoque.service.venda;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curso.controle_estoque.dominio.Produto;
import br.com.curso.controle_estoque.dominio.Venda;
import br.com.curso.controle_estoque.exceptions.VendaException;
import br.com.curso.controle_estoque.gateway.json.VendaJson;
import br.com.curso.controle_estoque.repository.VendaRepository;
import br.com.curso.controle_estoque.service.estoque.EstoqueService;
import br.com.curso.controle_estoque.service.produto.ProdutoService;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private EstoqueService estoqueService;
	
	@Transactional
	public void venda(VendaJson vendaJson){
	
		Produto produto = produtoService.getProduto(vendaJson.getDescricao());
		
		if( produto == null){
			throw new VendaException("Produto não existe.");
		}
		
		if( !estoqueService.isEstoqueSuficiente(produto.getId(), vendaJson.getQuantidade()) ){
			throw new VendaException("Estoque insuficiente.");
		}

		Venda venda = new Venda();
		venda.setQuantidade(vendaJson.getQuantidade());
		venda.setProduto(produto);
		
		vendaRepository.save(venda);
		
		estoqueService.atualizarEstoque(produto.getId(), vendaJson.getQuantidade());
	}

}
