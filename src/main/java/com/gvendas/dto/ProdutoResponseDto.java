package com.gvendas.dto;

import com.gvendas.entidades.Categoria;
import com.gvendas.entidades.Produto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("Produto retorno DTO")
public class ProdutoResponseDto {
    @ApiModelProperty(value = "codigo")
    private Long codigo;
    @ApiModelProperty(value = "descricao")
    private String descricao;
    @ApiModelProperty(value = "quantidade")
    private Integer quantidade;
    @ApiModelProperty(value = "preco de custo")
    private BigDecimal precoCusto;
    @ApiModelProperty(value = "preco de venda")
    private BigDecimal precoVenda;
    @ApiModelProperty(value = "observacao")
    private String observacao;
    @ApiModelProperty(value = "categoria")
    private CategoriaResponseDto categoria;

    public ProdutoResponseDto(){
    }
    public ProdutoResponseDto(Long codigo, String descricao, Integer quantidade, BigDecimal precoCusto, BigDecimal precoVenda, String observacao, CategoriaResponseDto categoria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.observacao = observacao;
        this.categoria = categoria;
    }

    public static ProdutoResponseDto converteParaProdutoDto(Produto produto){
        return new ProdutoResponseDto(
                produto.getCodigo(), produto.getDescricao(), produto.getQuantidade(),
                produto.getPrecoCusto(), produto.getPrecoVenda(), produto.getObservacao(),
                CategoriaResponseDto.converterParaCategoriaDto(produto.getCategoria()));
    }

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

    public CategoriaResponseDto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaResponseDto categoria) {
        this.categoria = categoria;
    }
}
