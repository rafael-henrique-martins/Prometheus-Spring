package com.gvendas.service;

import com.gvendas.entidades.Categoria;
import com.gvendas.exceptions.RegraNegocioException;
import com.gvendas.repository.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodas(){
        return categoriaRepository.findAll();
        }

    public Optional<Categoria> buscarPorCodigo(Long codigo){
        return categoriaRepository.findById(codigo);
    }

    public Categoria save(Categoria categoria){
        validarCategoriaDuplicada(categoria);
        return categoriaRepository.save((categoria));

    }

    public Categoria atualizar(Long codigo, Categoria categoria){
        Categoria categoriaSave = validadeSeCategoriaExiste(codigo);
        validarCategoriaDuplicada(categoria);
        BeanUtils.copyProperties(categoria, categoriaSave, "codigo"); // Substitui o que esta no banco pelo que esta chegando, exeto o id, pois é minha chave primaria
        return categoriaRepository.save(categoriaSave);
    }

    public void delete(Long codigo){
        categoriaRepository.deleteById(codigo);
    }

    private Categoria validadeSeCategoriaExiste(Long codigo){
        Optional<Categoria> categoria = buscarPorCodigo(codigo);
        if(categoria.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return  categoria.get();
    }

    private void validarCategoriaDuplicada(Categoria categoria){
        Categoria categoriaEncontrada = categoriaRepository.findByNome(categoria.getNome());
        if (categoriaEncontrada != null && categoriaEncontrada.getCodigo() != categoria.getCodigo()){
            throw new RegraNegocioException(String.format("A categoria %s já esta cadastrada",
                    categoria.getNome().toUpperCase()));
        }
    }

}
