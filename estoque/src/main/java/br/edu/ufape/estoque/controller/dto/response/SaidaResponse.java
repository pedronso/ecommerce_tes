package br.edu.ufape.estoque.controller.dto.response;

import java.util.Date;

import br.edu.ufape.estoque.model.Saida;
import lombok.Getter;

@Getter
public class SaidaResponse {
	private Long id;
	private int qtd;
	private ProdutoResponse produto;
	private Date dataSaida;
	private float precoUnidade;

	public SaidaResponse(Saida obj) {
		id = obj.getId();
		qtd = obj.getQtd();
		produto = new ProdutoResponse(obj.getProduto());
		dataSaida = obj.getDataSaida();
		precoUnidade = obj.getPrecoUnidade();
	}
}
