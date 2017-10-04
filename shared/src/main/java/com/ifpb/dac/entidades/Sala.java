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
@SequenceGenerator(name = "minha_seq_sala", 
        sequenceName = "seq_sala",
        initialValue = 37,
        allocationSize = 1)
public class Sala implements Serializable {
    
    @Id
    @GeneratedValue(generator = "minha_seq_sala", strategy = GenerationType.SEQUENCE)
    @Column(name = "codigo_sala")
    private int codigo_sala;
    
    @Embedded
    private Info info;

    public Sala(Info informacoes) {
        this.info = informacoes;
    }

    public Sala() {
    }

    public int getCodigo_sala() {
        return codigo_sala;
    }

    public void setCodigo_sala(int codigo_sala) {
        this.codigo_sala = codigo_sala;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Sala{" + "codigo_sala=" + codigo_sala + ", info=" + info + '}';
    }
         
}
