/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Duvida;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.entidades.Usuario;
import com.ifpb.dac.interfaces.DuvidaDao;
import com.ifpb.dac.interfaces.ProfessorDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author natan
 */
@Named
@SessionScoped
public class ControladorDuvida implements Serializable {
    
    @Inject
    private DuvidaDao duvidaDao;
    @Inject
    private ProfessorDao professorDao;

    // Atributos para cadastro
    private String nomeProfessor;
    private String pergunta;
    private List<String> professores = new ArrayList<>();
    private boolean visualizarCampoPergunta = false;
    
    // Atributos para resposta
    private List<Duvida> duvidas = new ArrayList<>();
    private boolean mostrarResponderDuvida = false;
    private Duvida editarDuvida = new Duvida();
    private Duvida d = new Duvida();
    
    private HttpSession sessao;
    
    @PostConstruct
    private void iniciarSessao() {
        sessao = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
    }
    
    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public List<String> getProfessores() {
        return professorDao.listarNomeProfessores();
    }

    public void setProfessores(List<String> professores) {
        this.professores = professores;
    }
    
    public String visualizarRealizarPergunta() {
        this.visualizarCampoPergunta = true;
        return null;
    }

    public boolean isVisualizarCampoPergunta() {
        return visualizarCampoPergunta;
    }

    public void setVisualizarCampoPergunta(boolean visualizarCampoPergunta) {
        this.visualizarCampoPergunta = visualizarCampoPergunta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }
    
    public String cadastrarPergunta() {
        Professor professor = professorDao.buscarPorNome(nomeProfessor);
//        System.out.println(professor.toString());
//        iniciarSessao();
        Aluno aluno = (Aluno) sessao.getAttribute("aluno");
        
        Duvida duvida = new Duvida(pergunta, aluno, professor);
        
        duvidaDao.adicionar(duvida);
        
        return null;
    }
    
    // ---------------------------------------------------------------------------------------------

    public List<Duvida> getDuvidas() {
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
//        System.out.println("USUARIO:::::::::::: " + usuario.toString());
        duvidaDao.buscarPorProfessorEDuvidaNaoRespondida(usuario.getId()).stream().forEach(d -> {
            System.out.println("AQUIIIIII::::::::: " + d.toString());
        });
        return duvidaDao.buscarPorProfessorEDuvidaNaoRespondida(usuario.getId());
    }

    public void setDuvidas(List<Duvida> duvidas) {
        this.duvidas = duvidas;
    }
    
    public String mostrar(Duvida duvida) {
        this.mostrarResponderDuvida = true;
        System.out.println("OBJETO QUANDO MOSTRAR::: " + duvida.toString());
        return null;
    }

    public boolean isMostrarResponderDuvida() {
        return mostrarResponderDuvida;
    }

    public void setMostrarResponderDuvida(boolean mostrarResponderDuvida) {
        this.mostrarResponderDuvida = mostrarResponderDuvida;
    }
    
    public void responderDuvida() {
        System.out.println("DUVIDA RESPONDIDA:: " + editarDuvida.toString());
    }

    public Duvida getEditarDuvida() {
        return editarDuvida;
    }

    public void setEditarDuvida(Duvida editarDuvida) {
        this.editarDuvida = editarDuvida;
    }
    
    public Duvida getD() {
        return d;
    }

    public void setD(Duvida d) {
        this.d = d;
    }
    
    
    
}
