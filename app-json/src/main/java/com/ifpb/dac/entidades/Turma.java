package com.ifpb.dac.entidades;

public class Turma {
    
    private int codigo;
    private String identificacao;
    private String disciplina;
    private int curso;
    private int professor;

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

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public int getProfessor() {
        return professor;
    }

    public void setProfessor(int professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Turma{" + "codigo=" + codigo + ", identificacao=" + identificacao + ", disciplina=" + disciplina + ", curso=" + curso + ", professor=" + professor + '}';
    }
    
}
