package com.ifpb.dac.entidades;

public class Curso {
    
    private int codigo;
    private String unidade;
    private String descricao;
    private String abreviacao;
    private int periodos;
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
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

    public int getPeriodo() {
        return periodos;
    }

    public void setPeriodo(int periodo) {
        this.periodos = periodo;
    }

    @Override
    public String toString() {
        return "Cursos{" + "codigo=" + codigo + ", unidade=" + unidade + ", descricao=" + descricao + ", abreviacao=" + abreviacao + ", periodos=" + periodos + '}';
    }
    
}
