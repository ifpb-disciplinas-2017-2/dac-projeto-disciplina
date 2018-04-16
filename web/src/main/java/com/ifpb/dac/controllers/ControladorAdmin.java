package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Coordenador;
import com.ifpb.dac.entidades.Pedido;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.entidades.Usuario;
import com.ifpb.dac.enums.Tipo;
import com.ifpb.dac.interfaces.AlunoDao;
import com.ifpb.dac.interfaces.CoordenadorDao;
import com.ifpb.dac.interfaces.PedidoDao;
import com.ifpb.dac.interfaces.ProfessorDao;
import com.ifpb.dac.interfaces.UsuarioDao;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rodrigobento
 */
@Named
@RequestScoped
public class ControladorAdmin implements Serializable {

    @Inject
    private UsuarioDao usuarioDao;
    private Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String realizarLogin() {
        Usuario admin = usuarioDao.autentica(usuario.getEmail(),
                usuario.getSenha(), Tipo.Administrador);
        if (admin != null) {
            return "menu.xhtml";
        } else {
            usuario = new Usuario();
            return null;
        }
    }
    
    public void logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();
        externalContext.invalidateSession();
        try {
            externalContext.redirect("../index.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
