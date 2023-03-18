package com.gvendas.controller;

import com.gvendas.dto.CategoriaResponseDto;
import com.gvendas.dto.CategoriaRequestDto;
import com.gvendas.entidades.Categoria;
import com.gvendas.service.CategoriaServiceImpl;
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


@Api(tags = "CATEGORIA")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaServiceImpl service;

    @ApiOperation(value = "Listar Todas",nickname = "listarTodas")
    @GetMapping
    public List<CategoriaResponseDto> listarTodas() {
        return service.listarTodas().stream().map(categoria -> CategoriaResponseDto.converterParaCategoriaDto(categoria))
                .collect(Collectors.toList()); // uso o collect(Collectors.toList()), poruqe estou retornando uma lista
    }

    @ApiOperation(value = "Lista por codigo",nickname = "buscarPorCodigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<CategoriaResponseDto> buscarPorCodigo(@PathVariable Long codigo){
        Optional<Categoria> categoria = service.buscarPorCodigo(codigo);
        return categoria.isPresent() ? ResponseEntity.ok(CategoriaResponseDto.converterParaCategoriaDto(categoria.get())) : ResponseEntity.notFound().build();
    }



    @ApiOperation(value = "Salvar", nickname = "save categoria")
    @PostMapping
    public ResponseEntity<CategoriaResponseDto> save(@Valid @RequestBody CategoriaRequestDto categoriaRequestDto){
        Categoria categoria = service.save(categoriaRequestDto.converterParaEntidade());
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResponseDto.converterParaCategoriaDto(categoria));
    }

    @ApiOperation(value = "Atualizar", nickname = "atualizar categoria")
    @PutMapping("/{codigo}")
    public ResponseEntity<CategoriaResponseDto> atualizar(@PathVariable Long codigo, @Valid @RequestBody CategoriaRequestDto categoriaRequestDto){
        Categoria categoria = service.atualizar(codigo, categoriaRequestDto.converterParaEntidade(codigo));
        return ResponseEntity.ok(CategoriaResponseDto.converterParaCategoriaDto(categoria));
    }

    @ApiOperation(value = "Deletar", nickname = "delete categoria")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo){
        service.delete(codigo);

    }

}
