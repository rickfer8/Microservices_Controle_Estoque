package br.com.controle.estoque.vendafeedback.service;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.controle.estoque.vendafeedback.dominio.VendaRedis;
import br.com.controle.estoque.vendafeedback.gateway.json.VendaFinalizadaJson;
import br.com.controle.estoque.vendafeedback.repository.VendaRedisRepository;

@Service
public class FinalizarService {

	@Autowired
	private VendaRedisRepository vendaRedisRepository;

	@RabbitListener(queues = "${fila.finalizado}")
	public void onMessage(Message message) throws JsonParseException, JsonMappingException, IOException {

		String json = new String(message.getBody(), "UTF-8");

		System.out.println("Mensagem recebida:" + json);

		ObjectMapper mapper = new ObjectMapper();
		VendaFinalizadaJson vendaChaveJson = mapper.readValue(json, VendaFinalizadaJson.class);

		VendaRedis venda = new VendaRedis();
		venda.setId(vendaChaveJson.getVendaChaveJson().getChave());
		venda.setMensagem(vendaChaveJson.getMensagem());
		venda.setDescricao(vendaChaveJson.getVendaChaveJson().getVendaJson().getDescricao());
		venda.setQuantidade(vendaChaveJson.getVendaChaveJson().getVendaJson().getQuantidade());

		System.out.println("Gravando no venda....");
		vendaRedisRepository.save(venda);

	}

}
