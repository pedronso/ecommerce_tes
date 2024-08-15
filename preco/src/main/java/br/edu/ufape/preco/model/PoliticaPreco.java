package br.edu.ufape.preco.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Data
@NoArgsConstructor
@Entity
public class PoliticaPreco {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String descricao;
	private int desconto;
	private long[] produto_id;
}
