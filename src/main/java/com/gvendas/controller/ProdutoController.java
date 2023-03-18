package com.gvendas.controller;

import com.gvendas.dto.ProdutoRequestDto;
import com.gvendas.dto.ProdutoResponseDto;
import com.gvendas.entidades.Produto;
import com.gvendas.service.ProdutoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Api(tags = "PRODUTO")
@RestController
@RequestMapping("/categorias/{codigoCategoria}/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoServiceImpl service;

    @ApiOperation(value = "Listar todos" ,nickname = "listarTodos")
    @GetMapping
    public List<ProdutoResponseDto> listarTodos(@PathVariable Long codigoCategoria) {
        return service.listarTodos(codigoCategoria).stream().map(produto -> ProdutoResponseDto.converteParaProdutoDto(produto))
                .collect(Collectors.toList());
    }
    @ApiOperation(value = "Procurar por codigo", nickname ="buscarPorCodigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<ProdutoResponseDto> buscarPorCodigo(@PathVariable Long codigo, @PathVariable Long codigoCategoria){
        Optional<Produto> produtoDto = service.buscarPorCodigo(codigo, codigoCategoria);
        return produtoDto.isPresent() ? ResponseEntity.ok(ProdutoResponseDto.converteParaProdutoDto(produtoDto.get())) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar", nickname ="save produto")
    @PostMapping
    public ResponseEntity<ProdutoResponseDto> save(@PathVariable Long codigoCategoria, @Valid @RequestBody ProdutoRequestDto produtoRequestDto){
        Produto produto = service.save(codigoCategoria, produtoRequestDto.converterParaEntidade(codigoCategoria));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponseDto.converteParaProdutoDto(produto));
    }

    @ApiOperation(value = "Atualizar", nickname ="atualizar produto")
    @PutMapping("/{codigoProduto}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long codigoCategoria,
                                             @PathVariable Long codigoProduto, @Valid @RequestBody ProdutoRequestDto produtoRequestDto){
        Produto produto = service.atualizar(codigoCategoria, codigoProduto, produtoRequestDto.converterParaEntidade(codigoCategoria, codigoProduto));
        return ResponseEntity.ok(ProdutoResponseDto.converteParaProdutoDto(produto));
    }

    @ApiOperation(value = "Deletar", nickname = "delete produto")
    @DeleteMapping("/{codigoProduto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto) {
        service.delete(codigoCategoria, codigoProduto);
    }
}
