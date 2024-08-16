package br.edu.ufape.estoque.controller.dto.response;

import java.util.Date;

import br.edu.ufape.estoque.model.Entrada;
import lombok.Getter;
import lombok.Setter;

@Getter
public class EntradaResponse {
	private Long id;
	private int qtd;
	private ProdutoResponse produto;
	private Date dataEntrada;
	private float precoUnidade;

	public EntradaResponse(Entrada obj) {
		id = obj.getId();
		qtd = obj.getQtd();
		produto = new ProdutoResponse(obj.getProduto());
		dataEntrada = obj.getDataEntrada();
		precoUnidade = obj.getPrecoUnidade();
	}
}
