package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Atividade;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.entidades.Turma;
import com.ifpb.dac.entidades.Usuario;
import com.ifpb.dac.interfaces.AtividadeDao;
import com.ifpb.dac.interfaces.EnviarEmail;
import com.ifpb.dac.interfaces.GoogleAgenda;
import com.ifpb.dac.interfaces.TurmaDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rodrigobento
 */
@Named
@SessionScoped
public class ControladorAtividade implements Serializable {

    @Inject
    private TurmaDao tDao;
    @Inject
    private GoogleAgenda gAgenda;
    @Inject
    private AtividadeDao aDao;
    @Inject
    private EnviarEmail eEmail;
    private boolean visualizarAtiv = false;
    private String valorSelect;
    private HttpSession sessao;
    private Atividade atividade = new Atividade();
    private Usuario usuario = new Usuario();
    private Professor professor = new Professor();
    private List<Atividade> atividades = new ArrayList<>();
    private List<String> disciplinasProfessores = new ArrayList<>();
    private List<Atividade> atividadesProfessor = new ArrayList<>();

    @PostConstruct
    public void init() {
        sessao = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        professor = (Professor) sessao.getAttribute("usuario");
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public List<Atividade> getAtividades() {
        List<Atividade> atvd = aDao.atividadesProfessor(professor.getNome());
        if (atvd == null) {
            FacesMessage message = new FacesMessage("Nenhuma atividade cadastrada...");
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage("Atividade", message);
            return new ArrayList<>();
        } else {
            return atvd;
        }
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
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<String> getDisciplinasProfessores() {
        return tDao.disciplinaProfessores(professor.getNome());
    }

    public void setDisciplinasProfessores(List<String> disciplinasProfessores) {
        this.disciplinasProfessores = disciplinasProfessores;
    }

    public String getValorSelect() {
        return valorSelect;
    }

    public void setValorSelect(String valorSelect) {
        this.valorSelect = valorSelect;
    }

    public String cadastrarAtividade() {
        Turma turma = tDao.retornarDiscProf(valorSelect, professor.getNome());
        atividade.setTurma(turma);
        atividade.setNotDiaAnterior(false);
        gAgenda.cadastrarEvento(atividade);
        eEmail.enviar(atividade);
        atividade = new Atividade();
        visualizarAtiv = false;
        return null;
    }

    public String visualizarAtividade() {
        visualizarAtiv = true;
        return null;
    }

    public String removerAtividade(Atividade a) {
        aDao.remover(a);
        gAgenda.removerEvento(a);
        return null;
    }

}
