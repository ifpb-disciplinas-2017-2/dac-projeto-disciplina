package com.ifpb.dac.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

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

    public Curso() {
    }

    public Curso(Info info, int periodo, String unidade) {
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

    @Override
    public String toString() {
        return "Curso{" + "codigo_curso=" + codigo_curso + ", info=" + info + ", periodo=" + periodo + ", unidade=" + unidade + '}';
    }

}
