package br.edu.ufape.catalogo.dto;

import br.edu.ufape.catalogo.model.Produto;
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
public class ProdutoResponse {

    private Long id;
    private String nome;
    private String descricao;
    private Long categoriaId;

    public ProdutoResponse(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.categoriaId = produto.getCategoria() != null ? produto.getCategoria().getId() : null;
    }
}
