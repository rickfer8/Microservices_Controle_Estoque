package br.com.controle_estoque.gateway.json;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaJson {

	@NotNull(message = "Descrição do produto é obrigatório")
	private String descricao;
	
	@NotNull(message = "Quantidade do produto é obrigatório")
	private Integer quantidade;

}
