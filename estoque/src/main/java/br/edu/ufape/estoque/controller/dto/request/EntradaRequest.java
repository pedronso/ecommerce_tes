package br.edu.ufape.estoque.controller.dto.request;

import java.util.Date;

import br.edu.ufape.estoque.model.Entrada;
import lombok.Getter;

@Getter
public class EntradaRequest {
	private int qtd;
	private ProdutoRequest produto;
	private Date dataEntrada;
	private float precoUnidade;

	public Entrada toEntity() {
		return new Entrada(0L, qtd, produto.toEntity(), dataEntrada, precoUnidade);
	}
}
