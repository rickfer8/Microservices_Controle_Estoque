package br.com.controle.estoque.buyprocess.service.banco;

import java.io.IOException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.controle.estoque.buyprocess.gateway.json.BancoRetornoJson;
import br.com.controle.estoque.buyprocess.gateway.json.VendaChaveJson;
import br.com.controle.estoque.buyprocess.gateway.json.VendaJson;

@Service
public class BancoService {

	@Value("${banco.link}")
	private String link;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public VendaRetorno vender(VendaChaveJson vendaChaveJson) throws IOException {

		VendaJson json = new VendaJson();
		json.setDescricao(vendaChaveJson.getVendaJson().getDescricao());
		json.setQuantidade(vendaChaveJson.getVendaJson().getQuantidade());

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<VendaJson> entity = new HttpEntity<VendaJson>(json, headers);

		try {
			ResponseEntity<BancoRetornoJson> bancoRetorno = restTemplate.exchange(link, HttpMethod.POST, entity, BancoRetornoJson.class);
			return new VendaRetorno(bancoRetorno.getBody().getMensagem(), true);
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
				ObjectMapper mapper = new ObjectMapper();
				BancoRetornoJson obj = mapper.readValue(ex.getResponseBodyAsString(), BancoRetornoJson.class);
				return new VendaRetorno(obj.getMensagem(), false);
			}
			throw ex;
		} catch (RuntimeException ex) {
			throw ex;
		}
	}
}
