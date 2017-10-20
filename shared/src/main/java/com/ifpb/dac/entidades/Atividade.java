package com.ifpb.dac.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author rodrigobento
 */
@Entity
public class Atividade implements Serializable {
    
    @Id
    private String id;
    private String resumo;
    private String descricao;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;
    private boolean notDiaAnterior;

    public Atividade() {
    }

    public Atividade(String resumo, String descricao, 
            LocalDateTime inicio, LocalDateTime fim, Turma turma, boolean notific) {
        this.resumo = resumo;
        this.descricao = descricao;
        this.inicio = inicio;
        this.fim = fim;
        this.turma = turma;
        this.notDiaAnterior = notific;
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

    public boolean isNotDiaAnterior() {
        return notDiaAnterior;
    }

    public void setNotDiaAnterior(boolean notDiaAnterior) {
        this.notDiaAnterior = notDiaAnterior;
    }

    @Override
    public String toString() {
        return "Atividade{" + "id=" + id + ", resumo=" + resumo + ", descricao=" + descricao + ", inicio=" + inicio + ", fim=" + fim + ", turma=" + turma + ", notDiaAnterior=" + notDiaAnterior + '}';
    }  
    
}
