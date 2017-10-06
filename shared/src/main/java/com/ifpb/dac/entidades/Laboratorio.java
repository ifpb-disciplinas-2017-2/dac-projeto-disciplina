
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
@SequenceGenerator(name = "minha_seq_lab",
        sequenceName = "seq_lab",
        initialValue = 37,
        allocationSize = 1)
public class Laboratorio implements Serializable {
   
    @Id
    @GeneratedValue(generator = "minha_seq_lab", strategy = GenerationType.SEQUENCE)
    @Column(name = "codigo_lab")
    private int codigo_lab;    
    @Embedded
    private Info informacao;
    @OneToMany(mappedBy = "laboratorio", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Aula> aulas;

    public Laboratorio() {
        aulas = new ArrayList<>();
    }
    
    public Laboratorio(Info informacao) {
        this();
        this.informacao = informacao;
    }    

    public int getCodigo_lab() {
        return codigo_lab;
    }

    public void setCodigo_lab(int codigo_lab) {
        this.codigo_lab = codigo_lab;
    }

    public Info getInformacao() {
        return informacao;
    }

    public void setInformacao(Info informacao) {
        this.informacao = informacao;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }
    
}
