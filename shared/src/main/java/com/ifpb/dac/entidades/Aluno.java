
package com.ifpb.dac.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

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
    @Column(length = 50, nullable = false)
    private String nome;
    private String email;
    private String senha; 
    private boolean logado;
    @ManyToMany(mappedBy = "alunos", fetch = FetchType.EAGER)
    private List<Turma> turmas;
    @ManyToOne
    private Curso curso;

    public Aluno() {
        this.turmas = new ArrayList<>();
    }

    public Aluno(String nome, String email, String senha, Curso curso, boolean logado) {
        this();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.curso = curso;
        this.logado = logado;
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

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }    

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", logado=" + logado + ", turmas=" + turmas + ", curso=" + curso + '}';
    }
    
}
