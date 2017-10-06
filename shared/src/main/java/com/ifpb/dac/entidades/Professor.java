package com.ifpb.dac.entidades;

import com.ifpb.dac.enums.Regime;
import com.ifpb.dac.enums.Unidade;
import com.ifpb.dac.enums.Vinculo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Professor implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "codigo_prof")
    private int codigo;
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    @Column(name = "regime")
    @Enumerated(EnumType.STRING)
    private Regime regime;
    @Column(name = "unidade")
    @Enumerated(EnumType.STRING)
    private Unidade unidade;
    @Column(name = "vinculo")
    @Enumerated(EnumType.STRING)
    private Vinculo vinculo;
    @OneToMany(mappedBy = "professor", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Aula> aulas;
    @OneToMany(mappedBy = "professor", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Turma> turmas;

    public Professor() {
        turmas = new ArrayList<>();
        aulas = new ArrayList<>();
    }

    public Professor(String email, String nome, Regime regime, Unidade unidade, Vinculo vinculo) {
        this();
        this.email = email;
        this.nome = nome;
        this.regime = regime;
        this.unidade = unidade;
        this.vinculo = vinculo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Regime getRegime() {
        return regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Vinculo getVinculo() {
        return vinculo;
    }

    public void setVinculo(Vinculo vinculo) {
        this.vinculo = vinculo;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

}
