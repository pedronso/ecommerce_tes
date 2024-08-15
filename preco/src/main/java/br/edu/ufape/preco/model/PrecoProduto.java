package br.edu.ufape.preco.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Data
@NoArgsConstructor
@Entity
public class PrecoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long[] produto_id;

    private Double precoBase;

    @ManyToOne
    private PoliticaPreco politicaPreco;
}