package com.ifpb.dac.entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author rodrigobento
 */
@Entity
@SequenceGenerator(name = "minha_seq_hora",
        sequenceName = "seq_hora",
        initialValue = 17,
        allocationSize = 1)
public class Horario implements Serializable {
    
    @Id
    @GeneratedValue(generator = "minha_seq_hora", strategy = GenerationType.SEQUENCE)
    @Column(name = "codigo_hora")
    private int codigo_hora;
    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;
    @Temporal(TemporalType.TIME)
    private Calendar inicio;
    @Temporal(TemporalType.TIME)
    private Calendar fim;
    @Transient
    private String horarioFormatado;
    @OneToMany(mappedBy = "horario", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Aula> aulas;

    public Horario(String descricao, Calendar inicio, Calendar fim) {
        this();
        this.descricao = descricao;
        this.inicio = inicio;
        this.fim = fim;
    }

    public Horario() {
        aulas = new ArrayList<>();
    }

    public int getCodigo_hora() {
        return codigo_hora;
    }

    public void setCodigo_hora(int codigo_hora) {
        this.codigo_hora = codigo_hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public String getHorarioFormatado() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String inicioFormat = sdf.format(inicio.getTime());
        String fimFormat = sdf.format(fim.getTime());
        return inicioFormat + " - " + fimFormat;
    }

    public void setHorarioFormatado(String horarioFormatado) {
        this.horarioFormatado = horarioFormatado;
    }
    
}
