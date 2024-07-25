/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ufape.catalogo.repository;

import br.edu.ufape.catalogo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pedro.alves
 */
public interface ProdutoInterface extends JpaRepository<Produto, Long> {
    
}
