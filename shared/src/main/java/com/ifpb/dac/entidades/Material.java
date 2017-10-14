package com.ifpb.dac.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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

    public Material(byte[] arquivo, String descricao, String nomeArquivo) {
        this.arquivo = arquivo;
        this.descricao = descricao;
        this.nomeArquivo = nomeArquivo;
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
    
}
