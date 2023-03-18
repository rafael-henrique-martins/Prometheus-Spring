package com.gvendas.dto;

import com.gvendas.entidades.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel
public class ProdutoDto {

    @ApiModelProperty(value = "codigo")
    private Long codigo;
    @ApiModelProperty(value = "descricao")
    private String descricao;
    @ApiModelProperty(value = "quantidade")
    private Integer quantidade;
    @ApiModelProperty(value = "precoCusto")
    private BigDecimal precoCusto;
    @ApiModelProperty(value = "precoVenda")
    private BigDecimal precoVenda;
    @ApiModelProperty(value = "observacao")
    private String observacao;
    @ApiModelProperty(value = "categoria")
    private Categoria categoria;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
