package br.com.curso.controle_estoque.gateway.json;

import javax.validation.constraints.NotNull;

public class VendaJson {
	
	@NotNull(message="Descrição do produto é obrigatório")
	private String descricao;
	
	@NotNull(message="Quantidade de venda é obrigatório")
	private Integer quantidade;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}	


}
