
package com.ifpb.dac.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Info implements Serializable {
    
    @Column(name = "abreviacao", nullable = false, length = 30)
    private String abreviacao;
    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;

    public Info() {
    }

    public Info(String abreviacao, String descricao) {
        this.abreviacao = abreviacao;
        this.descricao = descricao;
    }

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

    @Override
    public String toString() {
        return "Info{" + "abreviacao=" + abreviacao + ", descricao=" + descricao + '}';
    }
    
}
