package br.com.controle_estoque.gateway.http;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle_estoque.gateway.json.RetornoJson;
import br.com.controle_estoque.gateway.json.VendaChaveJson;
import br.com.controle_estoque.gateway.json.VendaJson;

@RestController
public class VendaController {
	
	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	@Value("${fila.saida}")
	private String nomeFila;
	
	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<RetornoJson> venda(
			@Valid @NotNull @RequestBody VendaJson vendaJson) throws Exception  {

		VendaChaveJson vendaChaveJson = new VendaChaveJson();
		vendaChaveJson.setVendaJson(vendaJson);
		vendaChaveJson.setChave(UUID.randomUUID().toString());

		ObjectMapper obj = new ObjectMapper();

		String json = obj.writeValueAsString(vendaChaveJson);
		
		rabbitTemplate.convertAndSend(nomeFila, json);
		
		RetornoJson retorno = new RetornoJson();
		retorno.setMensagem("Venda registrada com sucesso. Aguarda a confirmação da venda.");
		retorno.setChavePesquisa(vendaChaveJson.getChave());
		
		return new ResponseEntity<RetornoJson>(retorno, HttpStatus.OK);
	}

}
