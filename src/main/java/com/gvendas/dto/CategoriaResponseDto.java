package com.gvendas.dto;

import com.gvendas.entidades.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Categoria retorno DTO")
public class CategoriaResponseDto {

    @ApiModelProperty(value = "codigo")
    private Long codigo;

    @ApiModelProperty(value = "nome")
    private String nome;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public CategoriaResponseDto(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
    public static CategoriaResponseDto converterParaCategoriaDto(Categoria categoria) {
        return new CategoriaResponseDto(categoria.getCodigo(), categoria.getNome());
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
