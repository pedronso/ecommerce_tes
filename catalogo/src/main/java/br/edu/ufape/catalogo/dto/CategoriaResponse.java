package br.edu.ufape.catalogo.dto;

import br.edu.ufape.catalogo.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author pedro.alves
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoriaResponse {

    private Long id;
    private String nome;
    private String descricao;

    public CategoriaResponse(Categoria c) {
		this.id = c.getId();
		this.nome = c.getNome();
		this.descricao = c.getDescricao();
	}

}