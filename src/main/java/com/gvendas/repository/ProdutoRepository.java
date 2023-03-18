package com.gvendas.repository;

import com.gvendas.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // dentro de produto busco por Categoria e dentro da classe categoria eu quero o codigo
    List<Produto> findByCategoriaCodigo(Long codigo);

    @Query("SELECT prod"
            + "FROM Produto prod"
            + "WHERE prod.codigo = :codigo"
            + "    AND prod.categoria.codigo = :codigoCategoria")
    Optional<Produto> buscarPorCodigo(Long codigo, Long codigoCategoria);

//    a query de cima poderia ser escrita da seguinte forma
//    FindByCodigoAndCategoriaCodigo


    //buscar por codigo da Categoria, assim eu sei se essa descricao existe dentro do codigo da categoria
    Optional<Produto> findyByCategoriaCodigoAndDescricao(Long codigoCategoria, String descricao);
}

