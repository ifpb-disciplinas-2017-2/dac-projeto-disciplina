package com.ifpb.dac.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "minha_seq_disc",
        sequenceName = "seq_disc",
        initialValue = 9025,
        allocationSize = 1)
public class Disciplina implements Serializable {

    @Id
    @GeneratedValue(generator = "minha_seq_disc", strategy = GenerationType.SEQUENCE)
    @Column(name = "codigo_disc")
    private int codigo_disc;
//    @Embedded
//    private Info info;
    @Column(name = "abreviacao", length = 30)
    private String abreviacao;
    @Column(name = "descricao", length = 50)
    private String descricao;
    @Column(name = "aulas_semana", nullable = false)
    private int aulas_semana;
    @Column(name = "carga_horaria", nullable = false)
    private int carga_horaria;
    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "codigo_curso")
    private Curso curso;
    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Aula> aulas;

    public Disciplina(String abrev, String desc, int aulas_semana, int carga_horaria, Curso curso) {
        this();
        this.abreviacao = abrev;
        this.descricao = desc;
//        this.info = info;
        this.aulas_semana = aulas_semana;
        this.carga_horaria = carga_horaria;
        this.curso = curso;
    }

    public Disciplina() {
        this.aulas = new ArrayList<>();
    }

    public int getCodigo_disc() {
        return codigo_disc;
    }

    public void setCodigo_disc(int codigo_disc) {
        this.codigo_disc = codigo_disc;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
//    

//    public Info getInfo() {
//        return info;
//    }
//
//    public void setInfo(Info info) {
//        this.info = info;
//    }

    public int getAulas_semana() {
        return aulas_semana;
    }

    public void setAulas_semana(int aulas_semana) {
        this.aulas_semana = aulas_semana;
    }

    public int getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(int carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

}
