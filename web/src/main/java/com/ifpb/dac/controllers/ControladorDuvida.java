package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Duvida;
import com.ifpb.dac.entidades.Professor;
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
        Aluno aluno = (Aluno) sessao.getAttribute("aluno");

        Duvida duvida = new Duvida(pergunta, aluno, professor);
        pergunta = null;
        visualizarCampoPergunta = false;
        duvidaDao.adicionar(duvida);

        return null;
    }

    // ---------------------------------------------------------------------------------------------
    public List<Duvida> getDuvidas() {
        Professor professor = (Professor) sessao.getAttribute("usuario");
        List<Duvida> duvidas = duvidaDao.buscarPorProfessorEDuvidaNaoRespondida(
                professor.getCodigo());
        if (duvidas == null) {
            return new ArrayList<>();
        } else {
            return duvidas;
        }
    }

    public void setDuvidas(List<Duvida> duvidas) {
        this.duvidas = duvidas;
    }

    public boolean isMostrarResponderDuvida() {
        return mostrarResponderDuvida;
    }

    public void setMostrarResponderDuvida(boolean mostrarResponderDuvida) {
        this.mostrarResponderDuvida = mostrarResponderDuvida;
    }

    // Actions
    public String mostrar(Duvida duvida) {
        this.mostrarResponderDuvida = true;
        this.editarDuvida = duvida;
        return null;
    }

    // Actions
    public void responderDuvida() {

        duvidaDao.atualizar(editarDuvida);

        this.editarDuvida = null;
        this.mostrarResponderDuvida = false;
    }

    public Duvida getEditarDuvida() {
        return editarDuvida;
    }

    public void setEditarDuvida(Duvida editarDuvida) {
        this.editarDuvida = editarDuvida;
    }

}
