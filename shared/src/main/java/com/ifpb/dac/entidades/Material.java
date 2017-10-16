package com.ifpb.dac.entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author rodrigobento
 */
@Entity
@SequenceGenerator(name = "minha_seq_material",
        sequenceName = "seq_material",
        initialValue = 1,
        allocationSize = 1)
public class Material implements Serializable {
    
    @Id
    @GeneratedValue(generator = "minha_seq_material", strategy = GenerationType.SEQUENCE)
    private int id;
    @Lob
    private byte[] arquivo;
    private String descricao;
    private String nomeArquivo;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codigo_turma")
    private Turma turma;

    public Material(byte[] arquivo, String descricao, String nomeArquivo, Turma turma) {
        this.arquivo = arquivo;
        this.descricao = descricao;
        this.nomeArquivo = nomeArquivo;
        this.turma = turma;
    }

    public Material() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
    
}
