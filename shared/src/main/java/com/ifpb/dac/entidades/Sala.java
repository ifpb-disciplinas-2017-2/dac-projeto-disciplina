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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "minha_seq_sala", 
        sequenceName = "seq_sala",
        initialValue = 38,
        allocationSize = 1)
public class Sala implements Serializable {
    
    @Id
    @GeneratedValue(generator = "minha_seq_sala", strategy = GenerationType.SEQUENCE)
    @Column(name = "codigo_sala")
    private int codigo_sala;        
//    @Embedded
//    private Info info;
    @Column(name = "abreviacao", length = 30)
    private String abreviacao;
    @Column(name = "descricao", length = 50)
    private String descricao;
    @OneToMany(mappedBy = "sala", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Aula> aulas;

    public Sala(String desc, String abrev) {
        this();
        this.abreviacao = abrev;
        this.descricao = desc;
//        this.info = informacoes;
    }

    public Sala() {
        aulas = new ArrayList<>();
    }

    public int getCodigo_sala() {
        return codigo_sala;
    }

    public void setCodigo_sala(int codigo_sala) {
        this.codigo_sala = codigo_sala;
    }

//    public Info getInfo() {
//        return info;
//    }
//
//    public void setInfo(Info info) {
//        this.info = info;
//    }

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

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }
         
}
