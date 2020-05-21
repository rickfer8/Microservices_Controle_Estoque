package br.com.controle.estoque.buyprocess.service.processar;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.controle.estoque.buyprocess.gateway.json.VendaChaveJson;
import br.com.controle.estoque.buyprocess.gateway.json.VendaFinalizadaJson;
import br.com.controle.estoque.buyprocess.service.banco.BancoService;
import br.com.controle.estoque.buyprocess.service.banco.VendaRetorno;



@Service
public class ListenerService {
	
	@Autowired
	private BancoService bancoService;

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${fila.entrada}")
	private String nomeFilaRepublicar;
	
	@Value("${fila.finalizado}")
	private String nomeFilaFinalizado;
	
	@HystrixCommand(fallbackMethod = "republicOnMessage")
	@RabbitListener(queues="${fila.entrada}")
	public void onMessage(Message message) throws JsonParseException, JsonMappingException, IOException  {
		
		String json = new String(message.getBody(), "UTF-8");
		
		System.out.println("Mensagem recebida:" + json);
		
		ObjectMapper mapper = new ObjectMapper();
		VendaChaveJson vendaChaveJson = mapper.readValue(json, VendaChaveJson.class);
		
		VendaRetorno venda = bancoService.vender(vendaChaveJson);
		
		VendaFinalizadaJson vendaFinalizadaJson = new VendaFinalizadaJson();
		vendaFinalizadaJson.setVendaChaveJson(vendaChaveJson);
		vendaFinalizadaJson.setVendaOK(venda.isVendaOK());
		vendaFinalizadaJson.setMensagem(venda.getMensagem());
		
		org.codehaus.jackson.map.ObjectMapper obj = new org.codehaus.jackson.map.ObjectMapper();
		String jsonFinalizado = obj.writeValueAsString(vendaFinalizadaJson);
		
		rabbitTemplate.convertAndSend(nomeFilaFinalizado, jsonFinalizado);		
	}
	
	public void republicOnMessage(Message message) throws JsonParseException, JsonMappingException, IOException  {
		System.out.println("Republicando mensagem...");
		rabbitTemplate.convertAndSend(nomeFilaRepublicar, message);
	}

}
