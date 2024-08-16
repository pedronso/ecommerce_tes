package br.edu.ufape.estoque.controller.dto.request;

import java.util.Date;

import br.edu.ufape.estoque.model.Saida;

public class SaidaRequest {

	private int qtd;
	private ProdutoRequest produto;
	private Date dataSaida;
	private float precoUnidade;

	public Saida toEntity() {
		return new Saida(0L, qtd, produto.toEntity(), dataSaida, precoUnidade);
	}
}
