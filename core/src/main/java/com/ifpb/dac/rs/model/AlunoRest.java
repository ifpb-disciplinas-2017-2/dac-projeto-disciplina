package com.ifpb.dac.rs.model;

import java.io.Serializable;

/**
 *
 * @author lyndemberg
 */
public class AlunoRest implements Serializable{
    private int id;
    private String nome;
    private String email;
    private String senha;
    private boolean logado;
    private CursoRest curso;

    public AlunoRest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AlunoRest(int id, String nome, String email, String senha, boolean logado, CursoRest curso) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.logado = logado;
        this.curso = curso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public CursoRest getCurso() {
        return curso;
    }

    public void setCurso(CursoRest curso) {
        this.curso = curso;
    }
    
}
