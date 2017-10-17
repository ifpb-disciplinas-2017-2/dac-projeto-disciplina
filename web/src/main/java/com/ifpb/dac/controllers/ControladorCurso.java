package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.HorariosDTO;
import com.ifpb.dac.interfaces.CursoDao;
import com.ifpb.dac.interfaces.DisciplinaDao;
import com.ifpb.dac.interfaces.HorariosDao;
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
public class ControladorCurso implements Serializable {
    
    @Inject
    private CursoDao cDao;
    @Inject
    private HorariosDao hDao;
    @Inject
    private DisciplinaDao dDao;
    
    private String valorCurso;
    private String valorDisciplina;
    private boolean visualizarDisc = false;
    private boolean visualizar = false;
    private List<String> cursos = new ArrayList<>();
    private List<String> disciplinasCurso = new ArrayList<>();
    private List<HorariosDTO> horario = new ArrayList<>();

    public String getValorCurso() {
        return valorCurso;
    }

    public void setValorCurso(String valorCurso) {
        this.valorCurso = valorCurso;
    }

    public String getValorDisciplina() {
        return valorDisciplina;
    }

    public void setValorDisciplina(String valorDisciplina) {
        this.valorDisciplina = valorDisciplina;
    }

    public boolean isVisualizarDisc() {
        return visualizarDisc;
    }

    public void setVisualizarDisc(boolean visualizarDisc) {
        this.visualizarDisc = visualizarDisc;
    }

    public List<String> getCursos() {
        return cDao.listarNomeCursos();
    }

    public void setCursos(List<String> cursos) {
        this.cursos = cursos;
    }

    public List<String> getDisciplinasCurso() {
        return dDao.listarDisciplinaCurso(valorCurso);
    }

    public void setDisciplinasCurso(List<String> disciplinasCurso) {
        this.disciplinasCurso = disciplinasCurso;
    }

    public List<HorariosDTO> getHorario() {
        return hDao.listarHorarioCurso(valorCurso, valorDisciplina);
    }

    public void setHorario(List<HorariosDTO> horario) {
        this.horario = horario;
    }

    public boolean isVisualizar() {
        return visualizar;
    }

    public void setVisualizar(boolean visualizar) {
        this.visualizar = visualizar;
    }
    
    public String visualizarDisciplinasCurso(){
        visualizarDisc = true;
        visualizar = false;
        return null;
    }
    
    public String visualizarHorarios(){
        visualizar = true;
        return null;
    }
    
}
