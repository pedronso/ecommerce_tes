package br.edu.ufape.catalogo.dto;

import org.hibernate.validator.constraints.Length;

import br.edu.ufape.catalogo.model.Categoria;
import jakarta.validation.constraints.NotBlank;
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
public class CategoriaRequest {

    Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 35, message = "O nome deve ter entre 2 e 25 letras")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    @Length(min = 3, max = 35, message = "A descrição deve ter entre 3 e 50 letras")
    private String descricao;

    public Categoria toEntity() {
        Categoria categoria = new Categoria();

        categoria.setId(this.getId());
        categoria.setNome(this.getNome());
        categoria.setDescricao(this.getDescricao());
        return categoria;
    }
}
