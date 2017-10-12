package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.HorariosDTO;
import com.ifpb.dac.interfaces.HorariosDao;
import com.ifpb.dac.interfaces.TurmaDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rodrigobento
 */
@Named
@SessionScoped
public class ControladorTurma implements Serializable {
    
    @Inject
    private HorariosDao hDao;
    @Inject
    private TurmaDao tDao;
    private String valorDisciplina;
    private String valorProfessor;
    private boolean visualizarProfessor = false;
    private boolean visualizar = false;
    private List<String> disciplinas = new ArrayList<>();
    private List<String> professores = new ArrayList<>();
    private List<HorariosDTO> horario = new ArrayList<>();

    public String getValorDisciplina() {
        return valorDisciplina;
    }

    public void setValorDisciplina(String valorDisciplina) {
        this.valorDisciplina = valorDisciplina;
    }

    public String getValorProfessor() {
        return valorProfessor;
    }

    public void setValorProfessor(String valorProfessor) {
        this.valorProfessor = valorProfessor;
    }

    public boolean isVisualizarProfessor() {
        return visualizarProfessor;
    }

    public void setVisualizarProfessor(boolean visualizarProfessor) {
        this.visualizarProfessor = visualizarProfessor;
    }

    public boolean isVisualizar() {
        return visualizar;
    }

    public void setVisualizar(boolean visualizar) {
        this.visualizar = visualizar;
    }

    public List<String> getDisciplinas() {
        return tDao.listarTodasDisciplinas();
    }

    public void setDisciplinas(List<String> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<String> getProfessores() {
        return tDao.professoresDisciplina(valorDisciplina);
    }

    public void setProfessores(List<String> professores) {
        this.professores = professores;
    }

    public List<HorariosDTO> getHorario() {
        return hDao.listarHorarioTurma(valorDisciplina, valorProfessor);
    }

    public void setHorario(List<HorariosDTO> horario) {
        this.horario = horario;
    }
    
    public String visualizarProfessoresDiscTurma(){
//        List<String> profs = tDao.professoresDisciplina(valorDisciplina);
//        setProfessores(profs);
        visualizarProfessor = true;
        visualizar = false;
        return null;
    }
    
    public String visualizarHorarios(){
//        List<HorariosDTO> h = hDao.listarHorarioTurma(valorDisciplina, valorProfessor);
//        setHorario(h);
        visualizar = true;
        return null;
    }
    
    
}
