package com.ifpb.dac.entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author rodrigobento
 */
public class HorarioSalaDTO implements Serializable {
    
    private String dia;
    private Calendar inicio;
    private Calendar fim;
    private String descricao;
    private String nome;

    public HorarioSalaDTO() {
    }

    public HorarioSalaDTO(String dia, String descricao, Calendar inicio, 
            Calendar fim, String nome) {
        this.dia = dia;
        this.descricao = descricao;
        this.inicio = inicio;
        this.fim = fim;
        this.nome = nome;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Calendar getInicio() {
        return inicio;
    }

    public void setInicio(Calendar inicio) {
        this.inicio = inicio;
    }

    public Calendar getFim() {
        return fim;
    }

    public void setFim(Calendar fim) {
        this.fim = fim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.dia);
        hash = 47 * hash + Objects.hashCode(this.inicio);
        hash = 47 * hash + Objects.hashCode(this.fim);
        hash = 47 * hash + Objects.hashCode(this.descricao);
        hash = 47 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HorarioSalaDTO other = (HorarioSalaDTO) obj;
        if (!Objects.equals(this.dia, other.dia)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.inicio, other.inicio)) {
            return false;
        }
        if (!Objects.equals(this.fim, other.fim)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HorarioSalaDTO{" + "dia=" + dia + ", inicio=" + inicio + ", fim=" + fim + ", descricao=" + descricao + ", nome=" + nome + '}';
    }
    
    
}
