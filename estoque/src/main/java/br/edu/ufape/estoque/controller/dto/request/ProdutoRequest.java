package br.edu.ufape.estoque.controller.dto.request;

import br.edu.ufape.estoque.model.Produto;

public class ProdutoRequest {

	private int qtd_max;
	private int qtd_min;

	public Produto toEntity() {
		return new Produto(0L, qtd_max, qtd_min);
	}
}
