package com.ifpb.dac.entidades;

public class Disciplina {
    
    private int codigo;
    private int curso;
    private String descricao;
    private String abreviacao;
    private int periodo;
    private int carga_horaria;
    private int aulas_semana;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
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
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(int carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public int getAulas_semana() {
        return aulas_semana;
    }

    public void setAulas_semana(int aulas_semana) {
        this.aulas_semana = aulas_semana;
    }

    @Override
    public String toString() {
        return "Disciplinas{" + "codigo=" + codigo + ", curso=" + curso + ", descricao=" + descricao + ", abreviacao=" + abreviacao + ", periodo=" + periodo + ", carga_horaria=" + carga_horaria + ", aulas_semana=" + aulas_semana + '}';
    }
    
}
