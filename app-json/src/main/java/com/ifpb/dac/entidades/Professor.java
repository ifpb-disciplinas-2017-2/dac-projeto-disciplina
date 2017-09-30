package com.ifpb.dac.entidades;

public class Professor {
    
    private int codigo;
    private String nome;
    private String unidade;
    private String vinculo;
    private String regime;
    private String email;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Professores{" + "codigo=" + codigo + ", nome=" + nome + ", unidade=" + unidade + ", vinculo=" + vinculo + ", regime=" + regime + ", email=" + email + '}';
    }
        
}
