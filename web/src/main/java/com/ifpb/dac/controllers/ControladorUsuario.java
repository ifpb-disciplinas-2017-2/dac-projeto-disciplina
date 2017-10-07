package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Usuario;
import com.ifpb.dac.enums.Tipo;
import com.ifpb.dac.interfaces.UsuarioDao;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.webapp.FacesServlet;
import javax.inject.Inject;
import javax.inject.Named;
import javax.print.attribute.standard.Severity;

/**
 *
 * @author rodrigobento
 */
@Named
@RequestScoped
public class ControladorUsuario implements Serializable {

    @Inject
    private UsuarioDao dao;
    private String valorSelect;
    private List<String> tiposUsuario = Arrays.asList("Professor", "Publico");
    private Usuario usuario = new Usuario();

    public List<String> getTiposUsuario() {
        return tiposUsuario;
    }

    public void setTiposUsuario(List<String> palavras) {
        this.tiposUsuario = palavras;
    }

    public String getValorSelect() {
        return valorSelect;
    }

    public void setValorSelect(String valorSelect) {
        this.valorSelect = valorSelect;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String cadastrarUsuario() {
        usuario.setTipo(Enum.valueOf(Tipo.class, valorSelect));
        usuario.setLogado(false);
        dao.adicionar(usuario);
        usuario = new Usuario();
        return "index.xhtml";
    }

    public String realizarLogin() {
        Tipo tipo = Enum.valueOf(Tipo.class, valorSelect);
        Usuario autenticado = dao.autentica(usuario.getEmail(),
                usuario.getSenha(), tipo);
        usuario = new Usuario();
        if (autenticado != null) {
            if (autenticado.isLogado()) {
                return "principal.xhtml";
            } else {
                FacesMessage message = new FacesMessage("Enviado pedido de acesso para o administrador");
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage("Teste", message);
                return null;
            }
        } else {
            return null;
        }
    }

}
