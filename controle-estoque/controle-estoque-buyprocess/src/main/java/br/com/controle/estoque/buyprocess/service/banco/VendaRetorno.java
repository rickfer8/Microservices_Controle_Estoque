package br.com.controle.estoque.buyprocess.service.banco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaRetorno {
    private String mensagem;
    private boolean vendaOK;

}
