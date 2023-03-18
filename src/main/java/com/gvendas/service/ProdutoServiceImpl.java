package com.gvendas.service;

import com.gvendas.entidades.Produto;
import com.gvendas.exceptions.RegraNegocioException;
import com.gvendas.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl{

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaServiceImpl categoriaService;

    public List<Produto> listarTodos(Long codigoCategoria){
        return produtoRepository.findByCategoriaCodigo(codigoCategoria);
    }

    public Optional<Produto> buscarPorCodigo(Long codigo, Long codigoCategoria){
        return produtoRepository.buscarPorCodigo(codigo, codigoCategoria);
    }

    public Produto save(Long codigoCategoria, Produto produto){
        validarSeCategoriaDoProdutoExiste(codigoCategoria);
        validarProdutoDuplicado(produto);
        return produtoRepository.save(produto);

    }

    public Produto atualizar(Long codigoCategoria, Long codigoProduto, Produto produto){
        Produto produtoSalvar = validarSeProdutoExiste(codigoProduto, codigoCategoria);
        validarSeCategoriaDoProdutoExiste(codigoCategoria);
        validarProdutoDuplicado(produto);
        BeanUtils.copyProperties(produto, produtoSalvar, "codigo"); // Substitui o que esta no banco pelo que esta chegando, exeto o codigo, pois é minha chave primaria

        return produtoRepository.save(produtoSalvar);
    }

    public void delete(Long codigoCategoria, Long codigoProduto){
        Produto produto = validarSeProdutoExiste(codigoProduto, codigoCategoria);
        produtoRepository.delete(produto);
    }

    private void validarSeCategoriaDoProdutoExiste(Long codigoCategoria){
        if(codigoCategoria == null){
            throw new RegraNegocioException("A categoria não pode ser nula");
        }
        if(categoriaService.buscarPorCodigo(codigoCategoria).isEmpty()){
            throw new RegraNegocioException(String.format("A categoria de codigo %s informada não existe no cadastro", codigoCategoria));

        }
    }

    private Produto validarSeProdutoExiste(Long codigoProduto, Long codigoCategoria){
        Optional<Produto> produto = buscarPorCodigo(codigoProduto, codigoCategoria);
        if (produto.isEmpty()){
            throw new EmptyResultDataAccessException(1);

        }
        return produto.get();
    }

    private void validarProdutoDuplicado(Produto produto){
        Optional<Produto> produtoPorDescricao = produtoRepository.findyByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao());
        if (produtoPorDescricao.isPresent() && produtoPorDescricao.get().getCodigo() != produto.getCodigo()){
            throw new RegraNegocioException(String.format("o produto %s ja esta cadastrado", produto.getDescricao()));

        }
    }

}
