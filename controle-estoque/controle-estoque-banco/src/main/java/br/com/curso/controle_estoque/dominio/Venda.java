package br.com.curso.controle_estoque.dominio;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="venda")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venda {
	
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "id_produto", foreignKey = @ForeignKey(name = "fk_produto_venda"))
	private Produto produto;
	
	private Integer quantidade;
	

}
