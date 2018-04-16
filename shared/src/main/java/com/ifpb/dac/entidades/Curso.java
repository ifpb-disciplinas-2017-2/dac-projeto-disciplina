package com.ifpb.dac.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@SequenceGenerator(name = "minha_seq_curso",
        sequenceName = "seq_curso",
        initialValue = 26,
        allocationSize = 1)
public class Curso implements Serializable {

    @Id
    @GeneratedValue(generator = "minha_seq_curso", strategy = GenerationType.SEQUENCE)
    @Column(name = "codigo_curso")
    private int codigo_curso;
    @Embedded
    private Info info;
    @Column(name = "periodo", nullable = false)
    private int periodo;
    @Column(name = "unidade", nullable = false, length = 50)
    private String unidade;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Aula> aulas;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Turma> turmas;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="coordenador_codigo")
    private Coordenador coordenador;
    
    public Curso() {
        turmas = new ArrayList<>();
        aulas = new ArrayList<>();
    }

    public Curso(Info info, int periodo, String unidade) {
        this();
        this.info = info;
        this.periodo = periodo;
        this.unidade = unidade;
    }

    public int getCodigo_curso() {
        return codigo_curso;
    }

    public void setCodigo_curso(int codigo_curso) {
        this.codigo_curso = codigo_curso;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    @Override
    public String toString() {
        return "Curso{" + "codigo_curso=" + codigo_curso + ", info=" + info + ", periodo=" + periodo + ", unidade=" + unidade + ", aulas=" + aulas + ", turmas=" + turmas + ", coordenador=" + coordenador + '}';
    }

    
}
