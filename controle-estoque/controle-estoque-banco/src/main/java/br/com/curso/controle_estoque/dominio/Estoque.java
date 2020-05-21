package br.com.curso.controle_estoque.dominio;


import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="estoque")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estoque {
	
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	@JoinColumn(name = "id_produto", foreignKey = @ForeignKey(name = "fk_estoque_produto"))
	private Produto produto;
	private Integer quantidade;
	

}
