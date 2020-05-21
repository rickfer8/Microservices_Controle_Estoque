package br.com.controle.estoque.buyprocess.gateway.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaJson {	
	
	private String descricao;	
	
	private Integer quantidade;

}
