package com.gvendas.dto;

import com.gvendas.entidades.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@ApiModel("Categoria Requisicao DTO")
public class CategoriaRequestDto {

    @ApiModelProperty(value = "Nome")
    @NotBlank(message = "Nome")
    @Length(min = 3, max = 50, message = "Nome")
    private String nome;

    public Categoria converterParaEntidade(){
        return new Categoria(nome); // retornando uma categoria com o nome que foi preenchido
    }

    public Categoria converterParaEntidade(Long codigo){
        return new Categoria(codigo, nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
