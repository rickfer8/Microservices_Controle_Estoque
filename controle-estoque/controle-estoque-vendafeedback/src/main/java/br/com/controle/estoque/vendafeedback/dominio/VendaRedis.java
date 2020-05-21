package br.com.controle.estoque.vendafeedback.dominio;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash("venda")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaRedis {

	@Id
	private String id;
	private String mensagem;

	private String descricao;
	private Integer quantidade;

	private boolean vendaOK;

}
