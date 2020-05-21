package br.com.controle.estoque.vendafeedback.gateway.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VendaFinalizadaJson {

    private VendaChaveJson vendaChaveJson;
    private String mensagem;
    private boolean vendaOK;

}
