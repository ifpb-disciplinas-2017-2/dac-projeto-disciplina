
package com.ifpb.dac.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author rodrigobento
 */
@Entity
@SequenceGenerator(name = "minha_seq_aluno",
        sequenceName = "seq_aluno",
        initialValue = 1,
        allocationSize = 1)
public class Aluno implements Serializable {
    
    @Id
    @GeneratedValue(generator = "minha_seq_aluno", strategy = GenerationType.SEQUENCE)
    private int id;
    private String nome;
    private String email;
    private String senha; 
    @ManyToMany(mappedBy = "alunos", fetch = FetchType.EAGER)
    private List<Turma> turmas;
    @ManyToOne
    private Curso curso;

    public Aluno() {
        this.turmas = new ArrayList<>();
    }

    public Aluno(String nome, String email, String senha, Curso curso) {
        this();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.curso = curso;
    }
    
    public boolean add(Turma turma){
        return turmas.add(turma);
    }
    
    public boolean rmv(Turma turma){
        return turmas.remove(turma);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }   

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    
}
