/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.filial.repository;

// Importa a entidade Filial.
import br.com.ifba.prg03projeto.mercado.filial.entity.Filial;

// Importa o JpaRepository do Spring Data.
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilialRepository extends JpaRepository<Filial, Long> {
}
