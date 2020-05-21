package br.com.controle.estoque.vendafeedback.gateway.http;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle.estoque.vendafeedback.dominio.VendaRedis;
import br.com.controle.estoque.vendafeedback.exceptions.NaoFinalizadoException;
import br.com.controle.estoque.vendafeedback.repository.VendaRedisRepository;

@RestController
public class VendaController {

	@Autowired
	private VendaRedisRepository vendaRedisRepository;

	@RequestMapping(path = "/{chave}", method = RequestMethod.GET)
	public VendaRedis status(@PathVariable("chave") String chave) {
		Optional<VendaRedis> venda = vendaRedisRepository.findById(chave);

		if (!venda.isPresent()) {
			throw new NaoFinalizadoException();
		}

		return venda.get();
	}

}
