/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.dac.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author natan
 */
@Entity
public class Duvida implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "codigo_duvida")
    private int id;
    
    private String pergunta;
    
    private String resposta;
    
    @ManyToOne
    @JoinColumn(name = "codigo_aluno")
    private Aluno aluno;
    @ManyToOne
    @JoinColumn(name = "codigo_professor")
    private Professor professor;
    
    public Duvida() {
        this.resposta = null;
    }
    
    public Duvida(String pergunta, Aluno aluno, Professor professor) {
        this();
        this.pergunta = pergunta;
        this.aluno = aluno;
        this.professor = professor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
    
}
