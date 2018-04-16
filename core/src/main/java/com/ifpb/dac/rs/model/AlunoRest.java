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
    private boolean logado;
    private CursoRest curso;
    private String token;
    
    public AlunoRest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AlunoRest(int id, String nome, String email, boolean logado, CursoRest curso) {
        this.id = id;
        this.nome = nome;
        this.email = email;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}
