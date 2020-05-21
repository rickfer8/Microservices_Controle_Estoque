package br.com.curso.controle_estoque.gateway.http;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.curso.controle_estoque.gateway.json.RetornoJson;
import br.com.curso.controle_estoque.gateway.json.VendaJson;
import br.com.curso.controle_estoque.service.venda.VendaService;



@RestController
public class VendaController {
	
	@Autowired
	private VendaService vendaService;
	
	@RequestMapping(path = "/venda", method = RequestMethod.POST)
	public ResponseEntity<RetornoJson> pagamento(
			@Valid @NotNull @RequestBody VendaJson vendaJson) {

		vendaService.venda(vendaJson);
		
		RetornoJson retorno = new RetornoJson();
		retorno.setMensagem("Venda registrada com sucesso");
		
		return new ResponseEntity<RetornoJson>(retorno, HttpStatus.OK);
	}

}
