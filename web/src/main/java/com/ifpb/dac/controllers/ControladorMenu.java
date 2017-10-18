package com.ifpb.dac.controllers;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rodrigobento
 */
@RequestScoped
@Named
public class ControladorMenu {

    private String credenciais;
    private HttpSession sessao;

    @PostConstruct
    public void init() {
        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().
                getSession(false);
    }

    public String getCredenciais() {
        return (String) sessao.getAttribute("credenciais");
    }

    public void setCredenciais(String credenciais) {
        this.credenciais = credenciais;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml";
    }
    
    public void voltar() {
        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();
        try {
            externalContext.redirect("../principal.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
