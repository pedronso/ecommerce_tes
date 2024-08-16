package br.edu.ufape.residencia.util.dto;

import lombok.Data;

@Data
public class ProdutoDto {
	private Long id;
	private String nome;
	private String descricao;
	private CategoriaDto categoria;

}
