package com.ifpb.dac.entidades;

public class Laboratorio {
    
    private int codigo;
    private String descricao;
    private String abreviacao;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    @Override
    public String toString() {
        return "Laboratorios{" + "codigo=" + codigo + ", descricao=" + descricao + ", abreviacao=" + abreviacao + '}';
    }
    
    
    
}
