package com.gvendas.dto;

import com.gvendas.entidades.Categoria;
import com.gvendas.entidades.Produto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@ApiModel("Produto Requisicao DTO")
public class ProdutoRequestDto {

    @ApiModelProperty(value = "descricao")
    @NotBlank(message="Descricao")
    @Length(min=3, max= 50,message= "Nome")
    private String descricao;

    @ApiModelProperty(value = "quantidade")
    @NotNull(message="Quantidade")
    private Integer quantidade;

    @ApiModelProperty(value = "preco de custo")
    @NotNull(message="Preco do custo")
    private BigDecimal precoCusto;

    @ApiModelProperty(value = "preco de venda")
    @NotNull(message="Preco de venda")
    private BigDecimal precoVenda;

    @ApiModelProperty(value = "observacao")
    @Length(max= 500,message= "Observacao")
    private String observacao;


    public Produto converterParaEntidade(Long codigoCategoria){
        return new Produto(descricao, quantidade, precoCusto, precoVenda, observacao, new Categoria(codigoCategoria));
    }

    public Produto converterParaEntidade(Long codigoCategoria, Long codigoProduto){
        return new Produto(codigoProduto, descricao, quantidade, precoCusto, precoVenda, observacao, new Categoria(codigoCategoria));
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
