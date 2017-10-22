package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.enums.Regime;
import com.ifpb.dac.enums.Unidade;
import com.ifpb.dac.enums.Vinculo;
import com.ifpb.dac.interfaces.ProfessorDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rodrigobento
 */
@Named
@SessionScoped
public class ControladorCRUDProf implements Serializable {

    @Inject
    private ProfessorDao profDao;
    private Professor professor = new Professor();
    private List<String> regime = Arrays.asList("DE", "T40");
    private List<String> unidade = Arrays.asList("UFGP", "UNIND", "UNINFO");
    private List<String> vinculo = Arrays.asList("Efetivo", "Substituto");
    private List<Professor> professores = new ArrayList<>();
    private String valorRegime;
    private String valorUnidade;
    private String valorVinculo;
    private boolean editando = false;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<String> getRegime() {
        return regime;
    }

    public void setRegime(List<String> regime) {
        this.regime = regime;
    }

    public List<String> getUnidade() {
        return unidade;
    }

    public void setUnidade(List<String> unidade) {
        this.unidade = unidade;
    }

    public List<String> getVinculo() {
        return vinculo;
    }

    public void setVinculo(List<String> vinculo) {
        this.vinculo = vinculo;
    }

    public String getValorRegime() {
        return valorRegime;
    }

    public List<Professor> getProfessores() {
        return profDao.listarTodos();
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public void setValorRegime(String valorRegime) {
        this.valorRegime = valorRegime;
    }

    public String getValorUnidade() {
        return valorUnidade;
    }

    public void setValorUnidade(String valorUnidade) {
        this.valorUnidade = valorUnidade;
    }

    public String getValorVinculo() {
        return valorVinculo;
    }

    public void setValorVinculo(String valorVinculo) {
        this.valorVinculo = valorVinculo;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

    public String cadastrarProfessor() {
        if (profDao.verificarEmail(professor.getEmail())) {
            FacesMessage msg = new FacesMessage("Ja possui um professor cadastrado com esse email na base de dados");
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage("Cadastro", msg);
        } else {
            professor.setRegime(Enum.valueOf(Regime.class, valorRegime));
            professor.setVinculo(Enum.valueOf(Vinculo.class, valorVinculo));
            professor.setUnidade(Enum.valueOf(Unidade.class, valorUnidade));
            professor.setLogado(true);
            profDao.adicionar(professor);
            professor = new Professor();
        }
        return null;
    }

    public String editarProfessor(Professor prof) {
        editando = true;
        professor = prof;
        return null;
    }

    public String atualizarProfessor() {
        editando = false;
        professor.setRegime(Enum.valueOf(Regime.class, valorRegime));
        professor.setVinculo(Enum.valueOf(Vinculo.class, valorVinculo));
        professor.setUnidade(Enum.valueOf(Unidade.class, valorUnidade));
        profDao.atualizar(professor);
        professor = new Professor();
        return null;
    }

    public String removerProfessor(Professor prof) {
        profDao.remover(prof);
        return null;
    }

}
