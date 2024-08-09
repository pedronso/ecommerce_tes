package br.edu.ufape.preco.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Id;

import br.edu.ufape.catalogo.model.Produto;

public class PoliticaPreco {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private int desconto;
	
	@OneToMany
	private Produto produto;
}
