package com.ifpb.dac.rs.model;

import java.io.Serializable;

/**
 *
 * @author lyndemberg
 */
public class TurmaRest implements Serializable{
    private int codigo;
    private String identificacao;
    private String disciplina;
    private String professor;

    public TurmaRest() {
    }

    public TurmaRest(int codigo, String identificacao, String disciplina, String professor) {
        this.codigo = codigo;
        this.identificacao = identificacao;
        this.disciplina = disciplina;
        this.professor = professor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
    
    
    
}
