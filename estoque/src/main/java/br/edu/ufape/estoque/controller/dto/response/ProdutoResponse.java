package br.edu.ufape.estoque.controller.dto.response;

import br.edu.ufape.estoque.model.Produto;
import lombok.Setter;

@Setter
public class ProdutoResponse {
	private Long id;
	private int qtd_max;
	private int qtd_min;

	public ProdutoResponse(Produto obj) {
		id = obj.getId();
		qtd_max = obj.getQtd_max();
		qtd_min = obj.getQtd_min();
	}
}
