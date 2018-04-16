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
    
    private String usuario;
    
    @ManyToOne
    @JoinColumn(name = "codigo_aluno")
    private Aluno aluno;
    @ManyToOne
    @JoinColumn(name = "codigo_turma")
    private Turma turma;
    
    public Duvida() {
        
    }

    public Duvida(String pergunta, String resposta, String usuario, Aluno aluno, Turma turma) {
        this.pergunta = pergunta;
        this.resposta = resposta;
        this.usuario = usuario;
        this.aluno = aluno;
        this.turma = turma;
    }

    public Duvida(String pergunta, Aluno aluno, Turma turma) {
        this.pergunta = pergunta;
        this.aluno = aluno;
        this.turma = turma;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public String toString() {
        return "Duvida{" + "id=" + id + ", pergunta=" + pergunta + ", resposta=" + resposta + ", usuario=" + usuario + ", aluno=" + aluno + ", turma=" + turma + '}';
    }
    
   
    
}
