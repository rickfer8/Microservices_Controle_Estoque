package br.com.controle.estoque.buyprocess.gateway.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaFinalizadaJson {

    private VendaChaveJson vendaChaveJson;
    private String mensagem;
    private boolean vendaOK;
}
