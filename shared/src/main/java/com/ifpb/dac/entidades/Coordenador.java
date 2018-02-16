package com.ifpb.dac.entidades;

import com.ifpb.dac.enums.Regime;
import com.ifpb.dac.enums.Unidade;
import com.ifpb.dac.enums.Vinculo;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author jozimar
 */
@Entity
public class Coordenador implements Serializable {

    @Id
    private int codigo;
    private LocalDate inicioMandato;
    private LocalDate fimMandato;
    @Column(length = 50, nullable = false)
    private String nome;
    private String email;
    private String senha;
    private String telefone1;
    private String telefone2;
    private Unidade unidade;
    private Regime regime;
    private Vinculo vinculo;
    private boolean logado;
    @OneToOne
    private Curso curso;

    public Coordenador() {
    }

    public Coordenador(int codigo, LocalDate inicioMandato, LocalDate fimMandato, String nome, String email, String senha, String telefone1, String telefone2, Unidade unidade, Regime regime, Vinculo vinculo, boolean logado, Curso curso) {
        this.codigo = codigo;
        this.inicioMandato = inicioMandato;
        this.fimMandato = fimMandato;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.unidade = unidade;
        this.regime = regime;
        this.vinculo = vinculo;
        this.logado = logado;
        this.curso = curso;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getInicioMandato() {
        return inicioMandato;
    }

    public void setInicioMandato(LocalDate inicioMandato) {
        this.inicioMandato = inicioMandato;
    }

    public LocalDate getFimMandato() {
        return fimMandato;
    }

    public void setFimMandato(LocalDate fimMandato) {
        this.fimMandato = fimMandato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Regime getRegime() {
        return regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }

    public Vinculo getVinculo() {
        return vinculo;
    }

    public void setVinculo(Vinculo vinculo) {
        this.vinculo = vinculo;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}
