package com.ifpb.dac.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author rodrigobento
 */
@Entity
//@SequenceGenerator(name = "minha_seq_ativ",
//        sequenceName = "seq_ativ",
//        initialValue = 1,
//        allocationSize = 1)
public class Atividade implements Serializable {
    
    @Id
//    @GeneratedValue(generator = "minha_seq_ativ", strategy = GenerationType.SEQUENCE)
    private String id;
    private String resumo;
    // private String localizacao;
    private String descricao;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    @ManyToOne()
    @JoinColumn(name = "turma_id")
    private Turma turma;

    public Atividade() {
    }

    public Atividade(String resumo, String descricao, 
            LocalDateTime inicio, LocalDateTime fim, Turma turma) {
        this.resumo = resumo;
        this.descricao = descricao;
        this.inicio = inicio;
        this.fim = fim;
        this.turma = turma;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public String toString() {
        return "Atividade{" + "id=" + id + ", resumo=" + resumo + ", descricao=" + descricao + ", inicio=" + inicio + ", fim=" + fim + '}';
    }    
    
}
