package com.ifpb.dac.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "minha_seq_sala", 
        sequenceName = "seq_sala",
        initialValue = 37,
        allocationSize = 1)
public class Sala implements Serializable {
    
    @Id
    @GeneratedValue(generator = "minha_seq_sala", strategy = GenerationType.SEQUENCE)
    @Column(name = "codigo_sala")
    private int codigo_sala;
    @Column(name = "abreviacao", nullable = false, length = 30)
    private String abreviacao;
    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;

    public Sala(String abreviacao, String descricao) {
        this.abreviacao = abreviacao;
        this.descricao = descricao;
    }

    public Sala() {
    }

    public int getCodigo_sala() {
        return codigo_sala;
    }

    public void setCodigo_sala(int codigo_sala) {
        this.codigo_sala = codigo_sala;
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

    @Override
    public String toString() {
        return "Sala{" + "codigo_sala=" + codigo_sala + ", abreviacao=" + abreviacao + ", descricao=" + descricao + '}';
    }    
         
}
