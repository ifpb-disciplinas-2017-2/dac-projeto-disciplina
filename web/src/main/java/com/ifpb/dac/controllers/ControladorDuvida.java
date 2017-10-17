/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Duvida;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.interfaces.DuvidaDao;
import com.ifpb.dac.interfaces.ProfessorDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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

    private String nomeProfessor;
    private String pergunta;
    private List<String> professores = new ArrayList<>();
    private boolean visualizarCampoPergunta = false;
    private HttpSession sessao;
    
    private void iniciarSessao() {
        sessao = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true);
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
        iniciarSessao();
        Aluno aluno = (Aluno) sessao.getAttribute("aluno");
        
        Duvida duvida = new Duvida(pergunta, aluno, professor);
        
        duvidaDao.adicionar(duvida);
        
        return null;
    }
    
}
