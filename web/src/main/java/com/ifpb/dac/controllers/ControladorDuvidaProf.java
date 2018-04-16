
package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Duvida;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.interfaces.DuvidaDao;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lyndemberg
 */

@Named
@SessionScoped
public class ControladorDuvidaProf implements Serializable{
    
    @Inject
    private DuvidaDao duvidaDao;
    private HttpSession sessao;
    private Professor professorLogado;
    
    private List<Duvida> duvidasTurmas;
    private boolean respondendo = false;
    private Duvida duvida = new Duvida();

    @PostConstruct
    private void iniciarSessao() {
        sessao = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        professorLogado = (Professor) sessao.getAttribute("usuario");
    }
    
    
    public List<Duvida> getDuvidasTurmas() {
        return duvidaDao.listarDuvidasNaoRespondidasTurmasProfessor(professorLogado);
    }

    public void setDuvidasTurmas(List<Duvida> duvidasTurmas) {
        this.duvidasTurmas = duvidasTurmas;
    }

    public boolean isRespondendo() {
        return respondendo;
    }

    public void setRespondendo(boolean respondendo) {
        this.respondendo = respondendo;
    }

    public Duvida getDuvida() {
        return duvida;
    }

    public void setDuvida(Duvida duvida) {
        this.duvida = duvida;
    }
    
    public String mostrarParaResponder(Duvida d){
        respondendo = true;
        duvida = d;
        return null;
    }
    public String responder(){
        duvida.setUsuario(professorLogado.getNome());
        duvidaDao.atualizar(duvida);
        duvida = new Duvida();
        respondendo = false;
        return null;
    }
    
    
}
