package com.ifpb.dac.entidades;

import com.ifpb.dac.enums.TipoUsuarioMensagem;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author jozimar
 */
@Entity
public class Mensagem implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "codigo_mensagem")
    private int id;

    @Column(nullable = false)
    private String mensagem;

    @Column(name = "codigo_remetente")
    private int remetente;

    @Column(name = "codigo_destinatario")
    private int destinatario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_remetente")
    private TipoUsuarioMensagem tipoRemetente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_destinatario")
    private TipoUsuarioMensagem tipoDestinatario;

    @Column(nullable = false)
    private String nomeRemetente;

    public Mensagem() {
    }

    public Mensagem(String mensagem, int remetente, int destinatario, TipoUsuarioMensagem tipoRemetente, TipoUsuarioMensagem tipoDestinatario, String nomeRemetente) {
        this.mensagem = mensagem;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.tipoRemetente = tipoRemetente;
        this.tipoDestinatario = tipoDestinatario;
        this.nomeRemetente = nomeRemetente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getRemetente() {
        return remetente;
    }

    public void setRemetente(int remetente) {
        this.remetente = remetente;
    }

    public int getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(int destinatario) {
        this.destinatario = destinatario;
    }

    public TipoUsuarioMensagem getTipoRemetente() {
        return tipoRemetente;
    }

    public void setTipoRemetente(TipoUsuarioMensagem tipoRemetente) {
        this.tipoRemetente = tipoRemetente;
    }

    public TipoUsuarioMensagem getTipoDestinatario() {
        return tipoDestinatario;
    }

    public void setTipoDestinatario(TipoUsuarioMensagem tipoDestinatario) {
        this.tipoDestinatario = tipoDestinatario;
    }

    public String getNomeRemetente() {
        return nomeRemetente;
    }

    public void setNomeRemetente(String nomeRemetente) {
        this.nomeRemetente = nomeRemetente;
    }

    @Override
    public String toString() {
        return "Mensagem{" + "id=" + id + ", mensagem=" + mensagem + ", remetente=" + remetente + ", destinatario=" + destinatario + ", tipoRemetente=" + tipoRemetente + ", tipoDestinatario=" + tipoDestinatario + ", nomeRemetente=" + nomeRemetente + '}';
    }
}
