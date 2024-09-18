package br.edu.ufape.catalogo.dto;

import org.hibernate.validator.constraints.Length;

import br.edu.ufape.catalogo.model.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class ProdutoRequest {

    Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 35, message = "O nome deve ter entre 3 e 25 letras")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    @Length(min = 3, max = 35, message = "A descrição deve ter entre 3 e 50 letras")
    private String descricao;
    
    @NotNull(message = "O ID da categoria é obrigatório")
    private Long categoriaId;

    public Produto toEntity() {
        Produto produto = new Produto();

        produto.setId(this.id);
        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);

        return produto;
    }
}
