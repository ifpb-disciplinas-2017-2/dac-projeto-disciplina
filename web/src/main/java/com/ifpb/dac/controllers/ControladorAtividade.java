package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Atividade;
import com.ifpb.dac.entidades.Usuario;
import com.ifpb.dac.interfaces.AtividadeDao;
import com.ifpb.dac.interfaces.GoogleAgenda;
import com.ifpb.dac.interfaces.TurmaDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rodrigobento
 */
@Named
@RequestScoped
public class ControladorAtividade implements Serializable {
    
    @Inject
    private TurmaDao tDao;
    @Inject
    private GoogleAgenda gAgenda;
    @Inject
    private AtividadeDao aDao;
    private List<Atividade> atividades = new ArrayList<>();
    private Atividade atividade = new Atividade();
    private boolean visualizarAtiv = false;
    private HttpSession sessao;
    private Usuario usuario;
    
    @PostConstruct
    private void init() {
        sessao = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        usuario = (Usuario) sessao.getAttribute("usuario");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Email: " + usuario.getEmail());
    }
    
    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public List<Atividade> getAtividades() {
        return aDao.listarTodos();
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public boolean isVisualizarAtiv() {
        return visualizarAtiv;
    }

    public void setVisualizarAtiv(boolean visualizarAtiv) {
        this.visualizarAtiv = visualizarAtiv;
    }

    public Usuario getUsuario() {
        return (Usuario) sessao.getAttribute("usuario");
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String cadastrarAtividade(){
        atividade.setTurma(tDao.buscarPorId(20917));
        gAgenda.cadastrarEvento(atividade);
        atividade = new Atividade();
        return null;
    }
    
    public String visualizarAtividade(){
        setAtividades(aDao.listarTodos());
        visualizarAtiv = true;
        return null;
    }
    
    public String removerAtividade(Atividade a){
        gAgenda.removerEvento(a);
        return null;
    }
    
    
}
