package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Turma;
import com.ifpb.dac.interfaces.DisciplinaDao;
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
public class CtrlAtividadAluno implements Serializable {

    @Inject
    private TurmaDao tDao;
    @Inject
    private DisciplinaDao dDao;
    private Aluno aluno = new Aluno();
    private HttpSession sessao;
    private String valorDisciplina;
    private String valorProf;
    private boolean visualizarProf;
    private List<String> disciplinasCurso = new ArrayList<>();
    private List<String> professoresDisciplina = new ArrayList<>();
    private List<String> disciplinasProfessores = new ArrayList<>();

    @PostConstruct
    public void init() {
        sessao = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        aluno = (Aluno) sessao.getAttribute("aluno");
    }

    public String getValorDisciplina() {
        return valorDisciplina;
    }

    public void setValorDisciplina(String valorDisciplina) {
        this.valorDisciplina = valorDisciplina;
    }

    public String getValorProf() {
        return valorProf;
    }

    public void setValorProf(String valorProfessor) {
        this.valorProf = valorProfessor;
    }

    public boolean isVisualizarProf() {
        return visualizarProf;
    }

    public void setVisualizarProf(boolean visualizarProf) {
        this.visualizarProf = visualizarProf;
    }

    public List<String> getProfessoresDisciplina() {
        return tDao.professoresDisciplina(valorDisciplina);
    }

    public void setProfessoresDisciplina(List<String> professoresDisciplina) {
        this.professoresDisciplina = professoresDisciplina;
    }

    public void setDisciplinasProfessores(List<String> disciplinasProfessores) {
        this.disciplinasProfessores = disciplinasProfessores;
    }

    public List<String> getDisciplinasCurso() {
        return dDao.listarDisciplinaCurso(aluno.getCurso().getInfo().getDescricao());
    }

    public String visualizarProfessores() {
        System.out.println(valorDisciplina);
        setProfessoresDisciplina(tDao.professoresDisciplina(valorDisciplina));
        visualizarProf = true;
        return null;
    }

    public String cadastrar() {
        Turma turma = tDao.retornarDiscProf(valorDisciplina, valorProf);
        Turma auxiliar = tDao.buscarPorId(turma.getCodigo_turma());
        aluno.add(auxiliar);
        auxiliar.add(aluno);
        int verificarAlunoTurma = tDao.verificarAlunoTurma(auxiliar.
                getCodigo_turma(), aluno.getId());
        if (verificarAlunoTurma > 0) {
            FacesMessage message = new FacesMessage("O usuario ja esta cadastrado nesta disciplina");
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage("Acesso", message);
        } else {
            tDao.atualizar(auxiliar);
            System.out.println("Atualizado...");
        }
        visualizarProf = false;
        return null;
    }
}
